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
    context: Context
) {

    companion object {
        private const val TAG =
            "BillingManager"

        const val SUB_PRODUCT_ID =
            "cornerstone_pro_monthly"
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

    private val appContext =
        context.applicationContext

    private val _connectionState =
        MutableStateFlow(
            ConnectionState.DISCONNECTED
        )

    val connectionState:
            StateFlow<ConnectionState> =
        _connectionState.asStateFlow()

    private val _productDetails =
        MutableStateFlow<ProductDetails?>(
            null
        )

    val productDetails:
            StateFlow<ProductDetails?> =
        _productDetails.asStateFlow()

    private val _purchaseState =
        MutableStateFlow(
            PurchaseState.IDLE
        )

    val purchaseState:
            StateFlow<PurchaseState> =
        _purchaseState.asStateFlow()

    private val purchasesUpdatedListener =
        PurchasesUpdatedListener {
                billingResult,
                purchases ->

            when (
                billingResult.responseCode
            ) {
                BillingClient
                    .BillingResponseCode
                    .OK -> {

                    if (
                        purchases.isNullOrEmpty()
                    ) {
                        Log.d(
                            TAG,
                            "Purchase succeeded but no purchase data was returned."
                        )

                        _purchaseState.value =
                            PurchaseState.IDLE
                    } else {
                        purchases.forEach {
                                purchase ->

                            handlePurchase(
                                purchase
                            )
                        }
                    }
                }

                BillingClient
                    .BillingResponseCode
                    .USER_CANCELED -> {

                    Log.d(
                        TAG,
                        "User cancelled the purchase flow."
                    )

                    _purchaseState.value =
                        PurchaseState.IDLE
                }

                BillingClient
                    .BillingResponseCode
                    .ITEM_ALREADY_OWNED -> {

                    Log.d(
                        TAG,
                        "Subscription already owned. Refreshing purchases."
                    )

                    refreshPurchases()
                }

                BillingClient
                    .BillingResponseCode
                    .SERVICE_DISCONNECTED -> {

                    Log.w(
                        TAG,
                        "Billing service disconnected during purchase."
                    )

                    _connectionState.value =
                        ConnectionState.DISCONNECTED

                    _purchaseState.value =
                        PurchaseState.ERROR
                }

                else -> {
                    Log.e(
                        TAG,
                        "Purchase failed: " +
                                "code=${billingResult.responseCode}, " +
                                "message=${billingResult.debugMessage}"
                    )

                    _purchaseState.value =
                        PurchaseState.ERROR
                }
            }
        }

    private val billingClient:
            BillingClient =
        BillingClient
            .newBuilder(
                appContext
            )
            .setListener(
                purchasesUpdatedListener
            )
            .enablePendingPurchases(
                PendingPurchasesParams
                    .newBuilder()
                    .enableOneTimeProducts()
                    .build()
            )
            .build()

    fun connect() {
        if (
            billingClient.isReady
        ) {
            _connectionState.value =
                ConnectionState.CONNECTED

            queryProduct()
            refreshPurchases()

            return
        }

        if (
            _connectionState.value ==
            ConnectionState.CONNECTING
        ) {
            return
        }

        _connectionState.value =
            ConnectionState.CONNECTING

        Log.d(
            TAG,
            "Connecting to Google Play Billing."
        )

        billingClient.startConnection(
            object :
                BillingClientStateListener {

                override fun onBillingSetupFinished(
                    billingResult:
                    BillingResult
                ) {
                    if (
                        billingResult.responseCode ==
                        BillingClient
                            .BillingResponseCode
                            .OK
                    ) {
                        Log.d(
                            TAG,
                            "Connected to Google Play Billing."
                        )

                        _connectionState.value =
                            ConnectionState.CONNECTED

                        queryProduct()
                        refreshPurchases()
                    } else {
                        Log.e(
                            TAG,
                            "Billing connection failed: " +
                                    "code=${billingResult.responseCode}, " +
                                    "message=${billingResult.debugMessage}"
                        )

                        _connectionState.value =
                            ConnectionState.ERROR
                    }
                }

                override fun onBillingServiceDisconnected() {
                    Log.w(
                        TAG,
                        "Google Play Billing disconnected."
                    )

                    _connectionState.value =
                        ConnectionState.DISCONNECTED
                }
            }
        )
    }

    private fun queryProduct() {
        if (
            !billingClient.isReady
        ) {
            Log.w(
                TAG,
                "Cannot query subscription because BillingClient is not ready."
            )

            return
        }

        val product =
            QueryProductDetailsParams
                .Product
                .newBuilder()
                .setProductId(
                    SUB_PRODUCT_ID
                )
                .setProductType(
                    BillingClient
                        .ProductType
                        .SUBS
                )
                .build()

        val params =
            QueryProductDetailsParams
                .newBuilder()
                .setProductList(
                    listOf(product)
                )
                .build()

        billingClient
            .queryProductDetailsAsync(
                params
            ) {
                    billingResult,
                    queryProductDetailsResult ->

                if (
                    billingResult.responseCode ==
                    BillingClient
                        .BillingResponseCode
                        .OK
                ) {
                    val details =
                        queryProductDetailsResult
                            .productDetailsList
                            .firstOrNull {
                                    productDetails ->

                                productDetails
                                    .productId ==
                                        SUB_PRODUCT_ID
                            }

                    if (
                        details != null
                    ) {
                        Log.d(
                            TAG,
                            "Subscription product found: ${details.name}"
                        )

                        _productDetails.value =
                            details
                    } else {
                        Log.w(
                            TAG,
                            "No subscription product found for ID: $SUB_PRODUCT_ID"
                        )

                        _productDetails.value =
                            null
                    }
                } else {
                    Log.e(
                        TAG,
                        "Product query failed: " +
                                "code=${billingResult.responseCode}, " +
                                "message=${billingResult.debugMessage}"
                    )

                    _productDetails.value =
                        null
                }
            }
    }

    fun launchPurchase(
        activity: Activity
    ) {
        if (
            !billingClient.isReady
        ) {
            Log.e(
                TAG,
                "Cannot launch purchase because BillingClient is not ready."
            )

            connect()

            return
        }

        val details =
            _productDetails.value

        if (
            details == null
        ) {
            Log.e(
                TAG,
                "Cannot launch purchase because product details are unavailable."
            )

            _purchaseState.value =
                PurchaseState.ERROR

            queryProduct()

            return
        }

        val offerDetails =
            details
                .subscriptionOfferDetails
                ?.firstOrNull()

        val offerToken =
            offerDetails
                ?.offerToken

        if (
            offerToken.isNullOrBlank()
        ) {
            Log.e(
                TAG,
                "Cannot launch purchase because no subscription offer token was found."
            )

            _purchaseState.value =
                PurchaseState.ERROR

            return
        }

        val productDetailsParams =
            BillingFlowParams
                .ProductDetailsParams
                .newBuilder()
                .setProductDetails(
                    details
                )
                .setOfferToken(
                    offerToken
                )
                .build()

        val flowParams =
            BillingFlowParams
                .newBuilder()
                .setProductDetailsParamsList(
                    listOf(
                        productDetailsParams
                    )
                )
                .build()

        _purchaseState.value =
            PurchaseState.PROCESSING

        val billingResult =
            billingClient
                .launchBillingFlow(
                    activity,
                    flowParams
                )

        if (
            billingResult.responseCode !=
            BillingClient
                .BillingResponseCode
                .OK
        ) {
            Log.e(
                TAG,
                "Unable to launch billing flow: " +
                        "code=${billingResult.responseCode}, " +
                        "message=${billingResult.debugMessage}"
            )

            _purchaseState.value =
                PurchaseState.ERROR
        }
    }

    fun refreshPurchases() {
        if (
            !billingClient.isReady
        ) {
            Log.w(
                TAG,
                "Cannot refresh purchases because BillingClient is not ready."
            )

            connect()

            return
        }

        val params =
            QueryPurchasesParams
                .newBuilder()
                .setProductType(
                    BillingClient
                        .ProductType
                        .SUBS
                )
                .build()

        billingClient
            .queryPurchasesAsync(
                params
            ) {
                    billingResult,
                    purchases ->

                if (
                    billingResult.responseCode ==
                    BillingClient
                        .BillingResponseCode
                        .OK
                ) {
                    val cornerstonePurchases =
                        purchases.filter {
                                purchase ->

                            purchase.products
                                .contains(
                                    SUB_PRODUCT_ID
                                )
                        }

                    if (
                        cornerstonePurchases
                            .isEmpty()
                    ) {
                        Log.d(
                            TAG,
                            "No active Cornerstone subscription purchase found."
                        )

                        if (
                            _purchaseState.value !=
                            PurchaseState.PROCESSING
                        ) {
                            _purchaseState.value =
                                PurchaseState.IDLE
                        }
                    } else {
                        cornerstonePurchases
                            .forEach {
                                    purchase ->

                                handlePurchase(
                                    purchase
                                )
                            }
                    }
                } else {
                    Log.e(
                        TAG,
                        "Purchase refresh failed: " +
                                "code=${billingResult.responseCode}, " +
                                "message=${billingResult.debugMessage}"
                    )

                    if (
                        billingResult.responseCode ==
                        BillingClient
                            .BillingResponseCode
                            .SERVICE_DISCONNECTED
                    ) {
                        _connectionState.value =
                            ConnectionState.DISCONNECTED
                    }
                }
            }
    }

    private fun handlePurchase(
        purchase: Purchase
    ) {
        if (
            !purchase.products
                .contains(
                    SUB_PRODUCT_ID
                )
        ) {
            Log.w(
                TAG,
                "Ignoring purchase for another product: ${purchase.products}"
            )

            return
        }

        when (
            purchase.purchaseState
        ) {
            Purchase
                .PurchaseState
                .PURCHASED -> {

                Log.d(
                    TAG,
                    "Subscription purchased. Pro entitlement granted."
                )

                _purchaseState.value =
                    PurchaseState.ENTITLED

                if (
                    !purchase.isAcknowledged
                ) {
                    acknowledgePurchase(
                        purchase
                    )
                }
            }

            Purchase
                .PurchaseState
                .PENDING -> {

                Log.d(
                    TAG,
                    "Subscription purchase is pending."
                )

                _purchaseState.value =
                    PurchaseState.PENDING
            }

            else -> {
                Log.d(
                    TAG,
                    "Subscription purchase is neither purchased nor pending."
                )

                _purchaseState.value =
                    PurchaseState.IDLE
            }
        }
    }

    private fun acknowledgePurchase(
        purchase: Purchase
    ) {
        if (
            !billingClient.isReady
        ) {
            Log.w(
                TAG,
                "Cannot acknowledge purchase because BillingClient is not ready."
            )

            return
        }

        val params =
            AcknowledgePurchaseParams
                .newBuilder()
                .setPurchaseToken(
                    purchase.purchaseToken
                )
                .build()

        billingClient
            .acknowledgePurchase(
                params
            ) {
                    billingResult ->

                if (
                    billingResult.responseCode ==
                    BillingClient
                        .BillingResponseCode
                        .OK
                ) {
                    Log.d(
                        TAG,
                        "Subscription purchase acknowledged."
                    )
                } else {
                    Log.e(
                        TAG,
                        "Purchase acknowledgement failed: " +
                                "code=${billingResult.responseCode}, " +
                                "message=${billingResult.debugMessage}"
                    )
                }
            }
    }

    fun disconnect() {
        billingClient.endConnection()

        _connectionState.value =
            ConnectionState.DISCONNECTED
    }
}