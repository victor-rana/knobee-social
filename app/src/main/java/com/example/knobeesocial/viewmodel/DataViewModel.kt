package com.example.knobeesocial.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.knobeesocial.Singleton
import com.example.knobeesocial.data.DataFactory
import com.example.knobeesocial.data.DataService
import com.example.knobeesocial.model.DataModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

class DataViewModel : ViewModel() {

    var imagesLiveData: MutableLiveData<DataModel> = MutableLiveData()
    private var context: Context? = null
    var compositeDisposable: CompositeDisposable? = null

    fun loadData(){
        Log.d("TAG", "loadData: ")
        compositeDisposable = CompositeDisposable()
        fetchData()
    }

    private fun fetchData(){
        Log.d("TAG", "fetchAllApps: ")
        val singleton: Singleton? = Singleton.get()
        val dataService: DataService? = singleton!!.getDataService()

        val disposable: Disposable? = dataService?.fetchData(DataFactory().URL_FETCH_DATA)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.doOnError(Consumer { t ->
                Log.d("TAG", "fetchData Error ${t.localizedMessage}")
            })
            ?.subscribe(Consumer { t ->
                Log.d("TAG", "fetchData Response $t")
                changeDataSet(t)
            })

        if (disposable != null) {
            compositeDisposable?.add(disposable)
        }
    }


    fun changeDataSet(dataModel: DataModel){
        imagesLiveData.value = dataModel
    }

    private fun unSubscribeFromObservable() {
        if (compositeDisposable != null && !compositeDisposable!!.isDisposed) {
            compositeDisposable!!.dispose()
        }
    }

    fun reset() {
        unSubscribeFromObservable()
        compositeDisposable = null
        context = null
    }

}