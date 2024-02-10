package com.shcoading.photcodetest.ui.adaptors


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.shcoading.photcodetest.R
import com.shcoading.photcodetest.dataModels.SchoolListResponseItem

import kotlinx.android.synthetic.main.item_school.view.*


class SchoolListAdapter(
    private val context: Context, var items: List<SchoolListResponseItem>

) : RecyclerView.Adapter<SchoolListAdapter.CheckListItemViewHolder>() {

    inner class CheckListItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CheckListItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_school, parent, false)
        return CheckListItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    private var onItemClickListener: ((Int, SchoolListResponseItem) -> Unit)? = null

    fun setOnItemClickListener(listener: (Int, SchoolListResponseItem) -> Unit) {
        onItemClickListener = listener
    }

    override fun onBindViewHolder(holder: CheckListItemViewHolder, position: Int) {
        if (items.isNotEmpty()) {
            val data = items[position]

            try {
                data.let { model ->
                    model.school_name?.let {
                        holder.itemView.tvName.text = it
                    }
                    model.dbn?.let {
                        holder.itemView.tvDBN.text = it
                    }
                    holder.itemView.setOnClickListener {
                        onItemClickListener?.let { it(position, data) }
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}