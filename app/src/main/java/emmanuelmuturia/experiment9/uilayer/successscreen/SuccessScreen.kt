package emmanuelmuturia.experiment9.uilayer.successscreen

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import emmanuelmuturia.experiment9.datalayer.dto.DarajaDTO

@Composable
fun SuccessScreen(darajaDTO: DarajaDTO) {

    val qrCodeBitmap by rememberSaveable {
        mutableStateOf(decodeBase64ToBitmap(darajaDTO.qrCode))
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = BitmapPainter(qrCodeBitmap?.asImageBitmap() ?: ImageBitmap(1, 1)),
            contentDescription = "Daraja",
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(size = 210.dp)
        )
    }

}

fun decodeBase64ToBitmap(base64: String): Bitmap? {
    return try {
        val decodedBytes = Base64.decode(base64, Base64.DEFAULT)
        BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)
    } catch (ex: Exception) {
        null
    }
}