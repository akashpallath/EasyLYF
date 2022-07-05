package com.example.easylyf.activites;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.easylyf.PeriodCycle.PeriodActivity;
import com.example.easylyf.PeriodCycle.PeriodInfoActivity;
import com.example.easylyf.R;
import com.example.easylyf.Reminders.ReminderActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MenuUIActivity extends AppCompatActivity {

    private TextView Name;
    private ImageButton btn_shopping, btn_reminder, btn_period, btn_periodinfo;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_uiactivity);

        Name = findViewById(R.id.textView_name);
        btn_shopping = findViewById(R.id.btn_shopping);
        btn_reminder = findViewById(R.id.btn_reminder);
        btn_period = findViewById(R.id.btn_period);
        btn_periodinfo = findViewById(R.id.btn_periodinfo);

        firebaseAuth = FirebaseAuth.getInstance();
        checkUser();

        btn_periodinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuUIActivity.this, PeriodInfoActivity.class));
            }
        });

        btn_shopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuUIActivity.this, MainUserActivity.class));
            }
        });

        btn_reminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuUIActivity.this, ReminderActivity.class));
            }
        });

        btn_period.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuUIActivity.this, PeriodActivity.class));
            }
        });

    }


    private void checkUser() {
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if (user == null){
            startActivity(new Intent(MenuUIActivity.this, LoginActivity.class));
            finish();
        }
        else {
            loadMyInfo();
        }
    }

    private void loadMyInfo() {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users");
        ref.orderByChild("uid").equalTo(firebaseAuth.getUid())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot ds: dataSnapshot.getChildren()){
                            String name = ""+ds.child("name").getValue();
                            String email = ""+ds.child("email").getValue();
                            String phone = ""+ds.child("mobileno").getValue();
                            String accountType = ""+ds.child("accountType").getValue();

                            Name.setText(name +"("+accountType+")");

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }
}