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
        val graph = findNavController(R.id.myNavHostFragment).graph
        if (helper.getEmail().equals("") || helper.getEmail() == null) {
            graph.startDestination = R.id.loginFragment
        } else {
            graph.startDestination = R.id.listFragment
        }
        findNavController(R.id.myNavHostFragment).graph = graph
    }
}