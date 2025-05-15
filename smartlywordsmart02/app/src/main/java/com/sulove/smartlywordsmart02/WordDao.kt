
package com.sulove.smartlywordsmart02.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import com.sulove.smartlywordsmart02.Word

@Dao
interface WordDao {

    @Insert(onConflict = IGNORE)
    fun insert(word: Word)
}