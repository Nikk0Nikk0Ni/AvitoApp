package domain.models

import com.google.gson.annotations.SerializedName

data class AuthUserResponse(
    val status: String? = null,
    val token: String? = null,

    ) {
    companion object {
        const val STATUS_SUCCESSFUL = "success"
    }
}