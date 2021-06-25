package com.katofuji.kotlinproject01bb.components.parsers

import android.content.Context
import com.katofuji.kotlinproject01bb.MyApplication
import com.katofuji.kotlinproject01bb.components.objects.CharacterObject
import org.json.JSONArray
import org.json.JSONObject
import java.lang.Exception

class CharacterParser (
        protected var mContext: Context) {

    protected var mApplication : MyApplication? = null

    init {
        this.mApplication = mContext.applicationContext as MyApplication?
    }

    fun parseCharacterList(resultJson: JSONArray?) : ArrayList<CharacterObject>?
    {
        try {
            if (resultJson != null)
            {
                val characterList = ArrayList<CharacterObject>()
                val listL1 = resultJson.length()
                var characterObject : CharacterObject?
                for (i in 0 until listL1)
                {
                    characterObject = parseCharacterObject(resultJson.optJSONObject(i))
                    if (characterObject != null)
                    {
                        characterList.add(characterObject)
                    }
                }
                if (characterList.size > 0)
                {
                    return characterList
                }
            }
        }
        catch (ex : Exception)
        {
            ex.printStackTrace()
        }
        return null
    }

    fun parseCharacterObject(resultJson: JSONObject?) : CharacterObject?
    {
        try {
            if (resultJson != null)
            {
                val characterObject = CharacterObject()
                characterObject.charid = resultJson.optInt("char_id")
                characterObject.name = resultJson.optString("name")
                characterObject.birthday = resultJson.optString("birthday")
                val occupationJson = resultJson.optJSONArray("occupation")
                if (occupationJson != null)
                {
                    val listL1 = occupationJson.length()
                    val occupationList = ArrayList<String>()
                    for (i in 0 until listL1)
                    {
                        occupationList.add(occupationJson.optString(i))
                    }
                    if (occupationList.size > 0)
                    {
                        characterObject.occupation = occupationList
                    }
                }
                characterObject.img = resultJson.optString("img")
                characterObject.status = resultJson.optString("status")
                characterObject.nickname = resultJson.optString("nickname")
                val appearanceJson = resultJson.optJSONArray("appearance")
                if (appearanceJson != null)
                {
                    val appearanceList = ArrayList<Int>()
                    val listL1 = appearanceJson.length()
                    for (i in 0 until listL1)
                    {
                        appearanceList.add(appearanceJson.optInt(i))
                    }
                    if (appearanceList.size > 0)
                    {
                        characterObject.appearance = appearanceList
                    }
                }
                characterObject.portrayed = resultJson.optString("portrayed")
                characterObject.category = resultJson.optString("category")
                val bcsaJson = resultJson.optJSONArray("better_call_saul_appearance")
                if (appearanceJson != null)
                {
                    val bcsaList = ArrayList<Int>()
                    val listL1 = bcsaJson.length()
                    for (i in 0 until listL1)
                    {
                        bcsaList.add(bcsaJson.optInt(i))
                    }
                    if (bcsaList.size > 0)
                    {
                        characterObject.bettercallsaulappearance = bcsaList
                    }
                }
                return characterObject
            }
        }
        catch (ex: Exception)
        {
            ex.printStackTrace()
        }
        return null
    }
}