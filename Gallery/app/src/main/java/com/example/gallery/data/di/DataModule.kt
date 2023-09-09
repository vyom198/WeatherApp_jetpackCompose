package com.example.gallery.data.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.gallery.data.db.CachingDatabase
import com.example.gallery.data.db.GallleryDatabase
import com.example.gallery.data.db.MediaDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {


    @Provides
    @Singleton
    fun provideMiniGalleryDatabase(
     @ApplicationContext context: Context
    ): GallleryDatabase{
        return Room.databaseBuilder(context,GallleryDatabase::class.java,"mini_gallery_db")
            .fallbackToDestructiveMigration()
            .build()

    }

    @Provides
    fun provideMediadataSource(@ApplicationContext context: Context):MediaDataSource{
        return MediaDataSource(context)
    }
 @Provides
 fun provideCachingDb (db : GallleryDatabase):CachingDatabase{
     return CachingDatabase(db)
 }

}