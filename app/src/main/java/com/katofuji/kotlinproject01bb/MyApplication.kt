package com.katofuji.kotlinproject01bb

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.katofuji.kotlinproject01bb.components.RequestQueueSingleton
import com.katofuji.kotlinproject01bb.components.helpers.DownloadHelper
import com.katofuji.kotlinproject01bb.components.helpers.MyDataHolder
import com.katofuji.kotlinproject01bb.components.objects.CharacterObject


class MyApplication : Application() {

    private var mDownloadHelper: DownloadHelper? = null
    private var mDownloadData: ArrayList<CharacterObject>? = null

    companion object {

    }

    override fun onCreate() {
        try {
            AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        }
        catch (ex: Exception)
        {
            ex.printStackTrace()
        }
        super.onCreate()
        try {

            RequestQueueSingleton.getInstance(this)

            mDownloadHelper = DownloadHelper(this)

            MyDataHolder.getInstance()
        }
        catch (ex: Exception)
        {
            ex.printStackTrace()
        }
    }

    fun getDownloadHelper(): DownloadHelper?
    {
        return mDownloadHelper
    }

    fun getDownloadData(): ArrayList<CharacterObject>?
    {
        return mDownloadData
    }

    fun setDownloadData(downloadData : ArrayList<CharacterObject>)
    {
        mDownloadData = downloadData
    }
}