package com.give3.gizrog.give3

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity

abstract class BaseAppCompactActivity: AppCompatActivity() {

    fun startActivityForResult(activity: Activity, intent: Intent, requestCode: Int, bundle: Bundle?) {
        intent.putExtra("RequestCode", requestCode)
        ActivityCompat.startActivityForResult(activity, intent,1, bundle)
    }

}