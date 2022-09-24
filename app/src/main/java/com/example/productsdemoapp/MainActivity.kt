package com.example.productsdemoapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.example.productsdemoapp.helpers.PreferenceHelper

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // check logged to handle navigation
        var helper = PreferenceHelper(this)

        val navController = findNavController(R.id.myNavHostFragment)
        val navGraph = navController.navInflater.inflate(R.navigation.nav_graph)
        if (helper.getEmail().equals("") || helper.getEmail() == null) {
            navGraph.startDestination = R.id.loginFragment
        } else {
            navGraph.startDestination = R.id.listFragment;
        }
        navController.setGraph(navGraph);

    }
}