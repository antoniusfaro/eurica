package com.bangkit.eurica.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface SavedListDao {
    @Insert
    fun addToSavedList(favoriteUser: SavedList)

    @Query("SELECT * FROM saved_list")
    fun getSavedList(): List<SavedList>

    @Query("DELETE FROM saved_list WHERE saved_list.id = :id")
    fun removeFromSavedList(id: Int): Int
}