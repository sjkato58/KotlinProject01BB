package com.katofuji.kotlinproject01bb.activities

import android.view.View
import com.katofuji.kotlinproject01bb.databinding.ActivityMainBinding
import java.lang.Exception

class MainActivity : BaseActivity()
{
    private lateinit var binding: ActivityMainBinding

    override fun setupViews()
    {
        super.setupViews()
    }

    override fun setupContent()
    {
        super.setupContent()
        try {
            /*val homeFragment = MainFragment()
            val fTrans = supportFragmentManager.beginTransaction()
            fTrans.replace(R.id.fragmentContainer, homeFragment)
            fTrans.commit()*/
        }
        catch (ex: Exception)
        {
            ex.printStackTrace()
        }
    }

    override fun onBackPressed()
    {
        /*if (supportFragmentManager.backStackEntryCount > 0)
        {
            supportFragmentManager.popBackStack()
        }
        else*/
        {
            super.onBackPressed()
        }
        super.onBackPressed()
    }

    override fun getLayout(): View?
    {
        try {
            binding = ActivityMainBinding.inflate(layoutInflater)
            return binding.root
        }
        catch (ex: Exception)
        {
            ex.printStackTrace()
        }
        return null
    }
}