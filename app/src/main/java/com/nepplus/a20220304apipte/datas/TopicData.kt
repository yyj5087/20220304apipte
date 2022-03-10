package com.nepplus.a20220304apipte.datas

import org.json.JSONObject

class TopicData {
    var id = 0
    var title = ""
    var imageURL = ""
    var replyCount = 0

    val sideList = ArrayList<SideData>()

    var mySeletedSide : SideData? = null

    companion object{
        fun getTopicDataFromJson(jsonObject: JSONObject) : TopicData{
            val topicData = TopicData()
            topicData.id = jsonObject.getInt("id")
            topicData.title = jsonObject.getString("title")
            topicData.imageURL = jsonObject.getString("img_url")
            topicData.replyCount =jsonObject.getInt("reply_count")

            val sidesArr = jsonObject.getJSONArray("sides")
            for (i in 0 until sidesArr.length()){
                val sideObj = sidesArr.getJSONObject(i)
                val sideData = SideData.getSideDataFromJson(sideObj)
                topicData.sideList.add(sideData)
            }
            if (!jsonObject.isNull("my_side")){
                val mySideObj = jsonObject.getJSONObject("my_side")
                topicData.mySeletedSide = SideData.getSideDataFromJson(mySideObj)
            }
            else{

            }
            return topicData
        }

    }

}