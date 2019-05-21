package softocean.app.kotlinmvvm

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.*
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Response
import softocean.app.kotlinmvvm.repo.ApiRepository
import softocean.app.kotlinmvvm.vm.MainViewModel
import retrofit2.mock.Calls
import softocean.app.kotlinmvvm.repo.Api
import softocean.app.kotlinmvvm.vo.City

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class MainViewModelTest {
    private val testContext = TestCoroutineContext()

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesMainDispatcherRule = ViewModelScopeMainDispatcherRule(testContext)
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var mainViewModel: MainViewModel
    private lateinit var apiRepository: ApiRepository

    private var fakeCityList = listOf(City("1", "tokyo", "", ""), City("2", "osaka", "", ""))

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        apiRepository = Mockito.mock(ApiRepository::class.java)
        mainViewModel = MainViewModel(apiRepository)

        Mockito.`when`(apiRepository.getApiService()).
            thenReturn(Mockito.mock(Api::class.java))

        Mockito.`when`(apiRepository.getApiService().getCityList()).
            thenReturn(CompletableDeferred(Response.success(fakeCityList)))
    }

    @Test
    fun test_fetch_city_list_equal() {
        mainViewModel.getCityList()

        testContext.triggerActions()

        assertEquals(LiveDataTestUtil.getValue(mainViewModel.getCityLiveData()).size, fakeCityList.size)
    }


    @After
    fun teardown() {
        // reset main after the test is done
        Dispatchers.resetMain()
    }
}
