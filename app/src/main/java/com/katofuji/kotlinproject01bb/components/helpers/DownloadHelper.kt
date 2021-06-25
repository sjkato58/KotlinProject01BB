package com.katofuji.kotlinproject01bb.components.helpers

import android.content.Context
import android.text.TextUtils
import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.katofuji.kotlinproject01bb.MyApplication
import com.katofuji.kotlinproject01bb.R
import com.katofuji.kotlinproject01bb.components.RequestQueueSingleton
import com.katofuji.kotlinproject01bb.components.parsers.CharacterParser
import com.katofuji.kotlinproject01bb.utilities.BBConsts
import org.json.JSONArray
import java.lang.Exception

class DownloadHelper(
        protected var mContext: Context)
{

    protected var mApplication : MyApplication? = null

    init {
        this.mApplication = mContext.applicationContext as MyApplication?
    }

    fun downloadCoreFeed(downloadListener: DownloadListener)
    {
        try {
            val url = mContext.resources.getString(R.string.urls_corefeed)

            val listener = Response.Listener<String> {
                onDownloadSucceeded(it, downloadListener)
            }
            val errorListener = Response.ErrorListener {
                onFeedDownloadErrored(it, downloadListener)
            }
            Log.i("AppCore", "url: $url")
            val stringRequest = StringRequest(Request.Method.GET, url, listener, errorListener)
            stringRequest.tag = BBConsts.DownloadTags.COREFEED

            RequestQueueSingleton.getInstance(mContext).addToRequestQueue(stringRequest)
        }
        catch (ex : Exception)
        {
            ex.printStackTrace()
            downloadListener.onFailure(VolleyError("Unknown Error"))
        }
    }

    private fun onDownloadSucceeded(resultString: String, downloadListener: DownloadListener) {
        try {
            if (!TextUtils.isEmpty(resultString))
            {
                val jsonResult = JSONArray(resultString)
                val characterParser = CharacterParser(mContext)
                val contentList = characterParser.parseCharacterList(jsonResult)
                if (contentList != null) {
                    mApplication!!.setDownloadData(contentList)
                    downloadListener.onSuccess()
                }
            }
        }
        catch (ex : Exception)
        {
            ex.printStackTrace()
            downloadListener.onFailure(VolleyError("Parsing Error"))
        }
    }

    private fun onFeedDownloadErrored(volleyError: VolleyError, downloadListener: DownloadListener) {
        try {
            downloadListener.onFailure(volleyError)
        }
        catch (ex : Exception)
        {
            ex.printStackTrace()
            downloadListener.onFailure(VolleyError("Unknown Error"))
        }
    }

    fun onStopDownloads()
    {
        try {
            RequestQueueSingleton.getInstance(mContext).cancelDownloadRequest(BBConsts.DownloadTags.COREFEED)
        }
        catch (ex: Exception)
        {
            ex.printStackTrace()
        }
    }

    interface DownloadListener {
        fun onSuccess()
        fun onFailure(volleyError: VolleyError)
    }
}