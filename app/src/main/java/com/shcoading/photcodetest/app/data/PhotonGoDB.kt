package com.shcoading.photcodetest.app.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.shcoading.photcodetest.app.ApplicationScope
import com.shcoading.photcodetest.dataModels.SchoolListResponseItem

import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject
import javax.inject.Provider


@Database(
    entities = [SchoolListResponseItem::class],
    version = 1,
    exportSchema = false
)


abstract class PhotonGoDB : RoomDatabase() {
    abstract fun taskDao(): PhotonGoDao

    class Callback @Inject constructor(
        private val database: Provider<PhotonGoDB>,
        @ApplicationScope private val applicationScope: CoroutineScope
    ) : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
        }
    }
}