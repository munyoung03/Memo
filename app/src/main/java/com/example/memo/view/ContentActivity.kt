package com.example.memo.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.memo.room.DataBase
import com.example.memo.R
import com.example.memo.`fun`.startActivity
import com.example.memo.viewmodel.ContentViewModel
import com.example.memo.databinding.ActivityContentBinding

class ContentActivity : AppCompatActivity() {

    lateinit var mBinding: ActivityContentBinding
    lateinit var mViewmodel: ContentViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_content)
        mViewmodel = ViewModelProvider(this)[ContentViewModel::class.java]
        mBinding.viewModel = mViewmodel
        mBinding.lifecycleOwner = this
        mBinding.executePendingBindings()

        mViewmodel.memoDb = DataBase.getInstance(this)

        val contents = intent.getStringExtra("content")
        val title = intent.getStringExtra("title")
        val id = intent.getLongExtra("id", 0)

        mViewmodel.init(title, contents)

        with(mViewmodel) {
            btn_del.observe(this@ContentActivity, Observer {
                del(id)
                startActivity(MainActivity::class.java)
            })
        }

        with(mViewmodel) {
            btn_up.observe(this@ContentActivity, Observer {
                up(id)
                startActivity(MainActivity::class.java)
            })
        }

    }

    override fun onDestroy(){
        DataBase.destroyInstance()
        mViewmodel.memoDb = null
        super.onDestroy()
    }
}
