package org.sopt.dosopttemplate.util

import android.view.View
import com.google.android.material.snackbar.Snackbar

object ShowSnackBar{
    private var snackBar: Snackbar? = null

    fun showSnackBar(view:View, msg: String){
        snackBar = Snackbar.make(view, msg, Snackbar.LENGTH_SHORT)
        requireNotNull(snackBar).show()
    }
}