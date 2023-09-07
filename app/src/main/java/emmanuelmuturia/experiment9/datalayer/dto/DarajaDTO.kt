package emmanuelmuturia.experiment9.datalayer.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DarajaDTO(
    @SerialName("ResponseCode") val responseCode: String,
    @SerialName("RequestID") val requestId: String? = null,
    @SerialName("ResponseDescription") val responseDescription: String,
    @SerialName("QRCode") val qrCode: String
)