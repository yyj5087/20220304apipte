package com.nepplus.a20220304apipte

import android.util.Log
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class ServerUtil {

    interface JsonResponseHandler{
        fun onResponse(jsonObject: JSONObject)
    }

    companion object{
        private val BASE_URL = "http://54.180.52.26"
        fun postRequestLogin(id: String,pw :String, handler: JsonResponseHandler?){
            val urlString = "${BASE_URL}/user"

            val formData = FormBody.Builder()
                .add("email",id)
                .add("password",pw)
                .build()
            val request = Request.Builder()
                .url(urlString)
                .post(formData)
                .build()
            val client = OkHttpClient()
            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {

                }

                override fun onResponse(call: Call, response: Response) {
                    val bodyString = response.body!!.string()
                    val jsonObj = JSONObject(bodyString)
                    Log.d("서버테스트",jsonObj.toString())

                }

            })
        }
        fun putRequestSignUp(email: String, pw: String, nickname: String, handler: JsonResponseHandler?){
            val urlString = "${BASE_URL}/user"
            val formData = FormBody.Builder()
                .add("email",email)
                .add("password",pw)
                .add("nick_name",nickname)
                .build()
            val request = Request.Builder()
                .url(urlString)
                .put(formData)
                .build()
            val client = OkHttpClient()
            client.newCall(request).enqueue(object : Callback{
                override fun onFailure(call: Call, e: IOException) {

                }

                override fun onResponse(call: Call, response: Response) {
                    val bodyString = response.body!!.string()
                    val jsonObj = JSONObject(bodyString)
                    Log.d("서버응답",jsonObj.toString())
                    handler?.onResponse(jsonObj)
                }

            })
        }
    }
}