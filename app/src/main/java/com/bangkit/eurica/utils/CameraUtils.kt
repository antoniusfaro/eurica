package com.bangkit.eurica.utils

import android.app.Application
import android.content.ContentResolver
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.net.Uri
import android.os.Environment
import com.bangkit.eurica.R
import java.io.*
import java.text.SimpleDateFormat
import java.util.*


private const val FILENAME_FORMAT = "dd-MMM-yyyy"

val timeStamp: String = SimpleDateFormat(
    FILENAME_FORMAT,
    Locale.US
).format(System.currentTimeMillis())

fun createCustomTempFile(context: Context): File {
    val storageDir: File? = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
    return File.createTempFile(timeStamp, ".jpg", storageDir)
}

fun createFile(application: Application): File {
    val mediaDir = application.externalMediaDirs.firstOrNull()?.let {
        File(it, application.resources.getString(R.string.app_name)).apply { mkdirs() }
    }

    val outputDirectory = if (
        mediaDir != null && mediaDir.exists()
    ) mediaDir else application.filesDir

    return File(outputDirectory, "$timeStamp.jpg")
}

fun rotateBitmap(bitmap: Bitmap, isBackCamera: Boolean = false): Bitmap {
    val matrix = Matrix()
    return if (isBackCamera) {
        matrix.postRotate(90f)
        Bitmap.createBitmap(
            bitmap,
            0,
            0,
            bitmap.width,
            bitmap.height,
            matrix,
            true
        )
    } else {
        matrix.postRotate(-90f)
        matrix.postScale(-1f, 1f, bitmap.width / 2f, bitmap.height / 2f)
        Bitmap.createBitmap(
            bitmap,
            0,
            0,
            bitmap.width,
            bitmap.height,
            matrix,
            true
        )
    }
}

fun uriToFile(selectedImg: Uri, context: Context): File {
    var len: Int
    val buf = ByteArray(1024)

    val contentResolver: ContentResolver = context.contentResolver
    val tempFile = createCustomTempFile(context)

    val inStream = contentResolver.openInputStream(selectedImg) as InputStream
    val outStream: OutputStream = FileOutputStream(tempFile)

    while (inStream.read(buf).also { len = it } > 0)
        outStream.write(buf, 0, len)

    inStream.close()
    outStream.close()

    return tempFile
}

fun reduceFileImage(file: File): File {
    var len: Int
    var compressQuality = 100
    val decodedFile = BitmapFactory.decodeFile(file.path)

    do {
        val bmpStream = ByteArrayOutputStream()
        decodedFile.compress(Bitmap.CompressFormat.JPEG, compressQuality, bmpStream)

        val bmpPicByteArray = bmpStream.toByteArray()

        len = bmpPicByteArray.size
        compressQuality -= 5
    } while (len > 1000000)

    decodedFile.compress(
        Bitmap.CompressFormat.JPEG,
        compressQuality,
        FileOutputStream(file)
    )
    return file
}