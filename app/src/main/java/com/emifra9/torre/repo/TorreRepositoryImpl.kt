package com.emifra9.torre.repo


import com.emifra9.torre.api.TorreService
import com.emifra9.torre.data.TorreDao
import com.emifra9.torre.data.User
import com.emifra9.torre.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TorreRepositoryImpl @Inject constructor(
    private val torreService: TorreService,
    private val torreDao: TorreDao
):TorreRepository{



    override  fun getUser(userId: String): Flow<User> {
          return torreDao.load(userId)
    }

    override fun getAllUsers():  Flow<List<User>> {
        return torreDao.loadAllUsers()
    }


     override suspend fun  getUserApi(userId:String): Resource<User> {
        Resource.loading( null)
        return try {
            val result = torreService.getUser(userId)
            if (result.isSuccessful && result.body()!!.person.name.isNotEmpty()) {
                torreDao.save(result.body()!!.person)
                return Resource.success(result.body()!!.person)
            }else{
                Resource.error("User not found", null)
            }
        } catch (e: Throwable) {
            Resource.error("Unknown Error", null)
        }
    }



}