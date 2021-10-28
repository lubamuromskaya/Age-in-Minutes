package com.udemy.agecalc

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonDateSelector : Button = findViewById(R.id.button_date_selector)

        buttonDateSelector.setOnClickListener {
            dateSelection()
        }
    }

    private fun dateSelection() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener { view, year, month, day ->
                Toast.makeText(this, "selected date is $year-$month-$day", Toast.LENGTH_SHORT).show()
            },
            year,
            month,
            day
        ).show()

    }
}