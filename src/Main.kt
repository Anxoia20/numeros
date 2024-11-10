import java.io.File
const val BG_GREEN = "\u001B[42m"
const val BG_YELLOW = "\u001B[43m"
const val BLACK = "\u001B[30m"
const val RESET = "\u001B[0m"
const val BOLD = "\u001B[1m"
const val BG_RED = "\u001B[41m"
const val BG_WHITE = "\u001B[47m"

fun main(){
    var opcion= 0
    while (opcion!=3) {
        println("1. jugar")
        println("2. Ver traza de último intento")
        println("3. Salir")
        print("opción: ")
        opcion = readln().toInt()
        when (opcion) {
            1 -> {
                var intentos = 1
                var aciertos = 0
                var coincidencia = 0
                //crear numero aleatorio
                val aleatorio = mutableListOf(1, 2, 3, 4, 5, 6)
                aleatorio.shuffle()
                aleatorio.removeAt(5)
                aleatorio.removeAt(4)
                File("UltimaJugada.txt").writeText("${BG_WHITE}${BLACK}Número aleatorio :"+ aleatorio[0] + aleatorio[1] + aleatorio[2] + aleatorio[3]+"${RESET}\n")
                //numero usuario
                while (intentos != 11) {
                    var numero = mutableListOf<Int>()
                    println("Teclea un número de 4 cifras sin números repetidos")
                    var lista = readln()
                    for (i in lista)
                        numero.add(i.toString().toInt())
                    //compracion
                    aciertos = 0
                    coincidencia = 0
                    for (n in numero.indices) {
                        if (numero[n] == aleatorio[n]) {
                            aciertos++
                        } else if (aleatorio.contains(numero[n])) {
                            coincidencia++
                        }
                    }
                    println("${BG_GREEN}${BLACK}${BOLD}Aciertos: $aciertos ${RESET}")
                    println("${BG_YELLOW}${BLACK}${BOLD}Coincidencias: $coincidencia ${RESET}")

                    File("UltimaJugada.txt").appendText("${BG_WHITE}${BLACK}Intento $intentos: Número: $lista,${RESET}${BG_GREEN}${BLACK}Aciertos: $aciertos,${RESET}${BG_YELLOW}${BLACK} Coincidencias: $coincidencia${RESET}\n")

                    intentos++

                    if (numero == aleatorio) {
                        println("Lo as adivinado en el intento $intentos")
                        println("")
                        break
                    } else if (intentos == 11 && aciertos != 4) {
                        println("${BG_RED}${BLACK}${BOLD}Game Over el numero aleatorio era " + aleatorio[0] + aleatorio[1] + aleatorio[2] + aleatorio[3]+ "${RESET}")
                        println("")
                    }
                }
            }
            2 -> {
                if (File("UltimaJugada.txt").exists()){
                    println(File("UltimaJugada.txt").readText())
                }else{
                    println("${BG_RED}${BLACK}${BOLD}No hay intentos previos${RESET}")
                    println("")
                }
            }
            3 -> {
                println("${BOLD}Gracias por jugar")
            }
        }
    }
}