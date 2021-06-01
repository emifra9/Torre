package com.emifra9.torre.utils

import java.util.*

fun String.toCamelCase(): String {

    val words = this.lowercase(Locale.getDefault()).split(" ").toMutableList()

    var output = ""

    for(word in words){
        output += word.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() } +" "
    }

    output = output.trim()

    return output
}