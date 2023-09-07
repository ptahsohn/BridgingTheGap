package emmanuelmuturia.experiment9.domainlayer.repository

import emmanuelmuturia.experiment9.datalayer.dto.DarajaDTO

fun interface DarajaRepository {

    suspend fun getDarajaQRResponse(): DarajaDTO

}