package com.example.memo.widget

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.memo.view.ContentActivity
import com.example.memo.R
import com.example.memo.room.memo

class MemoAdapter : RecyclerView.Adapter<MemoAdapter.Holder>() {

    lateinit var memoList : ArrayList<memo>

    fun setList(list : ArrayList<memo>) {
        if(::memoList.isInitialized) return
        memoList = list
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return memoList.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {

        holder.bind(memoList[position])
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val titleTv = itemView.findViewById<TextView>(R.id.title)
        val timeTv = itemView.findViewById<TextView>(R.id.time)
        fun bind(Memo: memo) {
            titleTv.text = Memo.title
            timeTv.text = Memo.time
            itemView.setOnClickListener {
                val i = Intent(itemView.context, ContentActivity::class.java)
                i.putExtra("content", Memo.content)
                i.putExtra("title", Memo.title)
                i.putExtra("time", Memo.time)

                itemView.context.startActivity(i)
            }
        }
    }
}