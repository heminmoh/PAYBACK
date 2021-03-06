/**
 * Custom Progress Dialog
 * specify with PayBack Logo
 * 2022-06-18 11:00
 */
package com.example.payback.utilities

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.example.payback.R

class PayBackProgressDialog {

     lateinit var dialog: CustomDialog
    fun show(context : Context, title: CharSequence?): Dialog {
        val inflater = (context as Activity).layoutInflater
        val view = inflater.inflate(R.layout.progress_dialog_view, null)
        val cpTitle = view.findViewById<View>(R.id.cp_title) as TextView
        val cpCardView = view.findViewById<View>(R.id.cp_CardView) as CardView

        if (title != null) {
            cpTitle.text = title
        }
        cpCardView.setCardBackgroundColor(Color.parseColor("#70000000"))
        cpTitle.setTextColor(Color.WHITE)

        dialog = CustomDialog(context)
        dialog.setContentView(view)
        dialog.show()
        return dialog
    }



    class CustomDialog(context: Context) : Dialog(context, R.style.CustomDialogTheme) {

        init {

            window?.decorView?.rootView?.setBackgroundResource(R.color.green_400)

        }

    }

}