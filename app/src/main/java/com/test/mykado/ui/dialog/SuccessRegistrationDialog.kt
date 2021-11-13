package com.test.mykado.ui.dialog

import android.content.Context
import android.os.Bundle
import android.view.WindowManager
import androidx.annotation.NonNull
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.test.mykado.R
import kotlinx.android.synthetic.main.dialog_registration_success.*

class SuccessRegistrationDialog(
    @NonNull context: Context,
    style: Int,
    private val callback: IRegistration
) : BottomSheetDialog(context, style) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_registration_success)
        setCancelable(false)

        val lWindowParams: WindowManager.LayoutParams = WindowManager.LayoutParams()
        lWindowParams.copyFrom(window?.attributes)
        lWindowParams.width = WindowManager.LayoutParams.MATCH_PARENT
        lWindowParams.height = WindowManager.LayoutParams.MATCH_PARENT
        this.window?.attributes = lWindowParams

        btnOk.setOnClickListener {
            this.dismiss()
            callback.onSuccessRegistration()
        }
    }

    override fun onBackPressed() {
        this.dismiss()
        callback.onSuccessRegistration()
        super.onBackPressed()
    }

    interface IRegistration {
        fun onSuccessRegistration()
    }
}