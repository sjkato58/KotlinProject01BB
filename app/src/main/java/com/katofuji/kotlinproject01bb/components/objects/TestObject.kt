package com.katofuji.kotlinproject01bb.components.objects

import android.os.Parcel
import android.os.Parcelable
import org.json.JSONArray
import org.json.JSONObject
import java.io.Serializable
import java.util.*

class TestObject : Serializable, Parcelable {
    var name: String? = null
    var charId = 0
    private var occupation: ArrayList<String?>? = null
    private var seasons: ArrayList<Int?>? = null

    constructor() {}
    protected constructor(`in`: Parcel) {
        name = `in`.readString()
        charId = `in`.readInt()
        occupation = `in`.readArrayList(String::class.java.classLoader) as ArrayList<String?>?
        seasons = `in`.readArrayList(Int::class.java.classLoader) as ArrayList<Int?>?
        val arrayDemo = JSONArray(name)
        val listL1 = arrayDemo.length()
        var temper: JSONObject?
        for (i in 0 until listL1) {
            temper = arrayDemo.optJSONObject(i)
        }
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(name)
        dest.writeInt(charId)
        dest.writeList(occupation)
        dest.writeList(seasons)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<TestObject?> = object : Parcelable.Creator<TestObject?> {
            override fun createFromParcel(`in`: Parcel): TestObject? {
                return TestObject(`in`)
            }

            override fun newArray(size: Int): Array<TestObject?> {
                return arrayOfNulls(size)
            }
        }
    }
}