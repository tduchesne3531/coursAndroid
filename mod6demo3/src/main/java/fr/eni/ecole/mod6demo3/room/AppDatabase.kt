package fr.eni.ecole.mod6demo3.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import fr.eni.ecole.mod6demo3.bo.Music
import fr.eni.ecole.mod6demo3.dao.MusicDao

@Database(entities = [Music::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun musicDao() : MusicDao

    companion object{
        private var INSTANCE : AppDatabase? = null

        fun getInstance(context: Context) : AppDatabase {
            var instance = INSTANCE

            if(instance == null){
                instance = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "NotreMusique.db"

                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
            }
            return instance
        }
    }
}