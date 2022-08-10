package br.com.zup.hellozupper.data.datasourse.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.zup.hellozupper.data.datasourse.local.dao.HelloZupperDAO
import br.com.zup.hellozupper.data.model.FeedEntity

@Database(entities = [FeedEntity::class], version = 1)

abstract class HelloZupperDatabase : RoomDatabase() {
    abstract fun helloZupperDao(): HelloZupperDAO

    companion object {
        @Volatile
        private var INSTANCE: HelloZupperDatabase? = null

        fun getDatabase(context: Context): HelloZupperDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    HelloZupperDatabase::class.java,
                    "hellozupper_database"
                ).fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}