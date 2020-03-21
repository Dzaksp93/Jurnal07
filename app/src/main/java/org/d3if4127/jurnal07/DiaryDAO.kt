package org.d3if4127.jurnal07

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DiaryDAO {

    @Insert
    fun insert(diary: Diary)

    @Query("DELETE FROM DiaryS")
    fun clear()

    @Query("SELECT * FROM DiaryS")
    fun  get():LiveData<List<Diary>>

}