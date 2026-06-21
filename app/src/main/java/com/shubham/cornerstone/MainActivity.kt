package com.shubham.cornerstone

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.foundation.layout.fillMaxSize
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
    val context = LocalContext.current

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

        // Onboarding done → the real home screen.
        else -> {
            HomeScreen(
                profile = profile!!,
                onStartSession = {
                    // Placeholder until we build the training session screen next.
                    Toast.makeText(
                        context,
                        "Training session coming next!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            )
        }
    }
}