package com.blakelassgmail.attendance;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;
import java.util.Calendar;

public class EditMember extends AppCompatActivity {

    private Button backBtn;
    private Button editBtn;
    private Button addBtn;
    private Button dateBtn;
    private String name = "";
    private String phone = "";
    private String email = "";
    private String grade = "";
    private int studentId = -1;
    private int year_x, month_y, day_z;
    private static int DIALOG_ID = 0;

    private EditText nameView;
    private EditText phoneView;
    private EditText emailView;
    private EditText gradeView;
    private EditText studentView;
    private static final int REQUEST_CODE_EDIT_MEMBER = 2;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (requestCode) {
            case REQUEST_CODE_EDIT_MEMBER:
                if (resultCode == RESULT_OK) {
                    name = data.getStringExtra("name");
                    phone = data.getStringExtra("phone");
                    email = data.getStringExtra("email");
                    grade = data.getStringExtra("grade");
                    studentId = data.getIntExtra("studentId", -1);
                    year_x = data.getIntExtra("year", -1);
                    month_y = data.getIntExtra("month", -1);
                    day_z = data.getIntExtra("day", -1);
                }
                if (resultCode == RESULT_CANCELED) {
                    //Write your code if there's no result
                }
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_member);

        dateBtn = (Button) findViewById(R.id.dateBtn);
        backBtn = (Button) findViewById(R.id.back);
        editBtn = (Button) findViewById(R.id.editBtn);
        addBtn = (Button) findViewById(R.id.saveBtn);
        nameView = (EditText) findViewById(R.id.edit_name);
        phoneView = (EditText) findViewById(R.id.edit_phone);
        emailView = (EditText) findViewById(R.id.edit_email);
        gradeView = (EditText) findViewById(R.id.edit_grade);
        studentView = (EditText) findViewById(R.id.edit_student);

        //go back
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editBtn != null)
                {editBtn.setEnabled(false);}  //button which u want to disable
                Intent intent = new Intent(getApplicationContext(), DisplayMember.class);
                finish();
            }
        });

        //add member button
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (backBtn != null) {
                    backBtn.setEnabled(false);
                }  //button which u want to disable
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("name", nameView.getText().toString());
                intent.putExtra("phone", phoneView.getText().toString());
                intent.putExtra("email", emailView.getText().toString());
                intent.putExtra("studentId", studentView.getText().toString());
                intent.putExtra("grade", gradeView.getText().toString());
                setResult(RESULT_CANCELED, intent);
                finish();
            }
        });
    }
}