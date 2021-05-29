package com.emifra9.torre.repo


import com.emifra9.torre.api.TorreService
import com.emifra9.torre.data.TorreDao
import com.emifra9.torre.data.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withTimeout
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TorreRepositoryImpl @Inject constructor(
    private val torreService: TorreService,
    private val torreDao: TorreDao
):TorreRepository{



      override fun getUser(userId: String): Flow<User> {
          return torreDao.load(userId)
    }

     override suspend fun refreshUser(userId: String) {
         try {
             val result = withTimeout(5_000) {
                 torreService.getUser(userId)
             }
             torreDao.save(result)
         } catch (error: Throwable) {
             throw Error("Unable to refresh title", error)
         }

    }



}