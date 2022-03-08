package com.nepplus.a20220304apipte

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
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

        binding.btnNicknameCheck.setOnClickListener {
            val inputNickname = binding.edtNickname.text.toString()

            ServerUtil.getRequestDuplicatedCheck("NICK_NAME",inputNickname, object : ServerUtil.JsonResponseHandler{
                override fun onResponse(jsonObject: JSONObject) {
                    val code = jsonObject.getInt("code")
                    runOnUiThread {
                        when(code){
                            200->{
                                binding.txtNicknameCheckResult.text = "사용해도 좋은 닉네임 입니다."
                            }
                            else->{
                                binding.txtNicknameCheckResult.text = "다른 닉네임을 적어주십시오"
                            }
                        }
                    }
                }

            })
        }
        binding.edtNickname.addTextChangedListener {
            binding.txtNicknameCheckResult.text = "중복 확인을 해주십시오"
        }



        binding.btnEmailCheck.setOnClickListener {
//            입력 이메일 값 추출
            val inputEmail = binding.edtEmail.text.toString()
//            서버 중복확인 기능(/user_check - GET) API 활용=> ServerUtil에 함수 추가, 가져다 활용
//            그 응답 CODE값에 따라 다른 문구 배치.
            ServerUtil.getRequestDuplicatedCheck("EMAIL",inputEmail, object : ServerUtil.JsonResponseHandler{
                override fun onResponse(jsonObject: JSONObject) {
                    val code = jsonObject.getInt("code")
                    runOnUiThread {
                        when(code){
                            200->{
                                binding.txtEmailCheckResult.text = "사용해도 좋은 이메일 입니다."
                            }
                            else->{
                                binding.txtEmailCheckResult.text = "다른 이메일로 작성해주십시오"
                            }
                        }
                    }
                }

            })

       }
        binding.edtEmail.addTextChangedListener {
            binding.txtEmailCheckResult.text = "중복 확인을 해주십시오"
        }

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