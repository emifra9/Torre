package com.emifra9.torre.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(@PrimaryKey val publicId:String, val name:String, val summaryOfBio:String, val picture:String)
