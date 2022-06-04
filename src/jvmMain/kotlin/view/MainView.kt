package view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

const val PADDING_OFFSET = 3

@Composable
fun MainView(onSinglePlay: () -> Unit, onMultiplayerLocal: () -> Unit) {
    MaterialTheme {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    "TIC TAC TOE", fontWeight = FontWeight.Bold, fontSize = 40.sp,
                    modifier = Modifier.padding(
                        PADDING_OFFSET.dp
                    )
                )
                Text("by BELGRAK", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            }
            Button(
                onClick = onSinglePlay,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text("Single Play")
            }
            Button(
                onClick = onMultiplayerLocal,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text("Multiplayer local")
            }
        }
    }
}
