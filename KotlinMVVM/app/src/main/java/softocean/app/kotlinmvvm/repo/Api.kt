package softocean.app.kotlinmvvm.repo

import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import softocean.app.kotlinmvvm.vo.City

interface Api {
    @GET("111svk")
    fun getCityList(): Deferred<Response<List<City>>>
}