package com.example.news.catergories

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.example.domain.model.Categories
import com.example.news.R
import com.example.news.databinding.FragmentCategriesBinding

class categoriesFragment : Fragment() {

    private var _binding: FragmentCategriesBinding? = null
    private val binding get() = _binding

    lateinit var adapter: categries_adapter

    //business
    // entertainment
    // general
    // health
    // science
    // sports
    // technology:
    val categories = listOf(
        Categories(
            //
            "sports", R.drawable.sports,
            R.string.sports, R.color.sport_color
        ),
        Categories(
            "entertainment", R.drawable.entertainment,
            R.string.entertainment, R.color.entertainment_color
        ),
        Categories(
            //
            "health", R.drawable.health,
            R.string.health, R.color.health_color
        ),
        Categories(
            //
            "business", R.drawable.bussines,
            R.string.business, R.color.Business_color
        ),
        Categories(
            //
            "general", R.drawable.general,
            R.string.general, R.color.general_color
        ),
        Categories(
            //
            "science", R.drawable.science,
            R.string.science, R.color.Science_color
        ),
        Categories(
            //
            "technology", R.drawable.technology,
            R.string.technology, R.color.technology_color
        ),
    )


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCategriesBinding.inflate(inflater, container, false)

        activity?.findViewById<ImageView>(R.id.main_ic_search)?.visibility = View.GONE
        activity?.findViewById<View>(R.id.search_view)?.visibility = View.GONE
        activity?.findViewById<ImageView>(R.id.ic_menu)?.visibility = View.VISIBLE
        activity?.findViewById<TextView>(R.id.toolbar_title_tv)?.text = getString(R.string.news)

        adapter = categries_adapter(categories, object : OnCategorySelectedListener {
            override fun onCategorySelected(category: Categories) {
                Log.d("TAG", "onCategorySelected: ${category.apiID}")
                val action =
                    categoriesFragmentDirections.actionCategoriesFragmentToNewsFragment(category)
                findNavController().navigate(action)
            }

        })
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.categoryRv?.adapter = adapter

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}