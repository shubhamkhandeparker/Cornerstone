package com.shubham.cornerstone

import android.app.Activity
import android.content.Context
import android.util.Log
import com.android.billingclient.api.AcknowledgePurchaseParams
import com.android.billingclient.api.BillingClient
import com.android.billingclient.api.BillingClientStateListener
import com.android.billingclient.api.BillingFlowParams
import com.android.billingclient.api.BillingResult
import com.android.billingclient.api.PendingPurchasesParams
import com.android.billingclient.api.ProductDetails
import com.android.billingclient.api.Purchase
import com.android.billingclient.api.PurchasesUpdatedListener
import com.android.billingclient.api.QueryProductDetailsParams
import com.android.billingclient.api.QueryPurchasesParams
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class BillingManager(
    private val context: Context
) {

    companion object {
        private const val TAG = "BillingManager"
        const val SUB_PRODUCT_ID = "cornerstone_pro_monthly"
    }

    enum class ConnectionState {
        DISCONNECTED,
        CONNECTING,
        CONNECTED,
        ERROR
    }

    enum class PurchaseState {
        IDLE,
        PROCESSING,
        PENDING,
        ENTITLED,
        ERROR
    }

    private val _connectionState = MutableStateFlow(ConnectionState.DISCONNECTED)
    val connectionState: StateFlow<ConnectionState> = _connectionState.asStateFlow()

    private val _productDetails = MutableStateFlow<ProductDetails?>(null)
    val productDetails: StateFlow<ProductDetails?> = _productDetails.asStateFlow()

    private val _purchaseState = MutableStateFlow(PurchaseState.IDLE)
    val purchaseState: StateFlow<PurchaseState> = _purchaseState.asStateFlow()

    private val purchasesUpdatedListener = PurchasesUpdatedListener { billingResult, purchases ->
        when (billingResult.responseCode) {
            BillingClient.BillingResponseCode.OK -> {
                if (purchases.isNullOrEmpty()) {
                    Log.d(TAG, "Purchase OK but purchase list empty.")
                    _purchaseState.value = PurchaseState.IDLE
                } else {
                    purchases.forEach { purchase ->
                        handlePurchase(purchase)
                    }
                }
            }

            BillingClient.BillingResponseCode.USER_CANCELED -> {
                Log.d(TAG, "User cancelled purchase.")
                _purchaseState.value = PurchaseState.IDLE
            }

            BillingClient.BillingResponseCode.ITEM_ALREADY_OWNED -> {
                Log.d(TAG, "Item already owned. Refreshing purchases.")
                refreshPurchases()
            }

            else -> {
                Log.e(
                    TAG,
                    "Purchase failed: code=${billingResult.responseCode}, message=${billingResult.debugMessage}"
                )
                _purchaseState.value = PurchaseState.ERROR
            }
        }
    }

    private val billingClient: BillingClient = BillingClient.newBuilder(context)
        .setListener(purchasesUpdatedListener)
        .enablePendingPurchases(
            PendingPurchasesParams.newBuilder()
                .enableOneTimeProducts()
                .build()
        )
        .build()

    fun connect() {
        if (billingClient.isReady) {
            _connectionState.value = ConnectionState.CONNECTED
            queryProduct()
            refreshPurchases()
            return
        }

        if (_connectionState.value == ConnectionState.CONNECTING) return

        _connectionState.value = ConnectionState.CONNECTING
        Log.d(TAG, "Connecting to Play Billing...")

        billingClient.startConnection(object : BillingClientStateListener {
            override fun onBillingSetupFinished(billingResult: BillingResult) {
                if (billingResult.responseCode == BillingClient.BillingResponseCode.OK) {
                    Log.d(TAG, "Connected to Play Billing.")
                    _connectionState.value = ConnectionState.CONNECTED
                    queryProduct()
                    refreshPurchases()
                } else {
                    Log.e(TAG, "Billing connection failed: ${billingResult.debugMessage}")
                    _connectionState.value = ConnectionState.ERROR
                }
            }

            override fun onBillingServiceDisconnected() {
                Log.w(TAG, "Play Billing disconnected.")
                _connectionState.value = ConnectionState.DISCONNECTED
            }
        })
    }

    private fun queryProduct() {
        if (!billingClient.isReady) {
            Log.w(TAG, "Cannot query product. Billing client not ready.")
            return
        }

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
                    Log.w(TAG, "No product found for id=$SUB_PRODUCT_ID")
                    _productDetails.value = null
                }
            } else {
                Log.e(TAG, "queryProductDetails failed: ${billingResult.debugMessage}")
            }
        }
    }

    fun launchPurchase(activity: Activity) {
        if (!billingClient.isReady) {
            Log.e(TAG, "Cannot launch purchase. Billing client not ready.")
            connect()
            return
        }

        val details = _productDetails.value
        if (details == null) {
            Log.e(TAG, "Cannot launch purchase. Product details not loaded.")
            return
        }

        val offerToken = details.subscriptionOfferDetails
            ?.firstOrNull()
            ?.offerToken

        if (offerToken == null) {
            Log.e(TAG, "Cannot launch purchase. Offer token missing.")
            _purchaseState.value = PurchaseState.ERROR
            return
        }

        _purchaseState.value = PurchaseState.PROCESSING

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

        val result = billingClient.launchBillingFlow(activity, flowParams)

        if (result.responseCode != BillingClient.BillingResponseCode.OK) {
            Log.e(
                TAG,
                "launchBillingFlow failed: code=${result.responseCode}, message=${result.debugMessage}"
            )
            _purchaseState.value = PurchaseState.ERROR
        }
    }

    fun refreshPurchases() {
        if (!billingClient.isReady) {
            Log.w(TAG, "Cannot refresh purchases. Billing client not ready.")
            connect()
            return
        }

        val params = QueryPurchasesParams.newBuilder()
            .setProductType(BillingClient.ProductType.SUBS)
            .build()

        billingClient.queryPurchasesAsync(params) { billingResult, purchases ->
            if (billingResult.responseCode == BillingClient.BillingResponseCode.OK) {
                if (purchases.isEmpty()) {
                    Log.d(TAG, "No active subscription purchases found.")

                    if (_purchaseState.value != PurchaseState.PROCESSING) {
                        _purchaseState.value = PurchaseState.IDLE
                    }
                } else {
                    purchases.forEach { purchase ->
                        handlePurchase(purchase)
                    }
                }
            } else {
                Log.e(TAG, "queryPurchases failed: ${billingResult.debugMessage}")
            }
        }
    }

    private fun handlePurchase(purchase: Purchase) {
        if (!purchase.products.contains(SUB_PRODUCT_ID)) {
            Log.w(TAG, "Ignoring purchase for another product: ${purchase.products}")
            return
        }

        when (purchase.purchaseState) {
            Purchase.PurchaseState.PURCHASED -> {
                Log.d(TAG, "Purchase PURCHASED. Pro entitlement granted.")
                _purchaseState.value = PurchaseState.ENTITLED

                if (!purchase.isAcknowledged) {
                    val ackParams = AcknowledgePurchaseParams.newBuilder()
                        .setPurchaseToken(purchase.purchaseToken)
                        .build()

                    billingClient.acknowledgePurchase(ackParams) { result ->
                        Log.d(
                            TAG,
                            "Acknowledge result: code=${result.responseCode}, message=${result.debugMessage}"
                        )
                    }
                }
            }

            Purchase.PurchaseState.PENDING -> {
                Log.d(TAG, "Purchase PENDING.")
                _purchaseState.value = PurchaseState.PENDING
            }

            else -> {
                Log.d(TAG, "Purchase not purchased or pending.")
                _purchaseState.value = PurchaseState.IDLE
            }
        }
    }

    fun disconnect() {
        if (billingClient.isReady) {
            billingClient.endConnection()
        }

        _connectionState.value = ConnectionState.DISCONNECTED
    }
}