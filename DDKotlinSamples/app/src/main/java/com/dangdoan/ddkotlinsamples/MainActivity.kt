package com.dangdoan.ddkotlinsamples

import android.content.DialogInterface
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.util.Log
import android.view.View
import android.widget.Button
import com.dangdoan.ddkotlinsamples.R


class MainActivity : AppCompatActivity() {
    var confirmedExit: Boolean = false


    companion object {
        val TAG: String = MainActivity.javaClass.simpleName
    }

    //new start 1
    //Bundle?: the question mark indicates savedInstanceState is nullable
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.v(TAG, "onCreate()")
        setContentView(R.layout.activity_main)
        val buttonDrawerActvity: Button = findViewById(R.id.buttonDrawerActvity)
        buttonDrawerActvity.setOnClickListener(drawerActivityClick())

    }

    //new start 4
    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
    }

    //new start 2
    //foreground 1
    override fun onStart() {
        super.onStart()
    }

    //new start 3
    //foreground 2
    override fun onResume() {
        super.onResume()
    }

    //background 1
    //backpress 1
    override fun onPause() {
        super.onPause()
    }

    //background 2
    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }

    //background 3
    //backpress 2
    override fun onStop() {
        super.onStop()
    }

    //backpress 4
    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
    }

    //backpress 3
    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onBackPressed() {
        if(confirmedExit){
            super.onBackPressed()
            return
        }
        confirmExit()
    }

    fun exitApp(dialogInterface: DialogInterface?, which: Int){
        Log.v(TAG, "exitApp - WHICH = " + which)
        confirmedExit = true
        onBackPressed()
    }

    fun cancelExit(dialogInterface: DialogInterface?, which: Int){
        Log.v(TAG, "cancelExit - WHICH = " + which)
        confirmedExit = false
    }

    fun confirmExit() {
        val dialogBuilder: AlertDialog.Builder = AlertDialog.Builder(this@MainActivity)
        dialogBuilder.setTitle("Exit Confirmation")
        dialogBuilder.setMessage("Are you sure you want to exit the app?")
        //which is the button id. which = -1 if it is the positive button
        dialogBuilder.setPositiveButton("Yes") { dialog: DialogInterface?, which: Int -> exitApp(dialog, which) }
        //which = -2 if it is the negative button
        dialogBuilder.setNegativeButton("No"){dialog, which ->  cancelExit(dialog, which)}
        dialogBuilder.create().show()
    }

    fun drawerActivityClick(): View.OnClickListener = View.OnClickListener { view ->
        var intent: Intent = Intent(this, DrawerActivity::class.java);
        startActivity(intent)
    }
}
