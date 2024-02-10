package com.shcoading.photcodetest.api_services


sealed class Resource<T>(
    val data: T? = null,
    val message: String? = ""
) {
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)
    class Loading<T> : Resource<T>()
    class NoNetworkConnection<T> : Resource<T>()

}