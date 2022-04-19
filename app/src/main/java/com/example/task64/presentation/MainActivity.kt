package com.example.task64.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.example.task64.R
import com.example.task64.databinding.ActivityMainBinding
import example.task64.fragments.FavoriteFragment
import com.example.task64.presentation.fragments.MainFragment

class MainActivity : AppCompatActivity() , FragmentsTrans {
    private lateinit var mainBinding: ActivityMainBinding
    val favoriteFragment = FavoriteFragment()
    val mainFragment = MainFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = mainBinding.root

        setContentView(view)

       transact(mainFragment)

    }

    override fun onCreateOptionsMenu(menu : Menu): Boolean {
        menuInflater.inflate(R.menu.favorite_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.fav_id) {
            if (!favoriteFragment.isAdded) {
                transact(favoriteFragment)
            }
            return true
        }
        return super.onOptionsItemSelected(item)
    }


    override fun transact(f: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.placeholder, f)
                .addToBackStack(null)
                .commit()
    }


}