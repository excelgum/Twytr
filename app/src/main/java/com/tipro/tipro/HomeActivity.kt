package com.tipro.tipro

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_scrolling.*
import android.widget.ListView
import com.google.firebase.database.*
import UserListAdapter

class HomeActivity : AppCompatActivity() {

    var listView: ListView? = null
    var adapter: UserListAdapter? = null
    var itemList: ArrayList<UserDto>? = null


    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_insights -> {
                val intent = Intent(this@HomeActivity, InsightsActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                startActivityForResult(intent, 0)
                overridePendingTransition(0,0)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_searchs -> {
                val intent = Intent(this@HomeActivity, SearchActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                startActivityForResult(intent, 0)
                overridePendingTransition(0,0)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scrolling)
        setSupportActionBar(toolbar)
        val bottomNavigationView: BottomNavigationView = findViewById<BottomNavigationView>(R.id.navigation)
        bottomNavigationView.selectedItemId = R.id.navigation_home

        fab.setOnClickListener { view ->
            run {
                val intent = Intent(this@HomeActivity, PostActivity::class.java)
                startActivity(intent)
            }
            //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
            //.setAction("Action", null).show()
        }


        //setTitle("Hi %s!".format(intent.getStringExtra("Username")));
        title = "Hi %s!".format("Admin")
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        //val font = Typeface.createFromAsset(assets, "fonts/fontawesome-webfont.ttf")
        //val awesomeButton = findViewById<View>(R.id.awesome_button) as Button
        //awesomeButton.setTypeface(font)
        //awesomeButton.setText("\uf0e5")

        listView = findViewById<ListView>(R.id.listView)
        itemList = ArrayList<UserDto>()
        adapter = UserListAdapter(this, itemList!!)

        listView?.adapter = adapter
        adapter?.notifyDataSetChanged()

        var itemListener: ValueEventListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Get Post object and use the values to update the UI
                addDataToList(dataSnapshot)
            }
            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Item failed, log a message
            }
        }



        FirebaseDatabase.getInstance().getReference("admin").orderByKey().addListenerForSingleValueEvent(itemListener)

    }

    private fun addDataToList(dataSnapshot: DataSnapshot) {
        val children = dataSnapshot.children
        children.forEach {
            var user: UserDto = UserDto("Admin", it.child("text").value.toString())
            itemList?.add(user)
        }
        //alert adapter that has changed
        adapter?.notifyDataSetChanged()
    }
/*    fun generateData(): ArrayList<UserDto> {
        var result = ArrayList<UserDto>()

        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("admin")
        val message = myRef.key

        for (i in 0..20) {
            var user: UserDto = UserDto("Admin", "Awesome work ;)")
            result.add(user)
        }

        return result
    }*/
}