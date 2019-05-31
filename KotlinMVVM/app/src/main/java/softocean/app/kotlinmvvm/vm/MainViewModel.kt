package softocean.app.kotlinmvvm.vm

import android.util.Log
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
    private var toastMsgLiveData = SingleLiveEvent<String>()
    private var loadingShowLiveData = MutableLiveData<Boolean>()

    init {
        getCityList()           // ViewModel is created only once during Activity/Fragment lifetime
    }

    fun getLoadingShowLiveData() : MutableLiveData<Boolean>{
        return loadingShowLiveData
    }

    fun getCityLiveData() : MutableLiveData<List<City>>{
        return cityLiveData
    }

    fun getToastMsgLiveData() : SingleLiveEvent<String>{
        return toastMsgLiveData
    }

    fun getCityList(){
        loadingShowLiveData.postValue(true)
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                var request = repository.getApiService().getCityList()
                try{
                    var respone = request.await()
                    if(respone.isSuccessful){
                        cityLiveData.postValue(respone.body())
                    }else{
                        toastMsgLiveData.postValue("api error");
                    }
                }catch (e : Exception){
                    toastMsgLiveData.postValue("api error with exception");
                }
                loadingShowLiveData.postValue(false)
            }
        }
    }

}