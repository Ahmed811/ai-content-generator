package com.virastudio.postgeneratorai

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.widget.Toast
//+x@+Pt4@62RJuwY
object AppUtility {
    fun copyToClipboard(context: Context, text: String) {
        val clipboardManager = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

        val clipData = ClipData.newPlainText("text", text)
        clipboardManager.setPrimaryClip(clipData)
    Toast.makeText(context,"Text Copied to Clipboard",Toast.LENGTH_LONG).show()
    }

    fun shareTextToFacebook(context: Context, text: String) {
        copyToClipboard(context,text)
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.setPackage("com.facebook.katana") // Specify Facebook package name

        // Check if Facebook is installed
        if (intent.resolveActivity(context.packageManager) != null) {
            intent.putExtra(Intent.EXTRA_TEXT, text)
            context.startActivity(intent)
        } else {
            // Facebook is not installed, handle accordingly
            Toast.makeText(context, "Facebook app not installed", Toast.LENGTH_SHORT).show()
        }
    }
    fun shareText(context: Context, text: String) {
        copyToClipboard(context,text)
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"



        if (intent.resolveActivity(context.packageManager) != null) {
            intent.putExtra(Intent.EXTRA_TEXT, text)
            context.startActivity(intent)
        } else {

            Toast.makeText(context, "There is no app can handle this!", Toast.LENGTH_SHORT).show()
        }
    }

}