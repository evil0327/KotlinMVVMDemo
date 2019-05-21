package softocean.app.kotlinmvvm

import android.app.Application
import softocean.app.kotlinmvvm.di.AppModule
import softocean.app.kotlinmvvm.di.DaggerComponentHolder
import softocean.app.kotlinmvvm.di.DaggerMyDaggerComponent
import softocean.app.kotlinmvvm.di.RepoModule

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()

        val daggerComponent = DaggerMyDaggerComponent.builder()
            .appModule(AppModule(this))
            .repoModule(RepoModule())
            .build()
        DaggerComponentHolder.appComponent = daggerComponent
    }
}