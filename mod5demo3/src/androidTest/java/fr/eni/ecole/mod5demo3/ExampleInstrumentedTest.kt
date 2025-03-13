package fr.eni.ecole.mod5demo3

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput

import org.junit.Test
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleInstrumentedTest {
    @get:Rule
    val composeTestRule = createComposeRule()



    @Test
    fun show(){

        composeTestRule.setContent{
            Surface(modifier = Modifier.fillMaxSize()) {
                PasswordField(modifier = Modifier.fillMaxSize())
            }
        }
        composeTestRule.onNodeWithTag(TEST_TAG_FIELD_PWD).performTextInput("Hello World")
        composeTestRule.onNodeWithContentDescription("Montrer le mot de passe").performClick()
        composeTestRule.onNodeWithTag(TEST_TAG_FIELD_PWD).assert(hasText("Hello World"))
        composeTestRule.onNodeWithContentDescription("Masquer le mot de passe").assertIsDisplayed()
    }
}