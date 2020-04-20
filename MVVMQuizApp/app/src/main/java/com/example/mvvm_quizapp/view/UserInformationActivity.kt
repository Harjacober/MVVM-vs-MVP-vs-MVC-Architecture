package com.example.mvvm_quizapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.mvvm_quizapp.R
import com.example.mvvm_quizapp.viewmodel.QuizViewModel
import com.example.mvvm_quizapp.databinding.ActivityUserInformationBinding

class UserInformationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activityUserInformationBinding:ActivityUserInformationBinding = DataBindingUtil.setContentView(this,
            R.layout.activity_user_information)
        val quizViewModel = ViewModelProviders.of(this).get(QuizViewModel::class.java)
        activityUserInformationBinding.btnStartquiz.setOnClickListener {
            quizViewModel.saveUserInformation(activityUserInformationBinding.edFullname.text.toString())
            val intent = Intent(this, TakeTestActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
