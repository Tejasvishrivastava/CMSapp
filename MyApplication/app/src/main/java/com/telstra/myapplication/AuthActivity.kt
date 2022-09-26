package com.telstra.myapplication


import android.os.Bundle

import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.telstra.myapplication.databinding.AuthActivityMainBinding


class AuthActivity : AppCompatActivity() {


    private lateinit var binding: AuthActivityMainBinding

/*    private var mAdapter: android.widget.ListAdapter?= null;
    private var mQuestions: MutableList<Question> = ArrayList()*/
    override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    binding = AuthActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)

/*

    val navController = findNavController(R.id.nav_host_fragment_content_main)
    appBarConfiguration = AppBarConfiguration(navController.graph)
    setupActionBarWithNavController(navController, appBarConfiguration)*/

    // mApiService = RestClient.client.create(APIService::class.java)


    /* listRecyclerView!!.layoutManager = LinearLayoutManager(this)

            mAdapter = ListAdapter(this, mQuestions, R.layout.question_item)
            listRecyclerView!!.adapter = mAdapter*/


}

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }



}