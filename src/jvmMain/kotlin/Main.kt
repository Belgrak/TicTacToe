import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application

const val WIDTH = 500
const val HEIGHT = 500

@Composable
fun demoView() {
    MaterialTheme {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    "TIC TAC TOE", fontWeight = FontWeight.Bold, fontSize = 40.sp, modifier = Modifier.padding(
                        20.dp
                    )
                )
                Text("by BELGRAK", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            }
            Text("realisation is coming soon", fontSize = 18.sp, fontWeight = FontWeight.Medium, color = Color.Gray)
        }
    }
}

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        state = remember { WindowState(width = WIDTH.dp, height = HEIGHT.dp) },
        title = "TIC TAC TOE by BELGRAK",
        resizable = false
    ) {
        demoView()
    }
}
