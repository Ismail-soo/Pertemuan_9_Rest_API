package com.example.pertemuan9restapi.model.response


import com.example.pertemuan9restapi.model.request.DataAllMahasiswa
import com.google.gson.annotations.SerializedName

data class ResponseDataMahasiswa(
    @SerializedName("data")
    val `data`: List<DataAllMahasiswa>,
    @SerializedName("status")
    val status: String
)