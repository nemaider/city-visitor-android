package com.example.android.cityvisitor.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*


//private const val BASE_URL = "https://android-kotlin-fun-mars-server.appspot.com/"
private const val BASE_URL = "http://192.168.0.43:8080/api/v1/"
interface CityVisitorApiService {
    @GET("monuments/all-data")
    fun getMonuments():
    // The Coroutine Call Adapter allows us to return a Deferred, a Job with a result
            Deferred<MonumentResponse>

    @GET("tourists/getAllFavouritesMonuments")
    fun getFavouriteMonuments(@Query("touristId") touristId: String):
            Deferred<MonumentResponse>


    @POST("monuments/add")
    suspend fun addMonument(@Body monument: Monuments)

    @PATCH("tourists/add-to-favourite")
    suspend fun addMonumentToFavourite(@Query("touristId") touristId: String,
                               @Query("monumentId") monumentId: String)


}
private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .baseUrl(BASE_URL)
        .build()

object CityVisitorApi {
    val RETROFIT_SERVICE : CityVisitorApiService by lazy { retrofit.create(CityVisitorApiService::class.java) }
}
