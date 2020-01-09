package com.example.mobileassignment.jobDetailsAndApplyList

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mobileassignment.R
import com.example.mobileassignment.ViewApplicationActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.job_details.*
import java.util.ArrayList

class JobDetailsActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var mListadapter: ViewApplyAdapter
    private lateinit var jobDatabase: DatabaseReference
    private lateinit var applyDatabase: DatabaseReference
    private lateinit var userDatabase: DatabaseReference
    private lateinit var finderDatabase: DatabaseReference
    private val data = ArrayList<cardViewApplication>()
    private lateinit var loManage: RecyclerView.LayoutManager
    var maxid: Long = 0
    var mAuth = FirebaseAuth.getInstance().currentUser!!.uid

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.job_details)
        var jobId: String = intent.getStringExtra("jobId")

        jobDatabase = FirebaseDatabase.getInstance().reference.child("Job")

        jobDatabase.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                if(p0.exists())
                    maxid = p0.childrenCount
                for (i in 1..maxid){
                    if((i).toString().equals(jobId)){
                        profiletxt.text =  "Position: "+ p0.child((i).toString()).child("position").value.toString()
                        salarytxt.text =  p0.child((i).toString()).child("salary").value.toString()
                        requirementtxt.text =  p0.child((i).toString()).child("requirement").value.toString()
                        descriptiontxt.text =  p0.child((i).toString()).child("desc").value.toString()
                        categorytxt.text =  p0.child((i).toString()).child("category").value.toString()
                    }
                }
            }
            override fun onCancelled(p0: DatabaseError) {
            }
        })

        val tv: TextView = findViewById(R.id.textViewid)

        applyDatabase = FirebaseDatabase.getInstance().getReference("Application")

        applyDatabase.addListenerForSingleValueEvent(object: ValueEventListener{
            override fun onDataChange(p0: DataSnapshot) {
                if (p0.exists())
                    maxid = p0.childrenCount
                for (i in 1..maxid) {
                    if (p0.child((i).toString()).child("jobId").value.toString().equals(jobId)) {
                        var username: String = ""
                        var userAddress: String = ""
                        var userAge: String = ""
                        var userId: String =
                            p0.child(i.toString()).child("userId").value.toString()

                        finderDatabase = FirebaseDatabase.getInstance().getReference("Finder")

                        finderDatabase.addListenerForSingleValueEvent(object: ValueEventListener{
                            override fun onDataChange(ds: DataSnapshot) {
                                userAge = ds.child(userId).child("age").value.toString()
                            }
                            override fun onCancelled(p0: DatabaseError) {
                            }
                        })

                        userDatabase = FirebaseDatabase.getInstance().reference.child("User")

                        userDatabase.addListenerForSingleValueEvent(object : ValueEventListener {
                            override fun onDataChange(ds: DataSnapshot) {
                                username = ds.child(userId).child("user_name").value.toString()
                                userAddress = ds.child(userId).child("user_address").value.toString()
                                data.add(
                                    cardViewApplication(
                                        username,
                                        userAddress,
                                        userAge,
                                        userId
                                    )
                                )
                                tv.text = userId
                            }
                            override fun onCancelled(p0: DatabaseError) {
                            }
                        })
                    }
                }
                tv.visibility = View.INVISIBLE
            }
            override fun onCancelled(p0: DatabaseError) {
            }
        })

        buildRecyclerView()
    }

    open fun buildRecyclerView() {
        recyclerView = findViewById(R.id.recyclerView2)
        recyclerView.setHasFixedSize(true)
        loManage = LinearLayoutManager(this)
        mListadapter = ViewApplyAdapter(data)
        recyclerView.layoutManager = loManage
        recyclerView.adapter = mListadapter
        mListadapter.setOnItemClickListener(
            ViewApplyAdapter.OnItemClickListener(
                fun(it: Int) {
                    val i = Intent(this, ViewApplicationActivity::class.java)
                    i.putExtra("userId", data.get(it).userId)
                    i.putExtra("userage", data.get(it).userAge)
                    i.putExtra("userName", data.get(it).userName)
                    i.putExtra("userAddress", data.get(it).userAddress)
                    startActivity(i)
                })
        )
    }
}