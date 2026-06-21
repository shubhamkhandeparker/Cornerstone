package com.shubham.cornerstone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.shubham.cornerstone.ui.theme.CornerstoneTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Build the data layer once, here.
        val database = AppDatabase.get(applicationContext)
        val repository = UserProfileRepository(database.userProfileDao())

        setContent {
            CornerstoneTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CornerstoneApp(repository = repository)
                }
            }
        }
    }
}

@Composable
fun CornerstoneApp(repository: UserProfileRepository) {
    // Watch the profile. Null = still loading. onboardingComplete decides the screen.
    val profile by repository.profile.collectAsStateWithLifecycle(initialValue = null)

    when {
        // Onboarding not done yet → show onboarding.
        profile == null || !profile!!.onboardingComplete -> {
            val onboardingVm: OnboardingViewModel = viewModel(
                factory = OnboardingViewModel.Factory(repository)
            )
            OnboardingScreen(
                viewModel = onboardingVm,
                onFinished = { /* profile flow updates automatically → moves on */ }
            )
        }

        // Onboarding done → temporary home screen (we build the real one next).
        else -> {
            HomePlaceholder(profile = profile!!)
        }
    }
}

@Composable
private fun HomePlaceholder(profile: UserProfile) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "You're set up.",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "${profile.sport} · ${profile.level}",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}