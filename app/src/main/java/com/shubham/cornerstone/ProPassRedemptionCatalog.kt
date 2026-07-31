package com.shubham.cornerstone

data class ProPassRedemptionOption(
    val id: String,
    val title: String,
    val pointsCost: Int,
    val proPassDays: Int,
    val description: String,
    val isActive: Boolean = true,
    val sortOrder: Int = 0
) {
    init {
        require(id.isNotBlank()) {
            "Redemption option ID cannot be blank."
        }

        require(pointsCost > 0) {
            "Points cost must be greater than zero."
        }

        require(proPassDays > 0) {
            "Pro Pass duration must be greater than zero."
        }
    }
}

object ProPassRedemptionCatalog {

    const val THREE_DAY_PASS_ID =
        "pro_pass_3_days"

    const val SEVEN_DAY_PASS_ID =
        "pro_pass_7_days"

    const val THIRTY_DAY_PASS_ID =
        "pro_pass_30_days"

    private val options =
        listOf(
            ProPassRedemptionOption(
                id =
                    THREE_DAY_PASS_ID,
                title =
                    "3-Day Pro Pass",
                pointsCost =
                    500,
                proPassDays =
                    3,
                description =
                    "Unlock Cornerstone Pro for three days.",
                sortOrder =
                    10
            ),
            ProPassRedemptionOption(
                id =
                    SEVEN_DAY_PASS_ID,
                title =
                    "7-Day Pro Pass",
                pointsCost =
                    1_000,
                proPassDays =
                    7,
                description =
                    "Unlock Cornerstone Pro for one full week.",
                sortOrder =
                    20
            ),
            ProPassRedemptionOption(
                id =
                    THIRTY_DAY_PASS_ID,
                title =
                    "30-Day Pro Pass",
                pointsCost =
                    3_000,
                proPassDays =
                    30,
                description =
                    "Unlock Cornerstone Pro for thirty days.",
                sortOrder =
                    30
            )
        )

    fun activeOptions():
            List<ProPassRedemptionOption> {

        return options
            .filter { option ->
                option.isActive
            }
            .sortedBy { option ->
                option.sortOrder
            }
    }

    fun getOption(
        optionId: String
    ): ProPassRedemptionOption? {
        val safeOptionId =
            optionId.trim()

        return options.firstOrNull { option ->
            option.id == safeOptionId &&
                    option.isActive
        }
    }
}