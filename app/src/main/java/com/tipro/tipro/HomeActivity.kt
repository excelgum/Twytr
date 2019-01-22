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
    var itemList: ArrayList<HomeRow>? = null
    var username: String = "admin"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scrolling)
        setSupportActionBar(toolbar)
        val bottomNavigationView: BottomNavigationView = findViewById<BottomNavigationView>(R.id.navigation)
        bottomNavigationView.selectedItemId = R.id.navigation_home
        fab.setOnClickListener { view ->
            run {
                val intent = Intent(this@HomeActivity, PostActivity::class.java)
                intent.putExtra("username", username)
                startActivity(intent)
            }
        }
        username = intent.getStringExtra("username")
        title = "Hi %s!".format(username)
        navigation.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_insights -> {
                    val intent = Intent(this@HomeActivity, InsightsActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                    intent.putExtra("username", username)
                    startActivityForResult(intent, 0)
                    overridePendingTransition(0,0)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_searchs -> {
                    val intent = Intent(this@HomeActivity, SearchActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                    intent.putExtra("username", username)
                    startActivityForResult(intent, 0)
                    overridePendingTransition(0,0)
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        })

        //val font = Typeface.createFromAsset(assets, "fonts/fontawesome-webfont.ttf")
        //val awesomeButton = findViewById<View>(R.id.awesome_button) as Button
        //awesomeButton.setTypeface(font)
        //awesomeButton.setText("\uf0e5")

        listView = findViewById<ListView>(R.id.listView)
        itemList = ArrayList<HomeRow>()
        adapter = UserListAdapter(this, itemList!!)
        listView?.adapter = adapter
        adapter?.notifyDataSetChanged()

        val itemListener: ValueEventListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val children = dataSnapshot.children
                children.forEach {
                    var user: HomeRow = HomeRow(username, it.child("text").value.toString())
                    itemList?.add(user)
                }
                adapter?.notifyDataSetChanged()
            }
            override fun onCancelled(databaseError: DatabaseError) {
            }
        }
        FirebaseDatabase.getInstance().getReference("Posts").child(username).orderByKey().addListenerForSingleValueEvent(itemListener)
    }
}
