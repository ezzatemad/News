package com.example.news

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.view.View
import android.widget.EditText
import android.widget.ImageView
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.news.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    lateinit var searchView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        searchView = binding.toolBar.findViewById<View>(R.id.search_view)



        setSupportActionBar(binding.toolbartwo)
        supportActionBar?.hide()


        binding.icMenu.setOnClickListener {
            toggleDrawer()
        }
        // Retrieve the NavController associated with the NavHostFragment
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment
        navController = navHostFragment.navController

        binding.userMainNavigationView.setupWithNavController(navController)

        // App Bar Configuration
        appBarConfiguration = AppBarConfiguration(navController.graph, binding.mainDrawerMenu)
        setupActionBarWithNavController(navController, appBarConfiguration)


        searchViews()


    }


    fun searchViews() {


        val ic_clear = searchView
            .findViewById<ImageView>(R.id.clear_icon)

        val searchEditText = searchView
            .findViewById<EditText>(R.id.search_edit_text)


        //show search bar
        binding.mainIcSearch.setOnClickListener {
            binding.icMenu.visibility = View.GONE
            binding.toolbarTitleTv.visibility = View.GONE
            binding.mainIcSearch.visibility = View.GONE

            searchView.visibility = View.VISIBLE
        }

        //hide search bar
        ic_clear.setOnClickListener {
                searchEditText.text.clear()
                searchView.visibility = View.GONE

                binding.icMenu.visibility = View.VISIBLE
                binding.toolbarTitleTv.visibility = View.VISIBLE
                binding.mainIcSearch.visibility = View.VISIBLE
            }


    }

    private fun toggleDrawer() {
        if (binding.mainDrawerMenu.isDrawerOpen(GravityCompat.START)) {
            binding.mainDrawerMenu.closeDrawer(GravityCompat.START)
        } else {
            binding.mainDrawerMenu.openDrawer(GravityCompat.START)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}