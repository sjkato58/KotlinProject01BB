package com.katofuji.kotlinproject01bb.viewholders

import android.net.Uri
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.katofuji.kotlinproject01bb.R
import com.katofuji.kotlinproject01bb.components.objects.CharacterObject
import com.katofuji.kotlinproject01bb.databinding.CardRecyclerItemBinding
import com.katofuji.kotlinproject01bb.widgets.GlideRequests
import java.lang.Exception

class CharacterVH(
    val binding: CardRecyclerItemBinding,
    listerner : View.OnClickListener?
    ): RecyclerView.ViewHolder(binding.root), View.OnClickListener
{

    private var characterObject: CharacterObject? = null

    init
    {
        if (listerner != null)
        {
            binding.root.setOnClickListener(listerner)
        }
    }

    fun bindData(characterObject: CharacterObject?, glideApp: GlideRequests?)
    {
        try {
            if (characterObject != null)
            {
                binding.textview.text = characterObject.name

                val uri = Uri.parse(characterObject.img)

                glideApp!!.load(uri)
                    .into(binding.imageview)

                binding.root.setTag(R.id.meta_object, characterObject)
            }
        }
        catch (ex: Exception)
        {
            ex.printStackTrace()
        }
    }

    fun onViewRecycled()
    {
        try {
            binding.root.setTag(R.id.meta_object, null)
        }
        catch (ex: Exception)
        {
            ex.printStackTrace()
        }
    }

    override fun onClick(v: View) {
        Log.d("RecyclerView", "CLICK!")
    }
}