package com.example.soptseminar.utils

import com.example.soptseminar.model.ProfileData

class MakeDummy {
    companion object {
        fun makeDummyData(): MutableList<ProfileData> {
            val data = mutableListOf(
                ProfileData(1, "송훈기0", "010-1234-1234"),
                ProfileData(2, "송훈기1", "010-1234-1235"),
                ProfileData(3, "송훈기2", "010-1234-1236"),
                ProfileData(4, "송훈기3", "010-1234-1237"),
                ProfileData(5, "송훈기4", "010-1234-1238"),
                ProfileData(6, "송훈기5", "010-1234-1239"),
                ProfileData(7, "송훈기6", "010-1234-1230"),
                ProfileData(8, "송훈기7", "010-1234-1231"),
                ProfileData(9, "송훈기8", "010-1234-1232"),
                ProfileData(10, "송훈기9", "010-1234-1233"),
                ProfileData(11, "송훈기10", "010-1234-1234"),
                ProfileData(12, "송훈기11", "010-1234-1235"),
                ProfileData(13, "송훈기12", "010-1234-1236"),
                ProfileData(14, "송훈기13", "010-1234-1237"),
                ProfileData(15, "송훈기14", "010-1234-1238"),
                ProfileData(16, "송훈기15", "010-1234-1239"),
                ProfileData(17, "송훈기16", "010-1234-1230"),
                ProfileData(18, "송훈기17", "010-1234-1231"),
                ProfileData(19, "송훈기18", "010-1234-1232"),
                ProfileData(20, "송훈기19", "010-1234-1233"),
                ProfileData(21, "송훈기20", "010-1234-1234")
            )
            return data
        }
    }
}