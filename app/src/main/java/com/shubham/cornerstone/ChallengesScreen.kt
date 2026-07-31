package com.shubham.cornerstone

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel as composeViewModel
import com.shubham.cornerstone.ui.theme.Charcoal
import com.shubham.cornerstone.ui.theme.FightRed
import com.shubham.cornerstone.ui.theme.InkBlack
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

private enum class ChallengeConfirmationAction {
    ABANDON,
    RESTART
}

private data class ChallengeConfirmation(
    val challengeId: String,
    val challengeTitle: String,
    val action: ChallengeConfirmationAction
)

@Composable
fun ChallengesScreen(
    viewModel: ChallengeViewModel,
    onOpenChallenge: (String) -> Unit = {},
    onExit: () -> Unit
) {
    BackHandler(onBack = onExit)

    val context = LocalContext.current

    val database = remember(context.applicationContext) {
        AppDatabase.get(context.applicationContext)
    }

    val pointsRepository = remember(database) {
        CornerstonePointsRepository(
            database.cornerstonePointsDao()
        )
    }

    val earnedProPassRepository = remember(database) {
        EarnedProPassRepository(database)
    }

    val redemptionViewModel: ProPassRedemptionViewModel =
        composeViewModel(
            key = "pro_pass_redemption_view_model",
            factory = ProPassRedemptionViewModel.Factory(
                pointsRepository = pointsRepository,
                earnedProPassRepository = earnedProPassRepository
            )
        )

    val recentTransactionsFlow = remember(pointsRepository) {
        pointsRepository.observeRecentTransactions(
            limit = 10
        )
    }

    val recentTransactions by recentTransactionsFlow
        .collectAsStateWithLifecycle(
            initialValue = emptyList()
        )

    val uiState by viewModel.uiState
        .collectAsStateWithLifecycle()

    val redemptionUiState by redemptionViewModel.uiState
        .collectAsStateWithLifecycle()

    var challengeConfirmation by remember {
        mutableStateOf<ChallengeConfirmation?>(null)
    }

    var redemptionConfirmation by remember {
        mutableStateOf<ProPassRedemptionOption?>(null)
    }

    challengeConfirmation?.let { confirmation ->
        ChallengeConfirmationDialog(
            confirmation = confirmation,
            onConfirm = {
                when (confirmation.action) {
                    ChallengeConfirmationAction.ABANDON -> {
                        viewModel.abandonChallenge(
                            confirmation.challengeId
                        )
                    }

                    ChallengeConfirmationAction.RESTART -> {
                        viewModel.restartChallenge(
                            confirmation.challengeId
                        )
                    }
                }

                challengeConfirmation = null
            },
            onDismiss = {
                challengeConfirmation = null
            }
        )
    }

    redemptionConfirmation?.let { option ->
        ProPassRedemptionConfirmationDialog(
            option = option,
            onConfirm = {
                redemptionViewModel.redeem(
                    option.id
                )

                redemptionConfirmation = null
            },
            onDismiss = {
                redemptionConfirmation = null
            }
        )
    }

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
            ChallengesHeader(
                onExit = onExit
            )

            ChallengeSummaryRow(
                activeCount = uiState.activeChallengeCount,
                completedCount = uiState.completedChallengeCount
            )

            CornerstonePointsBalanceCard(
                pointsBalance = redemptionUiState
                    .pointsBalance
                    .coerceAtLeast(0)
            )

            ChallengeCategoryFilters(
                selectedCategory = uiState.selectedCategory,
                onCategorySelected = viewModel::selectCategory
            )

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(
                    start = 20.dp,
                    end = 20.dp,
                    top = 14.dp,
                    bottom = 28.dp
                ),
                verticalArrangement = Arrangement.spacedBy(
                    14.dp
                )
            ) {
                item(
                    key = "pro_pass_redemption"
                ) {
                    ProPassRedemptionCard(
                        uiState = redemptionUiState,
                        onRedeem = { option ->
                            if (
                                redemptionUiState.canAfford(
                                    option
                                )
                            ) {
                                redemptionConfirmation = option
                            } else {
                                redemptionViewModel.redeem(
                                    option.id
                                )
                            }
                        }
                    )
                }

                redemptionUiState.message?.let { message ->
                    item(
                        key = "redemption_message"
                    ) {
                        ChallengeMessageCard(
                            message = message,
                            onDismiss = {
                                redemptionViewModel.clearMessage()
                            }
                        )
                    }
                }

                item(
                    key = "points_history"
                ) {
                    CornerstonePointsHistoryCard(
                        transactions = recentTransactions
                    )
                }

                uiState.message?.let { message ->
                    item(
                        key = "challenge_message"
                    ) {
                        ChallengeMessageCard(
                            message = message,
                            onDismiss = {
                                viewModel.clearMessage()
                            }
                        )
                    }
                }

                if (uiState.visibleChallenges.isEmpty()) {
                    item(
                        key = "empty_challenges"
                    ) {
                        EmptyChallengesCard()
                    }
                } else {
                    items(
                        items = uiState.visibleChallenges,
                        key = { challenge ->
                            challenge.definition.id
                        }
                    ) { challenge ->
                        ChallengeCard(
                            challenge = challenge,
                            isProcessing = uiState.isProcessing,
                            onOpen = {
                                onOpenChallenge(
                                    challenge.definition.id
                                )
                            },
                            onStart = {
                                viewModel.startChallenge(
                                    challenge.definition.id
                                )
                            },
                            onAbandon = {
                                challengeConfirmation =
                                    ChallengeConfirmation(
                                        challengeId =
                                            challenge.definition.id,
                                        challengeTitle =
                                            challenge.definition.title,
                                        action =
                                            ChallengeConfirmationAction
                                                .ABANDON
                                    )
                            },
                            onRestart = {
                                challengeConfirmation =
                                    ChallengeConfirmation(
                                        challengeId =
                                            challenge.definition.id,
                                        challengeTitle =
                                            challenge.definition.title,
                                        action =
                                            ChallengeConfirmationAction
                                                .RESTART
                                    )
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun ChallengesHeader(
    onExit: () -> Unit
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
            modifier = Modifier.clickable(
                onClick = onExit
            ),
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

        Column {
            Text(
                text = "Challenges",
                fontSize = 26.sp,
                fontWeight = FontWeight.Black,
                color = MaterialTheme
                    .colorScheme
                    .onBackground
            )

            Text(
                text = "Build consistency. Earn rewards.",
                fontSize = 13.sp,
                color = MaterialTheme
                    .colorScheme
                    .onSurfaceVariant
            )
        }
    }
}

@Composable
private fun ChallengeSummaryRow(
    activeCount: Int,
    completedCount: Int
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 20.dp,
                vertical = 4.dp
            ),
        horizontalArrangement = Arrangement.spacedBy(
            12.dp
        )
    ) {
        ChallengeSummaryCard(
            value = activeCount.toString(),
            label = "Active",
            modifier = Modifier.weight(1f)
        )

        ChallengeSummaryCard(
            value = completedCount.toString(),
            label = "Completed",
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
private fun ChallengeSummaryCard(
    value: String,
    label: String,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        color = Charcoal
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = value,
                fontSize = 22.sp,
                fontWeight = FontWeight.Black,
                color = FightRed
            )

            Text(
                text = label,
                fontSize = 12.sp,
                color = MaterialTheme
                    .colorScheme
                    .onSurfaceVariant
            )
        }
    }
}

@Composable
private fun CornerstonePointsBalanceCard(
    pointsBalance: Int
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 20.dp,
                end = 20.dp,
                top = 12.dp
            ),
        shape = RoundedCornerShape(18.dp),
        color = FightRed.copy(
            alpha = 0.13f
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 18.dp,
                    vertical = 16.dp
                ),
            horizontalArrangement =
                Arrangement.SpaceBetween,
            verticalAlignment =
                Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "CORNERSTONE POINTS",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Black,
                    letterSpacing = 1.2.sp,
                    color = FightRed
                )

                Spacer(
                    modifier = Modifier.height(5.dp)
                )

                Text(
                    text = "Earned from completed challenges",
                    fontSize = 12.sp,
                    color = MaterialTheme
                        .colorScheme
                        .onSurfaceVariant
                )
            }

            Spacer(
                modifier = Modifier.size(16.dp)
            )

            Text(
                text = pointsBalance.toString(),
                fontSize = 30.sp,
                fontWeight = FontWeight.Black,
                color = MaterialTheme
                    .colorScheme
                    .onBackground
            )
        }
    }
}

