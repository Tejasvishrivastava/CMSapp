package com.telstra.myapplication.Fragments

import android.app.DatePickerDialog
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.telstra.myapplication.R
import java.text.SimpleDateFormat
import java.util.*

class CertListFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cert_list, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val certText1 = view.findViewById<TextView>(R.id.certtextview1)
        val certText2 = view.findViewById<TextView>(R.id.certtextview2)
        val certText3 = view.findViewById<TextView>(R.id.certtextview3)

        val renew_button1 = view.findViewById<Button>(R.id.renewButton1)
        val renew_button2 = view.findViewById<Button>(R.id.renewButton2)
        val renew_button3 = view.findViewById<Button>(R.id.renewButton3)

        val backButton = view.findViewById<Button>(R.id.backbutton)

        //val dateTv =view.findViewById<TextView>(R.id.date_picker_actions1)
        val valEditText1 = view.findViewById<EditText>(R.id.validity1)
        val valEditText2 = view.findViewById<EditText>(R.id.validity2)
        val valEditText3 = view.findViewById<EditText>(R.id.validity3)

        val valTextView1 = view.findViewById<TextView>(R.id.date_picker_actions1)
        val valTextView2 = view.findViewById<TextView>(R.id.date_picker_actions2)
        val valTextView3 = view.findViewById<TextView>(R.id.date_picker_actions3)
        backButton.setOnClickListener {

            findNavController().navigate(R.id.action_certListFragment_to_myDashboardFragment2)
        }

        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        renew_button1.setOnClickListener{
            val days = valEditText1.text.toString()
            val texttemp = "   Validity: \n\n " + days
            valTextView1.setText(texttemp)
            valEditText1.setText("")

        }
        renew_button2.setOnClickListener{
            val days = valEditText2.text.toString()
            val texttemp = "   Validity: \n\n " + days
            valTextView2.setText(texttemp)
            valEditText1.setText("")
        }
        renew_button3.setOnClickListener{
            val days = valEditText3.text.toString()
            val texttemp = "   Validity: \n\n " + days
            valTextView3.setText(texttemp)
            valEditText1.setText("")
        }

        val c1 ="_v : 3\n"+
                "\n" +
                "type : CA\n" +
                "\n" +
                "domain : www.team5lca.com\n" +
                "\n" +
                "email : mymail@gmail.com\n" +
                "\n" +
                "conf : U2FsdkX1.....z65I0OH\n"+
                "\n" + "cert : kHqPFJZ5W .....xrnKgbSfGc\n"
        val c2 ="_v : 3\n"+
                "\n" +
                "type : CA\n" +
                "\n" +
                "domain : www.team5lca.com\n" +
                "\n" +
                "email : john@gmail.com\n" +
                "\n" +
                "conf : U2Fsdb......AzoNn65I0OH\n"+
                "\n" + "cert : kHqPDDzoNn6 .......rnKgbSfGc"
        val c3 = "_v : 3\n"+
                "\n" +
                "type : CA\n" +
                "\n" +
                "domain : www.team5lca.com\n" +
                "\n" +
                "email : mondaymails@gmail.com\n" +
                "\n" +
                "conf : U2FsdGVkX.....zoNn65I0OH\n"+
                "\n" + "cert : kHqPFJm......rnKgbSfGc"

        certText1.setText(c1)
        certText2.setText(c2)
        certText3.setText(c3)




            fun EditText.transformIntoDatePicker(context: Context, format: String, maxDate: Date? = null) {
                isFocusableInTouchMode = false
                isClickable = true
                isFocusable = false

                val myCalendar = Calendar.getInstance()
                val datePickerOnDataSetListener =
                    DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                        myCalendar.set(Calendar.YEAR, year)
                        myCalendar.set(Calendar.MONTH, monthOfYear)
                        myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                        val sdf = SimpleDateFormat(format, Locale.UK)
                        setText(sdf.format(myCalendar.time))
                    }



            }

        }

    }
