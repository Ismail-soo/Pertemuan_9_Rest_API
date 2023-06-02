package com.example.pertemuan9restapi.model.request


import com.google.gson.annotations.SerializedName

data class DetailDataMahasiswa(
    @SerializedName("Nama")
    val nama: String,
    @SerializedName("Nim")
    val nim: String,
    @SerializedName("Telepon")
    val telepon: String
)