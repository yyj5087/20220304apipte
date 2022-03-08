package com.nepplus.a20220304apipte

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.nepplus.a20220304apipte.databinding.ActivityLoginBinding
import org.json.JSONObject

class LoginActivity : BasicActivity() {
    lateinit var binding : ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_login)
        setupEvents()
        setValues()
    }
    override fun setupEvents(){

        binding.autoLoginCheckBox.setOnCheckedChangeListener { compoundButton, isChecked ->
            Log.d("체크값변경","${isChecked}")
            ContextUtil.setAutoLogin(mContext,isChecked)
        }


        binding.btnSignUp.setOnClickListener {
            val myIntent = Intent(mContext,SignActivity::class.java)
            startActivity(myIntent)

        }

        binding.btnLogin.setOnClickListener {
            val inputId = binding.edtId.text.toString()
            val inputPw = binding.edtPassword.text.toString()
            ServerUtil.postRequestLogin(inputId,inputPw, object : ServerUtil.JsonResponseHandler{
                override fun onResponse(jsonObject: JSONObject) {
                    val code = jsonObject.getInt("code")
                    if(code == 200){
                        val dataObj = jsonObject.getJSONObject("data")
                        val userObj = dataObj.getJSONObject("user")
                        val nickname = userObj.getString("nick_name")

                        runOnUiThread {
                            Toast.makeText(mContext, "${nickname}님 환영합니다!", Toast.LENGTH_SHORT).show()
                        }
                        val token = dataObj.getString("token")
                        ContextUtil.setToken(mContext, token)

//                        메인화면으로 진입 => 클래스의 객체화(UI 동작 X)
                        val myIntent = Intent(mContext,MainActivity::class.java)
                        startActivity(myIntent)

                    }          
                    else{
                        val message = jsonObject.getString("message")
                        runOnUiThread {
                            Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show()
                        }
                        
                        
                    }
                }
                })
            }

    }
    override fun setValues(){

        binding.autoLoginCheckBox.isChecked = ContextUtil.getAutoLogin(mContext)
    }
}