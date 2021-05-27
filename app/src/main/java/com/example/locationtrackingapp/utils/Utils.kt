package com.example.locationtrackingapp.utils

import android.content.Context
import android.location.Location
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.content.edit
import com.example.locationtrackingapp.R
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
fun Location?.toText(): String {
    val currentTime = LocalDateTime.now()
    val formatter = DateTimeFormatter.ofPattern("HH:mm:ss.SSS")
    val formattedTime = currentTime.format(formatter)
    return if (this != null) {
        "($formattedTime - $latitude, $longitude)"
    } else {
        "Unknown location"
    }
}

// Объект для запроса пермишшенов на Location из Activity и Service.
internal object SharedPreferenceUtil {

    const val KEY_FOREGROUND_ENABLED = "tracking_foreground_location"

    fun getLocationTrackingPref(context: Context): Boolean =
        context.getSharedPreferences(context.getString(R.string.pref_file_key), Context.MODE_PRIVATE)
            .getBoolean(KEY_FOREGROUND_ENABLED, false)

    fun saveLocationTrackingPref(context: Context, requestingLocationUpdates: Boolean) {
        context.getSharedPreferences(context.getString(R.string.pref_file_key), Context.MODE_PRIVATE).edit {
            putBoolean(KEY_FOREGROUND_ENABLED, requestingLocationUpdates)
        }
    }
}