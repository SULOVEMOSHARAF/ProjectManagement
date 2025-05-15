package com.sulove.smartlywordsmart02.data

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.sulove.smartlywordsmart02.Word

class WordDatabase(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "word_database"
        private const val TABLE_WORDS = "words"
        private const val KEY_ID = "id"
        private const val KEY_WORD = "word"
        private const val KEY_MEANING = "meaning"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = ("CREATE TABLE $TABLE_WORDS($KEY_ID INTEGER PRIMARY KEY,$KEY_WORD TEXT,$KEY_MEANING TEXT)")
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_WORDS")
        onCreate(db)
    }

    fun addWord(word: Word) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(KEY_WORD, word.word)
        values.put(KEY_MEANING, word.meaning)
        db.insert(TABLE_WORDS, null, values)
        db.close()
    }

    fun getAllWords(): ArrayList<Word> {
        val wordsList = ArrayList<Word>()
        val selectQuery = "SELECT * FROM $TABLE_WORDS"
        val db = this.readableDatabase
        val cursor: Cursor? = db.rawQuery(selectQuery, null)
        cursor?.let {
            if (cursor.moveToFirst()) {
                do {
                    val word = Word(
                        cursor.getString(cursor.getColumnIndex(KEY_WORD)),
                        cursor.getString(cursor.getColumnIndex(KEY_MEANING))
                    )
                    wordsList.add(word)
                } while (cursor.moveToNext())
            }
            cursor.close()
        }
        db.close()
        return wordsList
    }
}
