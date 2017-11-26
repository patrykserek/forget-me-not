package com.keresmi.forgetmenot

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.os.Handler
import android.content.Intent

class SplashActivity : AppCompatActivity() {

    var SPLASH_TIME_OUT = 2000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        requestWindowFeature(Window.FEATURE_NO_TITLE);

//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash)
        Handler().postDelayed( object:Runnable{
            public override fun run() {
                val home = Intent(this@SplashActivity,MainActivity::class.java)
                startActivity(home)
                finish()
            }
        }, SPLASH_TIME_OUT.toLong() )
    }
}
