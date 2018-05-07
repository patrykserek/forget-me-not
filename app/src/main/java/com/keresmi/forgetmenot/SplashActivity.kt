package com.keresmi.forgetmenot

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.keresmi.forgetmenot.categories.CategoryActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = Intent(this, CategoryActivity::class.java)
        startActivity(intent)
        finish()
    }
}