package com.shubham.cornerstone

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FightGearCatalogResponse(
    val version: Int = 1,
    val updatedAt: String? = null,
    val products: List<FightGearProductDto> = emptyList()
)

@Serializable
data class FightGearProductDto(
    val id: String = "",
    val name: String = "",

    @SerialName("category")
    val categoryName: String = "",

    val retailerName: String = "",
    val dealText: String? = null,
    val imageUrl: String? = null,
    val productUrl: String? = null,
    val isAffiliateLink: Boolean = false,

    val brandName: String? = null,
    val partnerLabel: String? = null,
    val discountCode: String? = null,
    val countryCode: String = "IN",
    val isFeatured: Boolean = false,
    val dealExpiresAt: String? = null,
    val availability: String = "AVAILABLE",

    val currentPriceMinor: Long? = null,
    val originalPriceMinor: Long? = null,
    val currencyCode: String? = null,

    val isActive: Boolean = true,
    val sortOrder: Int = 0
) {
    fun toFightGearProduct(): FightGearProduct? {
        val safeId = id.trim()
        val safeName = name.trim()
        val safeRetailerName = retailerName.trim()

        if (
            safeId.isEmpty() ||
            safeName.isEmpty() ||
            safeRetailerName.isEmpty()
        ) {
            return null
        }

        val safeCategory = categoryName.toFightGearCategory()
            ?: return null

        val safeAvailability =
            availability.toFightGearAvailability()

        val safeCurrentPrice = currentPriceMinor
            ?.takeIf { it >= 0L }

        val safeOriginalPrice = originalPriceMinor
            ?.takeIf { it >= 0L }

        return FightGearProduct(
            id = safeId,
            name = safeName,
            category = safeCategory,
            retailerName = safeRetailerName,
            dealText = dealText.cleanOptionalText(),
            imageUrl = imageUrl.cleanHttpsUrl(),
            productUrl = productUrl.cleanHttpsUrl(),
            isAffiliateLink = isAffiliateLink,
            brandName = brandName.cleanOptionalText(),
            partnerLabel = partnerLabel.cleanOptionalText(),
            discountCode = discountCode.cleanOptionalText(),
            countryCode = countryCode
                .trim()
                .uppercase()
                .takeIf { it.length == 2 }
                ?: "IN",
            isFeatured = isFeatured,
            dealExpiresAt = dealExpiresAt.cleanOptionalText(),
            availability = safeAvailability,
            currentPriceMinor = safeCurrentPrice,
            originalPriceMinor = safeOriginalPrice,
            currencyCode = currencyCode
                ?.trim()
                ?.uppercase()
                ?.takeIf { it.length == 3 }
        )
    }
}

private fun String.toFightGearCategory(): FightGearCategory? {
    val normalizedValue = trim()
        .uppercase()
        .replace(" ", "_")
        .replace("-", "_")

    return FightGearCategory.entries.firstOrNull { category ->
        category.name == normalizedValue
    }
}

private fun String.toFightGearAvailability():
        FightGearAvailability {

    val normalizedValue = trim()
        .uppercase()
        .replace(" ", "_")
        .replace("-", "_")

    return FightGearAvailability.entries.firstOrNull { availability ->
        availability.name == normalizedValue
    } ?: FightGearAvailability.AVAILABLE
}

private fun String?.cleanOptionalText(): String? {
    return this
        ?.trim()
        ?.takeIf { it.isNotEmpty() }
}

private fun String?.cleanHttpsUrl(): String? {
    return this
        ?.trim()
        ?.takeIf { url ->
            url.startsWith(
                prefix = "https://",
                ignoreCase = true
            )
        }
}