package app.habitao.ui.components.habits

import android.os.Build
import androidx.annotation.RequiresApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.time.LocalDate

@Serializable
data class Habit  constructor(
    val id: Int = 0,
    val name: String = "",
    val description: String = "",
    val element: Element = Element.FIRE,
    var isPopular: Boolean = false,
    val importance: Int = 1,

    // ✔ DataStore serializacja (Kotlin Serialization)
    @Serializable(with = DateSerializer::class)
    val date: LocalDate = LocalDate.now(),

    var isCompleted: Boolean = false
) {
    // ✔ Ten empty constructor jest WYMAGANY przez Firestore
    // Firestore go używa do tworzenia obiektu przy deserializacji
    constructor() : this(
        id = 0,
        name = "",
        description = "",
        element = Element.FIRE,
        isPopular = false,
        importance = 1,
        date = LocalDate.now(),
        isCompleted = false
    )
}

@Serializable
enum class Element {
    FIRE, WATER, EARTH, AIR
}

object DateSerializer : KSerializer<LocalDate> {
    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("LocalDate", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: LocalDate) {
        encoder.encodeString(value.toString())
    }

    override fun deserialize(decoder: Decoder): LocalDate {
        return LocalDate.parse(decoder.decodeString())
    }
}
