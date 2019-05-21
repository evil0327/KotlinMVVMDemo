package softocean.app.kotlinmvvm.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import softocean.app.kotlinmvvm.repo.ApiRepository
import softocean.app.kotlinmvvm.vo.City
import javax.inject.Inject

class MainViewModel  @Inject constructor(private val repository: ApiRepository) :  ViewModel(){

    private var cityLiveData = MutableLiveData<List<City>>()

    fun getCityLiveData() : MutableLiveData<List<City>>{
        return cityLiveData
    }

    fun getCityList(){
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                var request = repository.getApiService().getCityList()
                var respone = request.await()
                if(respone.isSuccessful){
                    cityLiveData.postValue(respone.body())
                }
            }
        }
    }
}