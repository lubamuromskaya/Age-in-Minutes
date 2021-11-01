package com.udemy.agecalc

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private var dateField : TextView? = null
    private var dateInMinutesField : TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonDateSelector : Button = findViewById(R.id.button_date_selector)
        dateField = findViewById(R.id.date)
        dateInMinutesField = findViewById(R.id.result)

        buttonDateSelector.setOnClickListener {
            dateSelection()
        }
    }

    private fun dateSelection() {
        val calendar = Calendar.getInstance()
        val selectedYear: Int = calendar.get(Calendar.YEAR)
        val selectedMonth: Int = calendar.get(Calendar.MONTH)
        val selectedDay: Int = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener { _, year, month, day ->
                Toast.makeText(this, "selected date is $year-$month-$day", Toast.LENGTH_SHORT).show()

                val date = "$year/${month + 1}/$day"
                dateField?.text = date

                val dateFormatter = SimpleDateFormat("yyyy/MM/dd", Locale.ENGLISH)

                dateFormatter.parse(date)?.let { selectedDate ->
                    val selectedDateInMinutes = selectedDate.time / 60000

                    dateFormatter
                        .parse(dateFormatter.format(System.currentTimeMillis()))
                        ?.let { currentDate ->
                            val currentDateInMinutes = currentDate.time / 60000
                            val resultMinutes = currentDateInMinutes - selectedDateInMinutes
                            dateInMinutesField?.text = resultMinutes.toString()
                        }
                }
            },
            selectedYear,
            selectedMonth,
            selectedDay
        )

        datePickerDialog.datePicker.maxDate = System.currentTimeMillis()
        datePickerDialog.show()
    }
}
