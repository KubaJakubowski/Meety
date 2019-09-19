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
        val emailField = view.findViewById<EditText>(R.id.email)
        val passwordField = view.findViewById<EditText>(R.id.password)
        val passwordRetypeField = view.findViewById<EditText>(R.id.passwordRetype)


        buttonLogin?.setOnClickListener {
            val email = emailField.text.toString()
            val password = passwordField.text.toString()
            val passwordRetype = passwordRetypeField.text.toString()
            val matchingPasswords = password.compareTo(passwordRetype)

            Log.d("Debug", "Email: ".plus(email))
            Log.d("Debug", "Password: ".plus(password))
            Log.d("Debug", "Password retyped: ".plus(passwordRetype))
            Log.d("Debug", "Matching passwords: ".plus(matchingPasswords))

            //Registration Validation
            //TODO Change all Toasts to subtext underneath the corresponding EditText fields
            if (!(email).matches("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+\$".toRegex())){
                Toast.makeText(context, "Provide valid email address", Toast.LENGTH_LONG).show()
            }else if(!(password).matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}\$".toRegex())){
                Toast.makeText(context, "Your password must be at least 8 characters long and need to contain one lowercase letter, one uppercase letter and digit", Toast.LENGTH_LONG).show()
            }else if(matchingPasswords != 0){
                Toast.makeText(context, "Your passwords does not match", Toast.LENGTH_LONG).show()
            }else{
                //Registration
                var auth = FirebaseAuth.getInstance()
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(activity as FragmentActivity) { task ->
                        if (task.isSuccessful) {
                            Log.d("Debug", "createUserWithEmail:success")
                            val user = auth.currentUser
                            Toast.makeText(context, "Registration success", Toast.LENGTH_LONG).show()

                            activity?.finish()
                            startActivity(activity?.intent)
                        } else {
                            Log.d("Debug", "createUserWithEmail:failure", task.exception)
                            Toast.makeText(context, "Registration failed", Toast.LENGTH_LONG).show()
                        }
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

