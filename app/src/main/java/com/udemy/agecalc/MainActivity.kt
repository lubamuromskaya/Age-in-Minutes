package com.udemy.agecalc

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*

class MainActivity : AppCompatActivity() {
    private var dateField : TextView? = null
    private var dateInMinutes : TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonDateSelector : Button = findViewById(R.id.button_date_selector)
        dateField = findViewById(R.id.date)
        dateInMinutes = findViewById(R.id.result)

        buttonDateSelector.setOnClickListener {
            dateSelection()
        }
    }

    fun dateSelection() {
        val calendar = Calendar.getInstance()
        val selectedYear: Int = calendar.get(Calendar.YEAR)
        val selectedMonth: Int = calendar.get(Calendar.MONTH)
        val selectedDay: Int = calendar.get(Calendar.DAY_OF_MONTH)

        DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener { view, year, month, day ->
                Toast.makeText(this, "selected date is $year-$month-$day", Toast.LENGTH_SHORT).show()

                val date = "$year/${month + 1}/$day"
                dateField?.text = date

                val dateFormatter = SimpleDateFormat("yyyy/MM/dd", Locale.ENGLISH)
                val parsedDateInMinutes = dateFormatter.parse(date).time / 60000

                val currentDate = dateFormatter.parse(dateFormatter.format(System.currentTimeMillis()))
                val currentDateInMinutes = currentDate.time / 60000

                val resultMinutes = currentDateInMinutes - parsedDateInMinutes

                dateInMinutes?.text = resultMinutes.toString()
            },
            selectedYear,
            selectedMonth,
            selectedDay
        ).show()

    }
}