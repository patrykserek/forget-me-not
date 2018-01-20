package com.keresmi.forgetmenot.notification

import com.evernote.android.job.Job
import com.evernote.android.job.JobCreator


/**
 * Created by keresmi.
 * https://github.com/keresmi
 */
class NotificationJobCreator : JobCreator {

    override fun create(tag: String): Job? {
        return when (tag) {
            NotificationJob.TAG -> NotificationJob()
            else -> null
        }
    }
}