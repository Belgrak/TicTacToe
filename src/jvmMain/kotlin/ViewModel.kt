import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class ViewModel {
    var state by mutableStateOf(initialState())

    data class State(
        var screen: Screen,
        var gameMode: GameMode = GameMode.MultiplayerLocal
    )

    private fun initialState() = State(Screen.MainView)

    private inline fun updateState(update: State.() -> State) {
        state = state.update()
    }

    fun singlePlayMode() = updateState {
        copy(screen = Screen.GameView, gameMode = GameMode.SinglePlay)
    }

    fun multiplayerLocalMode() = updateState {
        copy(screen = Screen.GameView, gameMode = GameMode.MultiplayerLocal)
    }

    fun backToMain() = updateState {
        copy(screen = Screen.MainView)
    }
}
