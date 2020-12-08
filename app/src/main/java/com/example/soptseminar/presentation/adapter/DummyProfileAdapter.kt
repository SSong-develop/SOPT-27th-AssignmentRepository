package com.example.soptseminar.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.soptseminar.R
import com.example.soptseminar.data.remote.model.DummyUser
import com.example.soptseminar.databinding.DummyItemBinding

class DummyProfileAdapter : RecyclerView.Adapter<DummyProfileAdapter.DummyViewHolder>() {
    var dummyData = mutableListOf<DummyUser>()

    inner class DummyViewHolder(private val binding: DummyItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
            fun bind(data : DummyUser){
                binding.apply {
                    dummyData = data
                }
            }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DummyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: DummyItemBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.dummy_item, parent, false)
        return DummyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DummyViewHolder, position: Int) {
        holder.bind(dummyData[position])
    }

    override fun getItemCount(): Int = dummyData.size
}