package com.example.androidmytrainingsession

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.CountDownTimer
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.androidmytrainingsession.model.Exercise
import pl.droidsonroids.gif.GifImageView

class PracticeExercises : AppCompatActivity() {
    private lateinit var exercise: Exercise
    private lateinit var toolbar: Toolbar
    private lateinit var nameExercise: TextView
    private lateinit var timeTV: TextView
    private lateinit var beginBTN: Button
    private lateinit var gifIV: GifImageView
    private lateinit var timer: CountDownTimer
    private var onClick: Boolean = false

    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_practice_exercises)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        init()
        exercise = intent.getSerializableExtra(
            Exercise::class.java.simpleName,
            Exercise::class.java
        ) as Exercise
        setData()

        beginBTN.setOnClickListener {
            if (!onClick) {
                startExercise()
                onClick = true
            } else{
                finish()
            }
        }

    }

    private fun startExercise() {
        timeTV.isEnabled = true
        beginBTN.text = "Идет тренировка"
        beginBTN.isEnabled = false
        timeTV.text = formatTime(exercise.timePractice)
        timer = object : CountDownTimer(
            exercise.timePractice * 1000L,
            1000
        ) {
            override fun onTick(millisUntilFinished: Long) {
                timeTV.text = formatTime((millisUntilFinished / 1000).toInt())
            }

            override fun onFinish() {
                timeTV.text = "Упражнение завершено"
                beginBTN.text = "Продолжить"
                beginBTN.isEnabled = true
            }
        }.start()
    }

    private fun formatTime(sec: Int): String {
        val min = sec / 60
        val retSec = sec % 60
        return String.format("%02d:%02d", min, retSec)
    }

    private fun init() {
        toolbar = findViewById(R.id.toolbar)
        nameExercise = findViewById(R.id.nameExerciseTV)
        timeTV = findViewById(R.id.timeTV)
        beginBTN = findViewById(R.id.beginBTN)
        gifIV = findViewById(R.id.gifIV)
        setSupportActionBar(toolbar)
    }

    @SuppressLint("SetTextI18n")
    private fun setData() {
        nameExercise.text = exercise.name
        timeTV.text = "Оставшееся время ${exercise.timePractice} сек"
        timeTV.isEnabled = false
        beginBTN.text = "Начать"
        gifIV.setImageResource(exercise.gifImage)
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