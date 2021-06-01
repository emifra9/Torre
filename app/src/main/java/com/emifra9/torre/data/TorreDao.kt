package com.emifra9.torre.data


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TorreDao {


    @Query("SELECT * FROM user WHERE publicId like :userId")
    fun load(userId: String): Flow<User>

    @Query("SELECT * FROM user ")
    fun loadAllUsers(): Flow<List<User>>

    @Insert( onConflict = OnConflictStrategy.REPLACE)
    suspend  fun save(user: User)

}