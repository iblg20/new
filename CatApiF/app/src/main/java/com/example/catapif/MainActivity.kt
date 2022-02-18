package com.example.catapif

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.catapif.adapter.CatAdapter
import com.example.catapif.api.RetrofitApi
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_new.*
import kotlinx.android.synthetic.main.raw_items.*
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Coroutines
        retrieveList()

        //Refresh
        button_refresh.setOnClickListener {
            retrieveList()
        }
    }

    private fun retrieveList() {
        // Error handler
        val errorHandler = CoroutineExceptionHandler { _, exception ->
            AlertDialog.Builder(this).setTitle("Error")
                .setMessage(exception.message)
            Snackbar.make(
                snackbar_txt,
                R.string.snackbar_msg,
                Snackbar.LENGTH_INDEFINITE
            ).setAction("RETRY") {
                retrieveList()
            }.show()
        }

        // Run Coroutine
        val coroutineScope = CoroutineScope(Dispatchers.Main)
        coroutineScope.launch(errorHandler) {
            var resultList = RetrofitApi.service.getData()
            var catAdapter = CatAdapter(resultList, this@MainActivity)

            recyclerview_cats.layoutManager = GridLayoutManager(this@MainActivity, 2)
            recyclerview_cats.adapter = catAdapter

            catAdapter.setOnItemClickListener(object : CatAdapter.onItemClickListener {
                override fun onItemClick(position: Int) {
/*                    val imagePosition = position + 1
                    Toast.makeText(this@MainActivity, "You clicked on image # $imagePosition",
                        Toast.LENGTH_SHORT).show()*/

                    val intent = Intent(this@MainActivity, NewActivity::class.java)
                    intent.putExtra("url", resultList[position].url)
                    intent.putExtra("id", resultList[position].id)
                    startActivity(intent)
                }
            })
        }
    }

}