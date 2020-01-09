package com.example.mobileassignment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mobileassignment.models.Application
import com.example.mobileassignment.models.Job
import com.example.mobileassignment.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import java.util.ArrayList

class ViewApplicationActivity {

    private var recyclerView: RecyclerView? = null
    private var mListadapter: ViewJobActivity.ListAdapter? = null
    private val data = ArrayList<Application>()
    private val data1 = ArrayList<User>()
    lateinit var applyDatabase: DatabaseReference
    lateinit var userDatabase: DatabaseReference


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.job_details, container, false)

        recyclerView = view.findViewById<View>(R.id.recyclerView2) as RecyclerView

        val layoutManager = LinearLayoutManager(activity)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView!!.layoutManager = layoutManager

        applyDatabase = FirebaseDatabase.getInstance().getReference("Application")

        applyDatabase.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                if (p0!!.exists()) {
                    data.clear()
                    for (h in p0.children) {
                        val job = h.getValue(Job::class.java)
                        if (job!!.user_id == mAuth) {
                            data.add(job!!)
                        }

                    }
                    mListadapter = ListAdapter(data)
                    recyclerView!!.adapter = mListadapter
                }
            }

            override fun onCancelled(p0: DatabaseError) {
            }
        })




}