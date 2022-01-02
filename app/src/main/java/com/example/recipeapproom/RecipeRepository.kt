package com.example.recipeapproom

import androidx.lifecycle.LiveData

class RecipeRepository(private val recipeDao: RecipeDao) {

    val getRecipe: LiveData<List<Recipe>> = recipeDao.getRecipes()

    suspend fun addRecipe(recipe: Recipe){
        recipeDao.addRecipe(recipe)
    }

    suspend fun updateRecipe(recipe: Recipe){
        recipeDao.updateRecipe(recipe)
    }

    suspend fun deleteRecipe(recipe: Recipe){
        recipeDao.deleteRecipe(recipe)
    }

}