package di.modules

import androidx.lifecycle.ViewModel
import com.niko.avitoapp.viewModels.LogInViewModel
import com.niko.avitoapp.viewModels.ProductsViewModel
import com.niko.avitoapp.viewModels.RegisterViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import di.annotation.ViewModelKey

@Module
interface ViewModelsModule {
    @IntoMap
    @ViewModelKey(LogInViewModel::class)
    @Binds
    fun bindLogInViewModel(viewModel: LogInViewModel): ViewModel

    @IntoMap
    @ViewModelKey(RegisterViewModel::class)
    @Binds
    fun bindRegisterViewModel(viewModel: RegisterViewModel): ViewModel

    @IntoMap
    @ViewModelKey(ProductsViewModel::class)
    @Binds
    fun bindProductViewModel(viewModel: ProductsViewModel): ViewModel
}