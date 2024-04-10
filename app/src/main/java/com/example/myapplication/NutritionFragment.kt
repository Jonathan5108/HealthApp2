package com.example.myapplication

import android.annotation.SuppressLint
import android.app.SearchManager
import android.content.ComponentName
import android.content.Context
import android.os.Bundle
import android.view.Gravity
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
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.PopupWindow
import android.widget.RelativeLayout
import android.widget.SearchView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.Locale


class NutritionFragment: Fragment() {
    //vars for nutrition page
    lateinit var recyclerView: RecyclerView
    private lateinit var dataList: ArrayList<DataClass>
    lateinit var imageList: Array<Int>
    lateinit var titleList: Array<String>
    lateinit var searchView: SearchView
    private lateinit var searchList: ArrayList<DataClass>

    //recycler list items
    //private lateinit var editTextNewItem: EditText
    private lateinit var listViewItems: ListView
    private lateinit var itemsAdapter: ArrayAdapter<String>
    private val breakfastItems = ArrayList<String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?

    ): View {

        val view = inflater.inflate(R.layout.nutrition, container, false)

        //code for search popup window
        val buttonSearch = view.findViewById<ImageButton>(R.id.editSearch)
        buttonSearch.setOnClickListener {
            val width = ConstraintLayout.LayoutParams.MATCH_PARENT
            val height = ConstraintLayout.LayoutParams.MATCH_PARENT
            val focusable = true

            val window = PopupWindow(view,width, height, focusable)

            val viewSearch = layoutInflater.inflate(R.layout.search_popup, null)
            window.contentView = viewSearch

            val imageView = viewSearch.findViewById<ImageView>(R.id.close)
            imageView.setOnClickListener {
                window.dismiss()
            }
            window.showAtLocation(view, Gravity.CENTER, 0, 0)
        }

        // code for servings dropdown menu
        val spinnerServings: Spinner = view.findViewById(R.id.spinnerServing)
        val spinnerAdapter2 = ArrayAdapter.createFromResource(
            requireContext(), R.array.servings_dropdown, android.R.layout.simple_spinner_item
        )
        spinnerAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerServings.adapter = spinnerAdapter2

        // code for meals dropdown menu
        val spinnerMeals: Spinner = view.findViewById(R.id.spinnerMeal)
        val spinnerAdapter = ArrayAdapter.createFromResource(
            requireContext(), R.array.meals_dropdown, android.R.layout.simple_spinner_item
        )
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerMeals.adapter = spinnerAdapter

        //code for "my nutrition plan" popup window
        val buttonPlan = view.findViewById<Button>(R.id.plan)
        buttonPlan.setOnClickListener {
            val width = ConstraintLayout.LayoutParams.MATCH_PARENT
            val height = ConstraintLayout.LayoutParams.MATCH_PARENT
            val focusable = true

            val window = PopupWindow(view,width, height, focusable)

            val viewPlan = layoutInflater.inflate(R.layout.nutrion_plan_popup, null)
            window.contentView = viewPlan

            val imageView = viewPlan.findViewById<ImageView>(R.id.close)
            imageView.setOnClickListener {
                window.dismiss()
            }
            window.showAtLocation(view, Gravity.CENTER, 0, 0)
        }

        //code for searching
        val sView = inflater.inflate(R.layout.search_popup, container, false)

        imageList = arrayOf(
            R.drawable.baseline_food,
            R.drawable.baseline_local_drink_24,
            R.drawable.baseline_food,
            R.drawable.baseline_food,
            R.drawable.baseline_food,
            R.drawable.baseline_food)

        titleList = arrayOf(
            "Apple              95 cals/serving",
            "Coffee               1 cal/serving",
            "Egg                78 cals/serving",
            "Chicken Breast    231 cals/serving",
            "Chicken Thigh      206 cals/serving",
            "Rice (Basmati)     210 cals/serving")

        with (sView) {
            recyclerView = findViewById(R.id.recyclerView)
            searchView = findViewById(R.id.editSearch)
        }


        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.setHasFixedSize(true)

        dataList = arrayListOf()
        searchList = arrayListOf()
        getData()

//**** left to do:
//        -get recycler view in pop up working
//        - get recycler view to breakfasttiems list to work

        searchView.clearFocus()
        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView.clearFocus()
                return true
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun onQueryTextChange(newText: String?): Boolean {
                searchList.clear()
                val searchText = newText!!.lowercase(Locale.getDefault())
                if (searchText.isNotEmpty()){
                    dataList.forEach{
                        if (it.dataTitle.lowercase(Locale.getDefault()).contains(searchText)){
                            searchList.add(it)
                        }
                    }
                    recyclerView.adapter!!.notifyDataSetChanged()
                } else{
                    searchList.clear()
                    searchList.addAll(dataList)
                    recyclerView.adapter!!.notifyDataSetChanged()
                }
                return false
            }

        })

        //breakfast
        //editTextNewItem = sView.findViewById(R.id.searchView)
//        listViewItems = view.findViewById(R.id.listBreakfast)
//        itemsAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, breakfastItems)
//        listViewItems.adapter = itemsAdapter
//        view.findViewById<Button>(R.id.add).setOnClickListener {
//            addNewItemBreakfast()
//        }
//        listViewItems.setOnItemClickListener { _, _, position, _ ->
//            val currentItem = breakfastItems[position]
//            if(!currentItem.endsWith(" ✔")) {
//                breakfastItems[position] = breakfastItems[position] + " ✔"
//            } else {
//                breakfastItems[position] = currentItem.removeSuffix(" ✔")
//            }
//            itemsAdapter.notifyDataSetChanged()
//        }
//        listViewItems.setOnItemLongClickListener { _, _, position, _ ->
//            breakfastItems.removeAt(position)
//            itemsAdapter.notifyDataSetChanged()
//            true
//        }

        return view
    }


    //code for "log item" message
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val logButton = view.findViewById<Button>(R.id.add)
        val linearLayout = view.findViewById<LinearLayout>(R.id.linlayout)
        val notif = view.findViewById<TextView>(R.id.notif)

        logButton.setOnClickListener {
            linearLayout.visibility = View.VISIBLE
        }

        notif.setOnClickListener {
            linearLayout.visibility = View.INVISIBLE
        }
    }

    //code for recycler view for search bar
    private fun getData(){
        for (i in imageList.indices){
            val dataClass = DataClass(imageList[i],titleList[i])
            dataList.add(dataClass)
        }
        searchList.addAll(dataList)
        recyclerView.adapter = AdapterClass(searchList)
    }

    // code for breakfast recycler list
//    private fun addNewItemBreakfast() {
//        //val newItem = editTextNewItem.text.toString()
//        val newItem = searchList[0].toString()
//        if (newItem.isNotBlank()) {
//            breakfastItems.add(newItem)
//            itemsAdapter.notifyDataSetChanged()
//            //editTextNewItem.text.clear()
//            searchList.clear()
//        }
//    }


}
// code inspired from :

//https://medium.com/@evanbishop/popupwindow-in-android-tutorial-6e5a18f49cc7
//https://www.youtube.com/watch?v=q91GB08OL54


