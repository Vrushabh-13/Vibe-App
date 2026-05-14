package com.vrushabhgaikar.vibeplayer.utils


import com.vrushabhgaikar.vibeplayer.R
import com.vrushabhgaikar.vibeplayer.domain.model.PlaceholderType

object AppUtils{
    fun getPlaceHolder(placeholderType: PlaceholderType): Int {
        if (placeholderType == PlaceholderType.VIDEO) {
            return R.drawable.img_placeholder_video
        }
         if (placeholderType == PlaceholderType.AUDIO) {
            return R.drawable.img_placeholder_song
        }
        if(placeholderType == PlaceholderType.PROFILE){
            return R.drawable.ic_profile
        }
        return R.drawable.img_music_thumb
    }
}