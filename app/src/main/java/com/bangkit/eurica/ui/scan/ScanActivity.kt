package com.bangkit.eurica.ui.scan

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.bangkit.eurica.databinding.ActivityScanBinding
import com.bangkit.eurica.ml.EuricaModel
import com.bangkit.eurica.utils.createCustomTempFile
import com.bangkit.eurica.utils.uriToFile
import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.image.TensorImage
import org.tensorflow.lite.support.image.ops.ResizeOp
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer
import java.io.File
import java.nio.ByteBuffer


class ScanActivity : AppCompatActivity() {
    private lateinit var binding: ActivityScanBinding
    private var imagePath: String = ""
    private var imageFile: File? = null
    private var resultId = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivityScanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnScanNow.isEnabled = false
        binding.btnScanNow.isClickable = false

        binding.toScanResult.isEnabled = false
        binding.toScanResult.isClickable = false

        binding.toScanResult.setOnClickListener {
            val intent = Intent(this, ScanResultActivity::class.java)
            intent.putExtra(ScanResultActivity.EXTRA_ID, resultId)
            startActivity(intent)
        }

        if (!allPermissionsGranted()) {
            ActivityCompat.requestPermissions(
                this,
                REQUIRED_PERMISSIONS,
                REQUEST_CODE_PERMISSIONS
            )
        }

        binding.btnCamera.setOnClickListener {
            val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            cameraIntent.resolveActivity(packageManager)

            createCustomTempFile(application).also {
                val imageUri: Uri = FileProvider.getUriForFile(
                    this,
                    "com.bangkit.eurica",
                    it
                )
                imagePath = it.absolutePath
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)
                cameraIntentLauncher.launch(cameraIntent)
            }
        }

        binding.btnGallery.setOnClickListener {
            val galleryIntent = Intent()
            galleryIntent.action = Intent.ACTION_GET_CONTENT
            galleryIntent.type = "image/*"

            val gallerySelector = Intent.createChooser(
                galleryIntent,
                "Select Image from Gallery"
            )
            galleryIntentLauncher.launch(gallerySelector)

        }

        binding.btnScanNow.setOnClickListener {
            val filePath = imagePath
            val bitmap = BitmapFactory.decodeFile(filePath)

            var resized = Bitmap.createScaledBitmap(bitmap, 224, 224, true)
            val model = EuricaModel.newInstance(this)

            val image = TensorImage(DataType.FLOAT32)
            image.load(resized)

            val byteBuffer = image.buffer

            val inputFeature0 = TensorBuffer.createFixedSize(intArrayOf(1, 224, 224, 3), DataType.FLOAT32)
            inputFeature0.loadBuffer(byteBuffer)

            val outputs = model.process(inputFeature0)
            val outputFeature0 = outputs.outputFeature0AsTensorBuffer


            val maxIdx = getMax(outputFeature0.floatArray)
            resultId = when (maxIdx) {
                0 -> 1
                1 -> 2
                2 -> 3
                4 -> 5
                5 -> 6
                6 -> 8
                else -> -1
            }

            val resultText = when (maxIdx) {
                0 -> "Seribu"
                1 -> "Dua Ribu"
                2 -> "Lima Ribu"
                3 -> "Sepuluh Ribu"
                4 -> "Dua Puluh Ribu"
                5 -> "Lima Puluh Ribu"
                else -> "Seratus Ribu"
            }

            for (i in 0..6) {
                Log.d("INT", "${outputFeature0.intArray[i]}\n")
                Log.d("FLOAT !!!!", "${outputFeature0.floatArray[i]}\n")
            }

            model.close()

            binding.toScanResult.isEnabled = true
            binding.toScanResult.isClickable = true
        }
    }
    
    private fun getMax(arr: FloatArray): Int {
        var ind = 0
        var min = 0.0f

        for (i in 0..6) {
            if (arr[i] > min) {
                min = arr[i]
                ind = i
            }
        }
        return ind
    }

    private val cameraIntentLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == RESULT_OK) {
            val imageFromCamera = File(imagePath)
            imageFile = imageFromCamera

            val result = BitmapFactory.decodeFile(imageFile?.path)
            binding.ivCreateStory.setImageBitmap(result)

            binding.btnScanNow.isEnabled = true
            binding.btnScanNow.isClickable = true
        }
    }

    private val galleryIntentLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val selectedImg: Uri = result.data?.data as Uri

            contentResolver
            val imageFromGallery = uriToFile(selectedImg, this)

            imageFile = imageFromGallery
            binding.ivCreateStory.setImageURI(selectedImg)

            imagePath = imageFile!!.absolutePath

            binding.btnScanNow.isEnabled = true
            binding.btnScanNow.isClickable = true
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (!allPermissionsGranted()) {
                Toast.makeText(
                    this,
                    "Did not get permission",
                    Toast.LENGTH_SHORT
                ).show()
                finish()
            }
        }
    }

    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(baseContext, it) == PackageManager.PERMISSION_GRANTED
    }

    companion object {
        private const val REQUEST_CODE_PERMISSIONS = 10
        private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)
    }
}