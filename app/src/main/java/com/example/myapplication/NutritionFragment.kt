package com.example.myapplication

import android.annotation.SuppressLint
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
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.PopupWindow
import android.widget.RelativeLayout
import android.widget.SearchView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.Locale


class NutritionFragment : Fragment() {
    //vars for nutrition page
    private lateinit var recyclerView: RecyclerView
    private lateinit var dataList: ArrayList<DataClass>
    lateinit var imageList: Array<Int>
    lateinit var titleList: Array<String>
    private lateinit var searchView: SearchView
    private lateinit var searchList: ArrayList<DataClass>

    @SuppressLint("InflateParams")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?

    ): View {

        val view = inflater.inflate(R.layout.nutrition, container, false)



        //code for search popup window
        val popupButton = view.findViewById<Button>(R.id.editSearch)
        popupButton.setOnClickListener {
            val popUpClass = PopUpClass()
            popUpClass.showPopupWindow(it)
        }

        //val bttnPopUp: ImageButton = view.findViewById<ImageButton>(R.id.editSearch)
//        bttnPopUp.setOnClickerListener{
//            val window = PopupWindow(this)
//            val viewPopup = layoutInflater.inflate(R.layout.search_popup, null)
//            window.contentView = viewPopup
//            val bttn = view.findViewById<ImageButton>(R.id.editSearch)
//            bttn.setOnClickListener {
//                window.dismiss()
//            }
//            window.showAsDropDown(bttnPopUp)
//        }

        // code for meals dropdown menu
        val spinnerMeals: Spinner = view.findViewById(R.id.spinnerMeal)
        val spinnerAdapter = ArrayAdapter.createFromResource(
            requireContext(), R.array.meals_dropdown, android.R.layout.simple_spinner_item
        )
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerMeals.adapter = spinnerAdapter

        // code for servings dropdown menu
        val spinnerServings: Spinner = view.findViewById(R.id.spinnerServing)
        val spinnerAdapter2 = ArrayAdapter.createFromResource(
            requireContext(), R.array.servings_dropdown, android.R.layout.simple_spinner_item
        )
        spinnerAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerServings.adapter = spinnerAdapter2

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
            "Rice (Basmati)     210 cals/serving",)

        recyclerView = sView.findViewById(R.id.recyclerView)
        searchView = sView.findViewById(R.id.editSearch)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.setHasFixedSize(true)

        dataList = arrayListOf<DataClass>()
        searchList = arrayListOf<DataClass>()
        getData()

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

        return view
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

    //code for "log item" message
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val logButton = view.findViewById<ImageButton>(R.id.add)
        val linearLayout = view.findViewById<LinearLayout>(R.id.linlayout)
        val notif = view.findViewById<TextView>(R.id.notif)

        logButton.setOnClickListener {
            linearLayout.visibility = View.VISIBLE
        }

        notif.setOnClickListener {
            linearLayout.visibility = View.INVISIBLE
        }
    }


}
// code inspired from :

//https://medium.com/@evanbishop/popupwindow-in-android-tutorial-6e5a18f49cc7
//https://www.youtube.com/watch?v=q91GB08OL54


