package com.emifra9.torre.data

import com.google.gson.annotations.SerializedName

data class PersonJSONModel(
    @SerializedName("person")
    var person: User

)
