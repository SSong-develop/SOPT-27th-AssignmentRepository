package com.example.soptseminar.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.soptseminar.R
import com.example.soptseminar.data.remote.model.KakaoSearchDocument
import com.example.soptseminar.databinding.SettingSearchItemBinding

class KakaoSearchAdapter : RecyclerView.Adapter<KakaoSearchAdapter.searchViewHolder>(){
    var searchData = mutableListOf<KakaoSearchDocument>()

    inner class searchViewHolder(private val binding : SettingSearchItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data : KakaoSearchDocument){
            binding.apply {
                kakaoSearchData = data
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): searchViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: SettingSearchItemBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.setting_search_item, parent, false)
        return searchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: searchViewHolder, position: Int) {
        holder.bind(searchData[position])
    }

    override fun getItemCount(): Int = searchData.size

}