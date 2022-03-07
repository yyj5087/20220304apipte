package com.nepplus.a20220304apipte

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.nepplus.a20220304apipte.databinding.ActivitySignBinding
import org.json.JSONObject

class SignActivity : BasicActivity() {
    lateinit var binding : ActivitySignBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_sign)
        setupEvents()
        setValues()

    }
    override fun setupEvents() {

        binding.btnSignUp.setOnClickListener {
            val inputEmail = binding.edtEmail.text.toString()
            val inputPassword = binding.edtPassword.text.toString()
            val inputNickname = binding.edtNickname.text.toString()

            ServerUtil.putRequestSignUp(
                inputEmail,
                inputPassword,
                inputNickname,
                object : ServerUtil.JsonResponseHandler {
                    override fun onResponse(jsonObject: JSONObject) {
                        val code = jsonObject.getInt("code")
                        if(code == 200){
                            val dataObj = jsonObject.getJSONObject("data")
                            val userObj = dataObj.getJSONObject("user")
                            val nickname = userObj.getString("nick_name")
                            runOnUiThread {
                                Toast.makeText(mContext, "${nickname}님 가입축하드립니다.", Toast.LENGTH_SHORT).show()
                            }
                        }
                        else{
                            val message = jsonObject.getString("message")
                            runOnUiThread {
                                Toast.makeText(mContext, "실패 사유 : ${message} ", Toast.LENGTH_SHORT).show()
                            }
                            finish()

                        }
                    }

                }
            )
        }
    }

    override fun setValues() {

    }

}