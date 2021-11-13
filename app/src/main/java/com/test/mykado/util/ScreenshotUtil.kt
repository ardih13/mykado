package com.test.mykado.util

import android.app.Activity
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.view.View
import android.graphics.drawable.Drawable




class ScreenshotUtil private constructor() {
    /**
     * Measures and takes a screenshot of the provided [View].
     *
     * @param view The view of which the screenshot is taken
     * @return A [Bitmap] for the taken screenshot.
     */
    fun takeScreenshotForView(view: View): Bitmap {
        val returnedBitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(returnedBitmap)
        val bgDrawable = view.background
        if (bgDrawable != null) bgDrawable.draw(canvas)
        else canvas.drawColor(Color.WHITE)
        view.draw(canvas)
        return returnedBitmap
    }

    //create bitmap from the ScrollView
     fun getBitmapFromView(view: View, height: Int, width: Int): Bitmap? {
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        val bgDrawable = view.background
        if (bgDrawable != null) bgDrawable.draw(canvas) else canvas.drawColor(Color.WHITE)
        view.draw(canvas)
        return bitmap
    }

    fun takeScreenshotForScreen(activity: Activity): Bitmap {
        return takeScreenshotForView(activity.window.decorView.rootView)
    }

    companion object {
        private var mInstance: ScreenshotUtil? = null
        val instance: ScreenshotUtil?
            get() {
                if (mInstance == null) {
                    synchronized(ScreenshotUtil::class.java) {
                        if (mInstance == null) {
                            mInstance = ScreenshotUtil()
                        }
                    }
                }
                return mInstance
            }
    }
}