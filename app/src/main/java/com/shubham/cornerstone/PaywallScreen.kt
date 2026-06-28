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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shubham.cornerstone.ui.theme.Charcoal
import com.shubham.cornerstone.ui.theme.FightRed
import com.shubham.cornerstone.ui.theme.InkBlack

@Composable
fun PaywallScreen(
    onProEntitled: () -> Unit,
    onExit: () -> Unit
) {
    val scroll = rememberScrollState()
    val context = LocalContext.current
    val activity = context as? Activity

    // One BillingManager for the lifetime of this screen.
    val billingManager = remember {
        BillingManager(
            context = context.applicationContext,
            onProEntitled = onProEntitled
        )
    }

    val connectionState by billingManager.connectionState.collectAsState()
    val productDetails by billingManager.productDetails.collectAsState()

    // Connect when the paywall appears; release when it leaves.
    DisposableEffect(Unit) {
        billingManager.connect()
        onDispose { billingManager.disconnect() }
    }

    // Live price from Play if available, else the placeholder.
    val priceText = productDetails
        ?.subscriptionOfferDetails
        ?.firstOrNull()
        ?.pricingPhases
        ?.pricingPhaseList
        ?.firstOrNull()
        ?.formattedPrice
        ?: "₹99"

    // Button label reflects billing state so you can SEE the connection working.
    val (buttonLabel, buttonEnabled) = when (connectionState) {
        BillingManager.ConnectionState.CONNECTING -> "Connecting…" to false
        BillingManager.ConnectionState.ERROR -> "Store unavailable — retry" to true
        BillingManager.ConnectionState.CONNECTED ->
            if (productDetails == null) "Loading plan…" to false
            else "Unlock Pro" to true
        BillingManager.ConnectionState.DISCONNECTED -> "Connect to store" to true
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(listOf(Color(0xFF1E1416), InkBlack)))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .verticalScroll(scroll)
                .padding(horizontal = 24.dp)
        ) {
            Spacer(Modifier.height(16.dp))

            Text(
                text = "✕",
                fontSize = 20.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier
                    .clickable { onExit() }
                    .padding(8.dp)
            )

            Spacer(Modifier.height(24.dp))

            // Pro badge
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
                    modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp)
                )
            }

            Spacer(Modifier.height(24.dp))

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

            Spacer(Modifier.height(36.dp))

            // Feature list
            ProFeature("Daily pace tracking", "Know exactly how much to cut, every day.")
            ProFeature("On-track or behind alerts", "Real-time status so there are no surprises on fight day.")
            ProFeature("Your full weight curve", "See your whole camp at a glance and trust the trend.")
            ProFeature("Remembers everything", "Your target, your history, your pace — it compounds over weeks.")

            Spacer(Modifier.height(36.dp))

            // Price card
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                color = Charcoal
            ) {
                Row(
                    modifier = Modifier.padding(20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Row(verticalAlignment = Alignment.Bottom) {
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
                                modifier = Modifier.padding(bottom = 5.dp, start = 4.dp)
                            )
                        }
                        Text(
                            text = "Cancel anytime",
                            fontSize = 13.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }

            Spacer(Modifier.height(20.dp))

            Column(modifier = Modifier.navigationBarsPadding()) {
                Button(
                    onClick = {
                        when (connectionState) {
                            BillingManager.ConnectionState.CONNECTED -> {
                                if (activity != null) billingManager.launchPurchase(activity)
                            }
                            BillingManager.ConnectionState.ERROR,
                            BillingManager.ConnectionState.DISCONNECTED -> {
                                billingManager.connect()
                            }
                            else -> { /* connecting / loading — do nothing */ }
                        }
                    },
                    enabled = buttonEnabled,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(58.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = FightRed)
                ) {
                    Text(
                        text = buttonLabel,
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(Modifier.height(12.dp))
                Text(
                    text = "Free: AI combos, round timer & drills stay free forever.",
                    fontSize = 13.sp,
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(24.dp))
            }
        }
    }
}

@Composable
private fun ProFeature(title: String, subtitle: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 20.dp)
    ) {
        // Red check dot
        Surface(
            modifier = Modifier.size(28.dp),
            shape = CircleShape,
            color = FightRed.copy(alpha = 0.15f)
        ) {
            Box(contentAlignment = Alignment.Center) {
                Text("✓", color = FightRed, fontWeight = FontWeight.Black, fontSize = 15.sp)
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