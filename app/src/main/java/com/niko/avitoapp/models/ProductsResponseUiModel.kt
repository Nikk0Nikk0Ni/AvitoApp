package com.niko.avitoapp.models

import com.google.gson.annotations.SerializedName

data class ProductsResponseUiModel(
    @SerializedName("status")
    val status: String? = null,
    @SerializedName("count")
    val count: Int? = null,
    @SerializedName("Data")
    val data: List<ProductUiModel> = listOf()
) {
    companion object {
        const val STATUS_SUCCESSFUL = "success"
    }
}
