package com.shinjaehun.permissionfundamentalexample

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btn = findViewById<Button>(R.id.btnRequestPermission)
        btn.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                requestPermissions()
            }
        }
    }
    
    private fun hasWriteExternalStoragePermission() = 
        ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED

    private fun hasLocationForegroundPermission() =
        ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED

    private fun hasLocationBackgroundPermission() =
        ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_BACKGROUND_LOCATION) == PackageManager.PERMISSION_GRANTED
    // If you need this for a real app, then you also should check if the user actually uses Android Q.
    // Otherwise, you don't want to check that permission and it may crash without a check

    private fun requestPermissions() {
        var permissionsToRequest = mutableListOf<String>()
        if (!hasWriteExternalStoragePermission()) {
            permissionsToRequest.add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
            // 이게 29- 정책이라서 아마 적용되지 않고 있는 듯하다...
        }
        if (!hasLocationForegroundPermission()) {
            permissionsToRequest.add(Manifest.permission.ACCESS_COARSE_LOCATION)
        }
        if (!hasLocationBackgroundPermission() && hasLocationForegroundPermission()) {
            // To learn more why this works, look up "Request location permissions" in the Android Developer documentation.
            // There is a statement that says requesting foreground and background permissions at the same time will be ignored.
            // Furthermore, background permissions will not be granted unless foreground permissions were previously granted.
            permissionsToRequest.add(Manifest.permission.ACCESS_BACKGROUND_LOCATION)
        }

        if(permissionsToRequest.isNotEmpty()) {
            ActivityCompat.requestPermissions(this, permissionsToRequest.toTypedArray(), 0)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 0 && grantResults.isNotEmpty()) {
//            for (i in 0.. grantResults.size - 1)
            for (i in grantResults.indices) {
                if(grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    Log.i("PermissionsRequest", "${permissions[i]} granted")
                }
            }
        }
    }
}