package com.niko.avitoapp.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import di.annotation.IdQualifier
import domain.models.Product
import domain.usecases.GetProductDetail
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProductDetailViewModel @Inject constructor(
    private val getProductDetail: GetProductDetail,
    @IdQualifier
    private val productId: String
): ViewModel() {
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading
    private val _product = MutableLiveData<Product>()
    val product : LiveData<Product>
        get() = _product
    fun getDetail(){
        viewModelScope.launch {
            _isLoading.value = true
            _product.value = getProductDetail(productId)
            _isLoading.value = false
        }
    }
}