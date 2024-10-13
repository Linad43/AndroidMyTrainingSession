package com.example.androidmytrainingsession.servise

import com.example.androidmytrainingsession.R
import com.example.androidmytrainingsession.model.Exercise
import kotlin.math.min

class ExerciseDataBase {
    companion object {
        private val arrayExercise = arrayListOf(
            "1. Прыжки на двух ногах",
            "2. Чередование «руки — локти»",
            "3. Прыжки на одной ноге по квадрату",
            "4. Упор лежа с прямых ног + отжимания",
            "5. Бег на месте",
            "6. Берпи",
            "7. Бег на месте в упоре лежа",
            "8. Выпрыгивание из полуприседа",
            "9. Прыжки в упоре лежа вперед-назад",
            "10. Прыжки в упоре лежа влево-вправо",
            "11. Разножка",
            "12. Тройной прыжок"
        )
        private val arrayGif = arrayListOf(
            R.drawable.ex1,
            R.drawable.ex2,
            R.drawable.ex3,
            R.drawable.ex4,
            R.drawable.ex5,
            R.drawable.ex6,
            R.drawable.ex7,
            R.drawable.ex8,
            R.drawable.ex9,
            R.drawable.ex10,
            R.drawable.ex11,
            R.drawable.ex12
        )
        var exercise = arrayListOf<Exercise>()
            get() {
                if (field.isEmpty()) {
                    val result = arrayListOf<Exercise>()
                    for (i in 0..<(min(arrayExercise.size, arrayGif.size))) {
                        result.add(
                            Exercise(
                                arrayExercise[i],
                                10,
                                arrayGif[i]
                            )
                        )
                    }
                    exercise = result
                }
                return field
            }
    }
}
