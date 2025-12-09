package app.habitao.ui.components.stats

import androidx.annotation.DrawableRes
import app.habitao.R

data class SpiritualLevel(
    val english: String,
    val chinese: String,
    val pinyin: String,
    @DrawableRes val imgId: Int,
    val karmaThresh: Int
)

val spiritualLevels = listOf(
    SpiritualLevel("Apprentice", "学徒", "Xuétú", R.drawable.bonsai1, 0),
    SpiritualLevel("Practitioner", "修行者", "Xiūxíng zhě", R.drawable.bonsai1, 500),
    SpiritualLevel("Self-Disciplined One", "自律者", "Zìlǜ zhě", R.drawable.bonsai2, 1500),
    SpiritualLevel("Adept of Habit", "习惯达人", "Xíguàn dárén", R.drawable.bonsai2, 5000),
    SpiritualLevel("Tranquil Mind", "心如止水", "Xīn rú zhǐ shuǐ", R.drawable.bonsai3, 15000),
    SpiritualLevel("Guardian of the Way", "道守", "Dào shǒu", R.drawable.bonsai3, 50000)
)
