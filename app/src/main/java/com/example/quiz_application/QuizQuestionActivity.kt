package com.example.quiz_application

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

import androidx.core.content.ContextCompat
import com.example.quiz_application.ui.theme.Quiz_ApplicationTheme

@SuppressLint("RestrictedApi")
class QuizQuestionActivity : androidx.core.app.ComponentActivity(), View.OnClickListener {

    private var mCurrPosition: Int = 1
    private var mQuestionList: ArrayList<Question>? = null
    private var mSelectedOptPosition: Int = 0
    private var mUserName: String?=null
    private var mCorrectAnswer :Int =0

    private var timer :CountDownTimer?= null
    private var timeleft :Long = 6000

    private var progressBar: ProgressBar? = null
    private var tvProgress: TextView? = null
    private var tvQuestion: TextView? = null
    private var ivImage: ImageView? = null

    private var tvOptionOne: TextView? = null
    private var tvOptionTwo: TextView? = null
    private var tvOptionThree: TextView? = null
    private var tvOptionFour: TextView? = null
    private var btnSubmit: Button? = null

    private var showtimer : TextView?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_question)
        mUserName=intent.getStringExtra(Constants.USER_NAME)

        progressBar = findViewById(R.id.progress_bar)
        tvProgress = findViewById(R.id.tv_progress)
        tvQuestion = findViewById(R.id.tv_ques)
        ivImage = findViewById(R.id.iv_image)

        tvOptionOne = findViewById(R.id.tv_option_one)
        tvOptionTwo = findViewById(R.id.tv_option_two)
        tvOptionThree = findViewById(R.id.tv_option_three)
        tvOptionFour = findViewById(R.id.tv_option_four)
        btnSubmit = findViewById(R.id.btn_submit)

        showtimer= findViewById(R.id.show_timer)

        tvOptionOne?.setOnClickListener(this)
        tvOptionTwo?.setOnClickListener(this)
        tvOptionThree?.setOnClickListener(this)
        tvOptionFour?.setOnClickListener(this)
        btnSubmit?.setOnClickListener(this)

        mQuestionList = Constants.getQuestions()
        setQuestion()
    }

    private fun setQuestion() {
        defaultOptionView()
        val question = mQuestionList!![mCurrPosition - 1]
        ivImage?.setImageResource(question.image)
        tvQuestion?.text = question.question
        progressBar?.progress = mCurrPosition
        tvProgress?.text = "${mCurrPosition}/${progressBar?.max}"
        tvOptionOne?.text = question.optionOne
        tvOptionTwo?.text = question.optionTwo
        tvOptionThree?.text = question.optionThree
        tvOptionFour?.text = question.optionFour

        if (mCurrPosition == mQuestionList!!.size) {
            btnSubmit?.text = "FINISH"
        } else {
            btnSubmit?.text = "SUBMIT"
        }
        timeleft=6000
        timer?.cancel()
        starttimer()

    }

    private fun starttimer()
    {
        timer =object: CountDownTimer(timeleft,1000)
        {
            override fun onTick(millisUntilFinished: Long) {
                timeleft=millisUntilFinished
                showtimer?.text=((timeleft/1000)%60).toString()

            }

            override fun onFinish() {



            }

        }.start()

    }

    private fun defaultOptionView() {
        val options = ArrayList<TextView>()
        tvOptionOne?.let {
            options.add(0, it)
        }
        tvOptionTwo?.let {
            options.add(1, it)
        }
        tvOptionThree?.let {
            options.add(2, it)
        }
        tvOptionFour?.let {
            options.add(3, it)
        }

        for (option in options) {
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(
                this,
                R.drawable.default_option_border_bg
            )
        }
    }

    private fun SelectedOptionView(tv: TextView, selectedOptionNum: Int) {
        defaultOptionView()

        mSelectedOptPosition = selectedOptionNum

        tv.setTextColor(Color.parseColor("#1a1a1c"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(
            this,
            R.drawable.selected_option_border
        )
    }



    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.tv_option_one -> {
                tvOptionOne?.let {
                    SelectedOptionView(it, 1)

                }
            }

            R.id.tv_option_two -> {
                tvOptionTwo?.let {
                    SelectedOptionView(it, 2)

                }
            }

            R.id.tv_option_three -> {
                tvOptionThree?.let {
                    SelectedOptionView(it, 3)

                }
            }

            R.id.tv_option_four -> {
                tvOptionFour?.let {
                    SelectedOptionView(it, 4)

                }
            }

            R.id.btn_submit -> {
               if (mSelectedOptPosition == 0) {
                   mCurrPosition++

                    //Toast.makeText(this, "No Option Selected",Toast.LENGTH_LONG).show()


                    when {
                        mCurrPosition <= mQuestionList!!.size -> {
                            setQuestion()
                        }

                        else -> {
                           val intent= Intent(this, ResultActivity::class.java)
                            intent.putExtra(Constants.USER_NAME, mUserName)
                            intent.putExtra(Constants.CORRECT_ANSWER,mCorrectAnswer)
                            intent.putExtra(Constants.TOTAL_QUESTIONS,mQuestionList?.size)
                            startActivity(intent)
                            finish()

                        }
                    }
                } else {
                    val question = mQuestionList?.get(mCurrPosition - 1)
                    if (question!!.correctAnswer != mSelectedOptPosition) {
                        answerView(mSelectedOptPosition, R.drawable.wrong_option_border)
                    }
                    else
                    {
                        mCorrectAnswer++
                    }

                    answerView(question.correctAnswer, R.drawable.corrrect_option_bg)
                   setOptionTextColor(question.correctAnswer,R.color.option_color)

                    if (mCurrPosition == mQuestionList!!.size) {
                        btnSubmit?.text = "FINISH"
                    } else {
                        btnSubmit?.text = "GO TO NEXT QUESTION"
                    }

                    mSelectedOptPosition = 0




                }
            }
        }
    }

    private  fun setOptionTextColor(answer:Int, color :Int)
    {
        when(answer)
        {
            1->tvOptionOne?.setTextColor(ContextCompat.getColor(this,color))
            2->tvOptionTwo?.setTextColor(ContextCompat.getColor(this,color))
            3->tvOptionThree?.setTextColor(ContextCompat.getColor(this,color))
            4->tvOptionFour?.setTextColor(ContextCompat.getColor(this,color))

        }
    }

    private fun answerView(answer: Int, drawableView: Int) {
        when (answer) {
            1 -> {
                tvOptionOne?.background = ContextCompat.getDrawable(
                    this,
                    drawableView
                )
            }

            2 -> {
                tvOptionTwo?.background = ContextCompat.getDrawable(
                    this,
                    drawableView
                )
            }

            3 -> {
                tvOptionThree?.background = ContextCompat.getDrawable(
                    this,
                    drawableView
                )
            }

            4 -> {
                tvOptionFour?.background = ContextCompat.getDrawable(
                    this,
                    drawableView
                )
            }
        }
    }
}
