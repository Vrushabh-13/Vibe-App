package com.vrushabhgaikar.vibeplayer.data.local.dao


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vrushabhgaikar.vibeplayer.data.local.entity.PlaylistEntity
import com.vrushabhgaikar.vibeplayer.data.local.entity.PlaylistSongCrossRef
import kotlinx.coroutines.flow.Flow

@Dao
interface PlaylistDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createPlaylist(
        playlist: PlaylistEntity
    )

    @Query("""
        SELECT * FROM playlists
    """)
    fun getPlaylists():
            Flow<List<PlaylistEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addSongToPlaylist(
        crossRef: PlaylistSongCrossRef
    )

    @Query("""
        DELETE FROM playlist_songs
        WHERE playlistName = :playlistName
    """)
    suspend fun clearPlaylistSongs(
        playlistName: String
    )

    @Query("""
        SELECT mediaId FROM playlist_songs
        WHERE playlistName = :playlistName
    """)
    suspend fun getPlaylistSongIds(
        playlistName: String
    ): List<Long>
}