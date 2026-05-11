package com.example.complaintapp

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.Timestamp

class RegisterComplaintActivity : AppCompatActivity() {

    private lateinit var etName: EditText
    private lateinit var etRoll: EditText
    private lateinit var etTitle: EditText
    private lateinit var etDescription: EditText
    private lateinit var spinnerCategory: Spinner
    private lateinit var spinnerPriority: Spinner
    private lateinit var btnSubmit: Button
    private lateinit var db: FirebaseFirestore

    private val categories = arrayOf("IT","Library","Transport","Hostel","Accounts","Examination","Cafeteria","Administration")
    private val priorities = arrayOf("Low","Medium","High","Urgent")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        db = FirebaseFirestore.getInstance()

        etName = findViewById(R.id.etName)
        etRoll = findViewById(R.id.etRoll)
        etTitle = findViewById(R.id.etTitle)
        etDescription = findViewById(R.id.etDescription)
        spinnerCategory = findViewById(R.id.spinnerCategory)
        spinnerPriority = findViewById(R.id.spinnerPriority)
        btnSubmit = findViewById(R.id.btnSubmit)

        supportActionBar?.apply {
            title = "Register Complaint"
            setDisplayHomeAsUpEnabled(true)
        }

        spinnerCategory.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, categories)
        spinnerPriority.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, priorities)

        btnSubmit.setOnClickListener { submitComplaint() }
    }

    private fun submitComplaint() {
        val name = etName.text.toString().trim()
        val roll = etRoll.text.toString().trim()
        val title = etTitle.text.toString().trim()
        val desc = etDescription.text.toString().trim()
        val category = spinnerCategory.selectedItem.toString()
        val priority = spinnerPriority.selectedItem.toString()

        if (name.isEmpty()) { etName.error = "Required"; return }
        if (roll.isEmpty()) { etRoll.error = "Required"; return }
        if (title.isEmpty()) { etTitle.error = "Required"; return }
        if (desc.isEmpty()) { etDescription.error = "Required"; return }

        val complaint = hashMapOf(
            "studentName" to name,
            "rollNumber" to roll,
            "title" to title,
            "category" to category,
            "priority" to priority,
            "description" to desc,
            "status" to "Pending",
            "createdAt" to Timestamp.now()
        )

        db.collection("complaints").add(complaint)
            .addOnSuccessListener {
                Toast.makeText(this, "Complaint submitted!", Toast.LENGTH_SHORT).show()
                etName.text.clear()
                etRoll.text.clear()
                etTitle.text.clear()
                etDescription.text.clear()
                spinnerCategory.setSelection(0)
                spinnerPriority.setSelection(0)
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Error: ${e.message}", Toast.LENGTH_LONG).show()
            }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}