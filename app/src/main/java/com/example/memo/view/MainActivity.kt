package com.example.memo.view

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
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
    private lateinit var backPressHandler: onBackPressHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        mBinding.viewModel = mViewModel
        mBinding.lifecycleOwner = this
        mBinding.executePendingBindings()

        mViewModel.memoDb  = DataBase.getInstance(this)

        mViewModel.load()

        backPressHandler = onBackPressHandler(this@MainActivity)

        with(mViewModel) {
            btn.observe(this@MainActivity, Observer {
                startActivity(AddActivity::class.java)
            })
        }
    }


    override fun onDestroy(){
        DataBase.destroyInstance()
        mViewModel.memoDb = null
        super.onDestroy()
        finish()
    }

    override fun onBackPressed() {
        backPressHandler.onBackPressed()
    }

    inner class onBackPressHandler(var activity : Activity){
        private var backPressHandler: Long = 0

        fun onBackPressed(){
            if(System.currentTimeMillis() > backPressHandler+2000){
                backPressHandler = System.currentTimeMillis()
                Toast.makeText(activity,"한번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT).show()
                return
            }
            else{
                ActivityCompat.finishAffinity(activity)
            }
        }
    }
}
