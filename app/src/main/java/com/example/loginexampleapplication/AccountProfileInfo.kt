package com.example.loginexampleapplication

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.content.ContextCompat
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_account_profile_info.*
import java.util.jar.Manifest

class AccountProfileInfo : AppCompatActivity() {

    private val CAMERA_PERMISSION_REQUEST_CODE: Int= 2000
    val CAMERA_REQUEST_CODE=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account_profile_info)

        profileImage.setOnClickListener{
            prepTakePhoto()

        }
    }
        /*check permission*/
    private fun prepTakePhoto() {
      if(ContextCompat.checkSelfPermission(applicationContext!!, android.Manifest.permission.CAMERA)
          ==PackageManager.PERMISSION_GRANTED){
          takePhoto()
      }else{
          val permissionRequest = arrayOf(android.Manifest.permission.CAMERA)
          requestPermissions(permissionRequest, CAMERA_PERMISSION_REQUEST_CODE)
      }
    }
    /*Grand Permissions*/
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            CAMERA_PERMISSION_REQUEST_CODE->{
                if (grantResults.isNotEmpty()&&grantResults[0]==PackageManager.PERMISSION_GRANTED)
                    takePhoto()
                else{
                    Toast.makeText(applicationContext,"No permission", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    /*Get photo*/
    private fun takePhoto() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also {
            takePictureIntent-> takePictureIntent.resolveActivity(applicationContext!!.packageManager)?.also {
                startActivityForResult(takePictureIntent,CAMERA_REQUEST_CODE)
            }
        }
    }
    /*process photo*/
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            CAMERA_REQUEST_CODE->{
                if (resultCode==Activity.RESULT_OK&&data!=null){
                   var image=data.extras!!.get("data")as Bitmap
                    profileImage.setImageBitmap(image)
                }
            }
            else->{
                Toast.makeText(this,"Unrecognized request code",Toast.LENGTH_SHORT)
            }
        }

    }
}
