package com.katofuji.kotlinproject01bb.components.helpers

import java.lang.Exception
import kotlin.collections.HashMap

class MyDataHolder {
    companion object {
        private var mMyDataHolder: MyDataHolder? = null

        fun getInstance(): MyDataHolder
        {
            if (mMyDataHolder == null)
            {
                mMyDataHolder = MyDataHolder()
            }
            return mMyDataHolder!!
        }
    }

    private var mDataContainer: HashMap<String, Any> = HashMap()

    fun saveData(key: String, toBeSaved: Any)
    {
        try {
            if (mDataContainer != null)
            {
                mDataContainer.put(key, toBeSaved)
            }
        }
        catch (ex: Exception)
        {
            ex.printStackTrace()
        }
    }

    fun retrieveData(key: String): Any?
    {
        try {
            if (mDataContainer != null && mDataContainer.containsKey(key))
            {
                return mDataContainer[key]
            }
        }
        catch (ex: Exception)
        {
            ex.printStackTrace()
        }
        return null
    }

    fun removeData(key: String)
    {
        try {
            if (mDataContainer != null && mDataContainer.containsKey(key))
            {
                mDataContainer.remove(key)
            }
        }
        catch (ex: Exception)
        {
            ex.printStackTrace()
        }
    }
}