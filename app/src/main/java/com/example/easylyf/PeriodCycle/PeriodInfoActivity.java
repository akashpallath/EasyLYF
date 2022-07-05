package com.example.easylyf.PeriodCycle;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.easylyf.R;

public class PeriodInfoActivity extends AppCompatActivity {

    private ImageButton btn_knowledge, btn_exercise, btn_diet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_period_info);

        btn_knowledge = findViewById(R.id.btn_knowledge);
        btn_exercise = findViewById(R.id.btn_exercise);
        btn_diet = findViewById(R.id.btn_diet);

        btn_knowledge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PeriodInfoActivity.this, PeriodKnowledgeActivity.class));
            }
        });

        btn_exercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PeriodInfoActivity.this, PeriodExerciseActivity.class));
            }
        });

        btn_diet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PeriodInfoActivity.this, PeriodDietActivity.class));
            }
        });

    }
}