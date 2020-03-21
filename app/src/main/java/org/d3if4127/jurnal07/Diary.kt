package org.d3if4127.jurnal07

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "DiaryS")
data class Diary (

    @PrimaryKey(autoGenerate = true)
    var id:Long = 0L,
    @ColumnInfo(name = "Diary")
    var diary:String,
    @ColumnInfo(name = "tanggal")
    val tanggal:Long=System.currentTimeMillis()

)

