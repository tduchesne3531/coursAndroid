package fr.eni.ecole.mod4tp1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.eni.ecole.mod4tp1.ui.theme.CoursAndroidTheme
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CoursAndroidTheme {
                Scaffold(modifier = Modifier) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .padding(innerPadding)
                    ) {
                        Dice()
                    }
                }
            }
        }
    }
}

@Composable
fun Dice(
    modifier: Modifier = Modifier,
    viewModel: DiceViewModel = viewModel()
) {
    val dice by viewModel.dice.collectAsState()
    val totalFp by viewModel.totalFp.collectAsState()
    val totalSp by viewModel.totalSp.collectAsState()


    Column(
        modifier = Modifier
            .padding(12.dp)
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = dice,
                contentDescription = "Dé",
                modifier = Modifier
                    .padding(12.dp)
                    .size(100.dp)
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(text = "${viewModel.totalCast}")
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Total : $totalFp / ${viewModel.nbCastFP}")
            Text(text = "Total : $totalSp / ${viewModel.nbCastSP}")
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        )
        {
            Button(
                onClick = { viewModel.rollDiceFP() },
                modifier = Modifier.padding(12.dp),
                shape = CircleShape) {
                Text(text = "Gauche")
            }
            Button(
                onClick = { viewModel.rollDiceSP() },
                modifier = Modifier
                    .padding(12.dp),
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White, // Fond blanc
                    contentColor = Color.Blue    // Texte bleu
                )) {
                Text(
                    text = "Droite"
                    )
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = { viewModel.resetAll() },
                modifier = Modifier.padding(12.dp),
                shape = CircleShape) {
                Text(text = "Recommencer")
            }
        }

    }
}

@Preview
@Composable
fun PreviewDice() {
    Dice()
}