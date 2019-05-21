package softocean.app.kotlinmvvm.di

import androidx.lifecycle.ViewModel
import dagger.MapKey

import java.lang.annotation.*
import kotlin.reflect.KClass

@Documented
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
internal annotation class ViewModelKey(val value: KClass<out ViewModel>)