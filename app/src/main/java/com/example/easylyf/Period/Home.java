package com.example.easylyf.Period;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.easylyf.R;

import java.util.Calendar;

public class Home extends Fragment implements View.OnClickListener {
    private String m_Text = "";
    private String p_Text = "";
    SharedPreferences sharedPreferences;
    private String cycledays = "";
    private String ovulation_days = "";
    private String periodlength = "";
    private String save_day = "";
    private String save_month = "";
    private String save_year = "";
    private String ovulation_date = "";
    private String next_period_date = "";
    private String chance_of_getting_pregnant = "";
    Button b;
    TextView tv, tv2, tv3;
    SharedPreferences.Editor editor;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_layout, container, false);
        b = (Button)view.findViewById(R.id.button);
        tv = (TextView)view.findViewById(R.id.textview);
        tv2 = (TextView)view.findViewById(R.id.textView2);
        tv3 = (TextView)view.findViewById(R.id.textView3);
        b.setOnClickListener(this);
        super.onCreate(savedInstanceState);
        sharedPreferences = this.getActivity().getSharedPreferences("com.example.knowyourdate.ui.Period", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        cycledays = sharedPreferences.getString("cycledays", "");
        periodlength = sharedPreferences.getString("periodlength", "");
        Log.i("cycledays", cycledays);
        Log.i("periodlength", periodlength);

        if (cycledays.isEmpty()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("Enter the cycle length");
            final EditText input = new EditText(getActivity());
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
            input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_NORMAL);
            builder.setView(input);

// Set up the buttons
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Log.i("Hi", "Hi");
                    m_Text = input.getText().toString();
                    Log.i("Hi", m_Text);
                    editor.putString("cycledays", m_Text);
                    editor.commit();
                    cycledays = sharedPreferences.getString("cycledays", "");
                }
            });

            builder.show();
        }

        if (periodlength.isEmpty()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("Enter the period length");

// Set up the input
            final EditText input = new EditText(getActivity());
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
            input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_NORMAL);
            builder.setView(input);
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Log.i("Hi", "Hi");
                    p_Text = input.getText().toString();
                    Log.i("Hi", m_Text);
                    editor.putString("periodlength", p_Text);
                    editor.commit();
                    periodlength = sharedPreferences.getString("periodlength", "");
                }
            });

            builder.show();
        }

        save_day = sharedPreferences.getString("save_day", "");
        save_month = sharedPreferences.getString("save_month", "");
        save_year = sharedPreferences.getString("save_year", "");
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        Log.i("saveday", save_day);

        save_day = sharedPreferences.getString("save_day", "");
        save_month = sharedPreferences.getString("save_month", "");
        save_year = sharedPreferences.getString("save_year", "");

        if((!save_day.equals("")?Integer.parseInt(save_day) : 0) == dayOfMonth && (!save_month.equals("")?Integer.parseInt(save_month) : 0) == month && (!save_year.equals("")?Integer.parseInt(save_year) : 0) == year){
            Log.i("true", "correct");
            tv.setText("1st Day");
            b.setText("Edit Period");
        }
        else if(((!save_day.equals("")?Integer.parseInt(save_day) : 0)+1 == dayOfMonth && (!save_month.equals("")?Integer.parseInt(save_month) : 0) == month && (!save_year.equals("")?Integer.parseInt(save_year) : 0) == year) || (dayOfMonth == 1 && (!save_day.equals("")?Integer.parseInt(save_day) : 0) == 31 && (!save_month.equals("")?Integer.parseInt(save_month) : 0) + 1 == month && (!save_year.equals("")?Integer.parseInt(save_year) : 0) == year) || (dayOfMonth == 1 && (!save_day.equals("")?Integer.parseInt(save_day) : 0) == 31 && month == 0 && (!save_month.equals("")?Integer.parseInt(save_month) : 0) == 11 && (!save_year.equals("")?Integer.parseInt(save_year) : 0) + 1 == year)){
            tv.setText("2nd Day");
            b.setText("Edit Period");
        }
        else if(((!save_day.equals("")?Integer.parseInt(save_day) : 0)+2 == dayOfMonth && (!save_month.equals("")?Integer.parseInt(save_month) : 0) == month && (!save_year.equals("")?Integer.parseInt(save_year) : 0) == year) || (dayOfMonth == 1 && (!save_day.equals("")?Integer.parseInt(save_day) : 0) == 30 && (!save_month.equals("")?Integer.parseInt(save_month) : 0) + 1 == month && (!save_year.equals("")?Integer.parseInt(save_year) : 0) == year) || (dayOfMonth == 1 && (!save_day.equals("")?Integer.parseInt(save_day) : 0) == 30 && month == 0 && (!save_month.equals("")?Integer.parseInt(save_month) : 0) == 11 && (!save_year.equals("")?Integer.parseInt(save_year) : 0) + 1 == year)){
            if(3 <= Integer.parseInt(periodlength)){
                tv.setText("3rd day");
                b.setText("Edit Period");
            }
        }
        else if(((!save_day.equals("")?Integer.parseInt(save_day) : 0)+3 == dayOfMonth && (!save_month.equals("")?Integer.parseInt(save_month) : 0) == month && (!save_year.equals("")?Integer.parseInt(save_year) : 0) == year) || (dayOfMonth == 1 && (!save_day.equals("")?Integer.parseInt(save_day) : 0) == 29 && (!save_month.equals("")?Integer.parseInt(save_month) : 0) + 1 == month && (!save_year.equals("")?Integer.parseInt(save_year) : 0) == year) || (dayOfMonth == 1 && (!save_day.equals("")?Integer.parseInt(save_day) : 0) == 29 && month == 0 && (!save_month.equals("")?Integer.parseInt(save_month) : 0) == 11 && (!save_year.equals("")?Integer.parseInt(save_year) : 0) + 1 == year)){
            if(4 <= Integer.parseInt(periodlength)){
                tv.setText("4th day");
                b.setText("Edit Period");
            }
        }
        else if(((!save_day.equals("")?Integer.parseInt(save_day) : 0)+4 == dayOfMonth && (!save_month.equals("")?Integer.parseInt(save_month) : 0) == month && (!save_year.equals("")?Integer.parseInt(save_year) : 0) == year) || (dayOfMonth == 1 && (!save_day.equals("")?Integer.parseInt(save_day) : 0) == 28 && (!save_month.equals("")?Integer.parseInt(save_month) : 0) + 1 == month && (!save_year.equals("")?Integer.parseInt(save_year) : 0) == year) || (dayOfMonth == 1 && (!save_day.equals("")?Integer.parseInt(save_day) : 0) == 28 && month == 0 && (!save_month.equals("")?Integer.parseInt(save_month) : 0) == 11 && (!save_year.equals("")?Integer.parseInt(save_year) : 0) + 1 == year)){
            if(5 <= Integer.parseInt(periodlength)){
                tv.setText("5th day");
                b.setText("Edit Period");
            }
        }
        else if(((!save_day.equals("")?Integer.parseInt(save_day) : 0)+5 == dayOfMonth && (!save_month.equals("")?Integer.parseInt(save_month) : 0) == month && (!save_year.equals("")?Integer.parseInt(save_year) : 0) == year) || (dayOfMonth == 1 && (!save_day.equals("")?Integer.parseInt(save_day) : 0) == 27 && (!save_month.equals("")?Integer.parseInt(save_month) : 0) + 1 == month && (!save_year.equals("")?Integer.parseInt(save_year) : 0) == year) || (dayOfMonth == 1 && (!save_day.equals("")?Integer.parseInt(save_day) : 0) == 27 && month == 0 && (!save_month.equals("")?Integer.parseInt(save_month) : 0) == 11 && (!save_year.equals("")?Integer.parseInt(save_year) : 0) + 1 == year)){
            if(6 <= Integer.parseInt(periodlength)){
                tv.setText("6th day");
                b.setText("Edit Period");
            }
        }
        else{
            tv.setText("");
        }

        ovulation_days = Integer.toString((!cycledays.equals("")?Integer.parseInt(cycledays) : 0) / 2);

        ovulation_date = Integer.toString((!save_day.equals("")?Integer.parseInt(save_day) : 0) + (!ovulation_days.equals("")?Integer.parseInt(ovulation_days) : 0));

        if((!ovulation_date.equals("")?Integer.parseInt(ovulation_date) : 0) > 31){
            if((!save_day.equals("")?Integer.parseInt(save_day) : 0) > dayOfMonth){
                tv2.setText("");
            }
            else{
                ovulation_date = Integer.toString((!ovulation_date.equals("")?Integer.parseInt(ovulation_date) : 0) - 31) + "/" + Integer.toString((!save_month.equals("")?Integer.parseInt(save_month) : 0) + 2) +"/" + save_year;
                tv2.setText("Next ovulation is expected on " + ovulation_date);
            }}
        else{
            if((!save_day.equals("")?Integer.parseInt(save_day) : 0) > dayOfMonth){
                tv2.setText("");
            }
            else{
                ovulation_date = ovulation_date + "/" + Integer.toString((!save_month.equals("")?Integer.parseInt(save_month) : 0) + 1) +"/" + save_year;
                tv2.setText("Next ovulation is expected on " + ovulation_date);
            }}

        next_period_date = Integer.toString((!cycledays.equals("")?Integer.parseInt(cycledays) : 0) + (!save_day.equals("")?Integer.parseInt(save_day) : 0));
        if((!next_period_date.equals("")?Integer.parseInt(next_period_date) : 0) > 31){
            if((!save_day.equals("")?Integer.parseInt(save_day) : 0) > dayOfMonth){
                tv3.setText("");
            }
            else {
                next_period_date = Integer.toString((!next_period_date.equals("")?Integer.parseInt(next_period_date) : 0) - 31) + "/" + Integer.toString((!save_month.equals("")?Integer.parseInt(save_month) : 0) + 2) +"/" + save_year;
                tv3.setText("Next period is expected on " + next_period_date);
            }
        }
        else{
            if((!save_day.equals("")?Integer.parseInt(save_day) : 0) > dayOfMonth){
                tv3.setText("");
            }
            else {
                next_period_date = next_period_date + "/" + Integer.toString((!save_month.equals("")?Integer.parseInt(save_month) : 0) + 1) +"/" + save_year;
                tv3.setText("Next period is expected on " + next_period_date);
            }
        }

        return view;
    }


    @Override
    public void onClick(View v) {
            Calendar calendar = Calendar.getInstance();
            final int year = calendar.get(Calendar.YEAR);
            final int month = calendar.get(Calendar.MONTH);
            final int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
            editor = sharedPreferences.edit();
            tv = getActivity().findViewById(R.id.textview);
            tv2 = getActivity().findViewById(R.id.textView2);
            tv3 = getActivity().findViewById(R.id.textView3);
            DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int y, int m, int d) {
                    Log.i("dmy", d + "/" + m + "/" + y);
                    Log.i("ddmmyy", dayOfMonth + "/" + month + "/" + year);
                    editor.putString("save_day", Integer.toString(d));
                    editor.commit();
                    editor.putString("save_month", Integer.toString((m)));
                    editor.commit();
                    editor.putString("save_year", Integer.toString(y));
                    editor.commit();

                    save_day = sharedPreferences.getString("save_day", "");
                    save_month = sharedPreferences.getString("save_month", "");
                    save_year = sharedPreferences.getString("save_year", "");

                    if(Integer.parseInt(save_day) == dayOfMonth && Integer.parseInt(save_month) == month && Integer.parseInt(save_year) == year){
                        Log.i("true", "correct");
                        tv.setText("1st Day");
                        b.setText("Edit Period");
                    }
                    else if((Integer.parseInt(save_day)+1 == dayOfMonth && Integer.parseInt(save_month) == month && Integer.parseInt(save_year) == year) || (dayOfMonth == 1 && Integer.parseInt(save_day) == 31 && Integer.parseInt(save_month) + 1 == month && Integer.parseInt(save_year) == year) || (dayOfMonth == 1 && Integer.parseInt(save_day) == 31 && month == 0 && Integer.parseInt(save_month) == 11 && Integer.parseInt(save_year) + 1 == year)){
                        tv.setText("2nd Day");
                        b.setText("Edit Period");
                    }
                    else if((Integer.parseInt(save_day)+2 == dayOfMonth && Integer.parseInt(save_month) == month && Integer.parseInt(save_year) == year) || (dayOfMonth == 1 && Integer.parseInt(save_day) == 30 && Integer.parseInt(save_month) + 1 == month && Integer.parseInt(save_year) == year) || (dayOfMonth == 1 && Integer.parseInt(save_day) == 30 && month == 0 && Integer.parseInt(save_month) == 11 && Integer.parseInt(save_year) + 1 == year)){
                        if(3 <= Integer.parseInt(periodlength)){
                            tv.setText("3rd day");
                            b.setText("Edit Period");
                        }
                    }
                    else if((Integer.parseInt(save_day)+3 == dayOfMonth && Integer.parseInt(save_month) == month && Integer.parseInt(save_year) == year) || (dayOfMonth == 1 && Integer.parseInt(save_day) == 29 && Integer.parseInt(save_month) + 1 == month && Integer.parseInt(save_year) == year) || (dayOfMonth == 1 && Integer.parseInt(save_day) == 29 && month == 0 && Integer.parseInt(save_month) == 11 && Integer.parseInt(save_year) + 1 == year)){
                        if(4 <= Integer.parseInt(periodlength)){
                            tv.setText("4th day");
                            b.setText("Edit Period");
                        }
                    }
                    else if((Integer.parseInt(save_day)+4 == dayOfMonth && Integer.parseInt(save_month) == month && Integer.parseInt(save_year) == year) || (dayOfMonth == 1 && Integer.parseInt(save_day) == 28 && Integer.parseInt(save_month) + 1 == month && Integer.parseInt(save_year) == year) || (dayOfMonth == 1 && Integer.parseInt(save_day) == 28 && month == 0 && Integer.parseInt(save_month) == 11 && Integer.parseInt(save_year) + 1 == year)){
                        if(5 <= Integer.parseInt(periodlength)){
                            tv.setText("5th day");
                            b.setText("Edit Period");
                        }
                    }
                    else if((Integer.parseInt(save_day)+5 == dayOfMonth && Integer.parseInt(save_month) == month && Integer.parseInt(save_year) == year) || (dayOfMonth == 1 && Integer.parseInt(save_day) == 27 && Integer.parseInt(save_month) + 1 == month && Integer.parseInt(save_year) == year) || (dayOfMonth == 1 && Integer.parseInt(save_day) == 27 && month == 0 && Integer.parseInt(save_month) == 11 && Integer.parseInt(save_year) + 1 == year)){
                        if(6 <= Integer.parseInt(periodlength)){
                            tv.setText("6th day");
                            b.setText("Edit Period");
                        }
                    }
                    else{
                        tv.setText("");
                    }
                    ovulation_days = Integer.toString((!cycledays.equals("")?Integer.parseInt(cycledays) : 0) / 2);

                    ovulation_date = Integer.toString((!save_day.equals("")?Integer.parseInt(save_day) : 0) + (!ovulation_days.equals("")?Integer.parseInt(ovulation_days) : 0));

                    if((!ovulation_date.equals("")?Integer.parseInt(ovulation_date) : 0) > 31){
                        if((!save_day.equals("")?Integer.parseInt(save_day) : 0) > dayOfMonth){
                            tv2.setText("");
                        }
                        else{
                            ovulation_date = Integer.toString((!ovulation_date.equals("")?Integer.parseInt(ovulation_date) : 0) - 31) + "/" + Integer.toString((!save_month.equals("")?Integer.parseInt(save_month) : 0) + 2) +"/" + save_year;
                            tv2.setText("Next ovulation is expected on " + ovulation_date);
                        }}
                    else{
                        if((!save_day.equals("")?Integer.parseInt(save_day) : 0) > dayOfMonth){
                            tv2.setText("");
                        }
                        else{
                            ovulation_date = ovulation_date + "/" + Integer.toString((!save_month.equals("")?Integer.parseInt(save_month) : 0) + 1) +"/" + save_year;
                            tv2.setText("Next ovulation is expected on " + ovulation_date);
                        }}

                    next_period_date = Integer.toString((!cycledays.equals("")?Integer.parseInt(cycledays) : 0) + (!save_day.equals("")?Integer.parseInt(save_day) : 0));
                    if((!next_period_date.equals("")?Integer.parseInt(next_period_date) : 0) > 31){
                        if((!save_day.equals("")?Integer.parseInt(save_day) : 0) > dayOfMonth){
                            tv3.setText("");
                        }
                        else {
                            next_period_date = Integer.toString((!next_period_date.equals("")?Integer.parseInt(next_period_date) : 0) - 31) + "/" + Integer.toString((!save_month.equals("")?Integer.parseInt(save_month) : 0) + 2) +"/" + save_year;
                            tv3.setText("Next period is expected on " + next_period_date);
                        }
                    }
                    else{
                        if((!save_day.equals("")?Integer.parseInt(save_day) : 0) > dayOfMonth){
                            tv3.setText("");
                        }
                        else {
                            next_period_date = next_period_date + "/" + Integer.toString((!save_month.equals("")?Integer.parseInt(save_month) : 0) + 1) +"/" + save_year;
                            tv3.setText("Next period is expected on " + next_period_date);
                        }
                    }
                }
            }, year, month, dayOfMonth);
            datePickerDialog.show();
        }
    }





