package com.example.mobileassignment

import android.content.Context
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.mobileassignment.models.Application
import com.example.mobileassignment.models.Job
import com.example.mobileassignment.models.User
import com.google.firebase.database.*
import java.util.ArrayList

class JobDetailsActivity : AppCompatActivity() {
    lateinit var context: Context
    lateinit var databaseRef: DatabaseReference
    private val data = ArrayList<Application>()
    private val data1 = ArrayList<User>()
    lateinit var applyDatabase: DatabaseReference
    lateinit var userDatabase: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.job_details)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        val i = intent
        val position1 = i.getStringExtra("position")
        val description1 = i.getStringExtra("description")
        val salary1 = i.getStringExtra("salary")
        val requirement1 = i.getStringExtra("requirement")
        val category1 = i.getStringExtra("category")
        val jobid1 = i.getStringExtra("jobId")

        var salarytext: TextView = findViewById(R.id.salarytxt)
        var profiletext: TextView = findViewById(R.id.profiletxt)
        var descriptiontext: TextView = findViewById(R.id.descriptiontxt)
        var categorytext: TextView = findViewById(R.id.categorytxt)
        var reqirementtext: TextView = findViewById(R.id.requirementtxt)

        profiletext.text = position1
        descriptiontext.text = description1
        salarytext.text = salary1
        categorytext.text = category1
        reqirementtext.text = requirement1

        applyDatabase = FirebaseDatabase.getInstance().getReference("Application")

        applyDatabase.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {

            }

            override fun onCancelled(p0: DatabaseError) {

            }

        })

        userDatabase = FirebaseDatabase.getInstance().getReference("User")

        userDatabase.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {

            }

            override fun onCancelled(p0: DatabaseError) {

            }

        })






    }
}