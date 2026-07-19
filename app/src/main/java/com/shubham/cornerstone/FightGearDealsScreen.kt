package com.shubham.cornerstone

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.shubham.cornerstone.ui.theme.Charcoal
import com.shubham.cornerstone.ui.theme.FightRed
import com.shubham.cornerstone.ui.theme.InkBlack
import kotlinx.coroutines.flow.distinctUntilChanged
import java.text.NumberFormat
import java.util.Currency
import java.util.Locale
import kotlin.math.max
import kotlin.math.min

private const val MIN_VISIBLE_FRACTION_FOR_IMPRESSION = 0.50f

@Composable
fun FightGearDealsScreen(
    uiState: FightGearUiState,
    selectedCategory: FightGearCategory,
    onCategorySelected: (FightGearCategory) -> Unit,
    onViewDeal: (FightGearProduct) -> Unit,
    onRetry: () -> Unit,
    onExit: () -> Unit,
    onOpenAnalytics: () -> Unit = {},
    onProductImpression: (FightGearProduct) -> Unit = {}
) {
    BackHandler(onBack = onExit)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF161518),
                        InkBlack
                    )
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .navigationBarsPadding()
        ) {
            FightGearHeader(
                onExit = onExit,
                onOpenAnalytics = onOpenAnalytics
            )

            CategoryFilters(
                selectedCategory = selectedCategory,
                onCategorySelected = onCategorySelected
            )

            when (uiState) {
                FightGearUiState.Loading -> {
                    LoadingState()
                }

                FightGearUiState.Empty -> {
                    EmptyState()
                }

                is FightGearUiState.Error -> {
                    ErrorState(
                        message = uiState.message,
                        onRetry = onRetry
                    )
                }

                is FightGearUiState.Content -> {
                    ProductList(
                        products = uiState.products.filter { product ->
                            selectedCategory == FightGearCategory.ALL ||
                                    product.category == selectedCategory
                        },
                        onViewDeal = onViewDeal,
                        onProductImpression = onProductImpression
                    )
                }
            }
        }
    }
}

@Composable
private fun FightGearHeader(
    onExit: () -> Unit,
    onOpenAnalytics: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 20.dp,
                vertical = 18.dp
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Surface(
            modifier = Modifier.clickable {
                onExit()
            },
            shape = RoundedCornerShape(12.dp),
            color = Charcoal
        ) {
            Text(
                text = "<-",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = FightRed,
                modifier = Modifier.padding(
                    horizontal = 14.dp,
                    vertical = 10.dp
                )
            )
        }

        Spacer(
            modifier = Modifier.size(14.dp)
        )

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = "Fight Gear Deals",
                fontSize = 24.sp,
                fontWeight = FontWeight.Black,
                color = MaterialTheme.colorScheme.onBackground
            )

            Text(
                text = "India",
                fontSize = 13.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        if (BuildConfig.DEBUG) {
            Surface(
                modifier = Modifier.clickable {
                    onOpenAnalytics()
                },
                shape = RoundedCornerShape(999.dp),
                color = FightRed.copy(alpha = 0.14f)
            ) {
                Text(
                    text = "STATS",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Black,
                    letterSpacing = 1.sp,
                    color = FightRed,
                    modifier = Modifier.padding(
                        horizontal = 12.dp,
                        vertical = 8.dp
                    )
                )
            }
        }
    }
}

@Composable
private fun CategoryFilters(
    selectedCategory: FightGearCategory,
    onCategorySelected: (FightGearCategory) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState())
            .padding(
                horizontal = 20.dp,
                vertical = 8.dp
            ),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        FightGearCategory.entries.forEach { category ->
            val selected = category == selectedCategory

            Surface(
                modifier = Modifier.clickable {
                    onCategorySelected(category)
                },
                shape = RoundedCornerShape(999.dp),
                color = if (selected) {
                    FightRed
                } else {
                    Charcoal
                }
            ) {
                Text(
                    text = category.displayName,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (selected) {
                        Color.White
                    } else {
                        MaterialTheme.colorScheme.onSurfaceVariant
                    },
                    modifier = Modifier.padding(
                        horizontal = 16.dp,
                        vertical = 10.dp
                    )
                )
            }
        }
    }
}

