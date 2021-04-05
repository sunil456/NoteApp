package com.sunil.noteapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sunil.noteapp.dao.NoteDao
import com.sunil.noteapp.entities.Notes

@Database(entities = [Notes::class], version = 1, exportSchema = false)
abstract class NotesDatabase : RoomDatabase() {

    abstract fun noteDao():NoteDao

    companion object{
        var notesDatabase : NotesDatabase? = null

        @Synchronized
        fun getDatabase(context: Context): NotesDatabase {
            if (notesDatabase == null){
                notesDatabase = Room.databaseBuilder(
                    context,
                    NotesDatabase::class.java,
                    "notes.db"
                ).build()

            }
            return notesDatabase!!
        }
    }


}