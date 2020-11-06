package com.example.soptseminar.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.soptseminar.R
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {
    private lateinit var title : String
    private lateinit var subTitle : String
    private lateinit var date : String
    private lateinit var detail : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val intent = intent
        title = intent.getStringExtra("title").toString()
        subTitle = intent.getStringExtra("subtitle").toString()
        date = intent.getStringExtra("date").toString()
        detail = intent.getStringExtra("detail").toString()

        detail_title.text = title
        detail_subtitle.text = subTitle
        detail_date.text = date
        detail_txt.text = detail
    }
}
