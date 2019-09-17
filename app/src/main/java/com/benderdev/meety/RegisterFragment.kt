package com.benderdev.meety


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.FragmentManager
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
        val login = view.findViewById<EditText>(R.id.login)
        val password = view.findViewById<EditText>(R.id.password)

        // Checking if passwords are matching
        val matchingPasswords = view.findViewById<EditText>(R.id.password).text.toString()
            .compareTo(view.findViewById<EditText>(R.id.passwordRetype).text.toString())


        buttonLogin?.setOnClickListener {
            Log.d("Debug", "Login: ".plus(login?.text))
            Log.d("Debug", "Password: ".plus(password?.text))
            Log.d("Debug", "Matching passwords: ".plus(matchingPasswords))
        }

        buttonSignUp.setOnClickListener {
            val transaction = fragmentManager?.beginTransaction()
            transaction?.replace(R.id.main_content, LoginFragment())
            transaction?.commit()

        }


    }

}

