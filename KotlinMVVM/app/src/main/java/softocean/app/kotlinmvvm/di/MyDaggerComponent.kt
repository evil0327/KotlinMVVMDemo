package softocean.app.kotlinmvvm.di


import dagger.Component
import softocean.app.kotlinmvvm.ui.MainActivity
import softocean.app.kotlinmvvm.ui.MainFragment

import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, RepoModule::class])
interface MyDaggerComponent {
    fun inject(mainFragment: MainFragment)
}