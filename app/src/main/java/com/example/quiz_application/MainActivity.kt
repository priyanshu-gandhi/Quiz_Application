package com.example.quiz_application

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
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
import com.example.quiz_application.ui.theme.Quiz_ApplicationTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn_start :Button =findViewById(R.id.btn_start)
        val et_name : EditText=findViewById(R.id.et_name)
        btn_start.setOnClickListener{

            if(et_name.text.isEmpty())
            {
                Toast.makeText(this,"Please Enter your name",Toast.LENGTH_LONG).show()
            }
            else
            {
                 val intent=Intent(this, QuizQuestionActivity::class.java)
                 intent.putExtra(Constants.USER_NAME,et_name.text.toString())
                 startActivity(intent)
                 finish()
            }

        }
    }
}




