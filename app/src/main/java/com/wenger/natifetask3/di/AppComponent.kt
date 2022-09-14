package com.wenger.natifetask3.di

import android.app.Application
import com.wenger.natifetask3.app.MyApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Component(
    modules = [AppModule::class, NetworkModule::class,
        RepositoryModule::class, DatabaseModule::class,
        AndroidInjectionModule::class, FragmentModule::class,
        ViewModelModule::class]
)
@Singleton
interface AppComponent {

    fun inject (app: MyApp)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun appModule(appModule: AppModule): Builder

        fun build(): AppComponent
    }
}