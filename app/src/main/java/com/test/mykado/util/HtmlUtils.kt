package com.test.mykado.util

import android.text.Spanned
import android.text.Html

object HtmlUtils {
    /**
     * Get HTML Content
     *
     * @param content
     * @return
     */
     fun getHTMLContent(content: String?): Spanned {
        return Html.fromHtml(content)
    }
}