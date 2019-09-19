package com.benderdev.meety


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.*


class ProfileFragment : Fragment() {

    private lateinit var auth: FirebaseAuth


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val textEmail = view.findViewById<TextView>(R.id.textEmail)
        val buttonSignOut = view.findViewById<Button>(R.id.buttonSignOut)

        auth = FirebaseAuth.getInstance()

        textEmail.text = auth.currentUser?.email

        buttonSignOut.setOnClickListener {
            auth.signOut()
            Toast.makeText(context, "Signed Out", Toast.LENGTH_LONG)

            /*
            val transaction = fragmentManager?.beginTransaction()
            transaction?.replace(R.id.main_content, LoginFragment())
            transaction?.commit()
            */

            activity?.finish()
            startActivity(activity?.intent)
        }


    }


}
