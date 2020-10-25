package com.hnativ.androidlabpokedex.work

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.hnativ.androidlabpokedex.data.PokemonRepositoryImpl
import com.hnativ.androidlabpokedex.persistance.getDatabase
import retrofit2.HttpException

class RefreshDataWorker(appContext: Context, params: WorkerParameters) :
    CoroutineWorker(appContext, params) {

    companion object {
        const val WORK_NAME = "RefreshDataWorker"
    }

    override suspend fun doWork(): Result {
        val database = getDatabase(applicationContext)
        val repository = PokemonRepositoryImpl(database)

        return try {
            repository.refreshPokemonData()
            Result.success()
        } catch (e: HttpException) {
            Result.retry()
        }
    }
}