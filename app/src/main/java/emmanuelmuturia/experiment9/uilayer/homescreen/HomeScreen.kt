package emmanuelmuturia.experiment9.uilayer.homescreen

import androidx.compose.runtime.Composable
import emmanuelmuturia.experiment9.commons.DarajaState
import emmanuelmuturia.experiment9.uilayer.errorscreen.ErrorScreen
import emmanuelmuturia.experiment9.uilayer.loadingscreen.LoadingScreen
import emmanuelmuturia.experiment9.uilayer.successscreen.SuccessScreen

@Composable
fun HomeScreen(darajaState: DarajaState) {

    when(darajaState) {
        is DarajaState.Success -> SuccessScreen(darajaDTO = darajaState.darajaResponse)
        is DarajaState.Loading -> LoadingScreen()
        is DarajaState.Error -> ErrorScreen()
    }

}