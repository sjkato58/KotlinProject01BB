package com.katofuji.kotlinproject01bb.activities

import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import androidx.navigation.ActivityNavigator
import com.android.volley.VolleyError
import com.katofuji.kotlinproject01bb.R
import com.katofuji.kotlinproject01bb.components.helpers.DownloadHelper.DownloadListener
import com.katofuji.kotlinproject01bb.databinding.ActivitySplashscreenBinding
import java.lang.Exception

class SplashActivity : BaseActivity()
{
    private lateinit var binding: ActivitySplashscreenBinding

    var mIsDownloaded = false
    var mIsWaited = false

    override fun setupViews()
    {
        super.setupViews()

    }

    override fun setupContent()
    {
        super.setupContent()
        if (mApplication != null && mApplication!!.getDownloadHelper() != null)
        {
            val downloadListener = object : DownloadListener
            {
                override fun onSuccess()
                {
                    onDownloadSucceeded()
                }

                override fun onFailure(volleyError: VolleyError)
                {
                    onFeedDownloadErrored(volleyError)
                }
            }
            mApplication!!.getDownloadHelper()!!.downloadCoreFeed(downloadListener)
        }
        mUiHandler!!.postDelayed({
            mIsWaited = true
            launchMainActivity()
        }, 3000)
    }

    fun onDownloadSucceeded()
    {
        try {
            val dataString = mApplication!!.getDownloadData()
            Log.d("AppCore", "onDownloadSucceeded: $dataString")
            mIsDownloaded = true
            launchMainActivity()
        }
        catch (ex : Exception)
        {
            ex.printStackTrace()
        }
    }

    fun onFeedDownloadErrored(volleyError: VolleyError) {
        try {
            Toast.makeText(this, volleyError.localizedMessage, Toast.LENGTH_SHORT).show()
        }
        catch (ex : Exception)
        {
            ex.printStackTrace()
        }
    }

    private fun launchMainActivity()
    {
        try {
            Log.d("AppCore", "launchMainActivity: $mIsDownloaded & mIsWaited: $mIsWaited")
            if (mIsDownloaded && mIsWaited)
            {
                val mainIntent = Intent(this, MainActivity::class.java)
                val activityNavigator = ActivityNavigator(this)
                val destination = activityNavigator.createDestination().setIntent(mainIntent)
                activityNavigator.navigate(destination, null, null, null)
                //startActivity(mainIntent)
                finish()
            }
        }
        catch (ex : Exception)
        {
            ex.printStackTrace()
        }
    }

    override fun onDestroy() {
        try {
            mApplication!!.getDownloadHelper()!!.onStopDownloads()
        }
        catch (ex: Exception)
        {
            ex.printStackTrace()
        }
        super.onDestroy()
    }

    override fun getLayout(): View? {
        try {
            binding = ActivitySplashscreenBinding.inflate(layoutInflater)
            return binding.root
        }
        catch (ex: Exception)
        {
            ex.printStackTrace()
        }
        return null
    }
}