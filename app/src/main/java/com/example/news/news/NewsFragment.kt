package com.example.news.news

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.domain.model.news.Articles
import com.example.domain.model.sources.Sources
import com.example.news.R
import com.example.news.article.ArticleFragmentArgs
import com.example.news.catergories.categoriesFragment
import com.example.news.databinding.FragmentNewsBinding
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NewsFragment : Fragment() {

    private var _binding: FragmentNewsBinding? = null
    private val binding get() = _binding!!
    private val newsViewModel: NewsViewModel by viewModels()
    private var scrollPosition: Int = 0
    private var selectedTabIndex: Int = 0

    lateinit var adapter: NewsAdapter

    private val args: NewsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentNewsBinding.inflate(inflater, container, false)

        adapter = NewsAdapter(
            context = requireContext(),
            mutableListOf(),
            object : NewsItemClickListener {
                override fun newItemClick(article: Articles) {
                    // Save scroll position and selected tab index
                    val layoutManager = binding.newsRv.layoutManager as LinearLayoutManager
                    scrollPosition = layoutManager.findFirstVisibleItemPosition()
                    selectedTabIndex = binding.newsTabLayout.selectedTabPosition

                    val action = NewsFragmentDirections.actionNewsFragmentToArticleFragment(article)
                    findNavController().navigate(action)
                }
            })
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Hide toolbar icons based on your logic
        val toolBarMenu = activity?.findViewById<ImageView>(R.id.ic_menu)
        val toolBarSearch = activity?.findViewById<ImageView>(R.id.ic_search)
        val toolBarArrow = activity?.findViewById<ImageView>(R.id.ic_back)

        toolBarMenu?.visibility = View.VISIBLE
        toolBarSearch?.visibility = View.VISIBLE
        toolBarArrow?.visibility = View.GONE


        binding.newsRv.adapter = adapter
        handleIntent(args.categoryInfo?.apiID!!)
        Log.d("TAG", "onViewCreated: ${args.categoryInfo?.apiID}")
        setUpObserve()

        // Restore state after view is created
        savedInstanceState?.let {
            scrollPosition = it.getInt("scroll_position", 0)
            selectedTabIndex = it.getInt("selected_tab_index", 0)
            binding.newsRv.layoutManager?.scrollToPosition(scrollPosition)
            binding.newsTabLayout.getTabAt(selectedTabIndex)?.select()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        // Save scroll position and selected tab index
        val layoutManager = binding.newsRv.layoutManager as LinearLayoutManager
        outState.putInt("scroll_position", layoutManager.findFirstVisibleItemPosition())
        outState.putInt("selected_tab_index", binding.newsTabLayout.selectedTabPosition)
    }

    private fun setUpObserve() {
        newsViewModel.state.observe(viewLifecycleOwner, Observer { state ->
            when (state) {
                is NewsState.Loading -> binding.progressBar.visibility = View.VISIBLE
                is NewsState.Success -> {
                    binding.progressBar.visibility = View.GONE
                    bindTabs(state.sources)
                    // If sources are loaded successfully, select the first tab
                    binding.newsTabLayout.getTabAt(selectedTabIndex)?.select()
                }

                is NewsState.LoadNews -> {
                    binding.progressBar.visibility = View.GONE
                    adapter.bindNewNews(state.news)
                }

                is NewsState.Error -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(context, "Error: ${state.exception}", Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun bindTabs(sources: List<Sources?>) {
        sources.forEach {
            val tab = binding.newsTabLayout.newTab()
            tab.text = it?.name
            tab.tag = it
            binding.newsTabLayout.addTab(tab)
        }
        binding.newsTabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val source = tab?.tag as Sources
                source.id?.let {
                    sendTabIntent(it)
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}

            override fun onTabReselected(tab: TabLayout.Tab?) {
                val source = tab?.tag as Sources
                source.id?.let { sendTabIntent(it) }
            }
        })
        // Select the first tab initially if there are tabs available
        if (sources.isNotEmpty()) {
            binding.newsTabLayout.getTabAt(selectedTabIndex)?.select()
            val firstSourceId = sources[selectedTabIndex]?.id ?: ""
            sendTabIntent(firstSourceId)
        }
    }

    private fun sendTabIntent(sourceId: String) {
        lifecycleScope.launch {
            newsViewModel.channel.send(NewsIntent.LoadNews(sourceId))
        }
    }

    fun handleIntent(sourceId: String) {
        lifecycleScope.launch {
            newsViewModel.channel.send(NewsIntent.LoadSource(sourceId))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }
}
