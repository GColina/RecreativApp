package com.gcolina.recreativappcangreesl.addAssets

import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.gcolina.recreativappcangreesl.addAssets.components.DatePickerFragment
import com.gcolina.recreativappcangreesl.addAssets.viewModel.AddAssetViewModel
import com.gcolina.recreativappcangreesl.data.model.AlertModel
import com.gcolina.recreativappcangreesl.data.model.AssetModel
import com.gcolina.recreativappcangreesl.data.model.EarningsModel
import com.gcolina.recreativappcangreesl.databinding.ActivityAddAssetsBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint
import java.io.ByteArrayOutputStream

@AndroidEntryPoint
class AddAssetsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddAssetsBinding
    private val viewModel: AddAssetViewModel by viewModels()
    private var base64Image: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddAssetsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        listeners()
    }

    private fun listeners() {
        binding.btnCalendar.setOnClickListener { showDatePickerDialog() }
        binding.btnNowCalendar.setOnClickListener { getCurrentDate() }
        binding.btnSave.setOnClickListener { saveAsset() }
        binding.ivAsset.setOnClickListener { dispatchTakePictureIntent() }
    }

    private fun saveAsset() {
        val database = Firebase.database
        val auth = Firebase.auth
        val currentUser = auth.currentUser
        val asset =
            AssetModel(
                image = base64Image,
                installationDate = binding.tvInstallationDate.text.toString(),
                installationUser = currentUser?.email ?: "Cangrejo no identificado",
                releaseDate = "release1",
                storeName = binding.etStoreName.text.toString(),
                storeAddress = "add1",
                storeLocLat = 1.1,
                storeLocLng = 1.1,
                ownerName = binding.etOwnerName.text.toString(),
                ownerPhoneNumber = binding.etOwnerPhone.text.toString(),
                ownerEmail = binding.etOwnerEmail.text.toString(),
                earnings =
                listOf(
                    EarningsModel(
                        date = binding.tvInstallationDate.text.toString(),
                        totalEarnings = binding.etTotalEarnings.text.toString(),
                        netEarnings = binding.etNetEarnings.text.toString()
                    )
                ),
                alerts =
                listOf(
                    AlertModel(
                        date = binding.tvInstallationDate.text.toString(),
                        user = currentUser?.email ?: "Cangrejo no identificado",
                        message = binding.etAlert.text.toString()
                    )
                )
            )

        // Luego, usa la fuente de datos para obtener la lista de máquinas
        viewModel.saveAssets(
            database,
            asset
        )
    }

    private fun getCurrentDate() {
        val currentDate = java.util.Calendar.getInstance()
        val day = currentDate.get(java.util.Calendar.DAY_OF_MONTH)
        val month =
            currentDate.get(java.util.Calendar.MONTH) + 1 // Los meses en Calendar son 0-indexados
        val year = currentDate.get(java.util.Calendar.YEAR)
        binding.tvInstallationDate.text = "$day/$month/$year"
    }

    private fun showDatePickerDialog() {
        val datePicker = DatePickerFragment { day, month, year -> onDateSelected(day, month, year) }
        datePicker.show(supportFragmentManager, "datePicker")
    }

    private fun onDateSelected(day: Int, month: Int, year: Int) {
        binding.tvInstallationDate.text = "$day/$month/$year"
    }

    private fun dispatchTakePictureIntent() {
        @Suppress("ktlint:standard:property-naming")
        val REQUEST_IMAGE_CAPTURE = 100
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        try {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
        } catch (e: ActivityNotFoundException) {
            // display error state(camera app not found) to the user
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 100 && resultCode == RESULT_OK) {
            val imageBitmap = data?.extras?.get("data") as Bitmap?
            // Aquí puedes hacer lo que necesites con la imagen capturada
            if (imageBitmap != null) {
                // Convertir el bitmap a Base64
                base64Image = convertToBase64(imageBitmap!!)
                Log.d("base64Converted", " Base64 codificado : $base64Image")
                // Configurar la imagen capturada en el ImageView
                binding.ivAsset.setImageBitmap(imageBitmap)
                // La imagen capturada está disponible como bitmap aquí
            } else {
                // Si el bitmap es nulo, puede haber ocurrido un error al capturar la imagen
            }
        }
    }
    fun convertToBase64(bitmap: Bitmap): String {
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, stream)
        val image = stream.toByteArray()
        return Base64.encodeToString(image, Base64.DEFAULT)
    }

    // Despues para pasar de Base64ToBitmap
    fun convertBase64ToBitmap(base64String: String): Bitmap? {
        // Decodificar la cadena Base64 a un arreglo de bytes
        val decodedBytes = Base64.decode(base64String, Base64.DEFAULT)
        // Convertir el arreglo de bytes a un bitmap
        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)
    }

    // val bitmapFromBase64 = convertBase64ToBitmap(base64Image)
}
