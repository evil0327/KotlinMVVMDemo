package softocean.app.kotlinmvvm.repo

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiRepository {
    private var apiService : Api = Retrofit.Builder()
        .baseUrl("https://api.myjson.com/bins/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()
        .create(Api::class.java)

    fun getApiService(): Api {
        return apiService
    }

}