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
        val email = view.findViewById<EditText>(R.id.email)
        val password = view.findViewById<EditText>(R.id.password)

        buttonLogin?.setOnClickListener {
            Log.d("Debug", "Email: ".plus(email?.text))
            Log.d("Debug", "Password: ".plus(password?.text))

            var auth = FirebaseAuth.getInstance()

            auth.signInWithEmailAndPassword(email.text.toString().trim(), password.text.toString().trim())
                .addOnCompleteListener(activity as FragmentActivity) { task ->
                    if (task.isSuccessful) {
                        Log.d("Debug", "SignInWithEmail:success")
                        Toast.makeText(context, "Login success", Toast.LENGTH_LONG).show()
                    } else {
                        Log.d("Debug", "SignInWithEmail:failure", task.exception)
                        Toast.makeText(context, "Login failed", Toast.LENGTH_LONG).show()
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
