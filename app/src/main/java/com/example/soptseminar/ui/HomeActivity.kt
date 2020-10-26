package com.example.soptseminar.ui

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ItemTouchHelper.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.soptseminar.R
import com.example.soptseminar.adapter.ProfileAdapter
import com.example.soptseminar.model.ProfileData
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity(){
    private lateinit var profileAdapter : ProfileAdapter

    private var isChecked : Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        profileAdapter = ProfileAdapter(this)

        homeRecyclerView.apply {
            adapter = profileAdapter
            layoutManager = LinearLayoutManager(this@HomeActivity)
            isChecked = false
        }

        profileAdapter.data = mutableListOf(
            ProfileData("송훈기0","010-1234-1234"),
            ProfileData("송훈기1","010-1234-1235"),
            ProfileData("송훈기2","010-1234-1236"),
            ProfileData("송훈기3","010-1234-1237"),
            ProfileData("송훈기4","010-1234-1238"),
            ProfileData("송훈기5","010-1234-1239"),
            ProfileData("송훈기6","010-1234-1230"),
            ProfileData("송훈기7","010-1234-1231"),
            ProfileData("송훈기8","010-1234-1232"),
            ProfileData("송훈기9","010-1234-1233"),
            ProfileData("송훈기10","010-1234-1234"),
            ProfileData("송훈기11","010-1234-1235"),
            ProfileData("송훈기12","010-1234-1236"),
            ProfileData("송훈기13","010-1234-1237"),
            ProfileData("송훈기14","010-1234-1238"),
            ProfileData("송훈기15","010-1234-1239"),
            ProfileData("송훈기16","010-1234-1230"),
            ProfileData("송훈기17","010-1234-1231"),
            ProfileData("송훈기18","010-1234-1232"),
            ProfileData("송훈기19","010-1234-1233"),
            ProfileData("송훈기20","010-1234-1234")
        )
        profileAdapter.notifyDataSetChanged()
        homeRecyclerView.setHasFixedSize(true)

        // TouchHelper
        val itemTouchHelper = ItemTouchHelper(object :ItemTouchHelper.SimpleCallback(UP or DOWN , START or END){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return profileAdapter.moveItem(viewHolder.adapterPosition,target.adapterPosition)
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                profileAdapter.removeItem(viewHolder.adapterPosition)
            }

            override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
                super.onSelectedChanged(viewHolder, actionState)
                if(actionState == ACTION_STATE_DRAG){
                    viewHolder?.itemView?.setBackgroundColor(Color.LTGRAY)
                }
            }

            override fun clearView(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder
            ) {
                super.clearView(recyclerView, viewHolder)
                viewHolder.itemView.setBackgroundColor(Color.WHITE)
            }
        })
        itemTouchHelper.attachToRecyclerView(homeRecyclerView)

        act_btn.setOnClickListener {
            homeRecyclerView.apply {
                if(isChecked == false){
                    layoutManager = GridLayoutManager(this@HomeActivity,2)
                    isChecked = true
                } else {
                    layoutManager = LinearLayoutManager(this@HomeActivity)
                    isChecked = false
                }
            }
        }
    }

    fun detailPage(data: ProfileData){
        val intent = Intent(this,DetailActivity::class.java)
        intent.putExtra("title",data.title)
        intent.putExtra("subtitle",data.subTitle)
        intent.putExtra("date","2020-10-23")
        intent.putExtra("detail","살아있는 누군가입니다.")
        startActivity(intent)
    }
}