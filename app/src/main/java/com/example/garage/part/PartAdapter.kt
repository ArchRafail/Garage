package com.example.garage.part

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.ResourcesCompat
import com.example.garage.R


class PartAdapter(context: Context?, private val layout: Int, private val parts: List<Part>)
    :ArrayAdapter<Part?>(context!!, layout, parts) {

    private val TAG = "PartAdapter"
    private val inflater: LayoutInflater

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = inflater.inflate(layout, parent, false)
        val pictureView = view.findViewById<ImageView>(R.id.part_picture)
        val nameView = view.findViewById<TextView>(R.id.part_name)
        val descriptionView = view.findViewById<TextView>(R.id.part_description)
        val part = parts[position]
        if (part.getPictureResource() > 0) {
            pictureView.setImageResource(part.getPictureResource())
        } else {
            pictureView.visibility = View.INVISIBLE
        }
        if (parts[position].getChoose()) {
            view.findViewById<ConstraintLayout>(R.id.part_layout)
                .setBackgroundColor(ResourcesCompat
                    .getColor(view.resources, R.color.selected_item_background, null))
        } else {
            view.findViewById<ConstraintLayout>(R.id.part_layout)
                .setBackgroundColor(Color.TRANSPARENT)
        }
        nameView.text = part.getName()
        descriptionView.text = part.getDescription()

        return view
    }

    init {
        inflater = LayoutInflater.from(context)
    }

}