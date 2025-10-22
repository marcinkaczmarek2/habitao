package app.habitao.ui.components

import java.time.LocalDate

data class Habit(
    val id: Int,
    val name: String,
    val description: String,
    val element: Element,
    var isPopular: Boolean,
    val importance: Int = 1,
    //val userNote: String? = null,
    val date: LocalDate,
    var isCompleted: Boolean = false
)

enum class Element {
    FIRE, WATER, EARTH, AIR
}