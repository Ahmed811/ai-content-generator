package com.virastudio.postgeneratorai

import android.app.AlertDialog
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast

object AppInfo {


    val developerMail="virastudio001@gmail.com"


    fun sendEmail(context: Context, recipient: String, subject: String, body: String) {
        val emailIntent = Intent(Intent.ACTION_SEND).apply {
            type = "message/rfc822"
            putExtra(Intent.EXTRA_EMAIL, arrayOf(recipient))
            putExtra(Intent.EXTRA_SUBJECT, subject)
            putExtra(Intent.EXTRA_TEXT, body)
        }

        if (emailIntent.resolveActivity(context.packageManager) != null) {
            context.startActivity(emailIntent)
        } else {
            Toast.makeText(context, "No email app found", Toast.LENGTH_SHORT).show()
        }
    }

    fun openPlayStoreForReview(packageName: String, context: Context) {
        try {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("market://details?id=$packageName")
            context.startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            // If Google Play Store app is not available, open in browser
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://play.google.com/store/apps/details?id=$packageName")
            context.startActivity(intent)
        }
    }
//    fun showRatingPrompt(context: Context) {
//        val alertDialog = AlertDialog.Builder(context)
//            .setTitle("هل استمتعت بالتطبيق؟")
//            .setMessage("نرجو منك ان تقيم التطبيق،وذلك حتي نتمكن من تقديم تطبيقات اخري مفيدة للمجتمع")
//            .setPositiveButton("نعم") { dialog, which ->
//                openPlayStoreForRating(context)
//            }
//            .setNegativeButton("في وقت لاحق") { dialog, which ->
//                // User chose not to leave a rating
//                // You can add additional actions here if needed
//            }
//            .setCancelable(false)
//            .create()
//
//        alertDialog.show()
//    }

    private fun openPlayStoreForRating(context: Context) {
        val appPackageName = context.packageName
        try {
            context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$appPackageName")))
        } catch (e: android.content.ActivityNotFoundException) {
            context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=$appPackageName")))
        }
    }
}

