package com.example.easylyf.activites;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.easylyf.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegisterUserActivity extends AppCompatActivity {

    private EditText name,mobileno,dob,address,email,password,confirmpassword;
    private Button register,register_seller,back;

    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        back = findViewById(R.id.button_back);
        name = findViewById(R.id.name);
        mobileno = findViewById(R.id.mobileno);
        dob = findViewById(R.id.deliveryfee);
        address = findViewById(R.id.address);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        confirmpassword = findViewById(R.id.confirmpassword);
        register = findViewById(R.id.btn_reg2);
        register_seller = findViewById(R.id.register_seller);

        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Please wait");
        progressDialog.setCanceledOnTouchOutside(false);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //register user
                inputData();
            }
            
        });

        register_seller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //open seller activity
                startActivity(new Intent(RegisterUserActivity.this, RegisterSellerActivity.class));
            }
        });

    }

    private String Name,Mobileno,Dob,Address,Email,Password,Confirmpassword;
    private void inputData() {
        Name = name.getText().toString().trim();
        Mobileno = mobileno.getText().toString().trim();
        Dob = dob.getText().toString().trim();
        Address = address.getText().toString().trim();
        Email = email.getText().toString().trim();
        Password = password.getText().toString().trim();
        Confirmpassword = confirmpassword.getText().toString().trim();

        if(TextUtils.isEmpty(Name)){
            Toast.makeText(RegisterUserActivity.this, "Enter Name...", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(Mobileno)){
            Toast.makeText(RegisterUserActivity.this, "Enter Mobile No...", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(Dob)){
            Toast.makeText(RegisterUserActivity.this, "Enter Shop Name...", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(Address)){
            Toast.makeText(RegisterUserActivity.this, "Enter Delivery Fee...", Toast.LENGTH_SHORT).show();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(Email).matches()){
            Toast.makeText(RegisterUserActivity.this, "Enter Email...", Toast.LENGTH_SHORT).show();
            return;
        }
        if(Password.length()<6){
            Toast.makeText(RegisterUserActivity.this, "Enter Password...", Toast.LENGTH_SHORT).show();
            return;
        }
        if(!Password.equals(Confirmpassword)){
            Toast.makeText(RegisterUserActivity.this, "Enter ConfirmPassword...", Toast.LENGTH_SHORT).show();
            return;
        }

        createAccount();
    }

    private void createAccount() {
        progressDialog.setMessage("Creating Account...");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(Email,Password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        //acc created
                        saverFirebaseData();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        //failed
                        progressDialog.dismiss();
                        Toast.makeText(RegisterUserActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void saverFirebaseData() {
        progressDialog.setMessage("Saving Account Info...");

        String timestamp = ""+System.currentTimeMillis();

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("uid",""+firebaseAuth.getUid());
        hashMap.put("name",""+Name);
        hashMap.put("mobileno",""+Mobileno);
        hashMap.put("dod",""+Dob);
        hashMap.put("address",""+Address);
        hashMap.put("email",""+Email);
        hashMap.put("password",""+Password);
        hashMap.put("confirmpassword",""+Confirmpassword);
        hashMap.put("timestamp",""+timestamp);
        hashMap.put("accountType","User");
        hashMap.put("online","true");

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users");
        ref.child(firebaseAuth.getUid()).setValue(hashMap)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        progressDialog.dismiss();
                        startActivity(new Intent(RegisterUserActivity.this, MainUserActivity.class));
                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.dismiss();
                        Toast.makeText(RegisterUserActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

    }
}