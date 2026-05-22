package com.vrushabhgaikar.vibeplayer.data.local


import android.content.ContentUris
import android.content.Context
import android.provider.MediaStore
import com.vrushabhgaikar.vibeplayer.domain.model.MediaItemModel
import com.vrushabhgaikar.vibeplayer.domain.model.MediaType
import com.vrushabhgaikar.vibeplayer.domain.model.SourceType

class MediaStoreReader(
    private val context: Context
) {

    fun getLocalAudio(): List<MediaItemModel> {

        val audioList = mutableListOf<MediaItemModel>()

        val collection =
            MediaStore.Audio.Media.EXTERNAL_CONTENT_URI

        val projection = arrayOf(
            MediaStore.Audio.Media._ID,
            MediaStore.Audio.Media.TITLE,
            MediaStore.Audio.Media.ARTIST,
            MediaStore.Audio.Media.DURATION
        )

        val selection =
            "${MediaStore.Audio.Media.IS_MUSIC} != 0"

        val sortOrder =
            "${MediaStore.Audio.Media.DATE_ADDED} DESC"

        val query = context.contentResolver.query(
            collection,
            projection,
            selection,
            null,
            sortOrder
        )

        query?.use { cursor ->

            val idColumn =
                cursor.getColumnIndexOrThrow(MediaStore.Audio.Media._ID)

            val titleColumn =
                cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE)

            val artistColumn =
                cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST)

            val durationColumn =
                cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION)

            while (cursor.moveToNext()) {

                val id = cursor.getLong(idColumn)

                val title =
                    cursor.getString(titleColumn) ?: "Unknown"

                val artist =
                    cursor.getString(artistColumn) ?: "Unknown"

                val duration =
                    cursor.getLong(durationColumn)

                if (duration > 30000L && title.isNotBlank()) {

                    val uri = ContentUris.withAppendedId(
                        MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                        id
                    )

                    audioList.add(
                        MediaItemModel(
                            id = id,
                            title = title,
                            artist = artist,
                            uri = uri,
                            duration = duration,
                            mediaType = MediaType.AUDIO,
                            sourceType = SourceType.OFFLINE,
                            thumbnailUri = uri,


                            )
                    )
                }
            }
        }

        return audioList
    }

    fun getLocalVideos(): List<MediaItemModel> {

        val videoList = mutableListOf<MediaItemModel>()

        val collection =
            MediaStore.Video.Media.EXTERNAL_CONTENT_URI

        val projection = arrayOf(
            MediaStore.Video.Media._ID,
            MediaStore.Video.Media.TITLE,
            MediaStore.Video.Media.DURATION
        )

        val sortOrder =
            "${MediaStore.Video.Media.DATE_ADDED} DESC"

        val query = context.contentResolver.query(
            collection,
            projection,
            null,
            null,
            sortOrder
        )

        query?.use { cursor ->

            val idColumn =
                cursor.getColumnIndexOrThrow(MediaStore.Video.Media._ID)

            val titleColumn =
                cursor.getColumnIndexOrThrow(MediaStore.Video.Media.TITLE)

            val durationColumn =
                cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DURATION)

            while (cursor.moveToNext()) {

                val id = cursor.getLong(idColumn)

                val title =
                    cursor.getString(titleColumn) ?: "Unknown"

                val duration =
                    cursor.getLong(durationColumn)

                if (title.isNotBlank()) {

                    val uri = ContentUris.withAppendedId(
                        MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
                        id
                    )

                    videoList.add(
                        MediaItemModel(
                            id = id,
                            title = title,
                            artist = "Video",
                            uri = uri,
                            duration = duration,
                            mediaType = MediaType.VIDEO,
                            thumbnailUri = uri
                        )
                    )
                }
            }
        }

        return videoList
    }
}