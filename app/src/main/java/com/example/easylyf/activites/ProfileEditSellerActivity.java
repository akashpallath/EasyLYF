package com.example.easylyf.activites;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.easylyf.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class ProfileEditSellerActivity extends AppCompatActivity {

    private Button backBtn;
    private TextView Name, mobileNo, shopName, deliveryFee, Address;
    private Button update;
    private Switch shopOpenSwitch;

    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_edit_seller);

        backBtn = findViewById(R.id.button_back);
        Name = findViewById(R.id.name);
        mobileNo = findViewById(R.id.mobileno);
        shopName = findViewById(R.id.shop_name);
        deliveryFee = findViewById(R.id.deliveryfee);
        Address = findViewById(R.id.address);
        update = findViewById(R.id.btn_update);
        shopOpenSwitch = findViewById(R.id.switch_shopopen);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Please wait");
        progressDialog.setCanceledOnTouchOutside(false);

        firebaseAuth = FirebaseAuth.getInstance();
        checkUser();

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputData();
            }
        });

    }

    private String name, mobileno, shopname, deliveryfee, address;
    private boolean shopopen;
    private void inputData() {
        name = Name.getText().toString().trim();
        mobileno = mobileNo.getText().toString().trim();
        shopname = shopName.getText().toString().trim();
        deliveryfee = deliveryFee.getText().toString().trim();
        address = Address.getText().toString().trim();
        shopopen = shopOpenSwitch.isChecked();

        updateProfile();
    }

    private void updateProfile() {
        progressDialog.setMessage("Updating Profile");
        progressDialog.show();

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("name",""+name);
        hashMap.put("mobileno",""+mobileno);
        hashMap.put("shopname",""+shopname);
        hashMap.put("deliveryfee",""+deliveryfee);
        hashMap.put("address",""+address);
        hashMap.put("shopOpen",""+shopopen);
        
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users");
        ref.child(firebaseAuth.getUid()).updateChildren(hashMap)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        progressDialog.dismiss();
                        Toast.makeText(ProfileEditSellerActivity.this, "Profile updated...", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) { 
                        progressDialog.dismiss();
                        Toast.makeText(ProfileEditSellerActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void checkUser() {
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if (user == null){
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
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
                    public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                        for (DataSnapshot ds: datasnapshot.getChildren()){
                            String accountType = ""+ds.child("accountType").getValue();
                            String uid = ""+ds.child("uid").getValue();
                            String name = ""+ds.child("name").getValue();
                            String mobileno = ""+ds.child("mobileno").getValue();
                            String shopname = ""+ds.child("shopname").getValue();
                            String deliveryfee = ""+ds.child("deliveryfee").getValue();
                            String address = ""+ds.child("address").getValue();
                            String email = ""+ds.child("email").getValue();
                            String timestamp = ""+ds.child("timestamp").getValue();
                            String online = ""+ds.child("online").getValue();
                            String shopOpen = ""+ds.child("shopOpen").getValue();

                            Name.setText(name);
                            mobileNo.setText(mobileno);
                            shopName.setText(shopname);
                            deliveryFee.setText(deliveryfee);
                            Address.setText(address);

                            if (shopOpen.equals("true")){
                                shopOpenSwitch.setChecked(true);
                            }
                            else{
                                shopOpenSwitch.setChecked(false);
                            }

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }
}