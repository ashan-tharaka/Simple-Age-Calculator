package com.example.agecalculator;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

import es.dmoral.toasty.Toasty;

public class MainActivity extends AppCompatActivity {
Button btn_reset,btn_cal;
TextView txt_birth,txt_date;
EditText edit_birth,edit_date;
TextView text_age;
DatePickerDialog datePickerDialog;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_reset=(Button)findViewById(R.id.btn_reset);
        txt_birth=(TextView) findViewById(R.id.txt_birth);
        txt_date=(TextView) findViewById(R.id.txt_date);
        btn_cal=(Button)findViewById(R.id.btn_cal);
        edit_birth=(EditText)findViewById(R.id.edit_birth);
        edit_date=(EditText)findViewById(R.id.edit_date);
        text_age=(TextView) findViewById(R.id.text_age);


        LocalDate date= LocalDate.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("d/M/yyyy");
        String formattedDate = date.format(myFormatObj);

        edit_date.setText(formattedDate);




        edit_birth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                datePickerDialog = new DatePickerDialog(MainActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                edit_birth.setText(dayOfMonth + "/"
                                        + (monthOfYear + 1) + "/" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();


            }
        });
        edit_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                datePickerDialog = new DatePickerDialog(MainActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                edit_date.setText(dayOfMonth + "/"
                                        + (monthOfYear + 1) + "/" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });



        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit_birth.setText("");
                edit_date.setText("");
                text_age.setText("");




            }
        });
        btn_cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!"".contentEquals(edit_birth.getText().toString())&&!"".contentEquals(edit_date.getText().toString())){


                    String s1 = edit_date.getText().toString();
                    SimpleDateFormat sdf1 = new SimpleDateFormat("d/M/yyyy");
                    Date d1 = null;
                    try {
                        d1= sdf1.parse(s1);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    Calendar c1= Calendar.getInstance();
                    c1.setTime(d1);
                    int year1 = c1.get(Calendar.YEAR);
                    int month1 = c1.get(Calendar.MONTH) + 1;
                    int date1 = c1.get(Calendar.DATE);
                    LocalDate l2 = LocalDate.of(year1, month1, date1);


                    String s = edit_birth.getText().toString();
                    SimpleDateFormat sdf = new SimpleDateFormat("d/M/yyyy");
                    Date d = null;
                    try {
                        d = sdf.parse(s);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    Calendar c = Calendar.getInstance();
                    c.setTime(d);
                    int year = c.get(Calendar.YEAR);
                    int month = c.get(Calendar.MONTH) + 1;
                    int date = c.get(Calendar.DATE);
                    LocalDate l1 = LocalDate.of(year, month, date);

                    Period diff1 = Period.between(l1, l2);
                    text_age.setText("Your age is " + diff1.getYears() + " years "+diff1.getMonths()+" months and "+diff1.getDays()+" days.");
                    try {
                        InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                    } catch (Exception e) {
                        // TODO: handle exception
                    }

                }
else {
                    Toasty.warning(MainActivity.this,
                            "Please fill required fields",
                            Toast.LENGTH_SHORT)
                            .show();
}

            }
        });



    }

}