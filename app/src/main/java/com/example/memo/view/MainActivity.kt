package com.example.memo.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.memo.room.DataBase
import com.example.memo.R
import com.example.memo.viewmodel.MainViewModel
import com.example.memo.databinding.ActivityMainBinding
import com.example.memo.`fun`.startActivity

class MainActivity : AppCompatActivity() {

    lateinit var mBinding: ActivityMainBinding
    lateinit var mViewModel: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        mBinding.viewModel = mViewModel
        mBinding.lifecycleOwner = this
        mBinding.executePendingBindings()

        mViewModel.memoDb  = DataBase.getInstance(this)

        mViewModel.load()

        with(mViewModel) {
            btn.observe(this@MainActivity, Observer {
                startActivity(com.example.memo.view.AddActivity::class.java)
            })
        }
    }

    override fun onDestroy(){
        DataBase.destroyInstance()
        mViewModel.memoDb = null
        super.onDestroy()
    }
}
