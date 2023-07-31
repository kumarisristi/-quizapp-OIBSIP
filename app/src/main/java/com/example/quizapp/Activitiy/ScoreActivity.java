package com.example.quizapp.Activitiy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.quizapp.R;
import com.example.quizapp.databinding.ActivityQuestionBinding;
import com.example.quizapp.databinding.ActivityScoreBinding;

public class ScoreActivity extends AppCompatActivity {

    ActivityScoreBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        binding = ActivityScoreBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().hide();


        int totalSore = getIntent().getIntExtra("total", 0);
        int correctAns =  getIntent().getIntExtra("score", 0);
        int wrongAns = totalSore - correctAns;

        binding.totalQuestion.setText(String.valueOf(totalSore));
        binding.rightAnswer.setText(String.valueOf(correctAns));
        binding.wrongAnswer.setText(String.valueOf(wrongAns));

        binding.btnRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ScoreActivity.this, SetActivity.class);
                startActivity(intent);
                finish();
            }
        });


        binding.btnQuite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });



    }
}