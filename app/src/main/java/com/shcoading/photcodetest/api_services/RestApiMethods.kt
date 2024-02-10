package com.shcoading.photcodetest.api_services

import com.shcoading.photcodetest.dataModels.SchoolListResponseItem
import retrofit2.Response
import retrofit2.http.GET

interface RestApiMethods {

    @GET("/resource/s3k6-pzi2.json")
    suspend fun getAllSchoolList(): Response<List<SchoolListResponseItem>>
}