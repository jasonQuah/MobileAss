package com.example.mobileassignment

import android.content.Context
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference

class JobDetailsActivity : AppCompatActivity() {
    lateinit var context: Context
    lateinit var databaseRef: DatabaseReference

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

    }
}