package org.d3if4127.jurnal07

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.Transformations
import kotlinx.coroutines.*

class DiaryViewModel(val database: DiaryDAO, application: Application): AndroidViewModel(application) {

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    var diarys = database.get()

    fun onClickTambah(catat:String){
        uiScope.launch {
            val diary=Diary(0,catat)
            insert(diary)
        }
    }

    fun onClear() {
        uiScope.launch {
            clear()
            diarys = database.get()
        }
    }
    suspend fun clear() {
        withContext(Dispatchers.IO) {
            database.clear()
        }
    }


    private suspend fun insert(diary: Diary) {
        withContext(Dispatchers.IO) {

            database.insert(diary)
        }
    }



}