package softocean.app.kotlinmvvm.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import softocean.app.kotlinmvvm.vm.MainViewModel
import softocean.app.kotlinmvvm.vm.MyViewModelFactory

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindRepoViewModel(repoViewModel: MainViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: MyViewModelFactory): ViewModelProvider.Factory
}