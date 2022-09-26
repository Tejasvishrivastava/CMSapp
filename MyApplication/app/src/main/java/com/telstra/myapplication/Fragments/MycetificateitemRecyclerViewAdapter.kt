package com.telstra.myapplication.Fragments

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView

import com.telstra.myapplication.placeholder.PlaceholderContent.PlaceholderItem
import com.telstra.myapplication.databinding.FragmentItemBinding

class MycetificateitemRecyclerViewAdapter(
    private val values: List<PlaceholderItem>
) : RecyclerView.Adapter<MycetificateitemRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       /* val item = values[position]

        holder.contentView.text = item.content*/
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentItemBinding) : RecyclerView.ViewHolder(binding.root) {
   /*     val idView: TextView = binding.itemNumber*/
     /*   val contentView: TextView = binding.textView123

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }*/
    }

}