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

// 그렇다면 RecyclerView의 ViewHolder는 과연 어떤 역할을 할까요
// 코드를 보면 대충 예상이 되겠지만
// View를 매개변수로 받아서 , 이때 View는 리스트에 보여질 아이템의 View입니다.
// 그 View에게 알맞은 데이터를 쏴주는 역항을 합니다.
// 예로 user1은 "송훈기","1" 이라는 값을 가지고 user2는 "앵앵","2"라고 가정합시다
// 각각 데이터 형태는 똑같고 안의 내용물만 다르기 때문에 이를 리스트 형태로 보여주는 것이 효과적일 것입니다.
// 그럼 이 안의 내용물을 우리가 보여주고자 하는 리스트의 아이템 형태로 연결(bind)해주어야 합니다
// 그 역활을 ViewHolder가 하는 것입니다.

// 자, 그렇다면 itme이 클릭이 되었을 때 상세화면으로 넘어가야하는 이벤트를 생각해봅시다
// 먼저 첫번쨰로 click되는 대상은 리스트에 보여지는 아이템들 입니다.
// 기존에 ViewHolder는 itemView를 매개변수로 받아 보여준다고 애기했습니다.
// 그렇다면, clickListener를 달아줘야 하는 시점은 ViewHolder에서가 되는 것입니다.
// 그래서 bind 함수에 onClickListner를 추가해 이를 itemView에 달아주었습니다.

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