package com.example.recipeapproom

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recipeapproom.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var recipeRecyclerView: RecyclerView
    private lateinit var recipeAdapter: RecipeAdapter

    private val recipeViewModel by lazy { ViewModelProvider(this).get(RecipeViewModel::class.java) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        fetchData()
        binding.btnAddRecipe.setOnClickListener {
            openNewRecipeActivity()

        }
    }

    private fun setupRecyclerView() {
        recipeRecyclerView = binding.rvRecipe
        recipeAdapter = RecipeAdapter()
        recipeRecyclerView.adapter = recipeAdapter
        recipeRecyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun fetchData() {
        recipeViewModel.recipes.observe(this, {
            recipes -> recipeAdapter.updateRV(recipes)
        })

    }

    private fun openNewRecipeActivity() {
        val intent = Intent(this, AddRecipeActivity::class.java)
        startActivity(intent)
    }
}