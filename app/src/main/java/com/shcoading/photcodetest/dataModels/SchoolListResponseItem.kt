package com.shcoading.photcodetest.dataModels

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.shcoading.photcodetest.app.JSONConvertable
import org.jetbrains.annotations.NotNull

@Entity
data class SchoolListResponseItem(
    @PrimaryKey
    val id: Int? = null,
    val dbn: String? = null,
    val school_email: String? = "",
    val school_name: String? = "",
    val school_sports: String? = "",
    val overview_paragraph: String? = "",
    val location: String? = "",
    val phone_number: String? = "",
    val website: String? = ""
) : JSONConvertable