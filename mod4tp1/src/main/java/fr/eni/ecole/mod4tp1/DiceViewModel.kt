package fr.eni.ecole.mod4tp1

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class DiceViewModel : ViewModel() {

    private val _dice = MutableStateFlow<Int>(R.drawable.d1)
    val dice : MutableStateFlow<Int> = _dice

    private val diceList = listOf(
        R.drawable.d1,
        R.drawable.d2,
        R.drawable.d3,
        R.drawable.d4,
        R.drawable.d5,
        R.drawable.d6
    )

    private val _totalFp = MutableStateFlow(0)
    val totalFp: StateFlow<Int> = _totalFp

    private val _totalSp = MutableStateFlow(0)
    val totalSp: StateFlow<Int> = _totalSp

    var nbCastFP = 0
    var nbCastSP = 0
    var totalCast = 0


    fun rollDiceFP() {
        val index = (0..5).random()
        _dice.value = diceList[index]
        _totalFp.value += index + 1
        nbCastFP++
        totalCast++
    }

    fun rollDiceSP() {
        val index = (0..5).random()
        _dice.value = diceList[index]
        _totalSp.value += index + 1
        nbCastSP++
        totalCast++
    }

    fun resetAll(){
        _dice.value = R.drawable.d1
        _totalFp.value = 0
        _totalSp.value = 0
        nbCastFP = 0
        nbCastSP = 0
        totalCast = 0

    }

}