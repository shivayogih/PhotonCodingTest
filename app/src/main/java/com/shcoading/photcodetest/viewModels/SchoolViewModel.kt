package com.shcoading.photcodetest.viewModels

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shcoading.photcodetest.api_services.Resource
import com.shcoading.photcodetest.api_services.repository.APIRepository
import com.shcoading.photcodetest.app.Connectivity
import com.shcoading.photcodetest.app.data.PhotonGoDB
import com.shcoading.photcodetest.app.data.PhotonGoDao
import com.shcoading.photcodetest.dataModels.SchoolListResponseItem

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject


@HiltViewModel
class SchoolViewModel @Inject constructor(
    private val photonGoDao: PhotonGoDao,
    private val repository: APIRepository,
    private val photonGoDB: PhotonGoDB?
) : ViewModel() {


    val schoolListResponse: MutableLiveData<Resource<List<SchoolListResponseItem>>> =
        MutableLiveData()

    val school: MutableLiveData<SchoolListResponseItem> = MutableLiveData()

    //=================Start of Check List Details Response================================
    fun getSchoolList(context: Context) = viewModelScope.launch {
        getCheckListDetailsAPICall(context)
    }

    private suspend fun getCheckListDetailsAPICall(
        context: Context
    ) {
        schoolListResponse.postValue(Resource.Loading())
        if (Connectivity.isConnected(context)) {
            try {
                val response = repository.getAllSchoolList()
                schoolListResponse.postValue(handleCheckListDetailsResponse(response))
            } catch (t: Throwable) {

                println(t)
                when (t) {
                    is IOException -> schoolListResponse.postValue(
                        Resource.Error(
                            "Netwok Issue"
                        )
                    )
                    else -> {
                        schoolListResponse.postValue(Resource.Error("Data Parsing Error"))
                    }
                }
            }
        } else {
            schoolListResponse.postValue(Resource.NoNetworkConnection())
        }
    }

    private fun handleCheckListDetailsResponse(response: Response<List<SchoolListResponseItem>>): Resource<List<SchoolListResponseItem>>? {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }
}