@Composable
private fun ProPassRedemptionCard(
    uiState: ProPassRedemptionUiState,
    onRedeem: (ProPassRedemptionOption) -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        color = Charcoal
    ) {
        Column(
            modifier = Modifier.padding(18.dp)
        ) {
            Text(
                text = "Unlock Pro with Points",
                fontSize = 18.sp,
                fontWeight = FontWeight.Black,
                color = MaterialTheme
                    .colorScheme
                    .onBackground
            )

            Spacer(
                modifier = Modifier.height(5.dp)
            )

            Text(
                text =
                    "Redeem challenge points for temporary Cornerstone Pro access.",
                fontSize = 12.sp,
                lineHeight = 18.sp,
                color = MaterialTheme
                    .colorScheme
                    .onSurfaceVariant
            )

            uiState.activePass
                ?.takeIf { pass ->
                    pass.isActive()
                }
                ?.let { pass ->
                    Spacer(
                        modifier = Modifier.height(14.dp)
                    )

                    ActiveEarnedProPassCard(
                        pass = pass
                    )
                }

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            uiState.options.forEachIndexed {
                    index,
                    option ->

                ProPassRedemptionOptionCard(
                    option = option,
                    pointsBalance = uiState.pointsBalance,
                    isProcessing = uiState.isProcessing,
                    isThisOptionProcessing =
                        uiState.processingOptionId ==
                                option.id,
                    onRedeem = {
                        onRedeem(option)
                    }
                )

                if (index < uiState.options.lastIndex) {
                    Spacer(
                        modifier = Modifier.height(10.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun ActiveEarnedProPassCard(
    pass: EarnedProPassEntity
) {
    val expiryText = remember(
        pass.expiresAtEpochMs
    ) {
        formatDateTime(
            pass.expiresAtEpochMs
        )
    }

    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(14.dp),
        color = FightRed.copy(
            alpha = 0.12f
        )
    ) {
        Column(
            modifier = Modifier.padding(14.dp)
        ) {
            Text(
                text = "PRO PASS ACTIVE",
                fontSize = 10.sp,
                fontWeight = FontWeight.Black,
                letterSpacing = 1.sp,
                color = FightRed
            )

            Spacer(
                modifier = Modifier.height(5.dp)
            )

            Text(
                text = "Expires $expiryText",
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme
                    .colorScheme
                    .onBackground
            )

            Spacer(
                modifier = Modifier.height(3.dp)
            )

            Text(
                text = pass.description,
                fontSize = 11.sp,
                color = MaterialTheme
                    .colorScheme
                    .onSurfaceVariant
            )
        }
    }
}

@Composable
private fun ProPassRedemptionOptionCard(
    option: ProPassRedemptionOption,
    pointsBalance: Int,
    isProcessing: Boolean,
    isThisOptionProcessing: Boolean,
    onRedeem: () -> Unit
) {
    val safeBalance = pointsBalance.coerceAtLeast(0)

    val canAfford =
        safeBalance >= option.pointsCost

    val pointsNeeded =
        (option.pointsCost - safeBalance)
            .coerceAtLeast(0)

    val buttonText = when {
        isThisOptionProcessing -> {
            "Redeeming..."
        }

        canAfford -> {
            "Redeem ${option.pointsCost} points"
        }

        else -> {
            "Need $pointsNeeded more points"
        }
    }

    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(15.dp),
        color = Color(0xFF29282C)
    ) {
        Column(
            modifier = Modifier.padding(15.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement =
                    Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 12.dp)
                ) {
                    Text(
                        text = option.title,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Black,
                        color = MaterialTheme
                            .colorScheme
                            .onBackground
                    )

                    Spacer(
                        modifier = Modifier.height(4.dp)
                    )

                    Text(
                        text = option.description,
                        fontSize = 12.sp,
                        lineHeight = 17.sp,
                        color = MaterialTheme
                            .colorScheme
                            .onSurfaceVariant
                    )
                }

                Surface(
                    shape = RoundedCornerShape(999.dp),
                    color = FightRed.copy(
                        alpha = 0.14f
                    )
                ) {
                    Text(
                        text = "${option.proPassDays} DAYS",
                        fontSize = 9.sp,
                        fontWeight = FontWeight.Black,
                        letterSpacing = 0.7.sp,
                        color = FightRed,
                        modifier = Modifier.padding(
                            horizontal = 9.dp,
                            vertical = 6.dp
                        )
                    )
                }
            }

            Spacer(
                modifier = Modifier.height(13.dp)
            )

            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(
                        enabled = !isProcessing,
                        onClick = onRedeem
                    ),
                shape = RoundedCornerShape(12.dp),
                color = if (canAfford) {
                    FightRed
                } else {
                    Color(0xFF3A393E)
                }
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(44.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = if (
                            isProcessing &&
                            !isThisOptionProcessing
                        ) {
                            "Please wait..."
                        } else {
                            buttonText
                        },
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = if (canAfford) {
                            Color.White
                        } else {
                            MaterialTheme
                                .colorScheme
                                .onSurfaceVariant
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun CornerstonePointsHistoryCard(
    transactions:
    List<CornerstonePointsTransactionEntity>
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        color = Charcoal
    ) {
        Column(
            modifier = Modifier.padding(18.dp)
        ) {
            Text(
                text = "Recent Points Activity",
                fontSize = 17.sp,
                fontWeight = FontWeight.Black,
                color = MaterialTheme
                    .colorScheme
                    .onBackground
            )

            Spacer(
                modifier = Modifier.height(5.dp)
            )

            Text(
                text = "Your latest rewards and redemptions",
                fontSize = 12.sp,
                color = MaterialTheme
                    .colorScheme
                    .onSurfaceVariant
            )

            Spacer(
                modifier = Modifier.height(14.dp)
            )

            if (transactions.isEmpty()) {
                Text(
                    text = "No points activity yet.",
                    fontSize = 13.sp,
                    color = MaterialTheme
                        .colorScheme
                        .onSurfaceVariant
                )
            } else {
                transactions.forEachIndexed {
                        index,
                        transaction ->

                    CornerstonePointsTransactionRow(
                        transaction = transaction
                    )

                    if (index < transactions.lastIndex) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(1.dp)
                                .background(
                                    Color.White.copy(
                                        alpha = 0.06f
                                    )
                                )
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun CornerstonePointsTransactionRow(
    transaction:
    CornerstonePointsTransactionEntity
) {
    val amountText =
        if (transaction.amount > 0) {
            "+${transaction.amount}"
        } else {
            transaction.amount.toString()
        }

    val dateText = remember(
        transaction.createdAtEpochMs
    ) {
        formatDateTime(
            transaction.createdAtEpochMs
        )
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        horizontalArrangement =
            Arrangement.SpaceBetween,
        verticalAlignment =
            Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(end = 14.dp)
        ) {
            Text(
                text = transaction.description,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme
                    .colorScheme
                    .onBackground
            )

            Spacer(
                modifier = Modifier.height(4.dp)
            )

            Text(
                text = dateText,
                fontSize = 11.sp,
                color = MaterialTheme
                    .colorScheme
                    .onSurfaceVariant
            )
        }

        Text(
            text = amountText,
            fontSize = 16.sp,
            fontWeight = FontWeight.Black,
            color = if (transaction.amount >= 0) {
                FightRed
            } else {
                MaterialTheme
                    .colorScheme
                    .onSurfaceVariant
            }
        )
    }
}

@Composable
private fun ChallengeCategoryFilters(
    selectedCategory: ChallengeCategory?,
    onCategorySelected: (ChallengeCategory?) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(
                rememberScrollState()
            )
            .padding(
                horizontal = 20.dp,
                vertical = 14.dp
            ),
        horizontalArrangement =
            Arrangement.spacedBy(10.dp)
    ) {
        ChallengeCategoryChip(
            text = "All",
            selected = selectedCategory == null,
            onClick = {
                onCategorySelected(null)
            }
        )

        ChallengeCategory.entries.forEach { category ->
            ChallengeCategoryChip(
                text = category.displayName,
                selected =
                    selectedCategory == category,
                onClick = {
                    onCategorySelected(category)
                }
            )
        }
    }
}

@Composable
private fun ChallengeCategoryChip(
    text: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier.clickable(
            onClick = onClick
        ),
        shape = RoundedCornerShape(999.dp),
        color = if (selected) {
            FightRed
        } else {
            Charcoal
        }
    ) {
        Text(
            text = text,
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold,
            color = if (selected) {
                Color.White
            } else {
                MaterialTheme
                    .colorScheme
                    .onSurfaceVariant
            },
            modifier = Modifier.padding(
                horizontal = 16.dp,
                vertical = 10.dp
            )
        )
    }
}

@Composable
private fun ChallengeCard(
    challenge: ChallengeWithProgress,
    isProcessing: Boolean,
    onOpen: () -> Unit,
    onStart: () -> Unit,
    onAbandon: () -> Unit,
    onRestart: () -> Unit
) {
    val definition = challenge.definition
    val progress = challenge.progress

    val progressFraction =
        if (
            progress != null &&
            definition.durationDays > 0
        ) {
            (
                    progress.completedDays.toFloat() /
                            definition.durationDays.toFloat()
                    ).coerceIn(
                    minimumValue = 0f,
                    maximumValue = 1f
                )
        } else {
            0f
        }

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                onClick = onOpen
            ),
        shape = RoundedCornerShape(20.dp),
        color = Charcoal
    ) {
        Column(
            modifier = Modifier.padding(18.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement =
                    Arrangement.SpaceBetween,
                verticalAlignment =
                    Alignment.CenterVertically
            ) {
                ChallengeBadge(
                    text = definition
                        .difficulty
                        .displayName,
                    highlighted =
                        definition.difficulty ==
                                ChallengeDifficulty.ELITE
                )

                ChallengeStatusBadge(
                    status = challenge.status
                )
            }

            Spacer(
                modifier = Modifier.height(14.dp)
            )

            Text(
                text = definition.title,
                fontSize = 20.sp,
                fontWeight = FontWeight.Black,
                color = MaterialTheme
                    .colorScheme
                    .onBackground
            )

            Spacer(
                modifier = Modifier.height(6.dp)
            )

            Text(
                text = definition.shortDescription,
                fontSize = 14.sp,
                lineHeight = 20.sp,
                color = MaterialTheme
                    .colorScheme
                    .onSurfaceVariant
            )

            Spacer(
                modifier = Modifier.height(14.dp)
            )

            ChallengeRequirementRow(
                definition = definition
            )

            Spacer(
                modifier = Modifier.height(14.dp)
            )

            if (progress != null) {
                ChallengeProgressSection(
                    definition = definition,
                    progress = progress,
                    progressFraction =
                        progressFraction
                )

                Spacer(
                    modifier = Modifier.height(14.dp)
                )
            }

            ChallengeRewardRow(
                reward = definition.reward
            )

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            ChallengeActionButton(
                status = challenge.status,
                enabled = !isProcessing,
                onStart = onStart,
                onAbandon = onAbandon,
                onRestart = onRestart
            )
        }
    }
}

@Composable
private fun ChallengeRequirementRow(
    definition: ChallengeDefinition
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement =
            Arrangement.spacedBy(8.dp)
    ) {
        ChallengeRequirementPill(
            text = "${definition.durationDays} days"
        )

        if (
            definition.requiredActiveMinutesPerDay >
            0
        ) {
            ChallengeRequirementPill(
                text =
                    "${definition.requiredActiveMinutesPerDay} min/day"
            )
        }

        if (
            definition.requiredSessionsPerDay >
            1
        ) {
            ChallengeRequirementPill(
                text =
                    "${definition.requiredSessionsPerDay} sessions/day"
            )
        }

        if (
            definition.requiredRepetitionsPerDay >
            0
        ) {
            ChallengeRequirementPill(
                text =
                    "${definition.requiredRepetitionsPerDay} reps"
            )
        }
    }
}

@Composable
private fun ChallengeRequirementPill(
    text: String
) {
    Surface(
        shape = RoundedCornerShape(999.dp),
        color = Color(0xFF29282C)
    ) {
        Text(
            text = text,
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme
                .colorScheme
                .onSurfaceVariant,
            modifier = Modifier.padding(
                horizontal = 10.dp,
                vertical = 6.dp
            )
        )
    }
}

@Composable
private fun ChallengeProgressSection(
    definition: ChallengeDefinition,
    progress: ChallengeProgress,
    progressFraction: Float
) {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement =
                Arrangement.SpaceBetween
        ) {
            Text(
                text =
                    "${progress.completedDays} of ${definition.durationDays} days",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme
                    .colorScheme
                    .onSurfaceVariant
            )

            Text(
                text =
                    "${(progressFraction * 100).toInt()}%",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = FightRed
            )
        }

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        LinearProgressIndicator(
            progress = {
                progressFraction
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(7.dp),
            color = FightRed,
            trackColor = Color(0xFF29282C)
        )

        Spacer(
            modifier = Modifier.height(10.dp)
        )

        Text(
            text =
                "Current streak: ${progress.currentStreakDays} days",
            fontSize = 12.sp,
            color = MaterialTheme
                .colorScheme
                .onSurfaceVariant
        )
    }
}

@Composable
private fun ChallengeRewardRow(
    reward: ChallengeReward
) {
    val rewardText = when (reward.type) {
        ChallengeRewardType.NONE -> {
            "No reward"
        }

        ChallengeRewardType.POINTS -> {
            "${reward.points} Cornerstone Points"
        }

        ChallengeRewardType.PRO_PASS -> {
            "${reward.proPassDays}-Day Pro Pass"
        }
    }

    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(14.dp),
        color = FightRed.copy(
            alpha = 0.10f
        )
    ) {
        Row(
            modifier = Modifier.padding(14.dp),
            verticalAlignment =
                Alignment.CenterVertically
        ) {
            Text(
                text = "REWARD",
                fontSize = 10.sp,
                fontWeight = FontWeight.Black,
                letterSpacing = 1.sp,
                color = FightRed
            )

            Spacer(
                modifier = Modifier.size(10.dp)
            )

            Text(
                text = rewardText,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme
                    .colorScheme
                    .onBackground
            )
        }
    }
}

@Composable
private fun ChallengeActionButton(
    status: ChallengeStatus,
    enabled: Boolean,
    onStart: () -> Unit,
    onAbandon: () -> Unit,
    onRestart: () -> Unit
) {
    val buttonText: String
    val action: () -> Unit
    val buttonColor: Color

    when (status) {
        ChallengeStatus.NOT_STARTED -> {
            buttonText = "Start Challenge"
            action = onStart
            buttonColor = FightRed
        }

        ChallengeStatus.ACTIVE -> {
            buttonText = "Abandon Challenge"
            action = onAbandon
            buttonColor = Color(0xFF29282C)
        }

        ChallengeStatus.COMPLETED -> {
            buttonText = "Completed"
            action = {}
            buttonColor = Color(0xFF29282C)
        }

        ChallengeStatus.FAILED,
        ChallengeStatus.ABANDONED -> {
            buttonText = "Restart Challenge"
            action = onRestart
            buttonColor = FightRed
        }
    }

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                enabled =
                    enabled &&
                            status !=
                            ChallengeStatus.COMPLETED,
                onClick = action
            ),
        shape = RoundedCornerShape(14.dp),
        color = buttonColor
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = if (enabled) {
                    buttonText
                } else {
                    "Please wait..."
                },
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = if (
                    status ==
                    ChallengeStatus.COMPLETED
                ) {
                    MaterialTheme
                        .colorScheme
                        .onSurfaceVariant
                } else {
                    Color.White
                }
            )
        }
    }
}

