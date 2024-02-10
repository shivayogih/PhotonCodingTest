package com.shcoading.photcodetest.app

import com.google.gson.Gson

interface JSONConvertable {
    fun toJSON(): String = Gson().toJson(this)
}

inline fun <reified T: JSONConvertable> String.toObject(): T = Gson().fromJson(this, T::class.java)

inline fun <reified T: Any> String.toKotlinObject(): T =
    Gson().fromJson(this, T::class.java)