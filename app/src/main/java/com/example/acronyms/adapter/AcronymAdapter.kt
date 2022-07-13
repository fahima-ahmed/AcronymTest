package com.example.acronyms.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.acronyms.R
import com.example.acronyms.model.LongForm

class AcronymAdapter : RecyclerView.Adapter<AcronymAdapter.AcronymViewHolder>() {

    inner class AcronymViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private val differCallback = object : DiffUtil.ItemCallback<LongForm>() {
        override fun areItemsTheSame(oldItem: LongForm, newItem: LongForm): Boolean {
            return oldItem.lf == newItem.lf
        }

        override fun areContentsTheSame(oldItem: LongForm, newItem: LongForm): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AcronymViewHolder {
        return AcronymViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.acronym_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: AcronymViewHolder, position: Int) {
        val longFormItem = differ.currentList[position]
        holder.itemView.apply {
            this.findViewById<TextView>(R.id.tvLongForm).text = longFormItem.lf
            this.findViewById<TextView>(R.id.tvSince).text = "Since " + longFormItem.since
        }
    }

}













