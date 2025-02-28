package com.tori.flo_clone

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.tori.flo_clone.data.entities.Album
import com.tori.flo_clone.data.entities.Like

@Dao
interface AlbumDao {
    @Insert
    fun insert(album: Album)

    @Update
    fun update(album: Album)

    @Delete
    fun delete(album: Album)

    @Query("SELECT * FROM AlbumTable")
    fun getAlbums(): List<Album>

    @Insert
    fun likeAlbum(like : Like)

//    @Query("UPDATE AlbumTable SET isLike = :isLike WHERE id = :id")
//    fun updateIsLikeById(isLike: Boolean, id: Int)

    @Query("SELECT id FROM LikeTable WHERE userId = :userId AND albumId = :albumId;")
    fun isLikedAlbum(userId:Int, albumId:Int) : Int?

    @Query("DELETE FROM LikeTable WHERE userId = :userId AND albumId = :albumId;")
    fun disLikedAlbum(userId:Int, albumId:Int)

    @Query("SELECT AT.* FROM LikeTable as LT LEFT JOIN AlbumTable as AT ON LT.albumId = AT.id WHERE LT.userId = :userId")
    fun getLikedAlbums(userId: Int) : List<Album>

}