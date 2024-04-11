package com.example.herodiary.database.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.herodiary.R
import com.example.herodiary.database.room.dao.BoughtDao
import com.example.herodiary.database.room.dao.ConfigDao
import com.example.herodiary.database.room.dao.NoteDao
import com.example.herodiary.database.room.dao.ShopDao
import com.example.herodiary.database.room.dao.SystemTaskDao
import com.example.herodiary.database.room.dao.TaskDao
import com.example.herodiary.database.room.dao.UserDao
import com.example.herodiary.database.room.models.BoughtRoomModel
import com.example.herodiary.database.room.models.ConfigRoomModel
import com.example.herodiary.database.room.models.NoteRoomModel
import com.example.herodiary.database.room.models.ShopRoomModel
import com.example.herodiary.database.room.models.SystemTaskRoomModel
import com.example.herodiary.database.room.models.TaskRoomModel
import com.example.herodiary.database.room.models.UserRoomModel


@Database(
    entities = [
        UserRoomModel::class,
        TaskRoomModel::class,
        ShopRoomModel::class,
        BoughtRoomModel::class,
        ConfigRoomModel::class,
        NoteRoomModel::class,
        SystemTaskRoomModel::class
    ], version = 1
)
abstract class HeroBase : RoomDatabase() {
    abstract fun getUserDao(): UserDao
    abstract fun getTaskDao(): TaskDao
    abstract fun getShopDao(): ShopDao
    abstract fun getConfigDao(): ConfigDao
    abstract fun getBoughtDao(): BoughtDao
    abstract fun getNoteDao(): NoteDao
    abstract fun getSystemTaskDao(): SystemTaskDao

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
                db.execSQL("insert into systemTask values (1, 'Keep online 1 day', 'Keep online 1 day', false, 100, 50, 'LOGIN', 1);")
                db.execSQL("insert into systemTask values (2, 'Keep online 2 days', 'Keep online 2 day', false, 150, 70, 'LOGIN', 2);")
                db.execSQL("insert into systemTask values (3, 'Keep online 3 days', 'Keep online 3 day', false, 200, 100, 'LOGIN', 3);")
            }

            override fun onOpen(db: SupportSQLiteDatabase) {
                // do something every time database is open
            }
        }
    }
}