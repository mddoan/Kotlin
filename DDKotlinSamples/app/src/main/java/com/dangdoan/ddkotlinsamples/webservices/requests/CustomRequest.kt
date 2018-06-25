package com.dangdoan.ddkotlinsamples.webservices.requests

import com.android.volley.Request

class CustomRequest{
    private val APP_KEY = "8820fc98bcf759a40c5515433e13e777"
    var mQueryParams: MutableMap<String, String> = HashMap<String, String>()
    var mMethod: Int = Request.Method.GET
    lateinit var mUrl: String

    constructor(url: String, method: Int, queryParams: MutableMap<String, String>){
        mQueryParams = queryParams
        mUrl = url
        mMethod = method
    }

    fun getUrl(): String {
        val stringBuilder = StringBuilder(mUrl)
        val it = mQueryParams.entries.iterator()
        var i = 0
        while (it.hasNext()) {
            val pair = it.next() as java.util.Map.Entry<*, *>
            if (i == 0 && !stringBuilder.toString().contains("?")) {
                stringBuilder.append("?" + pair.key.toString() + "=" + pair.value.toString())
            } else {
                stringBuilder.append("&" + pair.key.toString() + "=" + pair.value.toString())
            }
            it.remove() // avoids a ConcurrentModificationException
            i++
        }
        stringBuilder.append("&appid=$APP_KEY")
        val completeUrl = stringBuilder.toString()
        return completeUrl
    }

    fun getMethod(): Int{
        return mMethod
    }

    fun getBaseUrl(): String {
        return mUrl
    }
}