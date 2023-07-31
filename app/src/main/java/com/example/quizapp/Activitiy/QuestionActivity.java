package com.example.quizapp.Activitiy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.TextView;

import com.example.quizapp.Model.QuestionModel;
import com.example.quizapp.R;
import com.example.quizapp.databinding.ActivityQuestionBinding;


import java.util.ArrayList;

public class QuestionActivity extends AppCompatActivity
{

    ArrayList<QuestionModel> list = new ArrayList<>();
    private int count = 0;
    private int position = 0;
    private int score = 0;
    CountDownTimer timer;


    ActivityQuestionBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityQuestionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().hide();

        resetTimer();
        timer.start();



        String setName = getIntent().getStringExtra("set");
        if (setName.equals("SET-1")) ;
        {
            setOne();
        }

       if  (setName.equals("SET-2")) {
            setTwo();
        }



         for (int  i = 0;i<4; i++){
             binding.optionContainer.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {

                     checkAnswer((Button) view);
                 }
             });
         }

         playAnimation(binding.question, 0, list.get(position).getQuestion());

         binding.next.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {

                 if (timer!= null){
                     timer.cancel();
                 }
                 timer.start();

                 binding.next.setEnabled(false);
                 binding.next.setAlpha((float) 0.3);
                 enableOption(true);
                 position ++;
                 if (position ==list.size()){
                     Intent intent = new Intent(QuestionActivity.this, ScoreActivity.class);
                     intent.putExtra("score", score);
                     intent.putExtra("total", list.size());
                     startActivity(intent);
                     finish();
                     return;
                 }

                 count =0;
                 playAnimation(binding.question,0,list.get(position).getQuestion());

             }
         });
    }

    private void resetTimer() {

        timer = new CountDownTimer(30000,1000){
            @Override
            public void onTick(long l){
                binding.timer.setText(String.valueOf(l/1000));
            }
            @Override
            public void onFinish(){

                Dialog dialog = new Dialog(QuestionActivity.this);
                dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
                dialog.setCancelable(false);
                dialog.setContentView(R.layout.timeout_dialog);
                dialog.findViewById(R.id.tryAgain).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(QuestionActivity.this, SetActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });

                dialog.show();
            }
        };

    }

    private void playAnimation(View view, int value, String data) {

        view.animate().alpha(value).scaleX(value).scaleY(value).setDuration(500).setStartDelay(100)
                .setInterpolator(new DecelerateInterpolator()).setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(@NonNull Animator animator) {

                        if (value ==0 && count <4){
                            String option ="";

                            if(count ==0){
                                option = list.get(position).getOptionA();

                            } else if (count ==1) {
                                option = list.get(position).getOptionB();

                            }
                            else if (count ==2) {
                                option = list.get(position).getOptionC();

                            }

                            else if (count ==3) {
                                option = list.get(position).getOptionD();

                            }
                            playAnimation(binding.optionContainer.getChildAt(count), 0, option);
                          count ++;

                        }
                    }

                    @Override
                    public void onAnimationEnd(@NonNull Animator animator) {
                        if (value ==0){
                           try {
                               ((TextView) view).setText(data);
                               binding.totalQuestion.setText(position+1+"/"+list.size());

                           } catch ( Exception e) {
                               ((Button)view ).setText(data);
                           }
                           view.setTag(data);
                           playAnimation(view, 1, data);

                        }
                    }

                    @Override
                    public void onAnimationCancel(@NonNull Animator animator) {

                    }
                    @Override
                    public void onAnimationRepeat(@NonNull Animator animator) {

                    }
                });

    }
    private void enableOption(boolean enable) {

        for (int i = 0; i<4; i++) {
            binding.optionContainer.getChildAt(i).setEnabled(enable);

            if(enable){
                binding.optionContainer.getChildAt(i).setBackgroundResource(R.drawable.btn_opt);
            }
        }

    }

    private void checkAnswer(Button selectedOption) {

        if (timer!=null){
            timer.cancel();
        }

        binding.next.setEnabled(true);
        binding.next.setAlpha(1);

        if (selectedOption.getText().toString().equals(list.get(position).getCorrectAnswer())){
            score ++;
            selectedOption.setBackgroundResource(R.drawable.correct_answer);
        } else  {

            selectedOption.setBackgroundResource(R.drawable.wrong_answer);
            Button correctOption = (Button)  binding.optionContainer.findViewWithTag(list.get(position).getCorrectAnswer());
            correctOption.setBackgroundResource(R.drawable.correct_answer);

        }
    }

    private void setTwo() {
        list.add(new QuestionModel("The capital of India?",
                "Patna", "Delhi", "Bihar", " Old Delhi",
                "Delhi"));

        list.add(new QuestionModel("Who is the first prime Minister  of India?",
                "Ram", "Narayan", " Mohan Singh", "Dr. Rajendra Prasad",
                "Dr. Rajendra Prasad"));

        list.add(new QuestionModel("The capital of Jharkhand?",
                "Ranchi", "Delhi", "Bihar", " Old Delhi",
                "Ranchi"));

        list.add(new QuestionModel("The capital of Bihar?",
                "Patna", "Kerala", "Bengal", " Kolkata",
                "Patna"));


    }

    private void setOne() {
        list.add(new QuestionModel("The country that has the highest in Barley Production?",
                 "China", "India", "Russia", " France",
                "Russia"));

        list.add(new QuestionModel("Patanjali is well known for the compilation of- ?",
                "Yoga Sutra", "Panchatantra", "Brahma Sutra", " Ayurveda",
                "Yoga Sutra"));

        list.add(new QuestionModel("The capital of India?",
                "Patna", "Delhi", "Bihar", " Old Delhi",
                "Delhi"));

        list.add(new QuestionModel("The metal whose salts are sensitive to light is?",
                "Zinc", "Copper", "Silver", " Alloy",
                "Zinc"));


    }

}