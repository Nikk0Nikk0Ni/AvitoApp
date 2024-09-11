package com.niko.avitoapp.mappers

import com.niko.avitoapp.models.ProductUiModel
import com.niko.avitoapp.models.ProductsResponseUiModel
import di.annotation.ApplicationScope
import domain.models.Product
import domain.models.ProductsResponse
import javax.inject.Inject

@ApplicationScope
class FakeApiPresMapper @Inject constructor() {
    fun mapProductToProductUiModel(product: Product): ProductUiModel {
        return ProductUiModel(
            product.id,
            product.name,
            product.category,
            product.price,
            product.discountedPrice,
            product.images,
            product.description,
            product.productRating,
            product.brand
        )
    }

    fun mapProductResponseToProductResponseUiModel(productsResponse: ProductsResponse): ProductsResponseUiModel {
        return ProductsResponseUiModel(
            productsResponse.status,productsResponse.count,mapListProductToListProductUiModel(productsResponse.data)
        )
    }

    private fun mapListProductToListProductUiModel(list: List<Product>): List<ProductUiModel>{
        return list.map { mapProductToProductUiModel(it) }
    }
}