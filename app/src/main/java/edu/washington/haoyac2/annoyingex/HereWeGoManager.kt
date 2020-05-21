package edu.washington.haoyac2.annoyingex

import android.content.Context
import androidx.work.*
import java.util.concurrent.TimeUnit

class HereWeGoManager(private val context: Context) {
    private var workManager = WorkManager.getInstance(context)

    companion object {
        const val ANNOYING_WORK_REQUEST_TAG = "ANNOYING_WORK_REQUEST_TAG"
    }

    fun startAnnoyingTheHeckOuttaPerson() {
        if (isAnnoyingTaskRunning()) {
            stopWork()
        }

        val constraints = Constraints.Builder()
            .setRequiresCharging(true)
            .build()

        val workRequest = PeriodicWorkRequestBuilder<SendMessageWorker>(20, TimeUnit.MINUTES)
            .setInitialDelay(5, TimeUnit.SECONDS)
            .setConstraints(constraints)
            .addTag(ANNOYING_WORK_REQUEST_TAG)
            .build()

        workManager.enqueue(workRequest)
    }

    private fun isAnnoyingTaskRunning(): Boolean {
        return when (workManager.getWorkInfosByTag(ANNOYING_WORK_REQUEST_TAG).get().firstOrNull()?.state) {
            WorkInfo.State.RUNNING,
            WorkInfo.State.ENQUEUED -> true
            else -> false
        }
    }

    fun stopWork() {
        workManager.cancelAllWorkByTag(ANNOYING_WORK_REQUEST_TAG)
    }
}