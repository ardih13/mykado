package com.test.mykado.util

import android.util.Patterns

class UrlUtils {

    companion object{
        fun isValidUrl(url: String): Boolean {
            val p = Patterns.WEB_URL
            val m = p.matcher(url)
            return m.matches()
        }
    }

}