package com.telstra.myapplication.Fragments

import android.content.ContentValues
import android.content.SharedPreferences
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.retrofitlibrary.model.User
import com.example.retrofitlibrary.model.UserResponse
import com.example.retrofitlibrary.rest.APIService
import com.example.retrofitlibrary.rest.RestClientHeroku
import com.telstra.myapplication.R
import com.telstra.myapplication.databinding.FragmentSigninBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response






class SignInFragment : Fragment() {

    var tokenresult: String = ""
    private var mApiServiceSignIn: APIService? = null


    lateinit var sharedPreferences: SharedPreferences
    lateinit var editor: SharedPreferences.Editor
    private lateinit var emailEditTextView: EditText
    private lateinit var passwordEditTextView: EditText
    private lateinit var loginbutton: Button
    private lateinit var checkbox: CheckBox
    private lateinit var strPassword: String
    private lateinit var strEmail: String
    private lateinit var strCheckBox: String
    private  lateinit var psswrdVisible : ImageView

    private var _binding: FragmentSigninBinding? = null


    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        _binding = FragmentSigninBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mApiServiceSignIn = RestClientHeroku.client.create(APIService::class.java)


        passwordEditTextView = view.findViewById<EditText>(R.id.passwordtext)
        emailEditTextView = view.findViewById<EditText>(R.id.emailtext)
        loginbutton = view.findViewById<Button>(R.id.Loginbutton)
        checkbox = view.findViewById<CheckBox>(R.id.checkBox)
        //psswrdVisible = view.findViewById(R.id.passwordImageView)
        sharedPreferences =
            androidx.preference.PreferenceManager.getDefaultSharedPreferences(context)
        editor = sharedPreferences.edit()
        checkSharedPreference()

        binding.Loginbutton.setOnClickListener {

            if (checkbox.isChecked) {
                editor.putString(getString(R.string.checkBox), "True")
                strEmail = emailEditTextView.text.toString()
                editor.putString("emailaddress", strEmail)
                strPassword = passwordEditTextView.text.toString()
                editor.putString("password", strPassword)
                editor.apply()
                editor.commit()
            } else {
                editor.putString(getString(R.string.checkBox), "False")
                editor.putString("emailaddress", "")
                editor.putString("password", "")
                editor.apply()
                editor.commit()
            }


            signIn()

        }

        binding.ToRegisterFragmentbutton.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
        binding.passwordImageView.setOnClickListener{
            if(passwordEditTextView.getInputType() == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
                passwordEditTextView.setInputType( InputType.TYPE_CLASS_TEXT or  InputType.TYPE_TEXT_VARIATION_PASSWORD)
            }else {
                passwordEditTextView.setInputType( InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD );
            }
            passwordEditTextView.setSelection(passwordEditTextView.length());
        }

        //  passwordEditTextView.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD

    }

    private fun signIn() {
        val userMail = emailEditTextView.text.toString()
        val userPassword = passwordEditTextView.text.toString()
        val user = User(userMail, userPassword)
        val call = mApiServiceSignIn!!.signIn(user);
        call.enqueue(object : Callback<UserResponse> {

            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if (response.isSuccessful) {
                    if (response.body().toString().contains("error=null")) {

                        val firstName_dashboard = response.body()!!.user.firstName
                        val userType_dashboard = response.body()!!.user.userType
                        val lastName_dashboard = response.body()!!.user.lastName
                        val useremail_dashboard = response.body()!!.user.email

                        tokenresult = response.body().toString().substringAfter("" + "token=")
                            .substringBefore(',')

                        emailEditTextView.setText(strEmail)

                        sharedPreferences =
                            androidx.preference.PreferenceManager.getDefaultSharedPreferences(context)
                        editor = sharedPreferences.edit()
                        checkSharedPreference()

                        editor.putString("firstName_dashboard", firstName_dashboard)
                        editor.putString("userType_dashboard", userType_dashboard)
                        editor.putString("lastName_dashboard", lastName_dashboard)
                        editor.putString("useremail_dashboard", useremail_dashboard)
                        editor.putString("authtoken", tokenresult)
                        editor.apply()
                        editor.commit()

                        // val userStr = response.body()!!.user

                        Toast.makeText(
                            this@SignInFragment.context,
                            "Logged In Successfully",
                            Toast.LENGTH_SHORT
                        ).show()
                        Log.d(ContentValues.TAG, response.body()!!.user.userType)
                        findNavController().navigate(R.id.action_FirstFragment_to_myDashboardFragment2)
                        return
                    } else {
                        Log.e(ContentValues.TAG, response.errorBody().toString())
                        //Log.d(  ContentValues.TAG, response.body().toString() + response.body()!!.error )
                        return
                    }

                }

            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Toast.makeText(this@SignInFragment.context, "Failed Connection", Toast.LENGTH_SHORT)
                    .show()
                Log.e(ContentValues.TAG, "Got error : " + t.localizedMessage)
                return
            }
        })
    }

    private fun checkSharedPreference() {
        strCheckBox = sharedPreferences.getString(getString(R.string.checkBox), "False").toString()
        strEmail = sharedPreferences.getString("emailaddress", "").toString()
        strPassword = sharedPreferences.getString("password", "").toString()
        emailEditTextView.setText(strEmail)
        passwordEditTextView.setText(strPassword)
        checkbox.isChecked = strCheckBox == "True"
    }

    public fun getusertoken(): String? {
        return tokenresult
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        @JvmStatic
        fun newInstance(isMyToken: String) = SignInFragment().apply {
            arguments = Bundle().apply {
                putString(tokenresult, isMyToken)
            }
        }
    }
}


