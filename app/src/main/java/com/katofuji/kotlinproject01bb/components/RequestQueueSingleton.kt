package com.katofuji.kotlinproject01bb.components

import android.content.Context
import android.graphics.Bitmap
import androidx.collection.LruCache
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.ImageLoader
import com.android.volley.toolbox.Volley

class RequestQueueSingleton constructor(mContext: Context){

    companion object {
        @Volatile
        private var INSTANCE: RequestQueueSingleton? = null
        fun getInstance(context: Context) =
                INSTANCE ?: synchronized(this) {
                    INSTANCE ?: RequestQueueSingleton(context).also {
                        INSTANCE = it
                    }
                }
    }
    val imageLoader: ImageLoader by lazy {
        ImageLoader(requestQueue,
                object : ImageLoader.ImageCache {
                    private val cache = LruCache<String, Bitmap>(20)
                    override fun getBitmap(url: String): Bitmap? {
                        return cache.get(url)
                    }
                    override fun putBitmap(url: String, bitmap: Bitmap) {
                        cache.put(url, bitmap)
                    }
                })
    }

    val requestQueue: RequestQueue by lazy {
        Volley.newRequestQueue(mContext.applicationContext)
    }

    fun <T> addToRequestQueue(req: Request<T>) {
        requestQueue.add(req)
    }

    fun cancelDownloadRequest(tag: String)
    {
        requestQueue.cancelAll(tag)
    }
}