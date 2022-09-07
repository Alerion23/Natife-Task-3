package com.wenger.natifetask3.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class UserResponse(
    @SerializedName("results") val results: List<ResultResponse>
)

@Parcelize
data class ResultResponse(
    @SerializedName("login") val id: LoginResponse,
    @SerializedName("name") val name: NameResponse,
    @SerializedName("picture") val picture: PictureResponse
) : Parcelable

@Parcelize
data class LoginResponse(
    @SerializedName("uuid") val uuid: String
) : Parcelable

@Parcelize
data class NameResponse(
    @SerializedName("first") val firstName: String,
    @SerializedName("last") val lastName: String,
) : Parcelable

@Parcelize
data class PictureResponse(
    @SerializedName("large") val url: String
) : Parcelable