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

    @Insert( onConflict = OnConflictStrategy.REPLACE)
    fun save(user: User)

}