package com.nepplus.a20220304apipte.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.nepplus.a20220304apipte.R
import com.nepplus.a20220304apipte.datas.TopicData

class TopicAdapter(
   val mContext: Context,
   resId: Int,
   val mList: List<TopicData>

) : ArrayAdapter<TopicData>(mContext,resId,mList){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var tempRow = convertView
        if (tempRow == null){
            tempRow = LayoutInflater.from(mContext).inflate(R.layout.topic_list_item,null)
        }
        var row = tempRow!!

        val data = mList[position]
        val txtTitle = row.findViewById<TextView>(R.id.txtTitle)
        val imgBackground = row.findViewById<ImageView>(R.id.imgBackground)
        val txtReplyCount = row.findViewById<TextView>(R.id.txtReplyContent)
        txtTitle.text = data.title

        Glide.with(mContext).load(data.imageURL).into(imgBackground)
        txtReplyCount.text = "${data.replyCount}명 참여중"


        return row
    }
}
