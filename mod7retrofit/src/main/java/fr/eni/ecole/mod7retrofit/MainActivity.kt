package fr.eni.ecole.mod7retrofit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import fr.eni.ecole.mod7retrofit.ui.theme.CoursAndroidTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CoursAndroidTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        verticalArrangement = Arrangement.spacedBy(56.dp)
                    ) {
                        Spacer(modifier = Modifier.height(30.dp))
                        WeatherScreen()

                    }
                }
            }
        }
    }
}

@Composable
fun WeatherScreen(viewModel: WeatherViewModel = viewModel(factory = WeatherViewModel.Factory))  {
    val weatherData by viewModel.weather.collectAsState(initial = null)

    LazyColumn {
        weatherData?.let{
            itemsIndexed(it.time){
                index, time ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween

                ) {
                    Text(text = time)
                    Text(text = "${it.temperatures[index]} C")

                }
            }
        }
    }
}

