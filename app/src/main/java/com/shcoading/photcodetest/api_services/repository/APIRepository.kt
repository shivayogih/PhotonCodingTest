package com.shcoading.photcodetest.api_services.repository

import com.shcoading.photcodetest.api_services.RetrofitInstance
import javax.inject.Inject

class APIRepository @Inject constructor() {
    suspend fun getAllSchoolList() = RetrofitInstance.api.getAllSchoolList()
}