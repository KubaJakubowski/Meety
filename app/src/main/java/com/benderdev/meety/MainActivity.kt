package com.benderdev.meety


import androidx.appcompat.app.AppCompatActivity


import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth


class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /* TODO: Ensure that auth is global and is maintained from the MainActivity, not like right now from the Register
        //Check if logged in
        auth = FirebaseAuth.getInstance()
        */


        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.main_content, RegisterFragment())
        transaction.commit()
    }
}
