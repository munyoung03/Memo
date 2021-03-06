package com.example.memo.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.memo.room.DataBase
import com.example.memo.widget.SingleLiveEvent
import java.time.LocalDate

class ContentViewModel: ViewModel() {

    var memoDb : DataBase? = null
    val title_id = MutableLiveData<String>()
    val contents_id = MutableLiveData<String>()
    val btn_up = SingleLiveEvent<Unit>()
    val btn_del = SingleLiveEvent<Unit>()

    val onlyDate: LocalDate = LocalDate.now()

    fun init(title : String, contents : String) {
        title_id.value = title
        contents_id.value = contents
    }

    fun del(id : Long){
        memoDb?.dao()?.delete(id)
        }

    fun up(id : Long){
        memoDb?.dao()?.update(title_id.value.toString(), onlyDate.toString(), contents_id.value.toString(), id)
    }

    fun upbtnClick(){
        btn_up.call()
    }

    fun delbtnClick(){
        btn_del.call()
    }
}