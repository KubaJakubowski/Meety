package com.benderdev.meety


import android.os.Bundle
import android.text.InputFilter
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_register.*


class RegisterFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val buttonLogin = view.findViewById<Button>(R.id.buttonLogin)
        val email = view.findViewById<EditText>(R.id.email)
        val password = view.findViewById<EditText>(R.id.password)
        val passwordRetype = view.findViewById<EditText>(R.id.passwordRetype)


        buttonLogin?.setOnClickListener {
            var toast = Toast.makeText(context, "Success", Toast.LENGTH_LONG)

            Log.d("Debug", "Email: ".plus(email?.text))
            Log.d("Debug", "Password: ".plus(password?.text))
            Log.d("Debug", "Password retyped: ".plus(passwordRetype?.text))
            val matchingPasswords = password.text.toString().compareTo(passwordRetype.text.toString())
            Log.d("Debug", "Matching passwords: ".plus(matchingPasswords))

            var auth = FirebaseAuth.getInstance()

            auth.createUserWithEmailAndPassword(email.text.toString().trim(), password.text.toString().trim())
                .addOnCompleteListener( activity as FragmentActivity) { task ->
                    if (task.isSuccessful) {
                        Log.d("Debug","createUserWithEmail:success")
                        val user = auth.currentUser
                        toast.show()
                    } else {
                        Log.d("Debug","createUserWithEmail:failure",task.exception)
                        Toast.makeText(context, "Registration failed",Toast.LENGTH_LONG ).show()
                    }
                }
               
        }

        buttonSignUp.setOnClickListener {
            val transaction = fragmentManager?.beginTransaction()
            transaction?.replace(R.id.main_content, LoginFragment())
            transaction?.commit()

        }


    }

}

