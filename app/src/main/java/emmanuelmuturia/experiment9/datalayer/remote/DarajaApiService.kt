package emmanuelmuturia.experiment9.datalayer.remote

import emmanuelmuturia.experiment9.datalayer.dto.DarajaDTO
import emmanuelmuturia.experiment9.datalayer.dto.DarajaRequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

fun interface DarajaApiService {

    @POST("mpesa/qrcode/v1/generate")
    suspend fun postDarajaData(@Body darajaRequestBody: DarajaRequestBody): DarajaDTO

}