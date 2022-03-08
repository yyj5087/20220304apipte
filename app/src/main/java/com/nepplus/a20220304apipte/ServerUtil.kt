package com.nepplus.a20220304apipte

import android.content.Context
import android.util.Log
import okhttp3.*
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import org.json.JSONObject
import java.io.IOException

class ServerUtil {

    interface JsonResponseHandler {
        fun onResponse(jsonObject: JSONObject)
    }

    companion object {
        private val BASE_URL = "http://54.180.52.26"
        fun postRequestLogin(id: String, pw: String, handler: JsonResponseHandler?) {
            val urlString = "${BASE_URL}/user"

            val formData = FormBody.Builder()
                .add("email", id)
                .add("password", pw)
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
                    Log.d("서버테스트", jsonObj.toString())

                    handler?.onResponse(jsonObj)
                }

            })
        }

        fun putRequestSignUp(
            email: String,
            pw: String,
            nickname: String,
            handler: JsonResponseHandler?
        ) {
            val urlString = "${BASE_URL}/user"
            val formData = FormBody.Builder()
                .add("email", email)
                .add("password", pw)
                .add("nick_name", nickname)
                .build()
            val request = Request.Builder()
                .url(urlString)
                .put(formData)
                .build()
            val client = OkHttpClient()
            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {

                }

                override fun onResponse(call: Call, response: Response) {
                    val bodyString = response.body!!.string()
                    val jsonObj = JSONObject(bodyString)
                    Log.d("서버응답", jsonObj.toString())
                    handler?.onResponse(jsonObj)
                }

            })
        }

        //        이메일 or 닉네임 중복 검사 함수
        fun getRequestDuplicatedCheck(
            type: String,
            inputValue: String,
            handler: JsonResponseHandler?
        ) {

//            1) 어느 주소로 가야하는가? + 어떤 파라미터를 첨부하는가? 도 주소에 같이 포함.
//            => 라이브러리의 도움을 받자. HttpUrl 클래스 (OkHttp 소속)

            val urlBuilder = "${BASE_URL}/user_check".toHttpUrlOrNull()!!.newBuilder()
                .addEncodedQueryParameter("type", type)
                .addEncodedQueryParameter("value", inputValue)
                .build()

            val urlString = urlBuilder.toString()

            val request = Request.Builder()
                .url(urlString)
                .get()
                .build()
            val client = OkHttpClient()
            client.newCall(request).enqueue(object : Callback{
                override fun onFailure(call: Call, e: IOException) {

                }

                override fun onResponse(call: Call, response: Response) {
                    val bodyString = response.body!!.string()
                    val jsonObj = JSONObject(bodyString)
                    handler?.onResponse(jsonObj)
                }

            })


        }
        fun getRequestMainInfo(context : Context, handler: JsonResponseHandler?){
            val urlBuilder ="${BASE_URL}/v2/main_info".toHttpUrlOrNull()!!.newBuilder()
                .build()
            val urlString = urlBuilder.toString()

            val request = Request.Builder()
                .url(urlString)
                .get()
                .header("X-Http-Token", ContextUtil.getToken(context))
                .build()

            val client = OkHttpClient()
            client.newCall(request).enqueue(object : Callback{
                override fun onFailure(call: Call, e: IOException) {

                }

                override fun onResponse(call: Call, response: Response) {
                    val jsonObj = JSONObject(response.body!!.string())
                    handler?.onResponse(jsonObj)
                }

            })
        }

        fun getRequestMyInfo(context : Context, handler: JsonResponseHandler?){
            val urlBuilder ="${BASE_URL}/user_info".toHttpUrlOrNull()!!.newBuilder()
                .build()
            val urlString = urlBuilder.toString()

            val request = Request.Builder()
                .url(urlString)
                .get()
                .header("X-Http-Token", ContextUtil.getToken(context))
                .build()

            val client = OkHttpClient()
            client.newCall(request).enqueue(object : Callback{
                override fun onFailure(call: Call, e: IOException) {

                }

                override fun onResponse(call: Call, response: Response) {
                    val jsonObj = JSONObject(response.body!!.string())
                    handler?.onResponse(jsonObj)
                }

            })
        }

    }
}