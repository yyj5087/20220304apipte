package com.nepplus.a20220304apipte

import android.content.Intent
import android.os.Bundle
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

        binding.btnSignUp.setOnClickListener {
            val myIntent = Intent(mContext,SignActivity::class.java)
            startActivity(myIntent)

        }

        binding.btnLogin.setOnClickListener {
            val inputId = binding.edtId.text.toString()
            val inputPw = binding.edtId.text.toString()
            ServerUtil.postRequestLogin(inputId,inputPw, object : ServerUtil.JsonResponseHandler{
                override fun onResponse(jsonObject: JSONObject) {
                    val code = jsonObject.getInt("code")
                    if(code == 0){
                        runOnUiThread {
                            Toast.makeText(mContext, "로그인 성공!", Toast.LENGTH_SHORT).show()
                        }
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

    }
}