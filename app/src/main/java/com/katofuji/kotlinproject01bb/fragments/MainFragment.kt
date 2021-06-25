package com.katofuji.kotlinproject01bb.fragments

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.AdapterView
import androidx.appcompat.widget.SearchView
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.katofuji.kotlinproject01bb.R
import com.katofuji.kotlinproject01bb.adapters.MainAdapter
import com.katofuji.kotlinproject01bb.components.helpers.MyDataHolder
import com.katofuji.kotlinproject01bb.components.objects.CharacterObject
import com.katofuji.kotlinproject01bb.databinding.FragmentMainBinding
import com.katofuji.kotlinproject01bb.utilities.BBUtils


class MainFragment : BaseFragment()
{

    var mAdapter : MainAdapter? = null
    var mList : ArrayList<CharacterObject>? = null
    var mSpanCount : Int = 2

    private var _binding: FragmentMainBinding? = null
    private val mBinding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        _binding = FragmentMainBinding.inflate(inflater)

        setRowCount(resources.configuration)

        mAdapter = MainAdapter(mGlideApp)
        mAdapter!!.mOnClickListener = View.OnClickListener { v -> onCharacterClicked(v) }
        mAdapter!!.mOnItemSelectedListener = object : AdapterView.OnItemSelectedListener
        {
            override fun onNothingSelected(parent: AdapterView<*>?) { }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long)
            {
                onSeasonSelected(parent, view, position, id)
            }
        }

        mBinding.recyclerview.adapter = mAdapter

        return mBinding.root
    }

    private fun setRowCount(newConfig: Configuration)
    {
        mSpanCount = 2
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE)
        {
            mSpanCount = 3
        }
        val gridLayoutManager = GridLayoutManager(context, mSpanCount)
        gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup()
        {
            override fun getSpanSize(position: Int): Int
            {
                val viewType = mAdapter?.getItemViewType(position)
                return when (viewType)
                {
                    2 ->
                    {
                        mSpanCount
                    }
                    else ->
                    {
                        1
                    }
                }
            }
        }
        mBinding.recyclerview.layoutManager = gridLayoutManager
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)


        mList = mApplication!!.getDownloadData()

        mAdapter?.mList = mList
        mAdapter?.sortCharacterList()
        mAdapter?.notifyDataSetChanged()
    }

    fun onCharacterClicked(view: View?)
    {
        if (BBUtils.isValid(this) && view != null)
        {
            val characterObject = view.getTag(R.id.meta_object)
            if (characterObject is CharacterObject)
            {
                val key = characterObject.charid.toString()
                MyDataHolder.getInstance().saveData(key, characterObject)

                val directions = MainFragmentDirections.actionMainFragmentToCharacterActivity(key)

                findNavController(this).navigate(directions)
            }
        }
    }

    fun onSeasonSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long)
    {
        if (BBUtils.isValid(this))
        {
            val list = resources.getStringArray(R.array.seasonslist)
            val item = list[position]
            Log.e("AppCore", "onItemSelected: $position & Season: $item")
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater)
    {
        inflater.inflate(R.menu.main_menu, menu)

        val myActionMenuItem: MenuItem? = menu?.findItem(R.id.action_search)
        if (myActionMenuItem != null)
        {
            val searchView: SearchView = myActionMenuItem.actionView as SearchView
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener
            {
                override fun onQueryTextSubmit(query: String?): Boolean
                {
                    myActionMenuItem.collapseActionView()
                    Log.e("AppCore", "onQueryTextSubmit: $query")
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean
                {
                    updateSearchTerms(newText)
                    Log.e("AppCore", "onQueryTextChange: $newText")
                    return false;
                }
            })
        }
        /*val menuItem = menu.findItem(R.id.menu_spinner)
        val seasonList = resources.getIntArray(R.array.seasonslist)
        val seasonArray = listOf(seasonList)

        val spinner = menuItem.actionView
        if (spinner is Spinner)
        {
            val adapter = ArrayAdapter(context!!, android.R.layout.simple_spinner_item, seasonArray)
            spinner.adapter = adapter
        }*/
    }

    fun updateSearchTerms(searchQuery: String?)
    {
        if (BBUtils.isValid(this))
        {
            mAdapter?.mSearchQuery = searchQuery
            mAdapter?.sortCharacterList()
            mAdapter?.notifyDataSetChanged()
        }
    }


    override fun onConfigurationChanged(newConfig: Configuration)
    {
        super.onConfigurationChanged(newConfig)
        setRowCount(newConfig)
    }

    override fun onDestroyView()
    {
        super.onDestroyView()
        _binding = null
    }
}