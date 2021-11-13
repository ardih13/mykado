package com.test.mykado.ui.activity

import android.Manifest
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import java.util.ArrayList

open class BaseLocalActivity : AppCompatActivity() {

    private val permissions = arrayOf(
        Manifest.permission.ACCESS_NETWORK_STATE,
        Manifest.permission.INTERNET,
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE
    )

//    fun initPermission() {
//        val localPerms = ArrayList<String>()
//        localPerms.addAll(listOf(*permissions))
//        Nammu.init(applicationContext)
//        Nammu.getGrantedPermissions()
//
//        Nammu.permissionCompare(object : PermissionListener {
//            override fun permissionsChanged(s: String) {
//
//            }
//
//            override fun permissionsGranted(s: String) {
//                localPerms.remove(s)
//            }
//
//            override fun permissionsRemoved(s: String) {
//
//            }
//        })
//        if (android.os.Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) {
//            if (!Nammu.checkPermission(Manifest.permission.CALL_PHONE) || !Nammu.checkPermission(
//                    Manifest.permission.CAMERA
//                )
//            ) {
//                Nammu.askForPermission(
//                    this,
//                    localPerms.toTypedArray(),
//                    object : PermissionCallback {
//                        override fun permissionGranted() {
//
//                        }
//
//                        override fun permissionRefused() {
//                            this@BaseLocalActivity.finish()
//                        }
//                    })
//            }
//        } else {
//            if (!Nammu.hasPermission(this, permissions))
//                Nammu.askForPermission(
//                    this,
//                    localPerms.toTypedArray(),
//                    object : PermissionCallback {
//                        override fun permissionGranted() {
//
//                        }
//
//                        override fun permissionRefused() {
//                            this@BaseLocalActivity.finish()
//                        }
//                    })
//        }
//
//    }

}