package view

import Screen
import ViewModel
import androidx.compose.runtime.Composable

@Composable
fun View(viewModel: ViewModel) {
    when (viewModel.state.screen) {
        Screen.MainView -> MainView(viewModel::singlePlayMode, viewModel::multiplayerLocalMode)
        Screen.GameView -> GameView(viewModel::backToMain, viewModel.state.gameMode)
    }
}
