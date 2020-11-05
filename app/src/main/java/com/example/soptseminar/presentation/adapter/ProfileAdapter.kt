package com.example.soptseminar.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.soptseminar.R
import com.example.soptseminar.databinding.ProfileItemBinding
import com.example.soptseminar.presentation.model.ProfileData
import kotlinx.android.synthetic.main.profile_item.view.*


// ViewHolder의 역할을 다시 한번 생각해보아야 합니다.
// Android의 Adapter 패턴은 Adapter가 데이터를 관리하고 리스트 형태의 View에게 데이터를 쏴주는 패턴입니다.
// 언어를 좀 정확하게 쓸필요가 있어요

// commit : Adapter의 생성자 인자 중 context를 제외시킴
class ProfileAdapter(
    private val context : Context,
) : RecyclerView.Adapter<ProfileAdapter.ProfileViewHolder>(){
    var data = mutableListOf<ProfileData>()

    class ProfileViewHolder(val binding : ProfileItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data : ProfileData, listener : View.OnClickListener){
            // Databinding의 profileData에 기존 data를 넘겨준다.
            binding.profileData = data
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProfileViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding : ProfileItemBinding = DataBindingUtil.inflate(layoutInflater,R.layout.profile_item,parent,false)
        return ProfileViewHolder(binding)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(
        holder: ProfileViewHolder,
        position: Int
    ) {
        val listener = View.OnClickListener {view : View ->
            view.findNavController().navigate(R.id.action_homeFragment_to_detailFragment)
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