@Composable
private fun ChallengeBadge(
    text: String,
    highlighted: Boolean
) {
    Surface(
        shape = RoundedCornerShape(999.dp),
        color = if (highlighted) {
            FightRed.copy(
                alpha = 0.16f
            )
        } else {
            Color(0xFF29282C)
        }
    ) {
        Text(
            text = text.uppercase(),
            fontSize = 9.sp,
            fontWeight = FontWeight.Black,
            letterSpacing = 0.8.sp,
            color = if (highlighted) {
                FightRed
            } else {
                MaterialTheme
                    .colorScheme
                    .onSurfaceVariant
            },
            modifier = Modifier.padding(
                horizontal = 10.dp,
                vertical = 6.dp
            )
        )
    }
}

@Composable
private fun ChallengeStatusBadge(
    status: ChallengeStatus
) {
    val text = when (status) {
        ChallengeStatus.NOT_STARTED -> {
            "NOT STARTED"
        }

        ChallengeStatus.ACTIVE -> {
            "ACTIVE"
        }

        ChallengeStatus.COMPLETED -> {
            "COMPLETED"
        }

        ChallengeStatus.FAILED -> {
            "FAILED"
        }

        ChallengeStatus.ABANDONED -> {
            "ABANDONED"
        }
    }

    val highlighted =
        status == ChallengeStatus.ACTIVE ||
                status == ChallengeStatus.COMPLETED

    ChallengeBadge(
        text = text,
        highlighted = highlighted
    )
}

