package app.habitao

import android.app.Application
import com.google.firebase.FirebaseApp

class HabitaoApp : Application() {
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
    }
}