package com.test.mykado.util

import android.graphics.Bitmap
import java.io.*

class FileUtil private constructor() {
    /**
     * Stores the given [Bitmap] to a path on the device.
     *
     * @param bitmap   The [Bitmap] that needs to be stored
     * @param filePath The path in which the bitmap is going to be stored.
     */
    open fun storeBitmap(bitmap: Bitmap, filePath: String?): Unit {
        val imageFile = File(filePath)
        imageFile.parentFile.mkdirs()
        try {
            val fout: OutputStream = FileOutputStream(imageFile)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, fout)
            fout.flush()
            fout.close()
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    companion object {
        private var mInstance: FileUtil? = null
        val instance: FileUtil?
            get() {
                if (mInstance == null) {
                    synchronized(FileUtil::class.java) {
                        if (mInstance == null) {
                            mInstance = FileUtil()
                        }
                    }
                }
                return mInstance
            }
    }
}