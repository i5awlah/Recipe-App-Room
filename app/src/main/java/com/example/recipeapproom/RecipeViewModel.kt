package com.example.recipeapproom

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import kotlinx.coroutines.*

class RecipeViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: RecipeRepository
    var recipes: LiveData<List<Recipe>>

    init {
        val recipeDao = RecipeDatabase.getDatabase(application).RecipeDao()
        repository = RecipeRepository(recipeDao)
        recipes = repository.getRecipe
    }

    fun addRecipe(recipe: Recipe) {
        CoroutineScope(Dispatchers.IO).launch {
            repository.addRecipe(recipe)
        }
    }

}