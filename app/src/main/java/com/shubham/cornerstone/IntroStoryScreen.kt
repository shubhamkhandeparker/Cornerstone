package com.shubham.cornerstone

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shubham.cornerstone.ui.theme.FightRed
import com.shubham.cornerstone.ui.theme.InkBlack

private data class IntroSlide(
    val number: String,
    val eyebrow: String,
    val title: String,
    val subtitle: String,
    val mockTitle: String,
    val mockCombo: String,
    val mockFooter: String
)

@Composable
fun IntroStoryScreen(
    onFinished: () -> Unit
) {
    val slides = remember {
        listOf(
            IntroSlide(
                number = "01",
                eyebrow = "SOLO TRAINING",
                title = "Your solo fight camp.",
                subtitle = "Get boxing, Muay Thai, and MMA combos built for your level.",
                mockTitle = "TONIGHT'S SESSION",
                mockCombo = "Jab • Cross • Lead hook • Low kick",
                mockFooter = "Built for your stance"
            ),
            IntroSlide(
                number = "02",
                eyebrow = "ROUND CONTROL",
                title = "Set the round. Start moving.",
                subtitle = "Pick tap-to-advance, 30s, 60s, 90s, or custom rounds.",
                mockTitle = "ROUND TIMER",
                mockCombo = "60 sec rounds",
                mockFooter = "Train at your own pace"
            ),
            IntroSlide(
                number = "03",
                eyebrow = "FIGHT FLOW",
                title = "One combo at a time.",
                subtitle = "Follow simple drills, keep moving, and build sharp habits every session.",
                mockTitle = "NEXT COMBO",
                mockCombo = "Slip • Cross • Hook • Exit",
                mockFooter = "Warmup → Drill → Finisher"
            ),
            IntroSlide(
                number = "04",
                eyebrow = "PRO TOOLS",
                title = "Make weight. Every time.",
                subtitle = "Cornerstone Pro helps you track your cut, pace, and fight timeline.",
                mockTitle = "WEIGHT CUT",
                mockCombo = "On track",
                mockFooter = "Daily pace guidance"
            )
        )
    }

    var page by remember { mutableIntStateOf(0) }
    val slide = slides[page]
    val isLastPage = page == slides.lastIndex
    val scroll = rememberScrollState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(
                        Color(0xFF1A1517),
                        InkBlack
                    )
                )
            )
    ) {
        Box(
            modifier = Modifier
                .size(360.dp)
                .align(Alignment.TopEnd)
                .padding(top = 20.dp)
                .blur(140.dp)
                .background(FightRed.copy(alpha = 0.18f), CircleShape)
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(scroll)
                    .padding(horizontal = 24.dp)
            ) {
                Spacer(Modifier.height(24.dp))

                BrandHeader()

                Spacer(Modifier.height(28.dp))

                PhoneMock(slide = slide)

                Spacer(Modifier.height(28.dp))

                Text(
                    text = "${slide.number}  ${slide.eyebrow}",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Black,
                    letterSpacing = 2.sp,
                    color = FightRed
                )

                Spacer(Modifier.height(12.dp))

                Text(
                    text = slide.title,
                    fontSize = 42.sp,
                    lineHeight = 45.sp,
                    fontWeight = FontWeight.Black,
                    color = MaterialTheme.colorScheme.onBackground
                )

                Spacer(Modifier.height(14.dp))

                Text(
                    text = slide.subtitle,
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(Modifier.height(28.dp))

                PageDots(
                    currentPage = page,
                    totalPages = slides.size
                )

                Spacer(Modifier.height(28.dp))
            }

            Surface(
                modifier = Modifier.fillMaxWidth(),
                color = InkBlack
            ) {
                Row(
                    modifier = Modifier
                        .navigationBarsPadding()
                        .padding(
                            start = 20.dp,
                            end = 20.dp,
                            top = 14.dp,
                            bottom = 22.dp
                        ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextButton(
                        onClick = {
                            if (page > 0) page--
                        },
                        enabled = page > 0
                    ) {
                        Text(
                            text = "BACK",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 1.5.sp,
                            color = if (page > 0) FightRed else Color.White.copy(alpha = 0.22f)
                        )
                    }

                    Spacer(Modifier.weight(1f))

                    Button(
                        onClick = {
                            if (isLastPage) {
                                onFinished()
                            } else {
                                page++
                            }
                        },
                        modifier = Modifier
                            .width(if (isLastPage) 190.dp else 120.dp)
                            .height(54.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = FightRed)
                    ) {
                        Text(
                            text = if (isLastPage) "ENTER THE GYM" else "NEXT",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Black,
                            letterSpacing = 1.sp
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun BrandHeader() {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Surface(
            modifier = Modifier.size(46.dp),
            color = Color.Black,
            shape = RoundedCornerShape(topStart = 14.dp, bottomEnd = 14.dp),
            border = BorderStroke(1.dp, FightRed.copy(alpha = 0.55f))
        ) {
            Image(
                painter = painterResource(id = R.drawable.cornerstone_logo),
                contentDescription = "Cornerstone logo",
                modifier = Modifier
                    .fillMaxSize()
                    .padding(4.dp),
                contentScale = ContentScale.Fit
            )
        }

        Spacer(Modifier.width(12.dp))

        Text(
            text = "CORNERSTONE",
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 3.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun PhoneMock(slide: IntroSlide) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(282.dp),
        contentAlignment = Alignment.Center
    ) {
        Surface(
            modifier = Modifier
                .width(188.dp)
                .height(270.dp),
            shape = RoundedCornerShape(30.dp),
            color = Color(0xFF101012),
            border = BorderStroke(1.dp, Color.White.copy(alpha = 0.12f))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(14.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Surface(
                    modifier = Modifier
                        .width(52.dp)
                        .height(5.dp),
                    shape = CircleShape,
                    color = Color.White.copy(alpha = 0.18f)
                ) {}

                Spacer(Modifier.height(16.dp))

                Text(
                    text = slide.mockTitle,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Black,
                    letterSpacing = 1.3.sp,
                    color = FightRed,
                    textAlign = TextAlign.Center
                )

                Spacer(Modifier.height(14.dp))

                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(18.dp),
                    color = Color(0xFF1D191B)
                ) {
                    Column(
                        modifier = Modifier.padding(
                            start = 12.dp,
                            end = 12.dp,
                            top = 14.dp,
                            bottom = 14.dp
                        ),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = slide.mockCombo,
                            fontSize = 16.sp,
                            lineHeight = 21.sp,
                            fontWeight = FontWeight.Black,
                            color = Color.White,
                            textAlign = TextAlign.Center
                        )

                        Spacer(Modifier.height(8.dp))

                        Text(
                            text = slide.mockFooter,
                            fontSize = 11.sp,
                            lineHeight = 15.sp,
                            color = Color.White.copy(alpha = 0.62f),
                            textAlign = TextAlign.Center
                        )
                    }
                }

                Spacer(Modifier.weight(1f))

                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(42.dp),
                    shape = RoundedCornerShape(14.dp),
                    color = FightRed
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Text(
                            text = "START",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Black,
                            letterSpacing = 1.4.sp,
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun PageDots(
    currentPage: Int,
    totalPages: Int
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        repeat(totalPages) { index ->
            Surface(
                modifier = Modifier
                    .width(if (index == currentPage) 24.dp else 8.dp)
                    .height(8.dp),
                shape = CircleShape,
                color = if (index == currentPage) FightRed else Color.White.copy(alpha = 0.18f)
            ) {}
        }
    }
}