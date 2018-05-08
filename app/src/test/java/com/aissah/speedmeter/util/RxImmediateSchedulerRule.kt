package com.aissah.speedmeter.util

import android.os.AsyncTask
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

/**
 * Rule to updateAsSynced the behaviour of RxJava for UnitTest. To execute the tasks on the mainThread
 */
class RxImmediateSchedulerRule : TestRule {

  private val immediate = Schedulers.from(AsyncTask.THREAD_POOL_EXECUTOR)

  override fun apply(base: Statement?, description: Description?): Statement {
    return object : Statement() {
      @Throws(Throwable::class)
      override fun evaluate() {
        RxJavaPlugins.setInitIoSchedulerHandler { immediate }
        RxJavaPlugins.setInitComputationSchedulerHandler { immediate }
        RxJavaPlugins.setInitNewThreadSchedulerHandler { immediate }
        RxJavaPlugins.setInitSingleSchedulerHandler { immediate }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { immediate }
        try {
          base?.evaluate()
        } finally {
          RxJavaPlugins.reset()
          RxAndroidPlugins.reset()
        }
      }
    }

  }
}