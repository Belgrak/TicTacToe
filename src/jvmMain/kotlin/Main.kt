import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import view.View

const val WIDTH = 500
const val HEIGHT = 500

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        state = remember { WindowState(width = WIDTH.dp, height = HEIGHT.dp) },
        title = "TIC TAC TOE by BELGRAK",
        resizable = false
    ) {
        val viewModel = remember { ViewModel() }
        View(viewModel)
    }
}
