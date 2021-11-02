/**
 * Submission Dicoding : GitHub User App
 *  Created By : Alfin Muhammad Nurdin AKA @apinchocs
 */

package com.projectassyifa.githubuserapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.projectassyifa.githubuserapp.screen.HomeActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()
        Handler().postDelayed({
            val intent = Intent( this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        },3000)
    }
}