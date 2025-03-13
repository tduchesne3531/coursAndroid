package fr.eni.ecole.mod4demo3

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class FoodViewModel(
    private val foodRepository: FoodRepository) : ViewModel()  {

        private val _foods = MutableStateFlow<List<String>>(emptyList())
        var foods : StateFlow<List<String>> = _foods.asStateFlow()

    init {
        _foods.value = foodRepository.getFoods()
    }

    // Define ViewModel factory in a companion object
    companion object {

        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras
            ): T {
//                // Get the Application object from extras
//                val application = checkNotNull(extras[APPLICATION_KEY])
//                // Create a SavedStateHandle for this ViewModel from extras
//                val savedStateHandle = extras.createSavedStateHandle()

                return FoodViewModel(
                    FoodRepository()
                ) as T
            }
        }
    }
}