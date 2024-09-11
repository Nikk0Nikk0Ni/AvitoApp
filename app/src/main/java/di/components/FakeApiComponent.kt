package di.components

import android.content.Context
import com.niko.avitoapp.fragments.LoginFragment
import com.niko.avitoapp.fragments.ProductDetailFragment
import com.niko.avitoapp.fragments.ProductListFragment
import com.niko.avitoapp.fragments.RegistrationFragment
import dagger.BindsInstance
import dagger.Component
import di.annotation.ApplicationScope
import di.annotation.EmailPatternQualifier
import di.modules.DataModule
import di.modules.DomainModule
import di.modules.ViewModelsModule

@ApplicationScope
@Component(modules = [DataModule::class,DomainModule::class,ViewModelsModule::class])
interface FakeApiComponent {
    fun inject(fragment: LoginFragment)
    fun inject(fragment: RegistrationFragment)
    fun inject(fragment: ProductListFragment)
    fun productDetailComponent(): ProductDetailSubcomponent.Factory
    @Component.Factory
    interface  FakeApiComponentFactory{
        fun create(@BindsInstance context: Context):FakeApiComponent
    }
}