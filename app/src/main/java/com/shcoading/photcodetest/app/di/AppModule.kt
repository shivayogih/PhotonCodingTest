package com.shcoading.photcodetest.app


import com.shcoading.photcodetest.app.data.PhotonGoDB
import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Qualifier
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(app: Application, callback: PhotonGoDB.Callback) =
        Room.databaseBuilder(app, PhotonGoDB::class.java, "photon")
            .fallbackToDestructiveMigration()
            .addCallback(callback)
            .build()

    @Provides
    fun provideTaskDao(db: PhotonGoDB) = db.taskDao()

    @ApplicationScope
    @Provides
    @Singleton
    fun provideApplicationScope() = CoroutineScope(SupervisorJob())


}

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class ApplicationScope