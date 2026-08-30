package com.shubham.cornerstone

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shubham.cornerstone.ui.theme.FightRed

enum class CornerstoneMainTab(
    val label: String,
    val icon: String
) {
    HOME(
        label = "Home",
        icon = "⌂"
    ),

    FIGHT_PATH(
        label = "Path",
        icon = "◈"
    ),

    TRAIN(
        label = "Train",
        icon = "▶"
    ),

    PROGRESS(
        label = "Progress",
        icon = "↗"
    ),

    MORE(
        label = "More",
        icon = "•••"
    )
}

@Composable
fun CornerstoneBottomBar(
    selectedTab: CornerstoneMainTab,
    onTabSelected: (CornerstoneMainTab) -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier =
            modifier
                .fillMaxWidth(),
        color =
            Color(
                0xFF151518
            ),
        shadowElevation =
            12.dp
    ) {
        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .navigationBarsPadding()
                    .height(
                        68.dp
                    )
                    .padding(
                        horizontal =
                            8.dp
                    ),
            horizontalArrangement =
                Arrangement.SpaceEvenly,
            verticalAlignment =
                Alignment.CenterVertically
        ) {
            CornerstoneMainTab.entries
                .forEach {
                        tab ->

                    BottomNavigationItem(
                        tab =
                            tab,
                        selected =
                            tab ==
                                    selectedTab,
                        onClick = {
                            onTabSelected(
                                tab
                            )
                        }
                    )
                }
        }
    }
}

@Composable
private fun BottomNavigationItem(
    tab: CornerstoneMainTab,
    selected: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier =
            Modifier
                .height(
                    58.dp
                )
                .clickable {
                    onClick()
                }
                .padding(
                    horizontal =
                        6.dp
                ),
        contentAlignment =
            Alignment.Center
    ) {
        Column(
            horizontalAlignment =
                Alignment.CenterHorizontally,
            verticalArrangement =
                Arrangement.Center
        ) {
            Surface(
                shape =
                    if (
                        tab ==
                        CornerstoneMainTab.TRAIN
                    ) {
                        CircleShape
                    } else {
                        RoundedCornerShape(
                            999.dp
                        )
                    },
                color =
                    when {
                        tab ==
                                CornerstoneMainTab.TRAIN -> {

                            FightRed
                        }

                        selected -> {
                            FightRed.copy(
                                alpha =
                                    0.14f
                            )
                        }

                        else -> {
                            Color.Transparent
                        }
                    }
            ) {
                Box(
                    modifier =
                        Modifier.padding(
                            horizontal =
                                if (
                                    tab ==
                                    CornerstoneMainTab.TRAIN
                                ) {
                                    13.dp
                                } else {
                                    10.dp
                                },
                            vertical =
                                if (
                                    tab ==
                                    CornerstoneMainTab.TRAIN
                                ) {
                                    8.dp
                                } else {
                                    5.dp
                                }
                        ),
                    contentAlignment =
                        Alignment.Center
                ) {
                    Text(
                        text =
                            tab.icon,
                        fontSize =
                            if (
                                tab ==
                                CornerstoneMainTab.TRAIN
                            ) {
                                18.sp
                            } else {
                                16.sp
                            },
                        fontWeight =
                            FontWeight.Black,
                        color =
                            when {
                                tab ==
                                        CornerstoneMainTab.TRAIN -> {

                                    Color.White
                                }

                                selected -> {
                                    FightRed
                                }

                                else -> {
                                    MaterialTheme
                                        .colorScheme
                                        .onSurfaceVariant
                                }
                            }
                    )
                }
            }

            if (
                tab !=
                CornerstoneMainTab.TRAIN
            ) {
                Text(
                    text =
                        tab.label,
                    fontSize =
                        9.sp,
                    fontWeight =
                        if (
                            selected
                        ) {
                            FontWeight.Black
                        } else {
                            FontWeight.Medium
                        },
                    color =
                        if (
                            selected
                        ) {
                            FightRed
                        } else {
                            MaterialTheme
                                .colorScheme
                                .onSurfaceVariant
                        }
                )
            } else {
                Text(
                    text =
                        "Train",
                    fontSize =
                        9.sp,
                    fontWeight =
                        FontWeight.Black,
                    color =
                        FightRed
                )
            }
        }
    }
}