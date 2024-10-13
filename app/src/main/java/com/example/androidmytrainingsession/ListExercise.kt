package com.example.androidmytrainingsession

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.AdapterView
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.androidmytrainingsession.model.Exercise
import com.example.androidmytrainingsession.servise.ExerciseDataBase
import com.example.androidmytrainingsession.servise.ExerciseDataBaseAdapter

class ListExercise : AppCompatActivity() {

    private var exerciseAdapter: ExerciseDataBaseAdapter? = null
    private val exercises = ExerciseDataBase.exercise
    private lateinit var toolbar: Toolbar
    private lateinit var listExerciseLV: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_list_exercise)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        init()

        exerciseAdapter = ExerciseDataBaseAdapter(this, exercises)
        listExerciseLV.adapter = exerciseAdapter
        listExerciseLV.onItemClickListener =
            AdapterView.OnItemClickListener { parent, viev, position, id ->
                val exercise = exerciseAdapter?.getItem(position)
                val intent = Intent(this, PracticeExercises::class.java)
                intent.putExtra(Exercise::class.java.simpleName, exercise)
                startActivity(intent)
                exercises.remove(exercise)
                exerciseAdapter?.notifyDataSetChanged()
            }
    }

    private fun init() {
        toolbar = findViewById(R.id.toolbar)
        listExerciseLV = findViewById(R.id.listExerciseLV)
        setSupportActionBar(toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.exit -> {
                finishAffinity()
            }
        }
        return true
    }
}