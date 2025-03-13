package fr.eni.ecole.mod6demo1

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SettingsViewModel(private val userPreferenceRepository: UserPreferenceRepository) : ViewModel() {

    private val _colors = MutableStateFlow<List<Color>>(
        listOf(Color.Red, Color.Green, Color.Blue)
    )
    val colors : StateFlow<List<Color>> = _colors

    private val _userColor = MutableStateFlow<Color>(Color.White)
    val selectedColor : StateFlow<Color> = _userColor

    init {
        getBgColor()
    }

    fun getBgColor() {
        viewModelScope.launch {
            userPreferenceRepository.getBgColor().collect {
                _userColor.value = it
            }
        }
    }

    fun setBgColor(color: Color) {
        viewModelScope.launch {
            userPreferenceRepository.saveBgColor(color)
        }
    }

    companion object {

        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras
            ): T {
                // Get the Application object from extras
                val application = checkNotNull(extras[APPLICATION_KEY])
                // Create a SavedStateHandle for this ViewModel from extras
                val savedStateHandle = extras.createSavedStateHandle()

                return SettingsViewModel(
                    UserPreferenceRepository(application.applicationContext)
                ) as T
            }
        }
    }
}
