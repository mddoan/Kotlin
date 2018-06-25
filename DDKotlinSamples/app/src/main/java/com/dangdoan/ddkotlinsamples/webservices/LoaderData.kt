package com.dangdoan.ddkotlinsamples.webservices

import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class LoaderData{
    var metaData: MetaData? = null
    var payLoadData: String? = null

    constructor(response: JSONObject?){
        /*
        The response might have meta data and payload separately
         */
        readMetaData()
        readPayload()

    }

    fun readMetaData(){

    }

    fun readPayload(){

    }

    fun getMetaDataObject(): MetaData?{
        return metaData
    }

    fun getPayloadJsonObject(): JSONObject?{
        try {
            return JSONObject(payLoadData)
        }catch (e: JSONException){
            e.printStackTrace()
        }
        return null
    }

    fun getPayloadJsonArray(): JSONArray?{
        try {
            return JSONArray(payLoadData)
        }catch (e: JSONException){
            e.printStackTrace()
        }
        return null
    }

}