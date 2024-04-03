package com.example.myapplication

import android.app.SearchManager
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class SearchResultActivity {

    class SearchResultsActivity : AppCompatActivity() {

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.nutrition)
            handleIntent(intent)
        }

        override fun onNewIntent(intent: Intent) {
            super.onNewIntent(intent)
            handleIntent(intent)
        }

        private fun handleIntent(intent: Intent) {
            if (Intent.ACTION_SEARCH == intent.action) {
                val query = intent.getStringExtra(SearchManager.QUERY)
                Log.d("SEARCH", "Search query was: $query")
            }
        }
    }

}

//code derived from: https://developer.android.com/develop/ui/views/search/training/setup#kotlin