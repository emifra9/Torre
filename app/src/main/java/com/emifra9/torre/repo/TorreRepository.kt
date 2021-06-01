package com.emifra9.torre.repo

import com.emifra9.torre.data.User
import com.emifra9.torre.utils.Resource
import kotlinx.coroutines.flow.Flow

interface TorreRepository{

    fun getUser(userId: String): Flow<User>

    fun getAllUsers(): Flow<List<User>>

    suspend fun  getUserApi(userId: String): Resource<User>
}