package com.example.mobileassignment

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mobileassignment.models.Job
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.add_job.*
import com.google.firebase.database.*
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import com.google.firebase.database.DataSnapshot
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.R.attr.name
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import com.example.mobileassignment.models.Category


class addJobActivity : AppCompatActivity() {

    private var mAuth = FirebaseAuth.getInstance()
    private lateinit var jobDatabase: DatabaseReference
    private lateinit var categoryDatabase: DatabaseReference
    private lateinit var spinner: Spinner
    private lateinit var listener: ValueEventListener
    private lateinit var adapter: ArrayAdapter<String>
    private lateinit var spinnerDataList: ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_job)

        spinner = findViewById<Spinner>(R.id.categorySpinner)

        categoryDatabase = FirebaseDatabase.getInstance().getReference("Category");

        spinnerDataList = ArrayList()
        adapter = ArrayAdapter(
            this@addJobActivity,
            android.R.layout.simple_spinner_dropdown_item,
            spinnerDataList
        )

        spinner.adapter = adapter
        retrieveData()

        mAuth = FirebaseAuth.getInstance()

        val addBtn = findViewById<Button>(R.id.addjobbtn)
        val backBtn = findViewById<Button>(R.id.backbtn)

        addBtn.setOnClickListener {
            saveNewJobFunction()
        }

        backBtn.setOnClickListener {
            backFunction()
        }
    }

    private fun saveNewJobFunction() {
        mAuth = FirebaseAuth.getInstance()

        val jobPosition = addJobsPosition.text.toString().trim()
        val jobDescription = addJobsDescription.text.toString().trim()
        val jobSalary = addJobsSalary.text.toString().trim()
        val jobRequirement = addJobsRequirement.text.toString().trim()
        val jobCategory = categorySpinner.selectedItem.toString()


        if (TextUtils.isEmpty(jobPosition)) {
            Toast.makeText(
                this, "Please enter the position", Toast.LENGTH_SHORT).show()
        }

        if (TextUtils.isEmpty(jobDescription)) {
            Toast.makeText(
                this, "Please enter the description", Toast.LENGTH_SHORT).show()
        }

        if (TextUtils.isEmpty(jobSalary)) {
            Toast.makeText(
                this, "Please enter the salary", Toast.LENGTH_SHORT).show()
        }

        if (TextUtils.isEmpty(jobRequirement)) {
            Toast.makeText(
                this, "Please enter the requirement", Toast.LENGTH_SHORT).show()
        }

        val userID:String = mAuth.currentUser!!.uid

        jobDatabase = FirebaseDatabase.getInstance().getReference()
        /*var database = FirebaseDatabase.getInstance()

        var rootRef = database.reference

        val userRef = rootRef.child("User").orderByChild("user_id").equalTo(userID)
        val valueEventListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (ds in dataSnapshot.children) {
                    val username = ds.child("user_address").getValue(String::class.java)
                    //Log.d(TAG, username)
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                //Log.d(TAG, databaseError.getMessage()) //Don't ignore errors!
            }
        }
        userRef.addListenerForSingleValueEvent(valueEventListener)*/



        val newJobid = jobDatabase.push().key

        if(newJobid!=null) {
            val jobb = Job(newJobid, jobPosition, jobDescription, jobSalary, jobRequirement, jobCategory, userID)
            jobDatabase.child("Job").child(newJobid).setValue(jobb).addOnCompleteListener {
                Toast.makeText(
                    applicationContext,
                    "Job Saved Successfully",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        /*AuthStateListener { firebaseAuth ->
                val firebaseUser = firebaseAuth.currentUser
                if (firebaseUser != null) {
                    val userId = firebaseUser.uid
                    val userEmail = firebaseUser.email
                }
            }*/
    }

    private fun backFunction(){
        startActivity(Intent(this, StaffHomePage::class.java))

    }

    public fun retrieveData(){
        listener = categoryDatabase.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(p0: DataSnapshot) {
                for (item in p0.getChildren()) {
                    /*val ct = Category()
                    ct.setName(p0.child(name.toString()).getValue(Category::class.java)?.getName())

                    spinnerDataList.add(ct.getName())*/
                    spinnerDataList.add(item.getValue().toString())
                }
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(p0: DatabaseError) {

            }
        });

    }

}