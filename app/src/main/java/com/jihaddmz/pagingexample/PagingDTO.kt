package com.jihaddmz.pagingexample


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable


@Parcelize
data class PagingDTO(
    @field:[Expose SerializedName("uri")]
    val uri: String?,
    @field:[Expose SerializedName("nb")]
    val nb: Int?,
) : Parcelable