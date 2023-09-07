package emmanuelmuturia.experiment9.datalayer.repository

import emmanuelmuturia.experiment9.datalayer.dto.DarajaDTO
import emmanuelmuturia.experiment9.datalayer.dto.DarajaRequestBody
import emmanuelmuturia.experiment9.datalayer.remote.DarajaApiService
import emmanuelmuturia.experiment9.domainlayer.repository.DarajaRepository

class DarajaRepositoryImpl(private val darajaApiService: DarajaApiService) : DarajaRepository {
    override suspend fun getDarajaQRResponse(): DarajaDTO {
        val darajaRequestBody = DarajaRequestBody(
            merchantName = "Emmanuel Muturia®",
            refNo = "Daraja API Test",
            amount = 10,
            trxCode = "BG",
            cpi = "808",
            size = "300"
        )
        return darajaApiService.postDarajaData(darajaRequestBody = darajaRequestBody)
    }

}