package com.example.memo.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.memo.room.DataBase
import com.example.memo.room.memo
import com.example.memo.widget.SingleLiveEvent
import java.time.LocalDate

class AddViewModel : ViewModel() {

    var memoDb: DataBase? = null
    val onlyDate: LocalDate = LocalDate.now()
    val btn = SingleLiveEvent<Unit>()
    val title = MutableLiveData<String>()
    val contents = MutableLiveData<String>()

    fun addData(){
        val newMemo = memo(
            0,
            title.value.toString(),
            onlyDate.toString(),
            contents.value.toString()
        )
        memoDb?.dao()?.insert(newMemo)
    }

    fun btnClick(){
        btn.call()
    }

}