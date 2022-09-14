package com.wenger.natifetask3.app

import android.app.Application
import com.wenger.natifetask3.di.AppComponent
import com.wenger.natifetask3.di.AppModule
import com.wenger.natifetask3.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject


class MyApp : Application(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> {
        return androidInjector
    }

    val component: AppComponent by lazy {
        DaggerAppComponent.builder()
            .application(this)
            .appModule(AppModule(this))
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        component.inject(this)
    }

}