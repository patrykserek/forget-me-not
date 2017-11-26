package com.keresmi.forgetmenot

import android.app.Application
import com.crashlytics.android.Crashlytics
import io.fabric.sdk.android.Fabric
import uk.co.chrisjenx.calligraphy.CalligraphyConfig

/**
 * Created by keresmi.
 * https://github.com/keresmi
 */

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Fabric.with(this, Crashlytics())
        initFont()
    }

    private fun initFont() = CalligraphyConfig.initDefault(CalligraphyConfig.Builder()
            .setDefaultFontPath("fonts/KGSorryNotSorryChub.ttf")
            .setFontAttrId(R.attr.fontPath)
            .build())
}
