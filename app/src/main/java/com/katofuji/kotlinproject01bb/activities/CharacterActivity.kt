package com.katofuji.kotlinproject01bb.activities

import android.view.View
import androidx.navigation.ActivityNavigator
import androidx.navigation.fragment.NavHostFragment
import com.katofuji.kotlinproject01bb.R
import com.katofuji.kotlinproject01bb.components.objects.CharacterObject
import com.katofuji.kotlinproject01bb.databinding.ActivityCharacterBinding
import java.lang.Exception

class CharacterActivity: BaseActivity() {

    private lateinit var binding: ActivityCharacterBinding

    override fun getLayout(): View? {
        try {
            binding = ActivityCharacterBinding.inflate(layoutInflater)
            return binding.root
        }
        catch (ex: Exception)
        {
            ex.printStackTrace()
        }
        return null
    }

    override fun setupViews() {
        super.setupViews()
        try {
            val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
            val navController = navHostFragment.navController
            navController.setGraph(R.navigation.nav_graph_character, intent.extras)
        }
        catch (ex: Exception)
        {
            ex.printStackTrace()
        }
    }

    override fun setupContent() {
        super.setupContent()
        try {
            /*val homeFragment = CharacterFragment()
            homeFragment.arguments = intent.extras
            val fTrans = supportFragmentManager.beginTransaction()
            fTrans.replace(R.id.fragmentContainer, homeFragment)
            fTrans.commit()*/
        }
        catch (ex: Exception)
        {
            ex.printStackTrace()
        }
    }

    fun openCharacterFragment(characterObject: CharacterObject)
    {
        try {
           /* val characterFragment = CharacterFragment()
            characterFragment.mCharacterObject = characterObject

            val fTrans = supportFragmentManager.beginTransaction()
            fTrans.replace(R.id.fragmentContainer, characterFragment)
            fTrans.addToBackStack(BBConsts.Tags.HOMEFRAGMENT)
            fTrans.commit()*/
        }
        catch (ex: Exception)
        {
            ex.printStackTrace()
        }
    }

    override fun onBackPressed() {
        /*if (supportFragmentManager.backStackEntryCount > 0)
        {
            supportFragmentManager.popBackStack()
        }
        else
        {
            super.onBackPressed()
        }*/
        super.onBackPressed()
    }

    override fun finish()
    {
        super.finish()
        ActivityNavigator.applyPopAnimationsToPendingTransition(this)
    }
}