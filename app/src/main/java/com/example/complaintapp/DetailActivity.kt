package com.example.complaintapp

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        supportActionBar?.apply {
            title = "Complaint Detail"
            setDisplayHomeAsUpEnabled(true)
        }

        val b = intent.extras ?: return

        findViewById<TextView>(R.id.tvDetailTitle).text = b.getString("title", "")
        findViewById<TextView>(R.id.tvDetailName).text = "👤 Student: ${b.getString("name", "")}"
        findViewById<TextView>(R.id.tvDetailRoll).text = "🎓 Roll No: ${b.getString("roll", "")}"
        findViewById<TextView>(R.id.tvDetailCategory).text = "📁 Category: ${b.getString("category", "")}"
        findViewById<TextView>(R.id.tvDetailPriority).text = "⚡ Priority: ${b.getString("priority", "")}"
        findViewById<TextView>(R.id.tvDetailStatus).text = "🔄 Status: ${b.getString("status", "Pending")}"
        findViewById<TextView>(R.id.tvDetailDate).text = "📅 Date: ${b.getString("date", "")}"
        findViewById<TextView>(R.id.tvDetailDescription).text = b.getString("description", "")
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}