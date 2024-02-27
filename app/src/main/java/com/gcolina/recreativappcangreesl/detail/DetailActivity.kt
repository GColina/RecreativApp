package com.gcolina.recreativappcangreesl.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gcolina.recreativappcangreesl.databinding.ActivityDetailBinding
import com.gcolina.recreativappcangreesl.detail.RecyclerViews.rvEarnings.EarningAdapter
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

private lateinit var binding: ActivityDetailBinding
private lateinit var earningAdapter: EarningAdapter

class DetailActivity : AppCompatActivity() {

    private val database = Firebase.database
    // Load the assets here
    // viewModel.getAssets(database)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)



        listeners()

    }

    private fun listeners() {
        binding.btnCalendar.setOnClickListener { showDatePickerDialog() }
        binding.btnNowCalendar.setOnClickListener { getCurrentDate() }
        binding.btnSave.setOnClickListener { getInfoDB() }

    }

    fun getInfoDB() {


    }

    fun getCurrentDate() {
        val currentDate = java.util.Calendar.getInstance()
        val day = currentDate.get(java.util.Calendar.DAY_OF_MONTH)
        val month =
            currentDate.get(java.util.Calendar.MONTH) + 1 // Los meses en Calendar son 0-indexados
        val year = currentDate.get(java.util.Calendar.YEAR)
        binding.tvDate.text = "$day/$month/$year"
    }

    private fun showDatePickerDialog() {
        val datePicker = DatePickerFragment { day, month, year -> onDateSelected(day, month, year) }
        datePicker.show(supportFragmentManager, "datePicker")
    }

    fun onDateSelected(
        day: Int,
        month: Int,
        year: Int,
    ) {
        binding.tvDate.text = "$day/$month/$year"
    }


}
