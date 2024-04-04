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
import androidx.recyclerview.widget.RecyclerView


class NutritionFragment : Fragment(), SearchView.OnCloseListener {

    // Declare Variables
    private lateinit var recyclerView: RecyclerView
    private lateinit var dataList: ArrayList<DataClass>
    lateinit var imageList: Array<Int>
    lateinit var titleList: Array<String>



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?

        //might need to go into main activity
        imageList = arrayOf(
            R.drawable.baseline_food,
            R.drawable.baseline_local_drink_24)

        titleList = arrayOf(
            "Apple",
            "Coffee")

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        dataList = arrayListOf<DataClass>()
        getData()


    ): View {
        val view = inflater.inflate(
            R.layout.nutrition, container, false
        )

        //code for recycler view for search bar
        fun getData(){
            for (i in imageList.indices){
                val dataClass = DataClass(imageList[i],titleList[i])
                dataList.add(dataClass)
            }
            recyclerView.adapter = AdapterClass(dataList)
        }


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

    override fun onClose(): Boolean {
        TODO("Not yet implemented")
    }
}

