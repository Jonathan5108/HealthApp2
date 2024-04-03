package com.example.myapplication

import android.app.SearchManager
import android.content.ComponentName
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.widget.EditText
import android.widget.ListView
import android.widget.Button
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import android.support.v4.view.MenuItemCompat
import android.widget.SearchView
import android.widget.Toast


class NutritionFragment : Fragment(), SearchView.OnCloseListener {

    // Declare Variables
    private lateinit var list: ListView
    private lateinit var adapter: ListViewAdapter
    private lateinit var editsearch: SearchView
    private lateinit var animalNameList: Array<String>
    private val arraylist = ArrayList<AnimalNames>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?

    ): View {
        val view = inflater.inflate(
            R.layout.nutrition, container, false
        )

        //code for search bar
        // Generate sample data
        animalNameList = arrayOf(
            "Lion", "Tiger", "Dog",
            "Cat", "Tortoise", "Rat", "Elephant", "Fox",
            "Cow", "Donkey", "Monkey"
        )

        // Locate the ListView in listview_main.xml
        list = view.findViewById(R.id.listview)

        for (i in animalNameList.indices) {
            val animalNames = AnimalNames(animalNameList[i])
            // Binds all strings into an array
            arraylist.add(animalNames)
        }

        // Pass results to ListViewAdapter Class
        adapter = ListViewAdapter(this, arraylist)

        // Binds the Adapter to the ListView
        list.adapter = adapter

        // Locate the EditText in listview_main.xml
        editsearch = view.findViewById(R.id.search)
        editsearch.setOnQueryTextListener(this)


        // code for dropdown menu
        val spinnerMeals: Spinner = view.findViewById(R.id.spinnerMeal)
        val spinnerAdapter = ArrayAdapter.createFromResource(
            requireContext(), R.array.meals_dropdown, android.R.layout.simple_spinner_item
        )
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerMeals.adapter = spinnerAdapter


        return view
    }

    fun onQueryTextSubmit(query: String): Boolean {
        return false
    }

     fun onQueryTextChange(newText: String): Boolean {
        val text = newText
        adapter.filter(text)
        return false
    }
}

