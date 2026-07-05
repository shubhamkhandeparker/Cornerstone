package com.shubham.cornerstone

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.shubham.cornerstone.ui.theme.Charcoal
import com.shubham.cornerstone.ui.theme.FightRed
import com.shubham.cornerstone.ui.theme.InkBlack

@Composable
fun PaywallScreen(
    onProEntitled: () -> Unit,
    onExit: () -> Unit
) {
    val context = LocalContext.current
    val activity = context as? Activity
    val lifecycleOwner = LocalLifecycleOwner.current

    val billingManager = remember {
        BillingManager(context = context.applicationContext)
    }

    val connectionState by billingManager.connectionState.collectAsState()
    val productDetails by billingManager.productDetails.collectAsState()
    val purchaseState by billingManager.purchaseState.collectAsState()

    DisposableEffect(Unit) {
        billingManager.connect()

        onDispose {
            billingManager.disconnect()
        }
    }

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                billingManager.refreshPurchases()
            }
        }

        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    LaunchedEffect(purchaseState) {
        if (purchaseState == BillingManager.PurchaseState.ENTITLED) {
            onProEntitled()
        }
    }

    val priceText = productDetails
        ?.subscriptionOfferDetails
        ?.firstOrNull()
        ?.pricingPhases
        ?.pricingPhaseList
        ?.firstOrNull()
        ?.formattedPrice
        ?: "₹100"

    val (buttonLabel, buttonEnabled) = when {
        purchaseState == BillingManager.PurchaseState.PROCESSING ->
            "Processing purchase..." to false

        purchaseState == BillingManager.PurchaseState.PENDING ->
            "Waiting for payment confirmation..." to false

        purchaseState == BillingManager.PurchaseState.ERROR ->
            "Try again" to true

        connectionState == BillingManager.ConnectionState.CONNECTING ->
            "Connecting..." to false

        connectionState == BillingManager.ConnectionState.ERROR ->
            "Store unavailable — retry" to true

        connectionState == BillingManager.ConnectionState.CONNECTED ->
            if (productDetails == null) {
                "Loading plan..." to false
            } else {
                "Unlock Pro" to true
            }

        connectionState == BillingManager.ConnectionState.DISCONNECTED ->
            "Connect to store" to true

        else ->
            "Unlock Pro" to true
    }

    val statusText = when (purchaseState) {
        BillingManager.PurchaseState.PROCESSING ->
            "Do not close the app yet. Waiting for Google Play..."

        BillingManager.PurchaseState.PENDING ->
            "Your payment is pending. Pro will unlock automatically after Google confirms it."

        BillingManager.PurchaseState.ERROR ->
            "Something went wrong. You can retry or reopen the paywall."

        else ->
            "Free: AI combos, round timer & drills stay free forever."
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF1E1416),
                        InkBlack
                    )
                )
            )
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .navigationBarsPadding()
                .padding(horizontal = 24.dp),
            verticalArrangement = Arrangement.Top
        ) {
            item {
                Spacer(Modifier.height(16.dp))

                Text(
                    text = "✕",
                    fontSize = 20.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier
                        .clickable {
                            onExit()
                        }
                        .padding(8.dp)
                )

                Spacer(Modifier.height(18.dp))

                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = FightRed
                ) {
                    Text(
                        text = "CORNERSTONE PRO",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Black,
                        letterSpacing = 2.sp,
                        color = Color.White,
                        modifier = Modifier.padding(
                            horizontal = 14.dp,
                            vertical = 8.dp
                        )
                    )
                }

                Spacer(Modifier.height(22.dp))

                Text(
                    text = "Make weight.\nEvery time.",
                    fontSize = 42.sp,
                    lineHeight = 46.sp,
                    fontWeight = FontWeight.Black,
                    color = MaterialTheme.colorScheme.onBackground
                )

                Spacer(Modifier.height(14.dp))

                Text(
                    text = "Your weight cut, tracked and adapted daily — so you never miss the scale or crash-cut last minute.",
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(Modifier.height(30.dp))
            }

            item {
                ProFeature(
                    title = "Daily pace tracking",
                    subtitle = "Know exactly how much to cut, every day."
                )

                ProFeature(
                    title = "On-track or behind alerts",
                    subtitle = "Real-time status so there are no surprises on fight day."
                )

                ProFeature(
                    title = "Your full weight curve",
                    subtitle = "See your whole camp at a glance and trust the trend."
                )

                ProFeature(
                    title = "Remembers everything",
                    subtitle = "Your target, your history, your pace — it compounds over weeks."
                )

                Spacer(Modifier.height(20.dp))
            }

            item {
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(20.dp),
                    color = Charcoal
                ) {
                    Row(
                        modifier = Modifier.padding(20.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(
                            modifier = Modifier.weight(1f)
                        ) {
                            Row(
                                verticalAlignment = Alignment.Bottom
                            ) {
                                Text(
                                    text = priceText,
                                    fontSize = 32.sp,
                                    fontWeight = FontWeight.Black,
                                    color = MaterialTheme.colorScheme.onBackground
                                )

                                Text(
                                    text = "/month",
                                    fontSize = 15.sp,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                                    modifier = Modifier.padding(
                                        bottom = 5.dp,
                                        start = 4.dp
                                    )
                                )
                            }

                            Spacer(Modifier.height(4.dp))

                            Text(
                                text = "Cancel anytime",
                                fontSize = 13.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }

                Spacer(Modifier.height(18.dp))
            }

            item {
                Button(
                    onClick = {
                        when {
                            purchaseState == BillingManager.PurchaseState.ERROR -> {
                                billingManager.refreshPurchases()
                            }

                            connectionState == BillingManager.ConnectionState.CONNECTED &&
                                    productDetails != null &&
                                    activity != null -> {
                                billingManager.launchPurchase(activity)
                            }

                            connectionState == BillingManager.ConnectionState.ERROR ||
                                    connectionState == BillingManager.ConnectionState.DISCONNECTED -> {
                                billingManager.connect()
                            }

                            else -> {
                                // Connecting, loading, processing, or pending.
                            }
                        }
                    },
                    enabled = buttonEnabled,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(58.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = FightRed
                    )
                ) {
                    Text(
                        text = buttonLabel,
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(Modifier.height(12.dp))

                Text(
                    text = statusText,
                    fontSize = 13.sp,
                    lineHeight = 18.sp,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(Modifier.height(40.dp))
            }
        }
    }
}

@Composable
private fun ProFeature(
    title: String,
    subtitle: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 20.dp)
    ) {
        Surface(
            modifier = Modifier.size(28.dp),
            shape = CircleShape,
            color = FightRed.copy(alpha = 0.15f)
        ) {
            Box(
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "✓",
                    color = FightRed,
                    fontWeight = FontWeight.Black,
                    fontSize = 15.sp
                )
            }
        }

        Spacer(Modifier.width(14.dp))

        Column {
            Text(
                text = title,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(Modifier.height(2.dp))

            Text(
                text = subtitle,
                fontSize = 14.sp,
                lineHeight = 19.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}