package com.keresmi.forgetmenot

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.keresmi.forgetmenot.categories.CategoriesFragment


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        switchFragment(CategoriesFragment())
    }

    private fun switchFragment(fragment: Fragment) {
        val ft = supportFragmentManager.beginTransaction()
        ft.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
        ft.replace(R.id.fragment_container, fragment)
        ft.commit()
    }
}
