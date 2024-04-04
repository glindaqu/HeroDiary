package com.example.herodiary.database.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.herodiary.R
import com.example.herodiary.database.room.dao.ConfigDao
import com.example.herodiary.database.room.dao.ShopDao
import com.example.herodiary.database.room.dao.TaskDao
import com.example.herodiary.database.room.dao.UserDao
import com.example.herodiary.database.room.models.BoughtRoomModel
import com.example.herodiary.database.room.models.ConfigRoomModel
import com.example.herodiary.database.room.models.ShopRoomModel
import com.example.herodiary.database.room.models.TaskRoomModel
import com.example.herodiary.database.room.models.UserRoomModel


@Database(
    entities = [
        UserRoomModel::class,
        TaskRoomModel::class,
        ShopRoomModel::class,
        BoughtRoomModel::class,
        ConfigRoomModel::class
    ], version = 1
)
abstract class HeroBase : RoomDatabase() {
    abstract fun getUserDao(): UserDao
    abstract fun getTaskDao(): TaskDao
    abstract fun getShopDao(): ShopDao
    abstract fun getConfigDao(): ConfigDao

    companion object {
        fun getDatabase(context: Context): HeroBase {
            return Room.databaseBuilder(
                context.applicationContext,
                HeroBase::class.java,
                "HeroDiary.db"
            ).addCallback(rdc)
                .build()
        }

        private var rdc: Callback = object : Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                db.query("INSERT INTO shop VALUES (1, 100, ${R.drawable.ava1});")
            }

            override fun onOpen(db: SupportSQLiteDatabase) {
                // do something every time database is open
            }
        }
    }
}