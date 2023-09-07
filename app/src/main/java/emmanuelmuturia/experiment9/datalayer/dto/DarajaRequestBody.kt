package emmanuelmuturia.experiment9.datalayer.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DarajaRequestBody(
    @SerialName("MerchantName") val merchantName: String,
    @SerialName("RefNo") val refNo: String,
    @SerialName("Amount") val amount: Int,
    @SerialName("TrxCode") val trxCode: String,
    @SerialName("CPI") val cpi: String,
    @SerialName("Size") val size: String
)
