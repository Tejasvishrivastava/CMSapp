package com.telstra.myapplication.Fragments

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.retrofitlibrary.rest.APIService
import com.example.retrofitlibrary.rest.RestClientHeroku
import com.telstra.myapplication.R
import com.telstra.myapplication.databinding.FragmentRegisterBinding
import com.telstra.myapplication.model.Register
import com.telstra.myapplication.model.RegisterResponse
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback


class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private var mApiServiceSignUp : APIService? = null
    private lateinit var lastname : EditText
    private lateinit var firstname : EditText
    private lateinit var rpassword : EditText

    private lateinit var rdepartment : EditText
    private lateinit var remail : EditText
    private lateinit var registerButton : Button


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?,savedInstanceState:
    Bundle?): View? {

      _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mApiServiceSignUp = RestClientHeroku.client.create(APIService::class.java)

        lastname = view.findViewById<EditText>(R.id.lastname)
        firstname = view.findViewById<EditText>(R.id.firstname)
        rpassword = view.findViewById<EditText>(R.id.Rpassowrd)
        remail = view.findViewById<EditText>(R.id.Remail)

        rdepartment = view.findViewById<EditText>(R.id.Rdepartment)
        registerButton = view.findViewById<Button>(R.id.RegisterButton)

        binding.RegisterButton.setOnClickListener {

            signUp()
        }

        binding.Signinbutton.setOnClickListener {

            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }

    }
    private fun signUp(){
        val registerLastName= lastname.text.toString()
        val registerPassword = rpassword.text.toString()
        val registerFirstName = firstname.text.toString()
        val regitsterEmail = remail.text.toString()
        val registerDepartment = rdepartment.text.toString()
        val register= Register(registerDepartment,regitsterEmail,registerFirstName,registerLastName,registerPassword,"regular")
        //val token = UserResponse()
        val call = mApiServiceSignUp!!.signUp(register);
        call.enqueue(object : retrofit2.Callback<RegisterResponse> {

            override fun onResponse(call: Call<RegisterResponse>, response: Response<RegisterResponse>) {
                if(response.isSuccessful){

                    if(response.toString().contains("error")){
                        Log.d(TAG, "User Response: " + response.body().toString())
                        return
                    }
                    else{


                        Log.d(TAG, "User Response: " + response.body().toString())
                    }
                }
                Toast.makeText(this@RegisterFragment.context," User Already Exists or fields are Missing ",Toast.LENGTH_SHORT ).show()
            }

            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                Log.e(TAG, "Got error : " + t.localizedMessage)
                return
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}