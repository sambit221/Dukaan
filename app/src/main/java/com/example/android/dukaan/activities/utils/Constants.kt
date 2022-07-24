package com.example.android.dukaan.activities.utils

import android.app.Activity
import android.content.Intent
import android.provider.MediaStore

object Constants {
    const val USERS: String ="users"

    const val Dukaan_Preferences: String = "DukaanPref"
    const val Logged_In_UserName: String = "logged in Username"
    const val EXTRA_USER_DETAIlS :String = "extra_user_details"
    const val READ_STORAGE_PERMISSION_CODE = 2
    const val PICK_IMAGE_REQUEST_CODE = 1

    // Request the intent to select the image using the unique code.
    // A function for user profile image selection from phone storage.
    fun showImageChooser(activity: Activity) {
        // An intent for launching the image selection of phone storage.
        val galleryIntent = Intent(
            Intent.ACTION_PICK,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        )
        // Launches the image selection of phone storage using the constant code.
        activity.startActivityForResult(galleryIntent, PICK_IMAGE_REQUEST_CODE)
    }
}