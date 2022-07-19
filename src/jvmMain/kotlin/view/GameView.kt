package view

import GameMode
import TicTacToe
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable

@Composable
fun GameView(onBackClick: () -> Unit, gameMode: GameMode) {
    Button(
        onClick = onBackClick
    ) { Icon(imageVector = Icons.Default.ArrowBack, "") }
    TicTacToe(gameMode).createLayout()
}
