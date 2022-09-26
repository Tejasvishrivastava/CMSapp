package com.telstra.myapplication.Fragments


import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

import androidx.navigation.fragment.findNavController
import com.example.retrofitlibrary.model.UserX
import com.example.retrofitlibrary.rest.mytoken
import com.telstra.myapplication.R
import com.telstra.myapplication.databinding.FragmentMyDashboardBinding
import kotlin.concurrent.fixedRateTimer


class MyDashboardFragment : Fragment() {

    private var _binding: FragmentMyDashboardBinding? = null
    lateinit var sharedPreferences: SharedPreferences
    lateinit var editor: SharedPreferences.Editor
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        _binding = FragmentMyDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       /* val activity: AuthActivity? = activity as AuthActivity?
        val mytoken: String = activity!!.getusertoken()
        binding.userDetails.setText(mytoken)*/

        /*val fm: FragmentManager? = fragmentManager
        val fragm:SignInFragment = fm!!.findFragmentById(R.id.FirstFragment) as SignInFragment
        binding.userdetailstext.setText(fragm.getusertoken())*/
        /*sharedPreferences = androidx.preference.PreferenceManager.getDefaultSharedPreferences(context)*/
       /* val details : UserX("6315d09e3952e03feb39c9ec","john@doe.com", "John","Doe","regular");
        binding.userdetailstext.setText(details.lastName)*/

        sharedPreferences = androidx.preference.PreferenceManager.getDefaultSharedPreferences(context)
        editor = sharedPreferences.edit()
        binding.userdetailstext.setText("Name: " +  sharedPreferences.getString("firstName_dashboard","").toString() +" "+
                sharedPreferences.getString("lastName_dashboard","").toString()+ " \nEmail : " +
                sharedPreferences.getString("useremail_dashboard","").toString()
         )

        binding.selfsigned.setOnClickListener{
            /*val i = Intent(activity, MyDashboardActivity::class.java)
            startActivity(i)
            (activity as Activity?)!!.overridePendingTransition(0, 0)*/
            findNavController().navigate(R.id.action_myDashboardFragment2_to_selfSignedFragment)
        }

        binding.signed.setOnClickListener {
            findNavController().navigate(R.id.action_myDashboardFragment2_to_signedCertificateFragment)
        }
        binding.mycert.setOnClickListener{
            findNavController().navigate(R.id.action_myDashboardFragment2_to_certListFragment)
        }
        binding.casigned.setOnClickListener{
            findNavController().navigate(R.id.action_myDashboardFragment2_to_caSignedFragment2)
        }
        binding.logoutbutton.setOnClickListener{
            mytoken= ""
            findNavController().navigate(R.id.action_myDashboardFragment2_to_FirstFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
