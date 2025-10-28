package app.habitao.ui.components

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
data class Habit @RequiresApi(Build.VERSION_CODES.O) constructor(
    val id: Int,
    val name: String,
    val description: String,
    val element: Element,
    var isPopular: Boolean,
    val importance: Int = 1,
    //val userNote: String? = null,
    @Serializable(with = DateSerializer::class)
    val date: LocalDate,
    var isCompleted: Boolean = false
)

@Serializable
enum class Element {
    FIRE, WATER, EARTH, AIR
}

@RequiresApi(Build.VERSION_CODES.O)
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