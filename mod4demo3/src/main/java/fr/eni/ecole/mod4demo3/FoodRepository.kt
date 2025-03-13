package fr.eni.ecole.mod4demo3

class FoodRepository {

    private val foods = listOf(
        "Burger",
        "Pizza",
        "Tartiflette",
        "No Vegetables",
        "Burger aux lasagnes"
    )

    fun getFoods() : List<String> {
        return foods
    }
}