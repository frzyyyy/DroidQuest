package ru.rsue.android.droidquest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast


class QuestActivity : AppCompatActivity() {

    private lateinit var mTrueButton: Button
    private lateinit var mFalseButton: Button
    private lateinit var mNextButton: ImageButton
    private lateinit var mBackButton: ImageButton
    private lateinit var mQuestionTextView: TextView
    private val mQuestionBank = listOf(
        Question(R.string.question_android, true),
        Question(R.string.question_linear, false),
        Question(R.string.question_service, false),
        Question(R.string.question_res, true),
        Question(R.string.question_manifest, true)
    )
    private var mCurrentIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quest)

        mQuestionTextView = findViewById(R.id.question_text_view)

        mQuestionTextView.setOnClickListener {
            mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.size
            updateQuestion()
        }

        mTrueButton = findViewById(R.id.true_button)
        mTrueButton.setOnClickListener {
            checkAnswer(true)
        }

        mFalseButton = findViewById(R.id.false_button)
        mFalseButton.setOnClickListener{
            checkAnswer(false)
        }

        mNextButton = findViewById(R.id.next_button)
        mNextButton.setOnClickListener {
            mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.size
            updateQuestion()
        }

        mBackButton = findViewById(R.id.back_button)
        mBackButton.setOnClickListener {
            mCurrentIndex = (mCurrentIndex - 1 + mQuestionBank.size) % mQuestionBank.size
            updateQuestion()
        }
        updateQuestion()
    }

    private fun updateQuestion() {
        val question = mQuestionBank[mCurrentIndex].textResId
        mQuestionTextView.setText(question)
    }

    private fun checkAnswer(userPressedTrue: Boolean) {
        val answerIsTrue = mQuestionBank[mCurrentIndex].answerTrue
        val messageResId = if (userPressedTrue == answerIsTrue) {
            R.string.correct_toast
        } else {
            R.string.incorrect_toast
        }
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show()
    }
}