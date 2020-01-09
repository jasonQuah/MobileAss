package com.example.mobileassignment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mobileassignment.models.Job
import com.example.mobileassignment.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import java.util.ArrayList

class ViewJobActivity : Fragment() {

    private var recyclerView: RecyclerView? = null
    private var mListadapter: ListAdapter? = null
    private val data = ArrayList<Job>()
    private val data1 = ArrayList<User>()
    lateinit var jobDatabase: DatabaseReference
    lateinit var userDatabase: DatabaseReference
    private var oname: String = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.view_job_list, container, false)

        var mAuth = FirebaseAuth.getInstance().currentUser!!.uid

        recyclerView = view.findViewById<View>(R.id.recyclerView) as RecyclerView

        val layoutManager = LinearLayoutManager(activity)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView!!.layoutManager = layoutManager

        jobDatabase = FirebaseDatabase.getInstance().getReference("Job")

        jobDatabase.addValueEventListener(object : ValueEventListener {
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

        userDatabase = FirebaseDatabase.getInstance().getReference("User")

        userDatabase.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                if (p0!!.exists()) {
                    for (h in p0.children) {
                        val u = h.getValue(User::class.java)
                        data1.add(u!!)
                    }
                }
            }
            override fun onCancelled(p0: DatabaseError) {
            }
        })
        return view
    }

    inner class ListAdapter(private val dataList: ArrayList<Job>) :
        RecyclerView.Adapter<ListAdapter.ViewHolder>() {

        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            internal var image: ImageView
            internal var textViewPosition: TextView
            internal var textViewName: TextView
            internal var textViewDesc: TextView

            init {
                this.image =  itemView.findViewById<View>(R.id.imageView3)as ImageView
                this.textViewPosition = itemView.findViewById<View>(R.id.jobProfile) as TextView
                this.textViewName = itemView.findViewById<View>(R.id.companyName) as TextView
                this.textViewDesc = itemView.findViewById<View>(R.id.jobDescription) as TextView
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListAdapter.ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.custom_view_job_list, parent, false)

            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ListAdapter.ViewHolder, position: Int) {
            for(i in 0..data1.size){
                if(data1[i].user_id.equals(dataList[position].user_id)){
                    oname = data1[i].user_name
                    break
                }
            }
            holder.textViewPosition.text = dataList[position].job_Position
            holder.textViewName.text = oname
            holder.textViewDesc.text = dataList[position].job_description

            holder.itemView.setOnClickListener {v->
                val i = Intent(v.context, JobDetailsActivity::class.java)

                i.putExtra("companyName", oname)
                i.putExtra("position", data[position].job_Position)
                i.putExtra("description", data[position].job_description)
                i.putExtra("jobId", data[position].job_id)
                i.putExtra("requirement", data[position].job_requirement)
                i.putExtra("salary",data[position].salary)
                i.putExtra("category",data[position].getcategory_name())
                v.context.startActivity(i)
            }
        }
        override fun getItemCount(): Int {
            return dataList.size
        }
    }
}



//compare job_id
//if same add user_id
//oname = username