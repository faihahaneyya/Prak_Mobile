package com.example.crybabyapps.Data.Api

import com.example.crybabyapps.Data.Model.PhotoModel
import retrofit2.http.GET

interface PhotoApiService {
    @GET("list")
    suspend fun getPhotos(): List<PhotoModel>
}