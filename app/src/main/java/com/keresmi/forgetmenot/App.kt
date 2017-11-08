package com.keresmi.forgetmenot

import android.app.Application
import com.crashlytics.android.Crashlytics
import io.fabric.sdk.android.Fabric


/**
 * Created by keresmi.
 * https://github.com/keresmi
 */

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Fabric.with(this, Crashlytics())
    }
}
