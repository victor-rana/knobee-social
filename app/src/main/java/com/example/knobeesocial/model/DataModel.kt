package com.example.knobeesocial.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName
import android.R.attr.data
import android.R.attr.data








class DataModel {
    @SerializedName("status")
    @Expose
    private var status: String? = null

    @SerializedName("code")
    @Expose
    private var code: Int? = null

    @SerializedName("message")
    @Expose
    private var message: String? = null

    @SerializedName("data")
    @Expose
    private var data: Data? = null

    fun getStatus(): String? {
        return status
    }

    fun setStatus(status: String?) {
        this.status = status
    }

    fun getCode(): Int? {
        return code
    }

    fun setCode(code: Int?) {
        this.code = code
    }

    fun getMessage(): String? {
        return message
    }

    fun setMessage(message: String?) {
        this.message = message
    }

    fun getData(): Data? {
        return data
    }

    fun setData(data: Data?) {
        this.data = data
    }

    class Data {
        @SerializedName("Gallery")
        @Expose
        var gallery: List<List<Any>>? = null
    }

}