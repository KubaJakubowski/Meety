package com.benderdev.meety


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
//import com.google.firebase.auth.FirebaseAuth
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
        val login = view.findViewById<EditText>(R.id.login)
        val password = view.findViewById<EditText>(R.id.password)

        buttonLogin?.setOnClickListener {
            Log.d("Debug", "Login: ".plus(login?.text))
            Log.d("Debug", "Password: ".plus(password?.text))
            //FirebaseAuth.getInstance().sign
        }


    }
}
