package fr.eni.ecole.mod5demo3b

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import fr.eni.ecole.mod5demo3b.ui.theme.CoursAndroidTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CoursAndroidTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxHeight(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        PasswordField(
                            modifier = Modifier.padding(innerPadding)
                        )

                    }
                }
            }
        }
    }
}


const val TEST_TAG_FIELD_PWD = "TextFieldPwd"

@Composable
fun PasswordField(modifier : Modifier = Modifier) {

    var password by rememberSaveable {
        mutableStateOf("")
    }
    var passwordVisible by rememberSaveable {
        mutableStateOf(false)
    }

    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .testTag(TEST_TAG_FIELD_PWD),
        value = password ,
        onValueChange = {password = it},
        label = {
            Text(text = "Mot de passe")
        },
        singleLine = true,
        placeholder = {
            Text(text = "Entrez votre mot de passe")
        },
        supportingText = {
            Text(text = "Entrez votre mot de passe")
        },
        visualTransformation = if(passwordVisible){
            VisualTransformation.None
        }else{
            PasswordVisualTransformation()
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        trailingIcon = {
            val image = if(passwordVisible){
                Icons.Filled.CheckCircle
            }else{
                Icons.Filled.Lock
            }

            val description = if(passwordVisible){
                "Masquer le mot de passe"
            }else{
                "Montrer le mot de passe"
            }
            IconButton(onClick = {
                passwordVisible = !passwordVisible
            }) {
                Icon(
                    imageVector = image, description)
            }
        }
    )
}