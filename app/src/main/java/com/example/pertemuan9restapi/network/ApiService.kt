package com.example.pertemuan9restapi.network

import com.example.pertemuan9restapi.model.response.ResponseDataMahasiswa
import com.example.pertemuan9restapi.model.response.ResponseDetailDataMahasiswa
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
interface ApiService {
    @GET("sub/restapi-slim/public/datamahasiswa/")
    fun getDataMahasiswa() : Call<ResponseDataMahasiswa>
    @GET("sub/restapi-slim/public/datamahasiswa/{nim}")
    fun getDetailMahasiswa(@Path("nim") nim : String) :
            Call<ResponseDetailDataMahasiswa>
}