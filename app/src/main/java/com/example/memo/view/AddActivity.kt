package com.example.memo.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.memo.*
import com.example.memo.room.DataBase
import com.example.memo.viewmodel.AddViewModel
import com.example.memo.`fun`.startActivity
import com.example.memo.databinding.ActivityAddBinding

class AddActivity : AppCompatActivity() {

    lateinit var mBinding: ActivityAddBinding
    lateinit var mViewModel: AddViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_add)
        mViewModel = ViewModelProvider(this)[AddViewModel::class.java]
        mBinding.viewModel = mViewModel
        mBinding.lifecycleOwner = this
        mBinding.executePendingBindings()

        mViewModel.memoDb = DataBase.getInstance(this)

        with(mViewModel) {
            btn.observe(this@AddActivity, Observer {
                mViewModel.addData()
                startActivity(MainActivity::class.java)
            })
        }
    }

    override fun onDestroy() {
        DataBase.destroyInstance()
        super.onDestroy()
    }
}
