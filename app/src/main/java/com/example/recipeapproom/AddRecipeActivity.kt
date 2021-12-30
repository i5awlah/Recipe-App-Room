package com.example.recipeapproom

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.recipeapproom.databinding.ActivityAddRecipeBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AddRecipeActivity : AppCompatActivity() {

    lateinit var binding: ActivityAddRecipeBinding
    private val recipeDatabase by lazy { RecipeDatabase.getDatabase(this).RecipeDao() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddRecipeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAdd.setOnClickListener {
            addNewRecipe()
        }
    }

    private fun addNewRecipe() {
        val title = binding.etTitle.text.toString()
        val author = binding.etAuthor.text.toString()
        val ingredients = binding.etIngredients.text.toString()
        val instructions = binding.etInstructions.text.toString()
        val newRecipe = Recipe(0, title, author, ingredients, instructions)

        CoroutineScope(Dispatchers.IO).launch {
            recipeDatabase.addRecipe(newRecipe)
        }


//        val intent = Intent(this, MainActivity::class.java)
//        startActivity(intent)
    }
}