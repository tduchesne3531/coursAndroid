package fr.eni.ecole.mod3demo2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import fr.eni.ecole.mod3demo2.ui.theme.CoursAndroidTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Hello(name = "Thomas")
        }
    }
}

@Composable
fun Hello(name: String) {

    Scaffold {


        Column (modifier = Modifier.padding(it)) {
            Text(
                text = "Hello $name !",
//        color = MaterialTheme.colorScheme.onPrimary
                color = Color.Red
            )
            Text(text = "Hello World !")
            Text(text = "Hello World !")
            Text(text = "Hello World !")
            Text(text = "Hello World !")
        }

    }
}

@Composable
@Preview
fun Preview() {
    Hello(name = "Thomas")
}