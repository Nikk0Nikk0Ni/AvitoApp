package com.niko.avitoapp.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import androidx.recyclerview.widget.LinearLayoutManager
import com.niko.avitoapp.R
import com.niko.avitoapp.adapters.CategoryAdapter
import com.niko.avitoapp.adapters.ProductsAdapter
import com.niko.avitoapp.databinding.FragmentProductListBinding
import com.niko.avitoapp.models.CategoryItem
import com.niko.avitoapp.viewModels.ProductsViewModel
import com.niko.avitoapp.viewModelsFactory.FakeApiViewModelFactory
import data.network.FakeShopApi
import di.FakeApiApplication
import javax.inject.Inject

class ProductListFragment : Fragment() {
    private var _binding: FragmentProductListBinding? = null
    private val binding: FragmentProductListBinding
        get() = _binding ?: throw RuntimeException(getString(R.string.productlistfragment_null))

    @Inject
    lateinit var viewModelFactory: FakeApiViewModelFactory

    @Inject
    lateinit var categoryAdapter: CategoryAdapter

    @Inject
    lateinit var productsAdapter: ProductsAdapter

    private val categoryList by lazy {
        listOf(
            CategoryItem(
                R.drawable.computer_svgrepo_com,
                getString(R.string.techincs), getString(R.string.computers_category)
            ),
            CategoryItem(
                R.drawable.shopping_svgrepo_com,
                getString(R.string.bags_category_name), getString(R.string.bags_category)
            ),
            CategoryItem(
                R.drawable.clothing_garment_svgrepo_com,
                getString(R.string.clothing_category_name), getString(R.string.clothing_category)
            ),
            CategoryItem(
                R.drawable.list_document_svgrepo_com,
                getString(R.string.all_products)
            )
        )
    }
    private val component by lazy {
        (requireActivity().application as FakeApiApplication).component
    }
    private val productViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[ProductsViewModel::class.java]
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        component.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initCategoryRecView()
        observeProductsList()
        initSortedSpinner()
        observeLoadingProductsList()
        initProductRecView()
    }

    private fun initSortedSpinner() {
        binding.sortSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val selectedItem = p0?.getItemAtPosition(p2) as String
                val sortOptions = resources.getStringArray(R.array.sort_options)
                when (selectedItem) {
                    sortOptions[0] ->{
                        productViewModel.resetIsSortedList()
                        productViewModel.resetPage()
                        productViewModel.resetAllProducts()
                        if(productViewModel.currentCategory != null){
                            productViewModel.currentCategory?.let {
                                productViewModel.getProductList(it)
                            }
                        }else{
                            productViewModel.getProductList()
                        }
                    }
                    sortOptions[1] -> {
                        productViewModel.setIsSortedListTrue()
                        productViewModel.setMaxToMinSortType()
                        productViewModel.resetPage()
                        productViewModel.resetAllProducts()
                        if(productViewModel.currentCategory != null){
                            productViewModel.currentCategory?.let {
                                productViewModel.getSortedProductList(it)
                            }
                        }else
                            productViewModel.getSortedProductList()
                    }

                    sortOptions[2] -> {
                        productViewModel.setIsSortedListTrue()
                        productViewModel.setMinToMaxType()
                        productViewModel.resetPage()
                        productViewModel.resetAllProducts()
                        if(productViewModel.currentCategory != null){
                            productViewModel.currentCategory?.let {
                                productViewModel.getSortedProductList(it)
                            }
                        }else
                            productViewModel.getSortedProductList()
                    }
                }

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }
    }

    private fun observeLoadingProductsList() {
        productViewModel.loading.observe(viewLifecycleOwner) {
            if (it)
                binding.loadingProgressBar.visibility = View.VISIBLE
            else
                binding.loadingProgressBar.visibility = View.GONE
        }
    }

    private fun observeProductsList() {
        productViewModel.productList.observe(viewLifecycleOwner) {
            productsAdapter.submitList(it)
        }
    }

    private fun initProductRecView() {
        binding.recyclerViewProducts.adapter = productsAdapter
        setProductAdapterSettings()
    }

    private fun setProductAdapterSettings() {
        productsAdapter.getDetailInfo = {
            TODO("DETAIL")
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }
        productsAdapter.uploadData = {
            if(productViewModel.isSortedList){
                if (productViewModel.currentCategory == null)
                    productViewModel.getSortedProductList()
                else {
                    productViewModel.currentCategory?.let{
                        productViewModel.getSortedProductList(it)
                    }
                }
            }
            else{
                if (productViewModel.currentCategory == null)
                    productViewModel.getProductList()
                else {
                    productViewModel.currentCategory?.let{
                        productViewModel.getProductList(it)
                    }
                }
            }

        }
    }

    private fun initCategoryRecView() {
        binding.recViewCategory.layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
        binding.recViewCategory.adapter = categoryAdapter
        setCategoryAdapterSettings()
    }

    private fun setCategoryAdapterSettings() {
        categoryAdapter.submitList(categoryList)
        categoryAdapter.onCategoryClick = {
            if(productViewModel.isSortedList){
                productViewModel.resetPage()
                productViewModel.resetAllProducts()
                if (it != null) {
                    productViewModel.getSortedProductList(it)
                } else {
                    productViewModel.resetCategory()
                    productViewModel.getSortedProductList()
                }
            }
            else{
                productViewModel.resetAllProducts()
                productViewModel.resetPage()
                if (it != null) {
                    productViewModel.getProductList(it)
                } else {
                    productViewModel.getSortedProductList()
                    productViewModel.resetCategory()
                }
            }
        }
    }

    companion object {
        fun navigate(fragment: Fragment) {
            fragment.findNavController().navigate(R.id.productListFragment, null, navOptions {
                anim {
                    enter = androidx.navigation.ui.R.anim.nav_default_enter_anim
                    popEnter = androidx.navigation.ui.R.anim.nav_default_pop_enter_anim
                    exit = androidx.navigation.ui.R.anim.nav_default_exit_anim
                    popExit = androidx.navigation.ui.R.anim.nav_default_pop_exit_anim
                }
                popUpTo(R.id.main_navigation) {
                    inclusive = true
                }
            })
        }
    }

}