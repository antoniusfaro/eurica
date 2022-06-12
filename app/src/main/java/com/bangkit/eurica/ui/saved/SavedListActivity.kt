package com.bangkit.eurica.ui.saved

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.eurica.R
import com.bangkit.eurica.adapter.ItemAdapter
import com.bangkit.eurica.adapter.SavedItemAdapter
import com.bangkit.eurica.data.ItemDataSource
import com.bangkit.eurica.databinding.ActivitySavedListBinding
import com.bangkit.eurica.local.SavedList
import com.bangkit.eurica.local.SavedListDao
import com.bangkit.eurica.local.UserDatabase
import com.bangkit.eurica.model.Item
import com.bangkit.eurica.model.ItemData
import com.bangkit.eurica.network.ApiConfig
import com.bangkit.eurica.network.ItemResponse
import com.bangkit.eurica.ui.scan.ItemListActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SavedListActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySavedListBinding
    private lateinit var savedListDao: SavedListDao
    private lateinit var userDb: UserDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivitySavedListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userDb = UserDatabase.getDatabase(application)!!
        savedListDao = userDb?.savedListDao()

//        binding.imageView2.setOnClickListener{
//            val intent = Intent(this, DetailSavedActivity::class.java)
//            startActivity(intent)
//        }

        var dataset: List<SavedList> = listOf()
        dataset = getSavedList()
//        Log.d("TEST DATASET", "$testDataset")
//        for (item in testDataset) {
//            Log.d("TEST DATASET", "$item")
//        }
        val recyclerView = findViewById<RecyclerView>(R.id.rv_saved_list)

        recyclerView.adapter = SavedItemAdapter(this, dataset, "Rp. 50,000.00")

    }

    private fun getSavedList(): List<SavedList> {
        return savedListDao!!.getSavedList()
    }

    companion object {
        private const val TAG = "SavedListActivity"
    }
}