@Composable
private fun ChallengeMessageCard(
    message: String,
    onDismiss: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                onClick = onDismiss
            ),
        shape = RoundedCornerShape(14.dp),
        color = FightRed.copy(
            alpha = 0.12f
        )
    ) {
        Text(
            text = message,
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme
                .colorScheme
                .onBackground,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(14.dp)
        )
    }
}

@Composable
private fun EmptyChallengesCard() {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        color = Charcoal
    ) {
        Text(
            text =
                "No challenges are available in this category.",
            fontSize = 14.sp,
            color = MaterialTheme
                .colorScheme
                .onSurfaceVariant,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(24.dp)
        )
    }
}

@Composable
private fun ProPassRedemptionConfirmationDialog(
    option: ProPassRedemptionOption,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text =
                    "Redeem ${option.title}?"
            )
        },
        text = {
            Text(
                text =
                    "${option.pointsCost} Cornerstone Points will be deducted. " +
                            "Your ${option.proPassDays}-day Pro Pass will start immediately. " +
                            "If another earned pass is active, the new time will be added after it."
            )
        },
        confirmButton = {
            TextButton(
                onClick = onConfirm
            ) {
                Text(
                    text = "REDEEM",
                    color = FightRed,
                    fontWeight = FontWeight.Bold
                )
            }
        },
        dismissButton = {
            TextButton(
                onClick = onDismiss
            ) {
                Text(
                    text = "CANCEL"
                )
            }
        }
    )
}

