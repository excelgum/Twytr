package com.twytr.twytr

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_post.*
import kotlinx.android.synthetic.main.activity_scrolling.*

class PostActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)
        button.setOnClickListener { view ->
            run {
                println("reached2")
                val intent = Intent(this@PostActivity,ScrollingActivity::class.java);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                startActivity(intent)
            }
            //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
            //.setAction("Action", null).show()
        }
    }
}
