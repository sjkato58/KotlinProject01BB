package com.katofuji.kotlinproject01bb.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import com.katofuji.kotlinproject01bb.MyApplication
import com.katofuji.kotlinproject01bb.widgets.GlideApp
import com.katofuji.kotlinproject01bb.widgets.GlideRequests

open class BaseFragment : Fragment()
{
    protected var mApplication : MyApplication? = null
    protected var mHandler: Handler? = null
    protected var mGlideApp: GlideRequests? = null

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        mApplication = requireActivity().application as MyApplication
        mHandler = Handler(Looper.getMainLooper())
        mGlideApp = GlideApp.with(this)
    }


    override fun onDestroy()
    {
        if (mHandler != null)
        {
            mHandler!!.removeCallbacksAndMessages(null)
            mHandler = null
        }
        super.onDestroy()
    }
}