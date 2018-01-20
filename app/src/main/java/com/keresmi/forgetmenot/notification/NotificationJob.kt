package com.keresmi.forgetmenot.notification

import android.app.Notification
import android.app.PendingIntent
import android.content.Intent
import android.support.v4.app.NotificationCompat
import android.support.v4.app.NotificationManagerCompat

import com.evernote.android.job.Job
import com.evernote.android.job.JobRequest
import com.evernote.android.job.util.support.PersistableBundleCompat
import com.keresmi.forgetmenot.MainActivity
import com.keresmi.forgetmenot.R
import java.util.*


/**
 * Created by keresmi.
 * https://github.com/keresmi
 */
class NotificationJob : Job() {

    companion object {
        val TAG: String = NotificationJob::class.java.simpleName
        private const val CHANNEL_ID = "APP_ALERT"
        private const val REQUEST_CODE = 0
        private const val NOTIFICATION_MESSAGE_KEY = "NOTIFICATION_MESSAGE_KEY"
    }

    override fun onRunJob(params: Params): Result {
        val message = params.extras.getString(NOTIFICATION_MESSAGE_KEY, "")
        val notification = createNotification(message)

        NotificationManagerCompat.from(context).notify(Random().nextInt(), notification)

        return Job.Result.SUCCESS
    }

    fun scheduleNotification(notificationTime: Long, notificationMessage: String) {
        JobRequest.Builder(TAG)
                .setExact(notificationTime - System.currentTimeMillis())
                .setExtras(PersistableBundleCompat()
                        .apply { putString(NOTIFICATION_MESSAGE_KEY, notificationMessage) })
                .build()
                .schedule()
    }

    private fun createNotification(notificationMessage: String): Notification {
        val pi = PendingIntent.getActivity(context, REQUEST_CODE,
                Intent(context, MainActivity::class.java), PendingIntent.FLAG_UPDATE_CURRENT)

        return NotificationCompat.Builder(context, CHANNEL_ID)
                .setContentTitle(context.getString(R.string.app_name))
                .setContentText(notificationMessage)
                .setAutoCancel(true)
                .setContentIntent(pi)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setShowWhen(true)
                .setLocalOnly(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .build()
    }
}