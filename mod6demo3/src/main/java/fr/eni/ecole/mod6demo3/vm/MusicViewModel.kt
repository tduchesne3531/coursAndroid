package fr.eni.ecole.mod6demo3.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import fr.eni.ecole.mod6demo3.bo.Music
import fr.eni.ecole.mod6demo3.dao.MusicDao
import fr.eni.ecole.mod6demo3.room.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MusicViewModel(
    private val musicDao: MusicDao
) : ViewModel() {

    private val _musics = MutableStateFlow<List<Music>>(emptyList())
    val musics: Flow<List<Music>> = _musics

    init {
        viewModelScope.launch {
            insertMusic(
                Music(
                    0L,
                    "Gimme Chocolate",
                    "Baby Metal",
                    120
                )
            )
            insertMusic(
                Music(
                    0L,
                    "Crazy Scary Holy Fantasy",
                    "Myth & Roid",
                    160
                )
            )
            loadMusic()

        }
    }

    private suspend fun loadMusic() {
        withContext(Dispatchers.IO) {
            _musics.value = musicDao.findAll()
        }
    }

    private suspend fun insertMusic(music: Music) {
        withContext(Dispatchers.IO) {
            musicDao.insert(music)
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

                return MusicViewModel(
                    AppDatabase.getInstance(application.applicationContext)
                        .musicDao()
                ) as T
            }
        }
    }
}