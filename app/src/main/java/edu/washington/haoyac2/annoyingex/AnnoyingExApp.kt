package edu.washington.haoyac2.annoyingex

import android.app.Application

class AnnoyingExApp: Application() {
    lateinit var hereWeGoManager: HereWeGoManager
        private set

    lateinit var annoyingNotificationManager: AnnoyingNotificationManager
        private set

    lateinit var apiManager: ApiManager

    override fun onCreate() {
        super.onCreate()
        hereWeGoManager = HereWeGoManager(this)
        annoyingNotificationManager = AnnoyingNotificationManager(this)
        apiManager = ApiManager(this)
    }
}