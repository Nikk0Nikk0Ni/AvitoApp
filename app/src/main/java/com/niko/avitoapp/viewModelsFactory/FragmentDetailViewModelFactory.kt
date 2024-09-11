package com.niko.avitoapp.viewModelsFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.niko.avitoapp.viewModels.ProductDetailViewModel
import di.annotation.IdQualifier
import domain.usecases.GetProductDetail
import javax.inject.Inject

class FragmentDetailViewModelFactory @Inject constructor(
    private val getProductDetail: GetProductDetail,
    @IdQualifier private val id: String
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProductDetailViewModel::class.java))
            return ProductDetailViewModel(getProductDetail,id) as T
        else
            throw RuntimeException("${modelClass.simpleName} != FragmentDetailViewModel")
    }
}