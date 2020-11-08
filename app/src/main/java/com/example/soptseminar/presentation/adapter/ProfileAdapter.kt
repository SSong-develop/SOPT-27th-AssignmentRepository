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
import com.example.soptseminar.presentation.activity.MainActivity
import com.example.soptseminar.presentation.model.ProfileData
import kotlinx.android.synthetic.main.profile_item.view.*


// ViewHolder의 역할을 다시 한번 생각해보아야 합니다.
// Android의 Adapter 패턴은 Adapter가 데이터를 관리하고 리스트 형태의 View에게 데이터를 쏴주는 패턴입니다.
// 언어를 좀 정확하게 쓸필요가 있어요

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
            // TODO : How to make ClickListener
            // Using navigation, make ClickListener!!!
            // navigate은 할건데, ViewModel을 통해서 onCreate을 하게 된다.
            // ViewModel Factory 패턴을 사용하면 띄울 때 딱 한번하게 되니까 괜찮지 않을까?
            // Custom ViewModel Factory!

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