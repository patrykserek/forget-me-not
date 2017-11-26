package com.keresmi.forgetmenot

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.keresmi.forgetmenot.categories.CategoriesFragment
import kotlinx.android.synthetic.main.activity_main.*
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        switchFragment(CategoriesFragment())
    }

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    private fun switchFragment(fragment: Fragment) {
        val ft = supportFragmentManager.beginTransaction()
        ft.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
        ft.replace(R.id.fragment_container, fragment)
        ft.commit()
    }
}
