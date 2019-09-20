package com.benderdev.meety


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_login.*


//import kotlinx.android.synthetic.main.activity_main.*
//import kotlinx.android.synthetic.main.fragment_login.*


class LoginFragment : Fragment() {

    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val buttonLogin = view.findViewById<Button>(R.id.buttonLogin)
        val emailField = view.findViewById<EditText>(R.id.email)
        val passwordField = view.findViewById<EditText>(R.id.password)

        buttonLogin?.setOnClickListener {

            val email = emailField.text.toString()
            val password = passwordField.text.toString()

            Log.d("Debug", "Email: ".plus(email))
            Log.d("Debug", "Password: ".plus(password))

            if (email.isEmpty() || password.isEmpty()) {
                Log.d("Debug", "SignInWithEmail:failed")
                Toast.makeText(context, "Provide valid credentials", Toast.LENGTH_LONG).show()

            } else {
                auth = FirebaseAuth.getInstance()

                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(activity as FragmentActivity) { task ->
                        if (task.isSuccessful) {
                            Log.d("Debug", "SignInWithEmail:success")
                            Toast.makeText(context, "Login success", Toast.LENGTH_LONG).show()

                            activity?.finish()
                            startActivity(activity?.intent)
                        } else {
                            Log.d("Debug", "SignInWithEmail:failure", task.exception)
                            Toast.makeText(
                                context,
                                "Login failed, user with following credentials does not exist",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
            }

        }

        buttonSignUp.setOnClickListener {
            val transaction = fragmentManager?.beginTransaction()
            transaction?.replace(R.id.main_content, RegisterFragment())
            transaction?.commit()


        }


    }
}
