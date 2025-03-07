package fr.eni.ecole.mod4demo2

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class CounterViewModel : ViewModel() {

    private val _counter = MutableStateFlow<Int>(0)
    val counter : StateFlow<Int> = _counter
    // Autre façon d'écrire :
//    val counter = _counter.asStateFlow()

    var nbPlus = 0
    var nbMoins = 0


    fun incrementCounter() {
        _counter.value = _counter.value.inc()
        nbPlus++
    }

    fun decrementCounter() {
        _counter.value = _counter.value.dec()
        nbMoins++

    }
}