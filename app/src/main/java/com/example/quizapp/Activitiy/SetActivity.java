package com.example.quizapp.Activitiy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.quizapp.Adapter.SetAdapter;
import com.example.quizapp.MainActivity;
import com.example.quizapp.Model.SetModel;
import com.example.quizapp.R;
import com.example.quizapp.databinding.ActivitySetBinding;

import java.util.ArrayList;

public class SetActivity extends AppCompatActivity {

    ActivitySetBinding binding;
    ArrayList<SetModel> list;

    ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySetBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());


        getSupportActionBar().hide();

        imageView= findViewById(R.id.btnBack);

        ImageView btn_back = findViewById(R.id.btnBack);
        btn_back.setOnClickListener(view -> {
            Intent secondActivityIntent = new Intent(
                    getApplicationContext(), MainActivity.class
            );
            startActivity(secondActivityIntent);
        });

        list = new  ArrayList<>();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        binding.setRecycler.setLayoutManager(linearLayoutManager);

        list.add(new SetModel("SET-1"));
        list.add(new SetModel("SET-2"));
        list.add(new SetModel("SET-3"));
        list.add(new SetModel("SET-4"));
        list.add(new SetModel("SET-5"));
        list.add(new SetModel("SET-6"));
        list.add(new SetModel("SET-7"));
        list.add(new SetModel("SET-8"));
        list.add(new SetModel("SET-9"));
        list.add(new SetModel("SET-10"));

        SetAdapter adapter = new SetAdapter(this, list);
        binding.setRecycler.setAdapter(adapter);


    }
}