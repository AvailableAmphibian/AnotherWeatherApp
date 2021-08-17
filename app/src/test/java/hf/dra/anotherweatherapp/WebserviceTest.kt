package hf.dra.anotherweatherapp

import hf.dra.anotherweatherapp.service.RetrofitInstance
import org.hamcrest.CoreMatchers.notNullValue
import org.hamcrest.CoreMatchers.nullValue
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class WebserviceTest {
    @Test
    fun getCityById(){
        //Retrieves Takahashi-shi in Japan
        val takahashi = RetrofitInstance.OPEN_WEATHER.getByCityId(1851137).execute()
        assertThat(takahashi.body(), notNullValue())
    }
    @Test
    fun getCityById2(){
        //Retrieves Takahashi-shi in Japan
        val takahashi = RetrofitInstance.OPEN_WEATHER.getByCityId(-3).execute()
        assertThat(takahashi.body(), nullValue())
    }
}