package com.vrushabhgaikar.vibeplayer.data.dummy

import androidx.core.net.toUri
import com.vrushabhgaikar.vibeplayer.domain.model.MediaItemModel
import com.vrushabhgaikar.vibeplayer.domain.model.MediaType
import com.vrushabhgaikar.vibeplayer.domain.model.SourceType

val dummyMediaList: List<MediaItemModel> = listOf(

    MediaItemModel(
        id = 1,
        title = "Blinding Lights",
        artist = "The Weeknd",
        uri = "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3".toUri(),
        thumbnailUri = "https://picsum.photos/300/300?random=1".toUri(),
        duration = 240000,
        mediaType = MediaType.AUDIO,
        sourceType = SourceType.ONLINE
    ),

    MediaItemModel(
        id = 2,
        title = "Shape of You",
        artist = "Ed Sheeran",
        uri = "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-2.mp3".toUri(),
        thumbnailUri = "https://picsum.photos/300/300?random=2".toUri(),
        duration = 210000,
        mediaType = MediaType.AUDIO,
        sourceType = SourceType.ONLINE
    ),

    MediaItemModel(
        id = 3,
        title = "Kesariya",
        artist = "Arijit Singh",
        uri = "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-3.mp3".toUri(),
        thumbnailUri = "https://picsum.photos/300/300?random=3".toUri(),
        duration = 250000,
        mediaType = MediaType.AUDIO,
        sourceType = SourceType.ONLINE
    ),

    MediaItemModel(
        id = 4,
        title = "Believer",
        artist = "Imagine Dragons",
        uri = "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-4.mp3".toUri(),
        thumbnailUri = "https://picsum.photos/300/300?random=4".toUri(),
        duration = 204000,
        mediaType = MediaType.AUDIO,
        sourceType = SourceType.ONLINE
    ),

    MediaItemModel(
        id = 5,
        title = "Levitating",
        artist = "Dua Lipa",
        uri = "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-5.mp3".toUri(),
        thumbnailUri = "https://picsum.photos/300/300?random=5".toUri(),
        duration = 233000,
        mediaType = MediaType.AUDIO,
        sourceType = SourceType.ONLINE
    ),

    MediaItemModel(
        id = 6,
        title = "Nature Video",
        artist = "Video Media",
        uri = "https://samplelib.com/lib/preview/mp4/sample-5s.mp4".toUri(),
        thumbnailUri = "https://picsum.photos/300/300?random=6".toUri(),
        duration = 5000,
        mediaType = MediaType.VIDEO,
        sourceType = SourceType.ONLINE
    ),

    MediaItemModel(
        id = 7,
        title = "Travel Vlog",
        artist = "Video Media",
        uri = "https://samplelib.com/lib/preview/mp4/sample-10s.mp4".toUri(),
        thumbnailUri = "https://picsum.photos/300/300?random=7".toUri(),
        duration = 10000,
        mediaType = MediaType.VIDEO,
        sourceType = SourceType.ONLINE
    ),

    MediaItemModel(
        id = 8,
        title = "Workout Mix",
        artist = "Alan Walker",
        uri = "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-6.mp3".toUri(),
        thumbnailUri = "https://picsum.photos/300/300?random=8".toUri(),
        duration = 280000,
        mediaType = MediaType.AUDIO,
        sourceType = SourceType.ONLINE
    ),

    MediaItemModel(
        id = 9,
        title = "Coding Beats",
        artist = "LoFi Studio",
        uri = "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-7.mp3".toUri(),
        thumbnailUri = "https://picsum.photos/300/300?random=9".toUri(),
        duration = 320000,
        mediaType = MediaType.AUDIO,
        sourceType = SourceType.ONLINE
    ),

    MediaItemModel(
        id = 10,
        title = "Movie Trailer",
        artist = "Video Media",
        uri = "https://samplelib.com/lib/preview/mp4/sample-15s.mp4".toUri(),
        thumbnailUri = "https://picsum.photos/300/300?random=10".toUri(),
        duration = 15000,
        mediaType = MediaType.VIDEO,
        sourceType = SourceType.ONLINE

    )
)