package com.nepplus.a20220304apipte.datas

import org.json.JSONObject

class ReplyData(
    var id: Int,
    var content: String
) {
    var whiter = UserData()
    var selectedSide = SideData()

    companion object{
        fun getReplyDataFromJson(jsonObject: JSONObject) : ReplyData{

            val replyData = ReplyData()
            replyData.id = jsonObject.getInt("id")
        }
    }
}