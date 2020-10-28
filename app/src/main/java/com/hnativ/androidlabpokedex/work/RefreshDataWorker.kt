package com.hnativ.androidlabpokedex.work

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.hnativ.androidlabpokedex.App
import retrofit2.HttpException

class RefreshDataWorker(appContext: Context, params: WorkerParameters) :
    CoroutineWorker(appContext, params) {

    companion object {
        const val WORK_NAME = "RefreshDataWorker"
    }

    override suspend fun doWork(): Result {
        val repository = App.instance.appComponent.repository()

        return try {
            repository.refreshPokemonData()
            Result.success()
        } catch (e: HttpException) {
            Result.retry()
        }
    }
}