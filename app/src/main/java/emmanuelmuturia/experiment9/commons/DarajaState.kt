package emmanuelmuturia.experiment9.commons

import emmanuelmuturia.experiment9.datalayer.dto.DarajaDTO

sealed interface DarajaState {

    data class Success(val darajaResponse: DarajaDTO): DarajaState

    data object Loading: DarajaState

    data object Error: DarajaState

}