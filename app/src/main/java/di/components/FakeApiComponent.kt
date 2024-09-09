package di.components

import android.content.Context
import com.niko.avitoapp.fragments.LoginFragment
import dagger.BindsInstance
import dagger.Component
import di.annotation.ApplicationScope
import di.modules.DataModule
import di.modules.DomainModule
import di.modules.ViewModelsModule

@ApplicationScope
@Component(modules = [DataModule::class,DomainModule::class,ViewModelsModule::class])
interface FakeApiComponent {
    fun inject(fragment: LoginFragment)
    @Component.Factory
    interface  FakeApiComponentFactory{
        fun create(@BindsInstance context: Context):FakeApiComponent
    }
}