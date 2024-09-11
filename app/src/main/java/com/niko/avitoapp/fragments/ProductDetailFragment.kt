package com.niko.avitoapp.fragments

import android.content.Context
import android.graphics.Paint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.niko.avitoapp.R
import com.niko.avitoapp.adapters.ProductDetailImageAdapter
import com.niko.avitoapp.databinding.FragmentProductDetailBinding
import com.niko.avitoapp.models.ImageDetailItem
import com.niko.avitoapp.viewModels.ProductDetailViewModel
import com.niko.avitoapp.viewModelsFactory.FragmentDetailViewModelFactory
import di.FakeApiApplication
import domain.models.Product
import javax.inject.Inject

class ProductDetailFragment : Fragment() {
    private var _binding: FragmentProductDetailBinding? = null
    private val binding: FragmentProductDetailBinding
        get() = _binding ?: throw RuntimeException("Fragment Product Detail == null")

    @Inject
    lateinit var viewModelFactory: FragmentDetailViewModelFactory
    private val productDetailViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[ProductDetailViewModel::class]
    }
    private val imageAdapter = ProductDetailImageAdapter()
    private val component by lazy {
        (requireActivity().application as FakeApiApplication).component.productDetailComponent()
            .create(requireArguments().getString(ID, Product.INCORRECT_ID))
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        component.inject(this)
        Log.e("AUF", requireArguments().getString(ID, Product.INCORRECT_ID))
        productDetailViewModel.getDetail()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeProductDetail()
        observeLoading()
        initViewPager()
    }

    private fun observeLoading() {
        productDetailViewModel.isLoading.observe(viewLifecycleOwner){
            if(it)
                binding.loadingProgressbar.visibility = View.VISIBLE
            else
                binding.loadingProgressbar.visibility = View.GONE
        }
    }

    private fun observeProductDetail() {
        productDetailViewModel.product.observe(viewLifecycleOwner) {
            setProductImagesToPager(it.images)
            setProductName(it.name)
            setProductPrice(it.price, it.discountedPrice)
            setProductCategory(it.category)
            setProductDescription(it.description)
        }
    }

    private fun setProductDescription(description: String?) {
        description?.let{
            binding.productDescription.text = String.format(getString(R.string.product_description),it)
        }
    }

    private fun setProductCategory(category: List<String>?) {
        category?.let {
            binding.productCategory.text = String.format(getString(R.string.product_category),it.joinToString(", "))
        }
    }

    private fun setProductPrice(price: Int?, discountedPrice: Int?) {
        if (discountedPrice == null) {
            price?.let {
                binding.productPrice.text =
                    String.format(getString(R.string.price), it.toString())
            }
        } else {
            binding.productDiscountedPrice.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            binding.productDiscountedPrice.text =
                String.format(getString(R.string.old_price), price.toString())
            binding.productDiscountedPrice.visibility = View.VISIBLE
            binding.productPrice.text =
                String.format(getString(R.string.new_pirce), discountedPrice.toString())
        }
    }

    private fun setProductName(name: String?) {
        binding.productName.text = String.format(getString(R.string.product_name), name)
    }

    private fun setProductImagesToPager(images: List<String>?) {
        val imageItems = images?.map { imageLink -> ImageDetailItem(imageLink) }
        imageItems?.let { items ->
            binding.productIconList.currentItem = items.size / 2
        }
        imageAdapter.submitList(imageItems)
    }

    private fun initViewPager() {
        binding.productIconList.adapter = imageAdapter
        binding.productIconList.layoutDirection = View.LAYOUT_DIRECTION_RTL
    }

    companion object {
        private const val ID = "id"
        fun newInstance(id: String): Fragment{
            return ProductDetailFragment().apply {
                arguments = bundleOf(ID to id)
            }
        }
        fun navigate(fragment: Fragment, id: String) {
            fragment.findNavController()
                .navigate(R.id.productDetailFragment, bundleOf(ID to id), navOptions {
                    anim {
                        enter = androidx.navigation.ui.R.anim.nav_default_enter_anim
                        popEnter = androidx.navigation.ui.R.anim.nav_default_pop_enter_anim
                        exit = androidx.navigation.ui.R.anim.nav_default_exit_anim
                        popExit = androidx.navigation.ui.R.anim.nav_default_pop_exit_anim
                    }
                    popUpTo(R.id.productListFragment)
                })
        }
    }
}