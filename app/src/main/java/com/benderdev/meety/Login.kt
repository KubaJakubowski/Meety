package com.benderdev.meety

import com.google.firebase.auth.FirebaseAuth

class Login {
    init {
        var userLogin = 0
        var userPassword = 0
        var userEmail = 0
    }


    fun SingUp(email: String, password: String ) {

        var auth = FirebaseAuth.getInstance()
        auth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener { type ->  }

    }
        //FirebaseAuth.getInstance().sign
    }

