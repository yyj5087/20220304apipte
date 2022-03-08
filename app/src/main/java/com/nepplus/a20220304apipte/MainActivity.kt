package com.nepplus.a20220304apipte

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import com.nepplus.a20220304apipte.Adapters.TopicAdapter
import com.nepplus.a20220304apipte.databinding.ActivityMainBinding
import com.nepplus.a20220304apipte.datas.TopicData

class MainActivity : BasicActivity() {

    val mTopicList = ArrayList<TopicData>()
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        setupEvents()
        setValues()
    }
    override fun setupEvents() {
        binding.btnLogin.setOnClickListener {
            val alert = AlertDialog.Builder(mContext)
                .setTitle("로그아웃")
                .setMessage("정말 로그아웃을 하겠습니까?")
                .setPositiveButton("확인",DialogInterface.OnClickListener { dialogInterface, i ->
                    ContextUtil.setToken(mContext,"")
                    val myIntent = Intent(mContext,MainActivity::class.java)
                    startActivity(myIntent)
                })
                .setNegativeButton("취소",null)
                .show()
        }
    }

    override fun setValues() {

    }


}