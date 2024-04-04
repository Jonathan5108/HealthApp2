package com.example.myapplication

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

class AdapterClass(private val dataList: ArrayList<DataClass>) : RecyclerView.Adapter<AdapterClass.ViewHolderClass>(){

    class ViewHolderClass(itemView: View): RecyclerView.ViewHolder(itemView){
        val rvImage: ImageView = itemView.findViewById(androidx.appcompat.R.id.image)

    }
}