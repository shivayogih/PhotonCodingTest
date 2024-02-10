package com.shcoading.photcodetest.app.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.shcoading.photcodetest.dataModels.SchoolListResponseItem


@Dao
interface PhotonGoDao {

    @Query("SELECT * FROM SchoolListResponseItem")
    fun getAllSchool(): LiveData<SchoolListResponseItem>
}