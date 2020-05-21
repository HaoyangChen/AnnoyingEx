package edu.washington.haoyac2.annoyingex

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import kotlin.random.Random

class SendMessageWorker(private val context: Context, workParams: WorkerParameters) : Worker(context, workParams) {

    override fun doWork(): Result {
        val theAnnoyingExApp = (applicationContext as AnnoyingExApp)

            theAnnoyingExApp.apiManager.fetchMessages({ messages ->
                theAnnoyingExApp.annoyingNotificationManager.postNotification(messages[Random.nextInt(0, messages.size)])
            },
                {
                    theAnnoyingExApp.annoyingNotificationManager.postNotification("unable to retrieve message")
                })
        return Result.success()
    }
}