import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import bot.easyBot
import view.PADDING_OFFSET

const val CELL_SIZE = 100

class TicTacToe(var gameMode: GameMode) {
    var nextCellState = CellState.X_STATE
    var text = mutableStateOf("X, your turn")
    var stopPlaying = mutableStateOf(false)
    var botTurn = Pair(0, 0)
    var isUserTurn = mutableStateOf(true)
    val xPositions = mutableListOf<Pair<Int, Int>>()
    val oPositions = mutableListOf<Pair<Int, Int>>()
    val listOfCellStates = mutableListOf<Triple<Int, Int, MutableState<String>>>()

    @Composable
    fun createLayout() {
        val gameInfo by remember { text }

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                gameInfo,
                fontWeight = FontWeight.Bold,
                fontSize = 40.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally).padding(PADDING_OFFSET.dp)
            )
            for (i in 0 until 3) {
                Row() {
                    for (j in 0 until 3) {
                        val cell = Cell(i, j)
                        listOfCellStates.add(Triple(i, j, cell.cellState))
                        when (gameMode) {
                            GameMode.SinglePlay -> cell.singlePlay()
                            GameMode.MultiplayerLocal -> cell.multiplayerLocalMode()
                        }
                    }
                }
            }
        }
    }

    fun checkGameState() {
        when {
            isWin(xPositions) -> {
                text.value = "X wins"
                stopPlaying.value = true
            }
            isWin(oPositions) -> {
                text.value = "O wins"
                stopPlaying.value = true
            }
            isDraw(xPositions + oPositions, stopPlaying) -> {
                text.value = "Ohh, it's a draw"
                stopPlaying.value = true
            }
        }
    }

    companion object WinAndDrawStates {
        val isWin =
            { positions: MutableList<Pair<Int, Int>> ->
                val verticalWin =
                    positions.map { it.first }.groupingBy { it }.eachCount().filter { it.value == 3 }.isNotEmpty()
                val horizontalWin =
                    positions.map { it.second }.groupingBy { it }.eachCount().filter { it.value == 3 }.isNotEmpty()
                val diagonalWin =
                    mutableListOf(
                        listOf(Pair(0, 0), Pair(1, 1), Pair(2, 2)),
                        listOf(Pair(0, 2), Pair(1, 1), Pair(2, 0))
                    ).any { win -> win.all { pair -> pair in positions } }
                horizontalWin || verticalWin || diagonalWin
            }
        val isDraw = { allPositions: List<Pair<Int, Int>>, stopPlaying: MutableState<Boolean> ->
            allPositions.size == 9 && !stopPlaying.value
        }
    }

    enum class CellState {
        X_STATE, O_STATE
    }

    inner class Cell(private val row: Int, private val column: Int) {
        var cellState = mutableStateOf("")

        private fun getBotTurn() {
            if (!isUserTurn.value && !stopPlaying.value) {
                botTurn = easyBot(xPositions, oPositions)
                val cell =
                    listOfCellStates.filter { triple ->
                        triple.first == botTurn.first && triple.second == botTurn.second
                    }
                when (nextCellState) {
                    CellState.X_STATE -> {
                        cell.first().third.value = "X"
                        xPositions.add(Pair(botTurn.first, botTurn.second))
                        nextCellState = CellState.O_STATE
                        text.value = "O, your turn"
                        isUserTurn.value = true
                    }
                    CellState.O_STATE -> {
                        cell.first().third.value = "O"
                        oPositions.add(Pair(botTurn.first, botTurn.second))
                        nextCellState = CellState.X_STATE
                        text.value = "X, your turn"
                        isUserTurn.value = true
                    }
                }
            }
            checkGameState()
        }

        @Composable
        fun singlePlay() {
            var state by remember { cellState }

            Button(
                modifier = Modifier.size(CELL_SIZE.dp).border(width = 2.dp, Color.Black),
                colors = ButtonDefaults.buttonColors(Color.White),
                shape = RectangleShape,
                onClick = {
                    if (state != "" || !isUserTurn.value || stopPlaying.value) {
                        return@Button
                    }
                    when (nextCellState) {
                        CellState.X_STATE -> {
                            state = "X"
                            xPositions.add(Pair(row, column))
                            nextCellState = CellState.O_STATE
                            text.value = "O, your turn"
                            isUserTurn.value = false
                        }
                        CellState.O_STATE -> {
                            state = "O"
                            oPositions.add(Pair(row, column))
                            nextCellState = CellState.X_STATE
                            text.value = "X, your turn"
                            isUserTurn.value = false
                        }
                    }
                    checkGameState()
                    getBotTurn()
                }
            ) { Text(state, fontSize = (CELL_SIZE / 2).sp, fontWeight = FontWeight.Bold) }
        }

        @Composable
        fun multiplayerLocalMode() {
            var state by remember { mutableStateOf("") }

            Button(
                modifier = Modifier.size(CELL_SIZE.dp).border(width = 2.dp, Color.Black),
                colors = ButtonDefaults.buttonColors(Color.White),
                shape = RectangleShape,
                onClick = {
                    if (state != "" || stopPlaying.value) {
                        return@Button
                    }
                    when (nextCellState) {
                        CellState.X_STATE -> {
                            state = "X"
                            xPositions.add(Pair(row, column))
                            nextCellState = CellState.O_STATE
                            text.value = "O, your turn"
                        }
                        CellState.O_STATE -> {
                            state = "O"
                            oPositions.add(Pair(row, column))
                            nextCellState = CellState.X_STATE
                            text.value = "X, your turn"
                        }
                    }
                    checkGameState()
                }
            ) { Text(state, fontSize = (CELL_SIZE / 2).sp, fontWeight = FontWeight.Bold) }
        }
    }
}
