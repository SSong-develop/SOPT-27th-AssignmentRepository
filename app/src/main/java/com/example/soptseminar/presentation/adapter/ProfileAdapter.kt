package com.example.soptseminar.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.soptseminar.R
import com.example.soptseminar.databinding.ProfileItemBinding
import com.example.soptseminar.model.ProfileData

class ProfileAdapter(
    private val itemClickListener: OnItemClickListener
) : RecyclerView.Adapter<ProfileAdapter.ProfileViewHolder>() {
    var data = mutableListOf<ProfileData>()

    class ProfileViewHolder(val binding: ProfileItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: ProfileData, clickListener: OnItemClickListener) {
            binding.apply {
                profileData = data
                executePendingBindings()
            }
            binding.root.setOnClickListener {
                clickListener.onItemClicked(data)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProfileViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ProfileItemBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.profile_item, parent, false)
        return ProfileViewHolder(binding)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(
        holder: ProfileViewHolder,
        position: Int
    ) {
        holder.bind(data[position], itemClickListener)
    }

    fun moveItem(from: Int, to: Int): Boolean {
        val tempData = data.get(from)
        data.removeAt(from)
        data.add(to, tempData)
        notifyItemMoved(from, to)
        notifyItemChanged(from, to)
        return true
    }

    fun removeItem(position: Int) {
        data.removeAt(position)
        notifyItemRemoved(position)
    }

}

interface OnItemClickListener {
    fun onItemClicked(profileData: ProfileData)
}