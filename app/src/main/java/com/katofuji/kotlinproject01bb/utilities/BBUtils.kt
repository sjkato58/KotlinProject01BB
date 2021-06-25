package com.katofuji.kotlinproject01bb.utilities

import android.text.TextUtils
import androidx.fragment.app.Fragment
import java.lang.Exception

class BBUtils {

    companion object {

        fun isValid(fragment: Fragment): Boolean
        {
            if (fragment.isAdded && fragment.activity != null)
            {
                return true
            }
            return false
        }

        fun isTextEmpty(sampleText: String?): Boolean
        {
            var wasEmpty = true
            try {
                if (!TextUtils.isEmpty(sampleText) && sampleText != "")
                {
                    wasEmpty = false
                }
            }
            catch (ex : Exception)
            {
                ex.printStackTrace()
            }
            return wasEmpty
        }
    }
}