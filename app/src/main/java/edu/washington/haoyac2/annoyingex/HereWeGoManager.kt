package edu.washington.haoyac2.annoyingex

import android.content.Context
import androidx.work.*
import java.util.concurrent.TimeUnit

class HereWeGoManager(private val context: Context) {
    private var workManager = WorkManager.getInstance(context)

    companion object {
        const val ANNOYING_WORK_REQUEST_TAG = "ANNOYING_WORK_REQUEST_TAG"
        const val FETCH_JSON_WORK_REQUEST_TAG = "FETCH_JSON_WORK_REQUEST_TAG"
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

    fun startAnnoyingTheHeckOuttaPerson2Days() {
        if (isAnnoyingTaskRunning()) {
            stopWork()
        }
        val fetchConstraints = Constraints.Builder()
            .setRequiresBatteryNotLow(true)
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val extraWorkRequest = PeriodicWorkRequestBuilder<SendMessageWorker>(2, TimeUnit.DAYS)
            .setInitialDelay(2, TimeUnit.DAYS)
            .setConstraints(fetchConstraints)
            .addTag(FETCH_JSON_WORK_REQUEST_TAG)
            .build()

        workManager.enqueue(extraWorkRequest)
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
        workManager.cancelAllWorkByTag(FETCH_JSON_WORK_REQUEST_TAG)
    }
}