package com.example.soptseminar.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.soptseminar.R
import com.example.soptseminar.model.ProfileData
import com.example.soptseminar.ui.HomeActivity
import kotlinx.android.synthetic.main.profile_item.view.*


// ViewHolder의 역할을 다시 한번 생각해보아야 합니다.
// Android의 Adapter 패턴은 Adapter가 데이터를 관리하고 리스트 형태의 View에게 데이터를 쏴주는 패턴입니다.
// 언어를 좀 정확하게 쓸필요가 있어요
class ProfileAdapter(
    private val context : Context,
) : RecyclerView.Adapter<ProfileAdapter.ProfileViewHolder>(){
    var data = mutableListOf<ProfileData>()

    class ProfileViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        fun bind(data : ProfileData, listener : View.OnClickListener){
            itemView.title_txt.text = data.title
            itemView.subtitle_txt.text = data.subTitle
            itemView.setOnClickListener(listener)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProfileViewHolder {
        val inflateView = LayoutInflater.from(context).inflate(R.layout.profile_item,parent,false)
        return ProfileViewHolder(inflateView)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(
        holder: ProfileViewHolder,
        position: Int
    ) {
        val listener = View.OnClickListener {
            val activity = context as HomeActivity
            activity.detailPage(data[position])
        }
        holder.bind(data[position],listener)
    }

    fun moveItem(from : Int, to : Int) : Boolean{
        val tempData = data.get(from)
        data.removeAt(from)
        data.add(to,tempData)
        notifyItemMoved(from,to)
        notifyItemChanged(from,to)
        return true
    }

    fun removeItem(position : Int){
        data.removeAt(position)
        notifyItemRemoved(position)
    }
}