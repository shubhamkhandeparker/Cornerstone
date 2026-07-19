package com.shubham.cornerstone

enum class FightGearCategory(
    val displayName: String
) {
    ALL("All"),
    BOXING_GLOVES("Boxing Gloves"),
    MMA_GLOVES("MMA Gloves"),
    MUAY_THAI_GLOVES("Muay Thai Gloves"),
    HAND_WRAPS("Hand Wraps"),
    MOUTHGUARDS("Mouthguards"),
    SHIN_GUARDS("Shin Guards"),
    HEADGEAR("Headgear"),
    HEAVY_BAGS("Heavy Bags"),
    FOCUS_MITTS("Focus Mitts"),
    ACCESSORIES("Accessories")
}

enum class FightGearAvailability {
    AVAILABLE,
    LIMITED,
    OUT_OF_STOCK,
    COMING_SOON
}

data class FightGearProduct(
    val id: String,
    val name: String,
    val category: FightGearCategory,
    val retailerName: String,
    val dealText: String?,
    val imageUrl: String?,
    val productUrl: String?,
    val isAffiliateLink: Boolean,

    // Partner and brand information
    val brandName: String? = null,
    val partnerLabel: String? = null,

    // Optional customer discount
    val discountCode: String? = null,

    // India-first, expandable later
    val countryCode: String = "IN",

    // Product promotion controls
    val isFeatured: Boolean = false,
    val dealExpiresAt: String? = null,
    val availability: FightGearAvailability =
        FightGearAvailability.AVAILABLE,

    // Store money using minor units.
    // Example: ₹2,499.00 = 249900 paise.
    val currentPriceMinor: Long? = null,
    val originalPriceMinor: Long? = null,
    val currencyCode: String? = null
)

sealed interface FightGearUiState {

    data object Loading : FightGearUiState

    data object Empty : FightGearUiState

    data class Content(
        val products: List<FightGearProduct>
    ) : FightGearUiState

    data class Error(
        val message: String
    ) : FightGearUiState
}