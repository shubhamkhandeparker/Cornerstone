package com.shubham.cornerstone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.shubham.cornerstone.ui.theme.CornerstoneTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

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

// Which screen we're showing once onboarding is done.
private enum class Screen { HOME, SESSION, GLOSSARY }

@Composable
fun CornerstoneApp(repository: UserProfileRepository) {
    val profile by repository.profile.collectAsStateWithLifecycle(initialValue = null)
    var screen by remember { mutableStateOf(Screen.HOME) }
    val scope = rememberCoroutineScope()

    when {
        // Onboarding not done → show onboarding.
        profile == null || !profile!!.onboardingComplete -> {
            val onboardingVm: OnboardingViewModel = viewModel(
                factory = OnboardingViewModel.Factory(repository)
            )
            OnboardingScreen(
                viewModel = onboardingVm,
                onFinished = { /* profile flow updates → moves on */ }
            )
        }

        // Onboarding done → Home, Session, or Glossary.
        else -> {
            when (screen) {
                Screen.HOME -> HomeScreen(
                    profile = profile!!,
                    onStartSession = { screen = Screen.SESSION },
                    onOpenGlossary = { screen = Screen.GLOSSARY }
                )
                Screen.SESSION -> SessionScreen(
                    combos = SessionData.beginnerBoxing(),
                    onFinishSession = {
                        scope.launch { repository.incrementSessionsCompleted() }
                        screen = Screen.HOME
                    },
                    onExit = { screen = Screen.HOME }
                )
                Screen.GLOSSARY -> GlossaryScreen(
                    onExit = { screen = Screen.HOME }
                )
            }
        }
    }
}