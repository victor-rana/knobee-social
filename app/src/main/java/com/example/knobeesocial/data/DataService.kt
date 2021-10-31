package com.example.knobeesocial.data
import com.example.knobeesocial.model.DataModel
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface DataService {
    @GET
    fun fetchData(@Url url: String): Observable<DataModel>

}