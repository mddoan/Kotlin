package com.dangdoan.ddkotlinsamples.webservices

import android.content.Context
import android.util.Log
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.dangdoan.ddkotlinsamples.webservices.requests.CustomRequest

class JsonLoader(context: Context, request: CustomRequest, tag: String, listener: VolleyLoader.LoaderListener,
                 errorListener: VolleyLoader.LoaderErrorListener) : VolleyLoader(context, request.getMethod(),
        request.getUrl(), tag, listener, errorListener) {

    fun loadJsonObjectData() {
        // Create request
        state = STATE_LOADING
        val jsonRequest = JsonObjectRequest(mMethod, mUrl, null, Response.Listener { response ->
            Log.v(TAG, "RESPONSE = $response")
            val vData = LoaderData(response)
            deliverResult(vData)
            state = STATE_DEFAULT
        }, Response.ErrorListener { error ->
            val networkResponse = error.networkResponse
            if (networkResponse != null && networkResponse.data != null &&
                    networkResponse.statusCode == 401) {
                // TODO Handle errors
            } else {
                reportError(error)
                state = STATE_DEFAULT
            }
        })

        jsonRequest.tag = mTag
        jsonRequest.retryPolicy = DefaultRetryPolicy(3000, 1, 2f)
        mVolley.addToRequestQueue(jsonRequest)

    }

    private fun deliverResult(vData: LoaderData) {
        mLoaderListener.onResponse(vData)
    }

    private fun reportError(error: VolleyError) {
        mLoaderErrorListener.onResponseError(error)

    }

    companion object {
        private val TAG = JsonLoader::class.java.simpleName
    }

}
