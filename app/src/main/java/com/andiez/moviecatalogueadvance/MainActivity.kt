package com.andiez.moviecatalogueadvance

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.andiez.moviecatalogueadvance.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_main) as NavHostFragment
        val navController = navHostFragment.findNavController()
        val toolbar = binding.toolbar
        NavigationUI.setupWithNavController(binding.bottomNavigationView, navController)
        NavigationUI.setupWithNavController(toolbar, navController)
        setSupportActionBar(toolbar)
    }

    fun setBottomNavVisibility(state: Boolean) {
        binding.bottomNavigationView.visibility = if (state) View.VISIBLE else View.GONE
    }
}