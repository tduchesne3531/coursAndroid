package fr.eni.ecole.mod1demo1

//fun main() {
//    print("Hello World")
//
//    //Déclaration de variables
//
//    var name: String? = null
//
//    name = "Pouet"
//
//    val COFFEE: String
//
//    COFFEE = "What else ?"
//}

//fun main() {
//    var null1 : String? = null
//
//    var null2 = null
//
//    println(null1?.length ?: "Pas de taille")
//    println(null2)
//
//    var world = " World !"
//
//    val message by lazy { "Hello $world" }
//
//    println(message)
//
//    world = " man !"
//
//    println(message)
//}

//fun main() {
//
//    //Conditions
//    val AGE = 18
//
//    val message = if (AGE >= 18) {
//        "Majeur"
//    } else {
//        "Mineur"
//    }
//    println(message)
//
//    when (AGE) {
//        0, 1, 2 -> println("C'est un bébé !")
//        in 3..18 -> println("C'est un mineur !")
//        is Int -> println("C'est un majeur")
//        else -> println("Autres")
//    }
//}

//fun main() {
//
//    // Boucles
//    val powers = listOf("Vol", "Invisibilité", "Force", "Pyrokinésie")
//
//    for(p in powers){
//        println(p)
//    }
//
//    for(i in 0..<powers.size){
//        println(powers[i])
//    }
//
//    for(j in 10 downTo 1 step 2)
//        println(j)
//}

fun main() {
    // Fonctions

    val articles = List(30) { id ->
        "Article $id"
    }

    val colors = mutableListOf<String>()
    colors.add("cornflowerblue")
    colors += "yellow"
    colors += "yellow"
    colors += "blellow"
    colors += "red"

    var nbColor = colors.filter {
        it == "yellow"
    }.count()

    println(nbColor)

    fun String.sayHello(){
        println("Hello $this")
    }

    "Michel".sayHello()

}