@Composable
private fun LoadingState() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator(
                color = FightRed
            )

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            Text(
                text = "Loading fight gear...",
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun EmptyState() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(
                horizontal = 24.dp,
                vertical = 40.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Gear deals coming soon",
            fontSize = 24.sp,
            fontWeight = FontWeight.Black,
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.Center
        )

        Spacer(
            modifier = Modifier.height(10.dp)
        )

        Text(
            text = "We are preparing useful boxing, MMA and Muay Thai gear recommendations for India.",
            fontSize = 15.sp,
            lineHeight = 22.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center
        )

        Spacer(
            modifier = Modifier.height(24.dp)
        )

        DisclosureCard()
    }
}

@Composable
private fun ErrorState(
    message: String,
    onRetry: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Unable to load gear",
            fontSize = 22.sp,
            fontWeight = FontWeight.Black,
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        Text(
            text = message,
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center
        )

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        Surface(
            modifier = Modifier.clickable {
                onRetry()
            },
            shape = RoundedCornerShape(14.dp),
            color = FightRed
        ) {
            Text(
                text = "Try again",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(
                    horizontal = 24.dp,
                    vertical = 12.dp
                )
            )
        }
    }
}

@Composable
private fun ProductList(
    products: List<FightGearProduct>,
    onViewDeal: (FightGearProduct) -> Unit,
    onProductImpression: (FightGearProduct) -> Unit
) {
    if (products.isEmpty()) {
        EmptyState()
        return
    }

    val listState = rememberLazyListState()

    LaunchedEffect(
        listState,
        products
    ) {
        snapshotFlow {
            val layoutInfo = listState.layoutInfo

            val viewportStart =
                layoutInfo.viewportStartOffset

            val viewportEnd =
                layoutInfo.viewportEndOffset

            layoutInfo.visibleItemsInfo
                .mapNotNull { itemInfo ->
                    val product = products.getOrNull(
                        itemInfo.index
                    ) ?: return@mapNotNull null

                    val itemStart = itemInfo.offset
                    val itemEnd =
                        itemInfo.offset + itemInfo.size

                    val visibleStart = max(
                        itemStart,
                        viewportStart
                    )

                    val visibleEnd = min(
                        itemEnd,
                        viewportEnd
                    )

                    val visibleSize =
                        (visibleEnd - visibleStart)
                            .coerceAtLeast(0)

                    val visibleFraction =
                        if (itemInfo.size > 0) {
                            visibleSize.toFloat() /
                                    itemInfo.size.toFloat()
                        } else {
                            0f
                        }

                    if (
                        visibleFraction >=
                        MIN_VISIBLE_FRACTION_FOR_IMPRESSION
                    ) {
                        product.id
                    } else {
                        null
                    }
                }
                .toSet()
        }
            .distinctUntilChanged()
            .collect { visibleProductIds ->
                visibleProductIds.forEach { productId ->
                    products
                        .firstOrNull { product ->
                            product.id == productId
                        }
                        ?.let { visibleProduct ->
                            onProductImpression(
                                visibleProduct
                            )
                        }
                }
            }
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        state = listState,
        contentPadding =
            androidx.compose.foundation.layout.PaddingValues(
                start = 20.dp,
                end = 20.dp,
                top = 16.dp,
                bottom = 24.dp
            ),
        verticalArrangement =
            Arrangement.spacedBy(14.dp)
    ) {
        items(
            items = products,
            key = { product ->
                product.id
            }
        ) { product ->
            ProductCard(
                product = product,
                onViewDeal = onViewDeal,
                onProductImpression =
                    onProductImpression
            )
        }

        item(
            key = "fight_gear_disclosure"
        ) {
            DisclosureCard()
        }
    }
}

