package com.emifra9.torre.api

import com.emifra9.torre.data.User
import retrofit2.http.GET
import retrofit2.http.Path

interface TorreService {

    @GET("{publicId}")
    suspend fun getUser( @Path("publicId")  publicId :String): User

}