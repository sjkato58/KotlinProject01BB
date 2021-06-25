package com.katofuji.kotlinproject01bb.viewholders

import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.RecyclerView
import com.katofuji.kotlinproject01bb.R
import com.katofuji.kotlinproject01bb.databinding.CardRecyclerSelectorBinding
import java.lang.Exception

class CharacterSeletorVH(
    val binding: CardRecyclerSelectorBinding,
    listerner : AdapterView.OnItemSelectedListener?
    ): RecyclerView.ViewHolder(binding.root)
{
    init
    {
        if (listerner != null)
        {
            binding.spinner.onItemSelectedListener = listerner
        }
    }

    fun bindData(selectedSeason: Int)
    {
        try {
            val context = itemView.context

            val adapter = ArrayAdapter.createFromResource(context, R.array.seasonslist, R.layout.card_spinner_item)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spinner.adapter = adapter

            if (selectedSeason != -1)
            {
                binding.spinner.setSelection(selectedSeason)
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

        }
        catch (ex: Exception)
        {
            ex.printStackTrace()
        }
    }
}