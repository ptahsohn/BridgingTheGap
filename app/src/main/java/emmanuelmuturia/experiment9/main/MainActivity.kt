package emmanuelmuturia.experiment9.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import dagger.hilt.android.AndroidEntryPoint
import emmanuelmuturia.experiment9.uilayer.homescreen.HomeScreen
import emmanuelmuturia.experiment9.uilayer.successscreen.DarajaViewModel
import emmanuelmuturia.experiment9.uilayer.theme.Experiment9Theme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Experiment9Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val darajaViewModel: DarajaViewModel = hiltViewModel()
                    val darajaState by darajaViewModel.darajaState.collectAsState()
                    HomeScreen(darajaState = darajaState)
                }
            }
        }
    }
}