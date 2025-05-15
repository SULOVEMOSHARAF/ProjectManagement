package com.sulove.smartlywordsmart.data;

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Database(entities = [Word::class], version = 1, exportSchema = false)
abstract class WordDatabase : RoomDatabase() {
    abstract fun wordDao(): WordDao

    companion object {
        @Volatile
        private var INSTANCE: WordDatabase? = null

        fun getDatabase(context: Context): WordDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WordDatabase::class.java,
                    "word_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}

suspend fun populateDatabaseWithWords(context: Context) {
    val db = WordDatabase.getDatabase(context)
    val wordDao = db.wordDao()

    val initialWords = listOf(
        Word(word = "abundance", meaning = "a very large quantity of something"),
        Word(word = "cacophony", meaning = "a harsh, discordant mixture of sounds"),
        Word(word = "ephemeral", meaning = "lasting for a very short time"),
        Word(word = "labyrinth", meaning = "a complicated irregular network of passages"),
        Word(word = "quintessential", meaning = "representing the most perfect or typical example of a quality or class"),
        Word(word = "serendipity", meaning = "the occurrence and development of events by chance in a happy or beneficial way")
        // Add more words here as needed
    )

    withContext(Dispatchers.IO) {
        initialWords.forEach { word ->
            wordDao.insert(word)
        }
    }
}
