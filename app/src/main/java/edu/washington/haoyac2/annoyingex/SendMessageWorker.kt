package edu.washington.haoyac2.annoyingex

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import kotlin.random.Random

class SendMessageWorker(private val context: Context, workParams: WorkerParameters) : Worker(context, workParams) {

    private var messageList: List<String>? = null

    override fun doWork(): Result {
        val theAnnoyingExApp = (applicationContext as AnnoyingExApp)

        if (messageList == null) {
            theAnnoyingExApp.apiManager.fetchMessages({ messages ->
                messageList = messages

                var index = Random.nextInt(0, messageList!!.size)
                theAnnoyingExApp.annoyingNotificationManager.postNotification(messageList!![index])
            },
                {
                    theAnnoyingExApp.annoyingNotificationManager.postNotification("unable to retrieve message")
                })
        }

        return Result.success()
    }
}