package com.example.huytrinh.songs.data.model

import com.google.gson.annotations.SerializedName

data class ApiAlbumModel (@SerializedName("id") val id: String,
                      @SerializedName("name") val name: String,
                      @SerializedName("numberOfSongs") val numberOfSongs: Int,
                      @SerializedName("albumImgUrl") val albumImgUrl: String)

