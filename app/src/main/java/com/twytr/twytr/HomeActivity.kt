package com.twytr.twytr

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_scrolling.*

class HomeActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_insights -> {
                val intent = Intent(this@HomeActivity,InsightsActivity::class.java)
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivityForResult(intent, 0);
                overridePendingTransition(0,0);
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_searchs -> {
                val intent = Intent(this@HomeActivity,SearchActivity::class.java);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivityForResult(intent, 0);
                overridePendingTransition(0,0);
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scrolling)
        setSupportActionBar(toolbar)
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.navigation) as BottomNavigationView
        bottomNavigationView.selectedItemId = R.id.navigation_home

        fab.setOnClickListener { view ->
            run {
                println("reached")
                val intent = Intent(this@HomeActivity,PostActivity::class.java);
                startActivity(intent)
            }
            //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
            //.setAction("Action", null).show()
        }


        //setTitle("Hi %s!".format(intent.getStringExtra("Username")));
        setTitle("Hi %s!".format("Admin"))
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }
}
