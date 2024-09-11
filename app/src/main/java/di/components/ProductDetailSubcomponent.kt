package di.components

import com.niko.avitoapp.fragments.ProductDetailFragment
import dagger.BindsInstance
import dagger.Subcomponent
import di.annotation.IdQualifier

@Subcomponent
interface ProductDetailSubcomponent {
    fun inject(fragment: ProductDetailFragment)
    @Subcomponent.Factory
    interface Factory{
        fun create(@IdQualifier @BindsInstance id: String):ProductDetailSubcomponent
    }
}