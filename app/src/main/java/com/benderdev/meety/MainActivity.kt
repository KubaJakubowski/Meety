package com.benderdev.meety


import androidx.appcompat.app.AppCompatActivity


import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth


class MainActivity : AppCompatActivity() {

    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /* TODO: Ensure that auth is global and is maintained from the MainActivity, not like right now from the Register
        //Check if logged in
        auth = FirebaseAuth.getInstance()
        */

        val transaction = supportFragmentManager.beginTransaction()

        auth = FirebaseAuth.getInstance()

        if(auth.currentUser == null ){
            transaction.replace(R.id.main_content, LoginFragment())
        }else{
            transaction.replace(R.id.main_content, ProfileFragment())
        }
        transaction.commit()



    }
}
