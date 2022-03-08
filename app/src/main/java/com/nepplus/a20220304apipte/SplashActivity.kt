package com.nepplus.a20220304apipte

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import org.json.JSONObject

class SplashActivity : BasicActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
    }
    override fun setupEvents() {

        var isTokenOk = false
        ServerUtil.getRequestMyInfo(mContext, object : ServerUtil.JsonResponseHandler{
            override fun onResponse(jsonObject: JSONObject) {
                val code = jsonObject.getInt("code")
                isTokenOk = (code == 200)
            }

        })

        val myHandler = Handler(Looper.getMainLooper())
        myHandler.postDelayed({
        val userAutoLogin = ContextUtil.getAutoLogin(mContext)
            val myIntent: Intent
            if(userAutoLogin && isTokenOk){
                myIntent = Intent(mContext,MainActivity::class.java)
            }
            else{
                myIntent = Intent(mContext,LoginActivity::class.java)
            }
            startActivity(myIntent)
            finish()


        },2500)

    }

    override fun setValues() {

    }


}