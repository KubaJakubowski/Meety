package com.benderdev.meety


import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
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
        val editName = view.findViewById<EditText>(R.id.editName)
        val editDescription = view.findViewById<EditText>(R.id.editDescription)
        val textPoints = view.findViewById<TextView>(R.id.textPoints)

        val buttonSignOut = view.findViewById<Button>(R.id.buttonSignOut)
        val buttonSave = view.findViewById<Button>(R.id.buttonSave)


        auth = FirebaseAuth.getInstance()
        textEmail.text = auth.currentUser?.email

        val database = FirebaseFirestore.getInstance()
        val reference = database.collection("users").document(auth.uid!!)
        reference.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    Log.d("Debug", "DocumentSnapshot data: ${document.data}")

                    editName.setText(document.data!!["name"].toString())
                    editDescription.setText(document.data!!["description"].toString())
                    textPoints.text = document.data!!["points"].toString()

                } else {
                    Log.d("Debug", "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.d("Debug", "Getting document failed. Following exception occurred: ", exception)
            }









        buttonSave.setOnClickListener {
            val data = hashMapOf("name" to editName.text.toString(), "description" to editDescription.text.toString())

            reference.update(data as Map<String, Any>)
                .addOnSuccessListener {
                    Toast.makeText(context, "Successfully updated data", Toast.LENGTH_LONG).show()
                }
                .addOnFailureListener { exception ->
                    Toast.makeText(context, "Data update failed", Toast.LENGTH_LONG).show()
                    Log.d("Debug", "Data update failed. Following exception occurred: ", exception)
                }
        }

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
