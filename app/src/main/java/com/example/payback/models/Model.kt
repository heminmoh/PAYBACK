package com.example.payback.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable

data class hitmodel(val total:Int?,val totalHits: Int?,val hits: List<Hits>?)
@Parcelize
data class Hits (
    val id: String?,
    val pageURL: String?,
    val type: String?,
    val tags: String?,
    val previewURL: String?,
    val previewWidth: Int?,
    val previewHeight: Int?,
    val webformatURL: String?,
    val webformatWidth: String?,
    val webformatHeight: String?,
    val largeImageURL: String?,
    val imageWidth: Int?,
    val imageHeight: Int?,
    val imageSize: Int?,
    val views: Int?,
    val downloads: Int?,
    val collections: Int?,
    val likes: Int?,
    val comments: Int?,
    val user_id: Int?,
    var user: String?,
    val userImageURL: String?

 ) : Parcelable
