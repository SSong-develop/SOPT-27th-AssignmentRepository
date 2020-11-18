package com.example.soptseminar.presentation.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.soptseminar.R
import com.example.soptseminar.databinding.ProfileItemBinding
import com.example.soptseminar.presentation.model.ProfileData

class ProfileAdapter(
    val clickListener : ProfileListener
) : RecyclerView.Adapter<ProfileAdapter.ProfileViewHolder>(){
    var data = mutableListOf<ProfileData>()

    // TODO : ClickListener 다시 생각
    class ProfileViewHolder(val binding : ProfileItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: ProfileData, clickListener: ProfileListener){
            binding.apply {
                profileData = data
                executePendingBindings()
                this.clickListener = clickListener
            }
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
        holder.bind(data[position],clickListener)
    }

    class ProfileListener(val clickListener : (userId : Long) -> Unit){
        fun onClick(profile : ProfileData) = clickListener(profile.userId)
    }

    // TODO : Make this function to Interface
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