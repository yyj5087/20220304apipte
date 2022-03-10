package com.nepplus.a20220304apipte.datas

import org.json.JSONObject

class UserData {
    var id = 0
    var email = ""
    var nickname = ""
    companion object{
        fun getUserDataFromJson(jsonObject: JSONObject) : UserData{
            val userData = UserData()
            userData.id = jsonObject.getInt("id")
            userData.email = jsonObject.getString("email")
            userData.nickname = jsonObject.getString("nick_name")
            return userData
        }
    }
}