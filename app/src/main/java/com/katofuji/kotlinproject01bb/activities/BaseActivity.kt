package com.katofuji.kotlinproject01bb.activities

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.katofuji.kotlinproject01bb.MyApplication
import java.lang.Exception

open class BaseActivity : AppCompatActivity() {

    protected var mApplication : MyApplication? = null
    protected var mUiHandler : Handler? = null

    override fun onCreate(savedInstanceState: Bundle?)
    {
        mApplication = application as MyApplication
        mUiHandler = Handler(Looper.getMainLooper())
        super.onCreate(savedInstanceState)
        try
        {
            setContentView(getLayout())

            setupViews()

            setupContent()
        }
        catch (ex : Exception)
        {
            ex.printStackTrace()
        }
    }

    open fun setupViews() {

    }

    open fun setupContent() {

    }

    override fun onResume() {
        super.onResume()
        try
        {

        }
        catch (ex : Exception)
        {
            ex.printStackTrace()
        }
    }

    override fun onPause() {
        try
        {

        }
        catch (ex : Exception)
        {
            ex.printStackTrace()
        }
        super.onPause()
    }

    override fun onDestroy() {
        try {
            if (mUiHandler != null)
            {
                mUiHandler!!.removeCallbacksAndMessages(null)
                mUiHandler = null
            }
        }
        catch (ex : Exception)
        {
            ex.printStackTrace()
        }
        super.onDestroy()
    }

    open fun getLayout(): View?
    {
        return null
    }
}