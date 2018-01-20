package com.keresmi.forgetmenot

import android.app.Application
import com.crashlytics.android.Crashlytics
import io.fabric.sdk.android.Fabric
import uk.co.chrisjenx.calligraphy.CalligraphyConfig
import com.evernote.android.job.JobManager
import com.evernote.android.job.JobConfig.setAllowSmallerIntervalsForMarshmallow
import com.keresmi.forgetmenot.notification.NotificationJobCreator


/**
 * Created by keresmi.
 * https://github.com/keresmi
 */

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Fabric.with(this, Crashlytics())
        initFont()
        initJobManager()
    }

    private fun initFont() = CalligraphyConfig.initDefault(CalligraphyConfig.Builder()
            .setDefaultFontPath("fonts/KGSorryNotSorryChub.ttf")
            .setFontAttrId(R.attr.fontPath)
            .build())

    private fun initJobManager() =
        JobManager.create(this).addJobCreator(NotificationJobCreator())
}
