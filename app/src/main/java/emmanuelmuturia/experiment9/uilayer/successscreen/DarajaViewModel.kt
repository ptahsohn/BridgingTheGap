package emmanuelmuturia.experiment9.uilayer.successscreen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import emmanuelmuturia.experiment9.commons.DarajaState
import emmanuelmuturia.experiment9.dependencyinjection.DarajaModule
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import okio.IOException
import javax.inject.Inject

@HiltViewModel
class DarajaViewModel @Inject constructor(
    application: Application
) : AndroidViewModel(application = application) {

    private var _darajaState = MutableStateFlow<DarajaState>(DarajaState.Loading)
    val darajaState: StateFlow<DarajaState> = _darajaState.asStateFlow()

    private val retrofit = DarajaModule.providesRetrofit()
    private val darajaApiService = DarajaModule.providesApiService(retrofit = retrofit)
    private val darajaRepository = DarajaModule.providesDarajaRepository(darajaApiService = darajaApiService)

    init {
        getDarajaResponse()
    }

    private fun getDarajaResponse() {
        viewModelScope.launch {

            _darajaState.update { DarajaState.Loading }

            try {
                _darajaState.update { DarajaState.Success(darajaResponse = darajaRepository.getDarajaQRResponse()) }
            } catch (e: IOException) {
                _darajaState.update { DarajaState.Error }
            }

        }
    }

}