@Composable
private fun ChallengeConfirmationDialog(
    confirmation: ChallengeConfirmation,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    val isAbandon =
        confirmation.action ==
                ChallengeConfirmationAction.ABANDON

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = if (isAbandon) {
                    "Abandon challenge?"
                } else {
                    "Restart challenge?"
                }
            )
        },
        text = {
            Text(
                text = if (isAbandon) {
                    "Your current progress for ${confirmation.challengeTitle} will stop. " +
                            "You can restart it later."
                } else {
                    "Your previous progress for ${confirmation.challengeTitle} will be " +
                            "deleted and the challenge will start again from day one."
                }
            )
        },
        confirmButton = {
            TextButton(
                onClick = onConfirm
            ) {
                Text(
                    text = if (isAbandon) {
                        "ABANDON"
                    } else {
                        "RESTART"
                    },
                    color = FightRed,
                    fontWeight = FontWeight.Bold
                )
            }
        },
        dismissButton = {
            TextButton(
                onClick = onDismiss
            ) {
                Text(
                    text = "CANCEL"
                )
            }
        }
    )
}

private fun formatDateTime(
    epochMs: Long
): String {
    return runCatching {
        Instant
            .ofEpochMilli(epochMs)
            .atZone(
                ZoneId.systemDefault()
            )
            .format(
                DateTimeFormatter.ofPattern(
                    "dd MMM yyyy, h:mm a"
                )
            )
    }.getOrDefault(
        "Recently"
    )
}