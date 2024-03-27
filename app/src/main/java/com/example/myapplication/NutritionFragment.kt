package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.widget.EditText
import android.widget.ListView
import android.widget.Button
import androidx.fragment.app.Fragment

class NutritionFragment : Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?

    ): View {
        val view = inflater.inflate(
        R.layout.nutrition, container, false
    )
        val spinnerMeals: Spinner = view.findViewById(R.id.spinnerMeal)
        val spinnerAdapter = ArrayAdapter.createFromResource(
            requireContext(), R.array.meals_dropdown,  android.R.layout.simple_spinner_item)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerMeals.adapter = spinnerAdapter

//        val rootView = inflater.inflate(R.layout.nutrition, container, false)
//        editTextNewItem = rootView.findViewById(R.id.editText)
//        listViewItems = rootView.findViewById(R.id.listView)
//        itemsAdapter = ArrayAdapter(requireContext(), R.layout.simple_list_item_1, todoItems)
//        listViewItems.adapter = itemsAdapter
//        rootView.findViewById<Button>(R.id.add).setOnClickListener {
//            addNewItem()
//        }
//        listViewItems.setOnItemClickListener { _, _, position, _ ->
//            val currentItem = todoItems[position]
//            if(!currentItem.endsWith(" ✔")) {
//                todoItems[position] = todoItems[position] + " ✔"
//            } else {
//                todoItems[position] = currentItem.removeSuffix(" ✔")
//            }
//            itemsAdapter.notifyDataSetChanged()
//        }
//        listViewItems.setOnItemLongClickListener { _, _, position, _ ->
//            todoItems.removeAt(position)
//            itemsAdapter.notifyDataSetChanged()
//            true

        return view
       }
    }

//    private fun addNewItem() {
//        val newItem = editTextNewItem.text.toString()
//        if (newItem.isNotBlank()) {
//            todoItems.add(newItem)
//            itemsAdapter.notifyDataSetChanged()
//            editTextNewItem.text.clear()
//        }
//    }
//}