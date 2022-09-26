package com.telstra.myapplication.Fragments

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.android.material.datepicker.MaterialDatePicker.Builder.datePicker
import com.telstra.myapplication.R
import com.telstra.myapplication.placeholder.PlaceholderContent
import java.util.*
import javax.xml.datatype.DatatypeConstants.MONTHS

/**
 * A fragment representing a list of Items.
 */
class cetificateitemFragment : Fragment() {

    private var columnCount = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_item_list, container, false)

        val RenewButton = view.findViewById<Button>(R.id.Renewal)
        val textview = view.findViewById<TextView>(R.id.textView123)
        val backButton = view.findViewById<Button>(R.id.backbutton)
        RenewButton.setOnClickListener{
            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)
            Toast.makeText(this@cetificateitemFragment.context,"remnew pressed",Toast.LENGTH_LONG).show()
     /*       val dpd = DatePickerDialog(context, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->

                // Display Selected date in textbox
                textview.setText("" + dayOfMonth + " " + MONTHS + ", " + year)

            }, year, month, day)

            dpd.show()*/
        }
        backButton.setOnClickListener{
     //       findNavController().navigate(R.id.action_cetificateitemFragment_to_myDashboardFragment2)

        }
        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                adapter = MycetificateitemRecyclerViewAdapter(PlaceholderContent.ITEMS)
            }
        }
        return view
    }

    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int) =
            cetificateitemFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}