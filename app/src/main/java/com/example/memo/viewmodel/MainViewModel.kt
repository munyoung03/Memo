package com.example.memo.viewmodel

import android.app.Activity
import android.widget.Toast
import androidx.core.app.ActivityCompat.finishAffinity
import androidx.lifecycle.ViewModel
import com.example.memo.room.DataBase
import com.example.memo.widget.MemoAdapter
import com.example.memo.widget.SingleLiveEvent
import com.example.memo.room.memo

class MainViewModel : ViewModel() {

    val btn = SingleLiveEvent<Unit>()

    private val memoList = ArrayList<memo>()

    val mAdapter = MemoAdapter()

    init { mAdapter.setList(memoList) }

    var memoDb : DataBase? = null

    fun load() {
        memoList.clear()
        memoList.addAll(memoDb?.dao()?.getAll()!!)
        mAdapter.notifyDataSetChanged()
    }

    fun btnClick(){
        btn.call()
    }


}

