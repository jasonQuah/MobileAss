package com.example.mobileassignment

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class StaffHomePage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.staff_main_page)

        val addBtn = findViewById<Button>(R.id.addjobBtn)
        val editBtn = findViewById<Button>(R.id.editjobBtn)
        val deleteBtn = findViewById<Button>(R.id.deletejobBtn)

        addBtn.setOnClickListener {
            startActivity(Intent(this, addJobActivity::class.java))
        }

        editBtn.setOnClickListener {
            startActivity(Intent(this, addJobActivity::class.java))
        }
        deleteBtn.setOnClickListener {
            startActivity(Intent(this, addJobActivity::class.java))
        }

    }
}