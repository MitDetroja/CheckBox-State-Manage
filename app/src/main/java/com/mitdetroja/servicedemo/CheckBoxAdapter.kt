package com.mitdetroja.servicedemo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CheckBoxAdapter(private val count: Int) :
    RecyclerView.Adapter<CheckBoxAdapter.CheckBoxViewHolder>() {

    private val checkBoxStatus = HashMap<Int, Boolean>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CheckBoxViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_row, parent, false)
        return CheckBoxViewHolder(view)

    }

    override fun onBindViewHolder(holder: CheckBoxViewHolder, position: Int) {
        val rowNum = position + 1
        holder.addRowNumber(rowNum)
        holder.addCheckBoxes(rowNum)
    }

    override fun getItemCount(): Int {
        return count
    }

    private fun getCheckStatus(id: Int): Boolean {
        return checkBoxStatus[id] ?: false
    }

    private fun setCheckStatus(id: Int, isChecked: Boolean) {
        checkBoxStatus[id] = isChecked
    }

    private fun createViewId(row: Int, item: Int): Int {
        return row * 1000 + item //To create Unique ID
    }


    inner class CheckBoxViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val checkBoxContainer: LinearLayout = itemView.findViewById(R.id.checkBoxContainer)
        private val rowNumber: TextView = itemView.findViewById(R.id.rowNumber)

        fun addCheckBoxes(count: Int) {
            checkBoxContainer.removeAllViews()

            for (i in 0 until count) {
                val checkBox = CheckBox(checkBoxContainer.context)
                checkBox.id = createViewId(count, i)  //count * 1000 + i
                checkBox.isChecked = getCheckStatus(checkBox.id)

                checkBox.setOnCheckedChangeListener { view, isChecked ->
                    setCheckStatus(view.id, isChecked)
                }
                checkBoxContainer.addView(checkBox)
            }
        }

        fun addRowNumber(rowNum: Int) {
            rowNumber.text = rowNum.toString()
        }
    }
}