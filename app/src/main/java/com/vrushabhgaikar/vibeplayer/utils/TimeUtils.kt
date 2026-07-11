package com.vrushabhgaikar.vibeplayer.utils

object TimeUtils {

    fun formatDuration(duration: Long): String {
        val totalSeconds = duration / 1000
        val minutes = totalSeconds / 60
        val seconds = totalSeconds % 60

        return String.format("%02d:%02d", minutes, seconds)
    }
}

//object TimeUtils {
//
//    fun formatDuration(duration: Long): String {
//
//        if (duration <= 0L) {
//            return "--:--"
//        }
//
//        val totalSeconds = duration / 1000
//        val minutes = totalSeconds / 60
//        val seconds = totalSeconds % 60
//
//        return String.format("%02d:%02d", minutes, seconds)
//    }
//}