package com.telstra.myapplication.Fragments

import android.content.ContentValues
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.retrofitlibrary.rest.APIService
import com.example.retrofitlibrary.rest.RestClientHeroku
import com.telstra.myapplication.R
import com.telstra.myapplication.databinding.FragmentSignedCertificateBinding
import com.telstra.myapplication.model.SignedCertificateRequest
import com.telstra.myapplication.model.SignedCertificateResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignedCertificateFragment : Fragment() {

    private var _binding: FragmentSignedCertificateBinding? = null
    private var mApiServiceCreateSignedCertificate : APIService? = null

    private lateinit var countryName : EditText
    private lateinit var stateOrProvinceName : EditText
    private lateinit var localityName : EditText
    private lateinit var organizationName: EditText
    private lateinit var organizationalUnitName : EditText
    private lateinit var commonName : EditText
    private lateinit var emailAddress : EditText
    private lateinit var keyBitSize : EditText
    private lateinit var csrSignAlgo : EditText
    private lateinit var validity: EditText
    private lateinit var subjectNameAlt1 : EditText
    private lateinit var subjectNameAlt2 : EditText
    private lateinit var keyUsage : EditText


    lateinit var sharedPreferences: SharedPreferences
    lateinit var editor: SharedPreferences.Editor
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        _binding = FragmentSignedCertificateBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mApiServiceCreateSignedCertificate = RestClientHeroku.client.create(APIService:: class.java)
        countryName = view.findViewById(R.id.countryName)
        stateOrProvinceName = view.findViewById(R.id.stateOrProvinceName)
        localityName = view.findViewById(R.id.localityName)
        organizationName = view.findViewById(R.id.organizationName)
        organizationalUnitName = view.findViewById(R.id.organizationalUnitName)
        commonName = view.findViewById(R.id.commonName)
        emailAddress  = view.findViewById(R.id.emailAddress)
        keyBitSize = view.findViewById(R.id.keyBitSize)
        keyUsage = view.findViewById(R.id.keyUsage)
        csrSignAlgo = view.findViewById(R.id.csrSignAlgo)
        validity  = view.findViewById(R.id.validity)
        subjectNameAlt1 = view.findViewById(R.id.subjectAltName1)
        subjectNameAlt2 = view.findViewById(R.id.subjectAltName2)



        sharedPreferences = androidx.preference.PreferenceManager.getDefaultSharedPreferences(context)
        editor = sharedPreferences.edit()
        checkSharedPreference()

        binding.submit.setOnClickListener{
            editor.putString("countryName_SignedCertificate",countryName.text.toString() )
            editor.putString("stateOrProvinceName_SignedCertificate",stateOrProvinceName.text.toString() )
            editor.putString("localityName_SignedCertificate",localityName.text.toString() )
            editor.putString("organizationName_SignedCertificate", organizationName.text.toString() )
            editor.putString("organizationalUnitName_SignedCertificate",organizationalUnitName.text.toString() )
            editor.putString("commonName_SignedCertificate",commonName.text.toString() )
            editor.putString("emailAddress_SignedCertificate",emailAddress.text.toString() )
            editor.putString("keyBitSize_SignedCertificate",keyBitSize.text.toString() )
            editor.putString("keyUsage_SignedCertificate",keyUsage.text.toString() )
            editor.putString("csrSignAlgo_SignedCertificate",csrSignAlgo.text.toString() )
            editor.putString("validity_SignedCertificate",validity.text.toString() )
            editor.putString("subjectNameAlt1_SignedCertificate",subjectNameAlt1.text.toString() )
            editor.putString("subjectNameAlt2_SignedCertificate",subjectNameAlt2.text.toString() )

            editor.apply()
            editor.commit()
            createSignedCertificate()
        }
        binding.backbutton.setOnClickListener {
            /*   val i = Intent(activity, MyDashboardActivity::class.java)
               startActivity(i)
               (activity as Activity?)!!.overridePendingTransition(0, 0)*/
            findNavController().navigate(R.id.action_signedCertificateFragment_to_myDashboardFragment2)
        }
    }

    private fun checkSharedPreference() {

        countryName.setText(sharedPreferences.getString("countryName_SignedCertificate", "").toString())
        stateOrProvinceName.setText(sharedPreferences.getString("stateOrProvinceName_SignedCertificate", "").toString())
        localityName.setText(sharedPreferences.getString("localityName_SignedCertificate", "").toString())
        organizationName.setText(sharedPreferences.getString("organizationName_SignedCertificate", "").toString())
        organizationalUnitName.setText(sharedPreferences.getString("organizationalUnitName_SignedCertificate", "").toString())
        commonName.setText(sharedPreferences.getString("commonName_SignedCertificate", "").toString())
        emailAddress.setText(sharedPreferences.getString("emailAddress_SignedCertificate", "").toString())
        keyBitSize.setText(sharedPreferences.getString("keyBitSize_SignedCertificate", "").toString())
        keyUsage.setText(sharedPreferences.getString("keyUsage_SignedCertificate", "").toString())
        csrSignAlgo.setText(sharedPreferences.getString("csrSignAlgo_SignedCertificate", "").toString())
        validity.setText(sharedPreferences.getString("validity_SignedCertificate", "").toString())
        subjectNameAlt1.setText(sharedPreferences.getString("subjectNameAlt1_SignedCertificate", "").toString())
        subjectNameAlt2.setText(sharedPreferences.getString("subjectNameAlt2_SignedCertificate", "").toString())
        keyUsage.setText(sharedPreferences.getString("keyUsage_SignedCertificate", "").toString())

    }

    private fun createSignedCertificate() {
        val commonNameInput = commonName.text.toString()
        val countryNameInput = countryName.text.toString()
        val stateOrProvinceNameInput =  stateOrProvinceName.text.toString()
        val localityNameInput = localityName.text.toString()
        val organizationNameInput =  organizationName.text.toString()
        val organizationalUnitNameInput = organizationalUnitName.text.toString()
        val emailAddressInput = emailAddress.text.toString()
        val keyBitSizeInput = keyBitSize.text.toString().toInt()
        val csrSignAlgoInput = csrSignAlgo.text.toString()
        val validityInput = validity.text.toString().toInt()
        val altNamesList = listOf<String>(subjectNameAlt1.text.toString(),subjectNameAlt2.text.toString())
        val keyUsageInput = keyUsage.text.toString()
        val signedCertificateRequestSubmit= SignedCertificateRequest(altNamesList,"CA:false","CA:false",commonNameInput,countryNameInput,csrSignAlgoInput,validityInput,emailAddressInput,keyBitSizeInput,keyUsageInput,localityNameInput,organizationNameInput,organizationalUnitNameInput,stateOrProvinceNameInput)
        //val token = UserResponse()
        val call = mApiServiceCreateSignedCertificate!!.createSignedCertificate("Owner "+sharedPreferences.getString("authtoken", "").toString(),signedCertificateRequestSubmit);
        call.enqueue(object : Callback<SignedCertificateResponse> {

            override fun onResponse(
                call: Call<SignedCertificateResponse>,
                response: Response<SignedCertificateResponse>
            ) {
                if(response.isSuccessful){

                    if(response.toString().contains("success")){
                        Log.d(ContentValues.TAG, "User Response: " + response.body().toString())
                        return
                    }
                    else{
                        Toast.makeText(this@SignedCertificateFragment.context,"Certificate Created successfully", Toast.LENGTH_LONG).show()
                        Log.d(ContentValues.TAG, "User Response: " + response.body().toString())
                    }
                }
               // Toast.makeText(this@SignedCertificateFragment.context,"Missing Details ", Toast.LENGTH_SHORT ).show()
            }

            override fun onFailure(call: Call<SignedCertificateResponse>, t: Throwable) {
                Log.e(ContentValues.TAG, "Got error : " + t.localizedMessage)
                return
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}