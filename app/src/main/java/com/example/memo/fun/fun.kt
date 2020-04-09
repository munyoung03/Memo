package com.example.memo.`fun`

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity

fun AppCompatActivity.startActivity(nextActivity: Class<*>) {
    startActivity(Intent(this, nextActivity))
    finish()
}