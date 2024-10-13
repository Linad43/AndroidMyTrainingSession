package com.example.androidmytrainingsession.servise

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.androidmytrainingsession.R
import com.example.androidmytrainingsession.model.Exercise
import pl.droidsonroids.gif.GifImageView

class ExerciseDataBaseAdapter(
    context: Context,
    listExercise: ArrayList<Exercise>
): ArrayAdapter<Exercise>(context, R.layout.list_item, listExercise) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView
        val exercise = getItem(position)
        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)

        }
        val gifIV = view?.findViewById<GifImageView>(R.id.gifIV)
        val name = view?.findViewById<TextView>(R.id.nameExercise)

        exercise?.gifImage?.let { gifIV?.setImageResource(it) }
        name?.text = exercise?.name

        return view!!
    }
}