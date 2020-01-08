package com.example.mobileassignment

import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mobileassignment.models.Jobs
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuth.AuthStateListener
import kotlinx.android.synthetic.main.add_job.*
import com.google.firebase.internal.FirebaseAppHelper.getUid
import com.google.firebase.auth.FirebaseUser
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.util.Log
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.sign_up_activity.*


class addJobActivity : AppCompatActivity() {

    private var mAuth = FirebaseAuth.getInstance()
    private lateinit var jobDatabase: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_job)

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


        if (TextUtils.isEmpty(jobPosition)) {
            Toast.makeText(
                this,
                "Please enter the position",
                Toast.LENGTH_SHORT
            ).show()
        }

        if (TextUtils.isEmpty(jobDescription)) {
            Toast.makeText(
                this,
                "Please enter the description",
                Toast.LENGTH_SHORT
            ).show()
        }

        if (TextUtils.isEmpty(jobSalary)) {
            Toast.makeText(
                this,
                "Please enter the salary",
                Toast.LENGTH_SHORT
            ).show()
        }

        if (TextUtils.isEmpty(jobRequirement)) {
            Toast.makeText(
                this,
                "Please enter the requirement",
                Toast.LENGTH_SHORT
            ).show()
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

            val jobb = Jobs(newJobid, jobPosition, jobDescription, jobSalary, jobRequirement, userID)
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



    }

}