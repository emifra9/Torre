package com.emifra9.torre.repo

import com.emifra9.torre.data.User
import kotlinx.coroutines.flow.Flow

interface TorreRepository{

    fun getUser(userId: String): Flow<User>
    suspend fun  refreshUser(userId: String)
}