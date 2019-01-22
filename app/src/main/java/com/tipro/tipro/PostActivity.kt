package com.tipro.tipro

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TextInputEditText
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_post.*
import java.util.*

class PostActivity : AppCompatActivity() {
    var username: String = "admin"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)
        username = intent.getStringExtra("username")
        button.setOnClickListener { view ->
            run {
                // Write a message to the database
                val database = FirebaseDatabase.getInstance()
                val myRef = database.getReference("Posts").child(username)
                UUID.randomUUID().toString()
                val mEdit = findViewById<TextInputEditText>(R.id.edittext)
                val key = myRef.push().key.toString()
                //myRef.setValue(mEdit.getText().toString())
                val msg = Message(username, mEdit.text.toString(), key)
                myRef.child(key).setValue(msg)
                val intent = Intent(this@PostActivity, HomeActivity::class.java)
                intent.putExtra("username", username)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }
        }
    }
}
