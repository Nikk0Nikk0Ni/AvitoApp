package data.mappers

import data.models.ProductDTO
import data.models.ProductDetailResponseDTO
import data.models.ProductsResponseDTO
import di.annotation.ApplicationScope
import domain.models.Product
import domain.models.ProductDetailResponse
import domain.models.ProductsResponse
import javax.inject.Inject

@ApplicationScope
class FakeApiDataMapper @Inject constructor() {
    fun mapProductsResponseDTOToProductsResponse(productsResponseDTO: ProductsResponseDTO): ProductsResponse {
        return ProductsResponse(
            productsResponseDTO.status,
            productsResponseDTO.count,
            mapListProductDTOToListProduct(productsResponseDTO.data)
        )
    }

    fun mapProductDetailResponseDTOToProductDetail(productDetail: ProductDetailResponseDTO):ProductDetailResponse{
        return ProductDetailResponse(productDetail.status,mapProductDTOPToProduct(productDetail.data))
    }

    fun mapProductDTOPToProduct(productDTO: ProductDTO): Product {
        return Product(
            productDTO.id,
            productDTO.name,
            productDTO.category,
            productDTO.price,
            productDTO.discountedPrice,
            productDTO.images,
            productDTO.description,
            productDTO.productRating,
            productDTO.brand
        )
    }

    private fun mapListProductDTOToListProduct(list: List<ProductDTO>): List<Product> {
        return list.map {
            Product(
                it.id,
                it.name,
                it.category,
                it.price,
                it.discountedPrice,
                it.images,
                it.description,
                it.productRating,
                it.brand
            )
        }
    }
}