@Composable
private fun ProductCard(
    product: FightGearProduct,
    onViewDeal: (FightGearProduct) -> Unit,
    onProductImpression: (FightGearProduct) -> Unit
) {
    val hasValidDealLink = product.productUrl
        ?.trim()
        ?.startsWith(
            prefix = "https://",
            ignoreCase = true
        ) == true

    val isUnavailable =
        product.availability ==
                FightGearAvailability.OUT_OF_STOCK ||
                product.availability ==
                FightGearAvailability.COMING_SOON

    val canOpenDeal =
        hasValidDealLink && !isUnavailable

    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        color = Charcoal
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            ProductImage(
                imageUrl = product.imageUrl,
                productName = product.name
            )

            Spacer(
                modifier = Modifier.height(14.dp)
            )

            ProductBadges(
                product = product
            )

            if (
                product.isFeatured ||
                product.availability !=
                FightGearAvailability.AVAILABLE ||
                product.isAffiliateLink ||
                !product.partnerLabel.isNullOrBlank()
            ) {
                Spacer(
                    modifier = Modifier.height(10.dp)
                )
            }

            Text(
                text = product.name,
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )

            product.brandName
                ?.takeIf { it.isNotBlank() }
                ?.let { brandName ->
                    Spacer(
                        modifier = Modifier.height(4.dp)
                    )

                    Text(
                        text = brandName,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }

            Spacer(
                modifier = Modifier.height(4.dp)
            )

            Text(
                text = product.retailerName,
                fontSize = 13.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            PriceSection(
                product = product
            )

            product.dealText
                ?.takeIf { it.isNotBlank() }
                ?.let { dealText ->
                    Spacer(
                        modifier = Modifier.height(8.dp)
                    )

                    Text(
                        text = dealText,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = FightRed
                    )
                }

            product.discountCode
                ?.takeIf { it.isNotBlank() }
                ?.let { discountCode ->
                    Spacer(
                        modifier = Modifier.height(12.dp)
                    )

                    DiscountCodeCard(
                        discountCode = discountCode
                    )
                }

            product.dealExpiresAt
                ?.takeIf { it.isNotBlank() }
                ?.let { expiry ->
                    Spacer(
                        modifier = Modifier.height(8.dp)
                    )

                    Text(
                        text = "Offer ends: $expiry",
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(
                        enabled = canOpenDeal
                    ) {
                        /*
                         * Guarantee that a clicked product has an
                         * impression even if the user tapped it while
                         * exactly on the visibility threshold.
                         *
                         * MainActivity will prevent duplicate events.
                         */
                        onProductImpression(product)
                        onViewDeal(product)
                    },
                shape = RoundedCornerShape(14.dp),
                color = if (canOpenDeal) {
                    FightRed
                } else {
                    Color(0xFF29282C)
                }
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = dealButtonText(
                            product = product,
                            hasValidDealLink =
                                hasValidDealLink
                        ),
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = if (canOpenDeal) {
                            Color.White
                        } else {
                            MaterialTheme.colorScheme
                                .onSurfaceVariant
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun ProductBadges(
    product: FightGearProduct
) {
    val partnerBadge = product.partnerLabel
        ?.trim()
        ?.takeIf { it.isNotEmpty() }
        ?: if (product.isAffiliateLink) {
            "AFFILIATE"
        } else {
            null
        }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement =
            Arrangement.spacedBy(8.dp),
        verticalAlignment =
            Alignment.CenterVertically
    ) {
        if (product.isFeatured) {
            ProductBadge(
                text = "FEATURED",
                highlighted = true
            )
        }

        when (product.availability) {
            FightGearAvailability.LIMITED -> {
                ProductBadge(
                    text = "LIMITED",
                    highlighted = true
                )
            }

            FightGearAvailability.OUT_OF_STOCK -> {
                ProductBadge(
                    text = "OUT OF STOCK",
                    highlighted = false
                )
            }

            FightGearAvailability.COMING_SOON -> {
                ProductBadge(
                    text = "COMING SOON",
                    highlighted = false
                )
            }

            FightGearAvailability.AVAILABLE -> Unit
        }

        partnerBadge?.let { label ->
            ProductBadge(
                text = label.uppercase(),
                highlighted = false
            )
        }
    }
}

@Composable
private fun ProductBadge(
    text: String,
    highlighted: Boolean
) {
    Surface(
        shape = RoundedCornerShape(999.dp),
        color = if (highlighted) {
            FightRed.copy(alpha = 0.18f)
        } else {
            Color(0xFF29282C)
        }
    ) {
        Text(
            text = text,
            fontSize = 9.sp,
            fontWeight = FontWeight.Black,
            letterSpacing = 0.8.sp,
            color = if (highlighted) {
                FightRed
            } else {
                MaterialTheme.colorScheme.onSurfaceVariant
            },
            modifier = Modifier.padding(
                horizontal = 9.dp,
                vertical = 5.dp
            )
        )
    }
}

@Composable
private fun PriceSection(
    product: FightGearProduct
) {
    val currentPrice = formatPrice(
        priceMinor = product.currentPriceMinor,
        currencyCode = product.currencyCode
    )

    val originalPrice = formatPrice(
        priceMinor = product.originalPriceMinor,
        currencyCode = product.currencyCode
    )

    if (currentPrice == null) {
        return
    }

    Spacer(
        modifier = Modifier.height(10.dp)
    )

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = currentPrice,
            fontSize = 18.sp,
            fontWeight = FontWeight.Black,
            color = MaterialTheme.colorScheme.onBackground
        )

        if (
            originalPrice != null &&
            product.originalPriceMinor != null &&
            product.currentPriceMinor != null &&
            product.originalPriceMinor >
            product.currentPriceMinor
        ) {
            Spacer(
                modifier = Modifier.width(10.dp)
            )

            Text(
                text = originalPrice,
                fontSize = 13.sp,
                color = MaterialTheme.colorScheme
                    .onSurfaceVariant,
                textDecoration =
                    TextDecoration.LineThrough
            )
        }
    }
}

@Composable
private fun DiscountCodeCard(
    discountCode: String
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        color = FightRed.copy(alpha = 0.10f)
    ) {
        Row(
            modifier = Modifier.padding(
                horizontal = 14.dp,
                vertical = 12.dp
            ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement =
                Arrangement.SpaceBetween
        ) {
            Text(
                text = "Use discount code",
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Text(
                text = discountCode.uppercase(),
                fontSize = 13.sp,
                fontWeight = FontWeight.Black,
                letterSpacing = 1.sp,
                color = FightRed
            )
        }
    }
}

@Composable
private fun ProductImage(
    imageUrl: String?,
    productName: String
) {
    val usableImageUrl = imageUrl
        ?.trim()
        ?.takeIf {
            it.startsWith(
                prefix = "https://",
                ignoreCase = true
            )
        }

    var imageFailed by remember(usableImageUrl) {
        mutableStateOf(false)
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(170.dp)
            .background(
                color = Color(0xFF252428),
                shape = RoundedCornerShape(14.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        when {
            usableImageUrl == null -> {
                ImageFallbackText(
                    text = "Product image unavailable"
                )
            }

            imageFailed -> {
                ImageFallbackText(
                    text = "Unable to load product image"
                )
            }

            else -> {
                Text(
                    text = "Loading image...",
                    fontSize = 13.sp,
                    color = MaterialTheme.colorScheme
                        .onSurfaceVariant
                )

                AsyncImage(
                    model = usableImageUrl,
                    contentDescription =
                        "$productName product image",
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp),
                    contentScale = ContentScale.Fit,
                    onSuccess = {
                        imageFailed = false
                    },
                    onError = {
                        imageFailed = true
                    }
                )
            }
        }
    }
}

@Composable
private fun ImageFallbackText(
    text: String
) {
    Text(
        text = text,
        fontSize = 13.sp,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        textAlign = TextAlign.Center,
        modifier = Modifier.padding(20.dp)
    )
}

@Composable
private fun DisclosureCard() {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        color = FightRed.copy(alpha = 0.10f)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Affiliate and partner disclosure",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(
                modifier = Modifier.height(6.dp)
            )

            Text(
                text = "Some links are affiliate or partner links. Cornerstone may earn a commission at no extra cost to you.",
                fontSize = 12.sp,
                lineHeight = 18.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(
                modifier = Modifier.height(6.dp)
            )

            Text(
                text = "Prices, discounts and availability may change. Check the retailer for the latest information.",
                fontSize = 12.sp,
                lineHeight = 18.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

private fun dealButtonText(
    product: FightGearProduct,
    hasValidDealLink: Boolean
): String {
    return when {
        product.availability ==
                FightGearAvailability.OUT_OF_STOCK -> {
            "Out of Stock"
        }

        product.availability ==
                FightGearAvailability.COMING_SOON -> {
            "Coming Soon"
        }

        !hasValidDealLink -> {
            "Coming Soon"
        }

        else -> {
            "View Deal"
        }
    }
}

private fun formatPrice(
    priceMinor: Long?,
    currencyCode: String?
): String? {
    if (
        priceMinor == null ||
        currencyCode.isNullOrBlank()
    ) {
        return null
    }

    return runCatching {
        val normalizedCurrency = currencyCode
            .trim()
            .uppercase()

        val locale =
            if (normalizedCurrency == "INR") {
                Locale("en", "IN")
            } else {
                Locale.getDefault()
            }

        val formatter =
            NumberFormat.getCurrencyInstance(locale)

        formatter.currency =
            Currency.getInstance(normalizedCurrency)

        formatter.format(
            priceMinor.toDouble() / 100.0
        )
    }.getOrNull()
}