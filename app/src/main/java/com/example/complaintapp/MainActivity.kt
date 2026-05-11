package com.example.complaintapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var fabAdd: FloatingActionButton
    private lateinit var tvEmpty: TextView
    private lateinit var adapter: ComplaintAdapter
    private val complaintList = mutableListOf<Complaint>()
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db = FirebaseFirestore.getInstance()  // ← initialize here, not outside

        recyclerView = findViewById(R.id.recyclerView)
        fabAdd = findViewById(R.id.fabAdd)
        tvEmpty = findViewById(R.id.tvEmpty)

        adapter = ComplaintAdapter(complaintList) { complaint ->
            val intent = Intent(this, DetailActivity::class.java).apply {
                putExtra("id", complaint.id)
                putExtra("name", complaint.studentName)
                putExtra("roll", complaint.rollNumber)
                putExtra("title", complaint.title)
                putExtra("category", complaint.category)
                putExtra("priority", complaint.priority)
                putExtra("description", complaint.description)
                putExtra("status", complaint.status)
                putExtra("date", complaint.createdAt?.toDate()?.toString() ?: "")
            }
            startActivity(intent)
        }

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        fabAdd.setOnClickListener {
            startActivity(Intent(this, RegisterComplaintActivity::class.java))
        }

        loadComplaints()
    }

    private fun loadComplaints() {
        db.collection("complaints")
            .orderBy("createdAt", Query.Direction.DESCENDING)
            .addSnapshotListener { value, error ->
                if (error != null || value == null) return@addSnapshotListener
                complaintList.clear()
                for (doc in value.documents) {
                    val c = doc.toObject(Complaint::class.java)
                    if (c != null) {
                        c.id = doc.id
                        complaintList.add(c)
                    }
                }
                adapter.notifyDataSetChanged()
                tvEmpty.visibility = if (complaintList.isEmpty()) View.VISIBLE else View.GONE
            }
    }
}