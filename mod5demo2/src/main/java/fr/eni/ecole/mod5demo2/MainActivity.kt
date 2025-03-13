package fr.eni.ecole.mod5demo2

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposableTarget
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.eni.ecole.mod5demo2.ui.theme.CoursAndroidTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CoursAndroidTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier.padding(100.dp)
                    ) {
                        GoToMap(
                            modifier = Modifier.padding(innerPadding)
                        )
                        Spacer(modifier = Modifier.padding(12.dp))
                        Call(
                            modifier = Modifier.padding(innerPadding)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun GoToMap(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = {
            Intent(Intent.ACTION_VIEW, Uri.parse("geo:48.039, -1.692"))
                .also {
                    context.startActivity(it)
                }

        }) {
            Text(text = "Go To Map")
        }
    }

}

@Composable
fun Call(
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val requestPermission =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestPermission()
        ) { isGranted ->
            if (isGranted) {
                Intent(Intent.ACTION_CALL, Uri.parse("tel:0636656565"))
                    .also {
                        context.startActivity(it)
                    }

            } else {
                Intent(Intent.ACTION_DIAL, Uri.parse("tel:0666666666"))
                    .also {
                        context.startActivity(it)
                    }
            }
        }
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = {
            requestPermission.launch(android.Manifest.permission.CALL_PHONE)
        }) {
            Text(text = "NightCall")
        }
    }
}

