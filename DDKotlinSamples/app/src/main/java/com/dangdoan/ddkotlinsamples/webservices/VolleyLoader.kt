package com.dangdoan.ddkotlinsamples.webservices

import android.content.Context
import com.android.volley.VolleyError

open class VolleyLoader{
    val STATE_DEFAULT = 0
    val STATE_LOADING = 1
    lateinit var mVolley: VolleySingleton

    protected lateinit var mLoaderListener: LoaderListener
    protected lateinit var mLoaderErrorListener: LoaderErrorListener
    protected var mMethod: Int = 0
    protected lateinit var mUrl: String
    protected lateinit var mContext: Context
    protected lateinit var mTag: String
    protected var state: Int = 0

    constructor(context: Context, method: Int, url: String, tag: String, loaderListener: LoaderListener,
                loaderErrorListener: LoaderErrorListener){
        mContext = context
        mUrl = url
        mTag = tag
        mLoaderListener = loaderListener
        mLoaderErrorListener = loaderErrorListener
        mVolley = VolleySingleton.getInstance(mContext)
    }

    interface LoaderListener {
        fun onResponse(volleyLoaderData: LoaderData)
    }

    interface LoaderErrorListener {
        fun onResponseError(error: VolleyError)
    }

    fun cancelLoadRequest(mTag: String) {
        mVolley.requestQueue.cancelAll(mTag)
    }
}