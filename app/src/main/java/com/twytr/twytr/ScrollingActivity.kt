package com.twytr.twytr

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_scrolling.*

class ScrollingActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_me -> {
                println("first")
                val intent = Intent(this@ScrollingActivity,ScrollingActivity::class.java);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                startActivity(intent)

                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_insights -> {
                println("second")
                val intent = Intent(this@ScrollingActivity,ScrollingActivity2::class.java);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                startActivity(intent)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_searchs -> {
                println("third")
                val intent = Intent(this@ScrollingActivity,ScrollingActivity3::class.java);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                startActivity(intent)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scrolling)
        setSupportActionBar(toolbar)
        fab.setOnClickListener { view ->
            run {
                println("reached")
                val intent = Intent(this@ScrollingActivity,PostActivity::class.java);
                startActivity(intent)
            }
            //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
            //.setAction("Action", null).show()
        }
        setTitle("Hi %s!".format(intent.getStringExtra("Username")));
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }
}
