package com.example.myapplication

import android.os.Bundle
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.google.android.material.tabs.TabLayout
import android.widget.ArrayAdapter
import android.app.Activity
import android.view.View
import android.widget.ImageButton
import android.widget.Toast
import android.widget.LinearLayout
import android.widget.SearchView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import java.util.Locale


class MainActivity : AppCompatActivity() {
    private var progr = 60
    private var progr2 = 90
    private var progress_bar : ProgressBar? = null
    private var progress_bar2 : ProgressBar? = null
    private var tv_progress :TextView?= null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        progress_bar = findViewById(R.id.progress_bar)
        progress_bar2 = findViewById(R.id.progress_bar2)
        tv_progress = findViewById(R.id.text_view_progress)

        updateProgressBar1()
        updateProgressBar2()

        val tabViewpager = findViewById<ViewPager>(R.id.viewPager)
        val tab_tablayout = findViewById<TabLayout>(R.id.tabbyCat)
        setupViewPager(tabViewpager)
        tab_tablayout.setupWithViewPager(tabViewpager)

    }


    private fun updateProgressBar1() {
        progress_bar!!.progress = progr
        tv_progress!!.text = "$progr%"
    }

    private fun updateProgressBar2() {
        progress_bar2!!.progress = progr2
        tv_progress!!.text = "$progr2%"
    }

    private fun setupViewPager(viewpager: ViewPager) {
        var adapter: ViewPagerAdapter = ViewPagerAdapter(supportFragmentManager)

        // TextFragment is the name of Fragment and the text
        // is a title of tab
        adapter.addFragment(NutritionFragment(), "Nutrition")
        adapter.addFragment(HomeFragment(), "Home")
        adapter.addFragment(ExerciseFragment(), "Exercise")
        adapter.addFragment(ProgressFragment(), "Progress")

        // setting adapter to view pager.
        viewpager.setAdapter(adapter)
    }

    class ViewPagerAdapter// this is a secondary constructor of ViewPagerAdapter class.
    public constructor(supportFragmentManager: FragmentManager) :
        FragmentPagerAdapter(supportFragmentManager) {

        // objects of arraylist. One is of Fragment type and
        // another one is of String type.*/
        private final var fragmentList1: ArrayList<Fragment> = ArrayList()
        private final var fragmentTitleList1: ArrayList<String> = ArrayList()

        // returns which item is selected from arraylist of fragments.
        override fun getItem(position: Int): Fragment {
            return fragmentList1.get(position)
        }

        // returns which item is selected from arraylist of titles
        override fun getPageTitle(position: Int): CharSequence {
            return fragmentTitleList1.get(position)
        }

        // returns the number of items present in arraylist.
        override fun getCount(): Int {
            return fragmentList1.size
        }

        // this function adds the fragment and title in 2 separate  arraylist.
        fun addFragment(fragment: Fragment, title: String) {
            fragmentList1.add(fragment)
            fragmentTitleList1.add(title)
        }

    }



}

