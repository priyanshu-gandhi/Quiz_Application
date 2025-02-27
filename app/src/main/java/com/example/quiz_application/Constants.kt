package com.example.quiz_application

import android.annotation.SuppressLint
import com.example.quiz_application.R.drawable.ic_tw

object Constants {

    const val USER_NAME :String="user_name"
    const val TOTAL_QUESTIONS : String="total_questions"
    const val CORRECT_ANSWER : String ="correct_answer"


    @SuppressLint("SuspiciousIndentation")
    fun getQuestions():ArrayList<Question>
    {
        val questionsList =ArrayList<Question>()

        val ques1=Question(
            1,"What country does this flag belongs to?",
            R.drawable.ic_flag_of_india,"Australia","India",
            "Japan","Brasil",2
        )
            questionsList.add(ques1)

        val ques2=Question(
            2,"Where is Golden Temple located?",
            R.drawable.ic_amritsar,"Amritsar","Delhi",
            "Chandigarh","Ludhiana",1
        )
        questionsList.add(ques2)

        val ques3=Question(
            3,"Which company is associated with  below logo?",
            R.drawable.ic_apple_logo,"Oppo","Samsung",
            "OnePlus","Apple",4
        )
        questionsList.add(ques3)

        val ques4=Question(
            4,"Name the personality?",
            R.drawable.ic_vk,"Salman Khan","Rohit Sharma",
            "Virat Kohli","Messi",3
        )
        questionsList.add(ques4)

        val ques5=Question(
            5,"Which is the highest mountain in the world?",
            R.drawable.ic_mounteve,"Mount Everest","Annapurna",
            "Makula","K2",1
        )
        questionsList.add(ques5)

        val ques6=Question(
            6,"What is the most famous Landmark in France?",
            ic_tw,"The Colosseum","The Leaning Tower of Pisa",
            " Buckingham Palace","The Eiffel Tower",4
        )
        questionsList.add(ques6)

        val ques7=Question(
            7,"Where is the origin of  pizza?",
            R.drawable.ic_italy,"Australia","Greece",
            "Italy","Brasil",3
        )
        questionsList.add(ques7)

            return questionsList


    }

}