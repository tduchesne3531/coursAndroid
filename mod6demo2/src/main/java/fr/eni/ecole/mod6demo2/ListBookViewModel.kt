package fr.eni.ecole.mod6demo2

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ListBookViewModel(dbHelper: BookDbHelper) : ViewModel() {
    private val dao = BookDao(dbHelper.writableDatabase, dbHelper.readableDatabase)

    private val _books = MutableStateFlow<List<Book>>(emptyList())
    val books = _books

    init {
        viewModelScope.launch {

            insertBook(
                Book(
                    0L,
                    "Kafka sur le rivage",
                    "54684611846",
                    "2002-01-01",
                    "Folio",
                    "Haruki Murakami"
                )
            )
            insertBook(
                Book(
                    0L,
                    "1Q84",
                    "54684611846",
                    "2002-01-01",
                    "Folio",
                    "Haruki Murakami"
                )
            )
            getAllBooks()

        }
    }

    suspend fun insertBook(book: Book) {
        withContext(Dispatchers.IO) {
            dao.insertBook(book)

        }
    }

    suspend fun getAllBooks() {
        withContext(Dispatchers.IO) {
           val booksDB = dao.getAllBooks()
            _books.value = booksDB
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

                return ListBookViewModel(
                    BookDbHelper(application.applicationContext)
                ) as T
            }
        }
    }
}