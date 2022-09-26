package com.telstra.myapplication.Fragments

import android.content.ContentValues
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.retrofitlibrary.rest.APIService
import com.example.retrofitlibrary.rest.RestClientHeroku
import com.telstra.myapplication.R
import com.telstra.myapplication.databinding.FragmentCaSignedBinding
import com.telstra.myapplication.model.*
import retrofit2.Call
import retrofit2.Response


class CaSignedFragment : Fragment() {

    private var _binding: FragmentCaSignedBinding? = null
    private var mApiServiceCreateCaCetificate : APIService? = null

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
   // private lateinit var keyUsage : EditText


    lateinit var sharedPreferences: SharedPreferences
    lateinit var editor: SharedPreferences.Editor

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        _binding = FragmentCaSignedBinding.inflate(inflater, container, false)
        return binding.root

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mApiServiceCreateCaCetificate = RestClientHeroku.client.create(APIService:: class.java)
        countryName = view.findViewById(R.id.countryName)
        stateOrProvinceName = view.findViewById(R.id.stateOrProvinceName)
        localityName = view.findViewById(R.id.localityName)
        organizationName = view.findViewById(R.id.organizationName)
        organizationalUnitName = view.findViewById(R.id.organizationalUnitName)
        commonName = view.findViewById(R.id.commonName)
        emailAddress  = view.findViewById(R.id.emailAddress)
        keyBitSize = view.findViewById(R.id.keyBitSize)
        //keyUsage = view.findViewById(R.id.keyUsage)
        csrSignAlgo = view.findViewById(R.id.csrSignAlgo)
        validity  = view.findViewById(R.id.validity)
        subjectNameAlt1 = view.findViewById(R.id.subjectAltName1)
        subjectNameAlt2 = view.findViewById(R.id.subjectAltName2)



        sharedPreferences = androidx.preference.PreferenceManager.getDefaultSharedPreferences(context)
        editor = sharedPreferences.edit()
        checkSharedPreference()

        binding.submit.setOnClickListener{
            editor.putString("countryName_CaSignCertificate",countryName.text.toString() )
            editor.putString("stateOrProvinceName_CaSignCertificate",stateOrProvinceName.text.toString() )
            editor.putString("localityName_CaSignCertificate",localityName.text.toString() )
            editor.putString("organizationName_CaSignCertificate", organizationName.text.toString() )
            editor.putString("organizationalUnitName_CaSignCertificate",organizationalUnitName.text.toString() )
            editor.putString("commonName_CaSignCertificate",commonName.text.toString() )
            editor.putString("emailAddress_CaSignCertificate",emailAddress.text.toString() )
            editor.putString("keyBitSize_CaSignCertificate",keyBitSize.text.toString() )
          //  editor.putString("keyUsage_CaSignCertificate",keyUsage.text.toString() )
            editor.putString("csrSignAlgo_CaSignCertificate",csrSignAlgo.text.toString() )
            editor.putString("validity_CaSignCertificate",validity.text.toString() )
            editor.putString("subjectNameAlt1_CaSignCertificate",subjectNameAlt1.text.toString() )
            editor.putString("subjectNameAlt2_CaSignCertificate",subjectNameAlt2.text.toString() )

            editor.apply()
            editor.commit()


            createCaCertificate()
        }
        binding.backbutton.setOnClickListener {
            /*   val i = Intent(activity, MyDashboardActivity::class.java)
               startActivity(i)
               (activity as Activity?)!!.overridePendingTransition(0, 0)*/
            findNavController().navigate(R.id.action_caSignedFragment2_to_myDashboardFragment2)
        }
    }

    private fun checkSharedPreference() {

        countryName.setText(sharedPreferences.getString("countryName_CaSignCertificate", "").toString())
        stateOrProvinceName.setText(sharedPreferences.getString("stateOrProvinceName_CaSignCertificate", "").toString())
        localityName.setText(sharedPreferences.getString("localityName_CaSignCertificate", "").toString())
        organizationName.setText(sharedPreferences.getString("organizationName_CaSignCertificate", "").toString())
        organizationalUnitName.setText(sharedPreferences.getString("organizationalUnitName_CaSignCertificate", "").toString())
        commonName.setText(sharedPreferences.getString("commonName_CaSignCertificate", "").toString())
        emailAddress.setText(sharedPreferences.getString("emailAddress_CaSignCertificate", "").toString())
        keyBitSize.setText(sharedPreferences.getString("keyBitSize_CaSignCertificate", "").toString())
       // keyUsage.setText("encipherOnly,digitalSignature")
        csrSignAlgo.setText(sharedPreferences.getString("csrSignAlgo_CaSignCertificate", "").toString())
        validity.setText(sharedPreferences.getString("validity_CaSignCertificate", "").toString())
        subjectNameAlt1.setText(sharedPreferences.getString("subjectNameAlt1_CaSignCertificate", "").toString())
        subjectNameAlt2.setText(sharedPreferences.getString("subjectNameAlt2_CaSignCertificate", "").toString())
       // keyUsage.setText(sharedPreferences.getString("keyUsage_CASignCertificate", "").toString())

    }

    private fun createCaCertificate() {
        var commonNameInput = "www.tesltra.com"
        var csrSignAlgoInput = "sha256"
        var validityInput = 365
        var altNamesList = listOf<String>()
        var keyUsageInput = "cRLSign, keyCertSign"
        var keyBitSizeInput = 512

        commonNameInput = commonName.text.toString()
        val countryNameInput = countryName.text.toString()
        val stateOrProvinceNameInput =  stateOrProvinceName.text.toString()
        val localityNameInput = localityName.text.toString()
        val organizationNameInput =  organizationName.text.toString()
        val organizationalUnitNameInput = organizationalUnitName.text.toString()
        val emailAddressInput = emailAddress.text.toString()
        keyBitSizeInput = keyBitSize.text.toString().toInt()
        csrSignAlgoInput = csrSignAlgo.text.toString()
        validityInput = validity.text.toString().toInt()
        altNamesList = listOf()
        keyUsageInput = "encipherOnly,digitalSignature"
        val CaCertificateRequestSubmit= CaCertificateRequest(altNamesList, "CA:true","CA:true",commonNameInput,csrSignAlgoInput,countryNameInput,validityInput,emailAddressInput,keyUsageInput,localityNameInput,organizationNameInput,organizationalUnitNameInput,stateOrProvinceNameInput)
        //val token = UserResponse()
        val call = mApiServiceCreateCaCetificate!!.createCaCertificate("Owner "+sharedPreferences.getString("authtoken", "").toString(),CaCertificateRequestSubmit);
        call.enqueue(object : retrofit2.Callback<CaCertificateResponse> {

            override fun onResponse(
                call: Call<CaCertificateResponse>,
                response: Response<CaCertificateResponse>
            ) {
                if(response.isSuccessful){

                    if(response.toString().contains("success")){
                        Log.d(ContentValues.TAG, "User Response: " + response.body().toString())
                        return
                    }
                    else{
                        Toast.makeText(this@CaSignedFragment.context,"Certificate Created successfully",Toast.LENGTH_LONG).show()
                        Log.d(ContentValues.TAG, "User Response: " + response.body().toString())
                    }
                }
               // Toast.makeText(this@CaSignedFragment.context,"Missing Details",Toast.LENGTH_SHORT ).show()
            }

            override fun onFailure(call: Call<CaCertificateResponse>, t: Throwable) {
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