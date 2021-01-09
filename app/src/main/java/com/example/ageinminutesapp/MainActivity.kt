package com.example.ageinminutesapp

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnDatePicker.setOnClickListener{view->
            clickDatePicker(view)
            //Toast.makeText(this, "Button works", Toast.LENGTH_LONG).show()
        }

        //TODO: Add current date
    }

    fun clickDatePicker(view:View){

        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)
        
        val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener {
            view, selectedYear, selectedMonth, selectedDayOfMonth ->
            val selectedDate = "$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"
            tvSelectedDate.setText(selectedDate)
            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
            val theDate = sdf.parse(selectedDate)
            val selectedDateinDays = theDate!!.time/(3600000*24)
            val selectedDateinHours = theDate!!.time/3600000
            val selectedDateinMinutes = theDate!!.time/60000

            val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
            val currentDateInMinutes = currentDate!!.time / 60000
            val currentDateInHours = currentDate!!.time / 3600000
            val currentDateinDays = currentDate!!.time/(3600000*24)

            val differenceInMinutes = currentDateInMinutes-selectedDateinMinutes
            val differenceInHours = currentDateInHours-selectedDateinHours
            val differenceInDays = currentDateinDays - selectedDateinDays


            tvAgeinMinutes.text = "${differenceInMinutes.toString()}"
            tvAgeinHours.text = "${differenceInHours.toString()}"
            tvAgeinDays.text = "${differenceInDays.toString()}"

            /*
            tvSelectedDate.setText(theDate.toString())
            Toast.makeText(this, selectedDate, Toast.LENGTH_LONG).show()
            */

        }
                , year
                , month
                , day)
        dpd.datePicker.setMaxDate(Date().time - 86400000)
        dpd.show()
    }

}