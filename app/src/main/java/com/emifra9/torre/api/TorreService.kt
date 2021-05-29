package com.emifra9.torre.api

import com.emifra9.torre.data.PersonJSONModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface TorreService {

    @GET("{publicId}")
    suspend fun getUser( @Path("publicId")  publicId :String): Response<PersonJSONModel>

}