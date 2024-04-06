package com.example.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.PopupWindow
import android.widget.SearchView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView

class PopUpClass2 : Fragment(){

    @SuppressLint("ClickableViewAccessibility", "InflateParams")
    fun showPopupWindow(view: View) {

        val popupView = LayoutInflater.from(context).inflate(R.layout.nutrion_plan_popup, null, false)

        val width = ConstraintLayout.LayoutParams.MATCH_PARENT
        val height = ConstraintLayout.LayoutParams.MATCH_PARENT
        val focusable = true

        val popupWindow = PopupWindow(popupView, width, height, focusable)

        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0)

        popupView.setOnTouchListener { _, _ ->
            popupWindow.dismiss()
            true
        }
    }
}

// code inspired from https://medium.com/@evanbishop/popupwindow-in-android-tutorial-6e5a18f49cc7
