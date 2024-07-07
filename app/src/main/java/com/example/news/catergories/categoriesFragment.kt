package com.example.news.catergories

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.fragment.findNavController
import com.example.domain.model.Categories
import com.example.news.R
import com.example.news.databinding.FragmentCategriesBinding
import com.example.news.news.NewsFragment

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
            "entertainment", R.drawable.politics,
            R.string.Politics, R.color.Politics_color
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
            "general", R.drawable.environment,
            R.string.enviroment, R.color.enviroment_color
        ),
        Categories(
            //
            "science", R.drawable.science,
            R.string.science, R.color.Science_color
        ),
        Categories(
            //
            "technology", R.drawable.science,
            R.string.technology, R.color.Science_color
        ),
    )


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCategriesBinding.inflate(inflater, container, false)

        activity?.findViewById<ImageView>(R.id.ic_search)?.visibility = View.GONE

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