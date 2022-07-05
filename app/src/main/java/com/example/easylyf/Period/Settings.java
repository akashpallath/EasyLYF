package com.example.easylyf.Period;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.easylyf.R;

public class Settings extends Fragment implements View.OnClickListener{
    TextView tv;
    Button b2;
    EditText e1, e2;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    private String cycledays;
    private String periodlength;
    private String cycledays_edit = "";
    private String perioddays_edit = "";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.settings_layout, container, false);
        super.onCreate(savedInstanceState);
        e1 = (EditText)view.findViewById(R.id.editText);
        e2 = (EditText)view.findViewById(R.id.editText2);
        b2 = (Button)view.findViewById(R.id.button2);
        b2.setOnClickListener(this);

        sharedPreferences = this.getActivity().getSharedPreferences("com.example.knowyourdate.ui.Period", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        cycledays = sharedPreferences.getString("cycledays", "");
        periodlength = sharedPreferences.getString("periodlength", "");

        e1.setText(cycledays);
        e2.setText(periodlength);

        cycledays_edit = e1.getText().toString();
        perioddays_edit = e2.getText().toString();

        editor.putString("cycledays", cycledays_edit);
        editor.putString("periodlength", perioddays_edit);

        return view;
    }

    @Override
    public void onClick(View v) {
        e1 = (EditText)getActivity().findViewById(R.id.editText);
        e2 = (EditText)getActivity().findViewById(R.id.editText2);
        Log.i("e1",e1.getText().toString());
        cycledays_edit = e1.getText().toString();
        perioddays_edit = e2.getText().toString();

        editor.putString("cycledays", cycledays_edit);
        editor.commit();
        editor.putString("periodlength", perioddays_edit);
        editor.commit();
    }
}