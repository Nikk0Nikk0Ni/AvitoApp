package com.niko.avitoapp.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.niko.avitoapp.mappers.FakeApiPresMapper
import com.niko.avitoapp.models.ProductUiModel
import di.annotation.IdQualifier
import domain.models.Product
import domain.usecases.GetProductDetail
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProductDetailViewModel @Inject constructor(
    private val getProductDetail: GetProductDetail,
    @IdQualifier
    private val productId: String,
    private val mapper : FakeApiPresMapper
): ViewModel() {
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading
    private val _product = MutableLiveData<ProductUiModel>()
    val product : LiveData<ProductUiModel>
        get() = _product
    fun getDetail(){
        viewModelScope.launch {
            _isLoading.value = true
            _product.value = mapper.mapProductToProductUiModel(getProductDetail(productId))
            _isLoading.value = false
        }
    }
}