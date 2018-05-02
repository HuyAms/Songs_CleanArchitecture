package com.example.huytrinh.songs.presentation.util

import io.reactivex.Scheduler

interface SchedulerProvider {

    fun getUIScheduler(): Scheduler

    fun getIOScheduler(): Scheduler
}