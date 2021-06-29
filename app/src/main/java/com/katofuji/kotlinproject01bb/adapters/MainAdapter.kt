package com.katofuji.kotlinproject01bb.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import com.katofuji.kotlinproject01bb.components.objects.CharacterObject
import com.katofuji.kotlinproject01bb.components.objects.SelectorObject
import com.katofuji.kotlinproject01bb.viewholders.CharacterSeletorVH
import com.katofuji.kotlinproject01bb.viewholders.CharacterVH
import com.katofuji.kotlinproject01bb.databinding.CardRecyclerItemBinding
import com.katofuji.kotlinproject01bb.databinding.CardRecyclerSelectorBinding
import com.katofuji.kotlinproject01bb.utilities.BBUtils
import com.katofuji.kotlinproject01bb.widgets.GlideRequests
import java.lang.Exception

class MainAdapter(
    val mGlideApp: GlideRequests?
) : RecyclerView.Adapter<RecyclerView.ViewHolder>()
{
    var mSearchQuery : String? = ""
    var mList: ArrayList<Any> = arrayListOf<Any>()
    var mSeasonCheck = ""
    var mOnClickListener : View.OnClickListener? = null
    var mOnItemSelectedListener : AdapterView.OnItemSelectedListener? = null

    private var mModList: ArrayList<Any> = arrayListOf()

    fun sortCharacterList()
    {
        try
        {
            if (mList != null)
            {
                mModList = arrayListOf()
                val selector = SelectorObject()
                mModList.add(selector)
                val tempList = arrayListOf<Any>()
                if (!BBUtils.isTextEmpty(mSeasonCheck))
                {

                }
                else
                {
                    tempList.addAll(mList);
                }
                if (!BBUtils.isTextEmpty(mSearchQuery))
                {
                    mSearchQuery = mSearchQuery?.lowercase()
                    for (item in tempList)
                    {
                        if (item is CharacterObject && item.name?.lowercase()!!.contains(mSearchQuery!!))
                        {
                            mModList.add(item)
                        }
                    }
                }
                else
                {
                    mModList.addAll(mList);
                }
            }
        }
        catch (ex: Exception)
        {
            ex.printStackTrace()
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (mModList.size > 0)
        {
            val item = mModList[position]
            if (item !is CharacterObject)
            {
                return 2
            }
            else
            {
                return 1
            }
        }
        return super.getItemViewType(position)
    }

    override fun getItemCount(): Int {
        if (mModList.size > 0)
        {
            return mModList.size
        }
        return 0
    }

    fun onSetupViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder
    {
        when (viewType)
        {
            2 -> {
                val binding = CardRecyclerSelectorBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return CharacterSeletorVH(binding, mOnItemSelectedListener)
            }
            else -> {
                val binding = CardRecyclerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return CharacterVH(binding, mOnClickListener)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return onSetupViewHolder(parent, viewType)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        try {
            if (holder is CharacterVH)
            {
                val characterHolder = holder
                val item = mModList[position]
                if (item is CharacterObject) {
                    characterHolder.bindData(item, mGlideApp)
                }
            }
            else if (holder is CharacterSeletorVH)
            {
                holder.bindData(-1)
            }
        }
        catch (ex: Exception)
        {
            ex.printStackTrace()
        }
    }

    override fun onViewRecycled(holder: RecyclerView.ViewHolder) {
        super.onViewRecycled(holder)
        try {
            if (holder is CharacterVH)
            {
                holder.onViewRecycled()
            }
        }
        catch (ex: Exception)
        {
            ex.printStackTrace()
        }
    }
}