package com.katofuji.kotlinproject01bb.fragments

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.katofuji.kotlinproject01bb.R
import com.katofuji.kotlinproject01bb.components.helpers.MyDataHolder
import com.katofuji.kotlinproject01bb.components.objects.CharacterObject
import com.katofuji.kotlinproject01bb.databinding.FragmentCharacterBinding
import com.katofuji.kotlinproject01bb.utilities.BBUtils
import java.lang.Exception

class CharacterFragment : BaseFragment()
{
    var mCharacterObject : CharacterObject? = null

    val mArgs: CharacterFragmentArgs by navArgs()

    var _binding: FragmentCharacterBinding? = null
    private val mBinding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        _binding = FragmentCharacterBinding.inflate(inflater)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)
        retrieveArguments()

        populateCharacterDetails()
    }

    open fun retrieveArguments()
    {
        val key = mArgs.fhKeyCharacterid
        if (key is String)
        {
            val itemObject = MyDataHolder.getInstance().retrieveData(key)
            if (itemObject is CharacterObject)
            {
                mCharacterObject = itemObject
            }
        }
    }

    protected fun populateCharacterDetails()
    {
        if (BBUtils.isValid(this))
        {
            if (mCharacterObject != null)
            {
                val uri = Uri.parse(mCharacterObject!!.img)
                mGlideApp!!.load(uri)
                    .into(mBinding.characterImageview)

                val name = mCharacterObject!!.name
                val nametext = resources.getString(R.string.characterfrag_name) + " $name"
                mBinding.characterNametv.text = nametext

                val occupation = mCharacterObject!!.occupation
                val occuptext = resources.getString(R.string.characterfrag_occupation) + " $occupation"
                mBinding.characterOccupationtv.text = occuptext.replace("[", "").replace("]", "")

                val status = mCharacterObject!!.status
                val statustext = resources.getString(R.string.characterfrag_status) + " $status"
                mBinding.characterStatustv.text = statustext

                val nickname = mCharacterObject!!.nickname
                val nicktext = resources.getString(R.string.characterfrag_nickname) + " $nickname"
                mBinding.characterNicknametv.text = nicktext

                val seasonalApp = mCharacterObject!!.appearance
                var seasonalTxt = resources.getString(R.string.characterfrag_seasonappearance)
                if (seasonalApp != null)
                {
                    for (item in seasonalApp)
                    {
                        seasonalTxt = "$seasonalTxt $item, "
                    }
                    seasonalTxt = seasonalTxt.substring(0, seasonalTxt.length-2)
                    mBinding.characterSeasonapptv.text = seasonalTxt
                }
            }
            else
            {

            }
        }
    }

    override fun onDestroyView()
    {
        super.onDestroyView()
        _binding = null
    }
}