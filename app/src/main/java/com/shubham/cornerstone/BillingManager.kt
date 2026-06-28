package com.shubham.cornerstone

import android.app.Activity
import android.content.Context
import android.util.Log
import com.android.billingclient.api.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * Owns the Google Play Billing connection and the subscription purchase flow.
 * Step 1 scope: connect to Play, query the product, expose state. Purchase wiring comes next.
 */
class BillingManager(
    private val context: Context,
    private val onProEntitled: () -> Unit
) {

    companion object {
        private const val TAG = "BillingManager"
        // Must EXACTLY match the subscription product ID you create in Play Console.
        const val SUB_PRODUCT_ID = "cornerstone_pro_monthly"
    }

    // What the UI can observe.
    private val _connectionState = MutableStateFlow(ConnectionState.DISCONNECTED)
    val connectionState: StateFlow<ConnectionState> = _connectionState.asStateFlow()

    private val _productDetails = MutableStateFlow<ProductDetails?>(null)
    val productDetails: StateFlow<ProductDetails?> = _productDetails.asStateFlow()

    enum class ConnectionState { DISCONNECTED, CONNECTING, CONNECTED, ERROR }

    // Listens for purchase results. Wired now so the client builds; used fully in the next step.
    private val purchasesUpdatedListener = PurchasesUpdatedListener { billingResult, purchases ->
        if (billingResult.responseCode == BillingClient.BillingResponseCode.OK && purchases != null) {
            for (purchase in purchases) {
                handlePurchase(purchase)
            }
        } else {
            Log.d(TAG, "Purchases updated, code=${billingResult.responseCode}")
        }
    }

    private val billingClient: BillingClient = BillingClient.newBuilder(context)
        .setListener(purchasesUpdatedListener)
        .enablePendingPurchases(
            PendingPurchasesParams.newBuilder().enableOneTimeProducts().build()
        )
        .build()

    /** Connect to Google Play. Call once when the paywall opens. */
    fun connect() {
        if (_connectionState.value == ConnectionState.CONNECTING) return
        _connectionState.value = ConnectionState.CONNECTING
        Log.d(TAG, "Connecting to Play Billing…")

        billingClient.startConnection(object : BillingClientStateListener {
            override fun onBillingSetupFinished(billingResult: BillingResult) {
                if (billingResult.responseCode == BillingClient.BillingResponseCode.OK) {
                    Log.d(TAG, "Connected to Play Billing.")
                    _connectionState.value = ConnectionState.CONNECTED
                    queryProduct()
                    queryExistingPurchases()
                } else {
                    Log.e(TAG, "Connection failed: ${billingResult.debugMessage}")
                    _connectionState.value = ConnectionState.ERROR
                }
            }

            override fun onBillingServiceDisconnected() {
                Log.w(TAG, "Play Billing disconnected.")
                _connectionState.value = ConnectionState.DISCONNECTED
            }
        })
    }

    /** Ask Play for the details of our subscription product (price, title, offer token). */
    private fun queryProduct() {
        val params = QueryProductDetailsParams.newBuilder()
            .setProductList(
                listOf(
                    QueryProductDetailsParams.Product.newBuilder()
                        .setProductId(SUB_PRODUCT_ID)
                        .setProductType(BillingClient.ProductType.SUBS)
                        .build()
                )
            )
            .build()

        billingClient.queryProductDetailsAsync(params) { billingResult, productDetailsList ->
            if (billingResult.responseCode == BillingClient.BillingResponseCode.OK) {
                val details = productDetailsList.firstOrNull()
                if (details != null) {
                    Log.d(TAG, "Product found: ${details.name}")
                    _productDetails.value = details
                } else {
                    Log.w(TAG, "No product found for id=$SUB_PRODUCT_ID (not created in Play Console yet?)")
                }
            } else {
                Log.e(TAG, "queryProductDetails failed: ${billingResult.debugMessage}")
            }
        }
    }

    /** Launch the Play purchase sheet for the subscription. Used in the next step. */
    fun launchPurchase(activity: Activity) {
        val details = _productDetails.value
        if (details == null) {
            Log.e(TAG, "Cannot purchase: product details not loaded yet.")
            return
        }
        val offerToken = details.subscriptionOfferDetails?.firstOrNull()?.offerToken
        if (offerToken == null) {
            Log.e(TAG, "Cannot purchase: no subscription offer token.")
            return
        }

        val flowParams = BillingFlowParams.newBuilder()
            .setProductDetailsParamsList(
                listOf(
                    BillingFlowParams.ProductDetailsParams.newBuilder()
                        .setProductDetails(details)
                        .setOfferToken(offerToken)
                        .build()
                )
            )
            .build()

        billingClient.launchBillingFlow(activity, flowParams)
    }

    /** On reconnect, restore Pro if the user already owns an active subscription. */
    private fun queryExistingPurchases() {
        val params = QueryPurchasesParams.newBuilder()
            .setProductType(BillingClient.ProductType.SUBS)
            .build()

        billingClient.queryPurchasesAsync(params) { billingResult, purchases ->
            if (billingResult.responseCode == BillingClient.BillingResponseCode.OK) {
                for (purchase in purchases) {
                    handlePurchase(purchase)
                }
            }
        }
    }

    /** Validate, grant Pro, and acknowledge the purchase so Play doesn't auto-refund it. */
    private fun handlePurchase(purchase: Purchase) {
        if (purchase.purchaseState == Purchase.PurchaseState.PURCHASED) {
            onProEntitled()

            if (!purchase.isAcknowledged) {
                val ackParams = AcknowledgePurchaseParams.newBuilder()
                    .setPurchaseToken(purchase.purchaseToken)
                    .build()
                billingClient.acknowledgePurchase(ackParams) { result ->
                    Log.d(TAG, "Acknowledge result: ${result.responseCode}")
                }
            }
        }
    }

    /** Release the connection when done (e.g. leaving the paywall). */
    fun disconnect() {
        billingClient.endConnection()
    }
}