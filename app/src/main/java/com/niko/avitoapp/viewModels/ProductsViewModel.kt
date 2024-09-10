package com.niko.avitoapp.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import data.network.FakeShopApi
import domain.models.Product
import domain.models.ProductsResponse
import domain.usecases.GetProductList
import domain.usecases.GetProductListByCategory
import domain.usecases.SortByPriceCategoryProduct
import domain.usecases.SortByPriceProduct
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProductsViewModel @Inject constructor(
    private val getProductList: GetProductList,
    private val getProductListByCategory: GetProductListByCategory,
    private val sortByPriceCategoryProduct: SortByPriceCategoryProduct,
    private val sortByPriceProduct: SortByPriceProduct
) : ViewModel() {
    private val _productList = MutableLiveData<List<Product>>()
    val productList: LiveData<List<Product>>
        get() = _productList
    private val _isError = MutableLiveData<Boolean>()
    val isError: LiveData<Boolean>
        get() = _isError
    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean>
        get() = _loading
    private var page = 1
    private var _currentCategory: String? = null
    val currentCategory: String?
        get() = _currentCategory
    private var sortType: String = ""
    private var _isSortedList = false
    val isSortedList: Boolean
        get() = _isSortedList


    fun setMaxToMinSortType(){
        sortType = FakeShopApi.SORT_MAX_TO_MIN
    }
    fun setMinToMaxType(){
        sortType = FakeShopApi.SORT_MIN_TO_MAX
    }

    fun resetIsSortedList(){
        _isSortedList = false
    }

    fun setIsSortedListTrue(){
        _isSortedList = true
    }

    fun resetAllProducts() {
        _productList.value = emptyList()
    }

    fun resetPage() {
        page = 1
    }

    fun resetCategory() {
        _currentCategory = null
    }

    fun getProductList(category: String? = null) {
        if (category == null) {
            viewModelScope.launch {
                _loading.value = true
                val productResponse = getProductList(page)
                if (productResponse.status == ProductsResponse.STATUS_SUCCESSFUL) {
                    val list = productList.value?.toMutableList() ?: mutableListOf()
                    list.addAll(productResponse.data)
                    _productList.value = list
                    page++
                } else
                    _isError.value = true
                _loading.value = false
            }
        } else {
            if (currentCategory != category) {
                _currentCategory = category
                resetAllProducts()
                resetPage()
            }
            viewModelScope.launch {
                _loading.value = true
                val productResponse = getProductListByCategory(category, page)
                if (productResponse.status == ProductsResponse.STATUS_SUCCESSFUL) {
                    val list = productList.value?.toMutableList() ?: mutableListOf()
                    list.addAll(productResponse.data)
                    _productList.value = list
                    page++
                } else
                    _isError.value = true
                _loading.value = false

            }
        }

    }

    fun getSortedProductList(category: String? = null){
        if (category == null) {
            viewModelScope.launch {
                _loading.value = true
                val productResponse = sortByPriceProduct(sortType,page)
                if (productResponse.status == ProductsResponse.STATUS_SUCCESSFUL) {
                    val list = productList.value?.toMutableList() ?: mutableListOf()
                    list.addAll(productResponse.data)
                    _productList.value = list
                    page++
                } else
                    _isError.value = true
                _loading.value = false
            }
        }else{
            viewModelScope.launch {
                _loading.value = true
                val productResponse = sortByPriceCategoryProduct(sortType,category,page)
                if (productResponse.status == ProductsResponse.STATUS_SUCCESSFUL) {
                    val list = productList.value?.toMutableList() ?: mutableListOf()
                    list.addAll(productResponse.data)
                    _productList.value = list
                    page++
                } else
                    _isError.value = true
                _loading.value = false
            }
        }
    }


    fun resetErrorLoading() {
        _isError.value = false
    }
}