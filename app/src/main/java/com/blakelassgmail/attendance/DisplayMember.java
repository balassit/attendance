package com.blakelassgmail.attendance;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class DisplayMember extends AppCompatActivity {

    //Array of options --> ArrayAdapter --> ListView

    //ListView :{views, items.xml}

    private Button backBtn;
    private Button editBtn;
    private TextView nameView;
    private TextView phoneView;
    private TextView emailView;
    private TextView gradeView;
    private TextView studentView;
    private static final int REQUEST_CODE_VIEW_MEMBER = 2;
    private static final int REQUEST_CODE_EDIT_MEMBER = 3;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (requestCode) {
            case REQUEST_CODE_VIEW_MEMBER:
                if (resultCode == RESULT_OK) {
                    nameView.setText(data.getStringExtra("name"));
                    phoneView.setText(data.getStringExtra("phone"));
                    emailView.setText(data.getStringExtra("email"));
                    gradeView.setText(data.getStringExtra("grade"));
                    studentView.setText(data.getStringExtra("studentId"));
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
        setContentView(R.layout.activity_display_member);

        backBtn = (Button) findViewById(R.id.back);
        editBtn = (Button) findViewById(R.id.editBtn);
        nameView = (TextView) findViewById(R.id.view_name);
        phoneView = (TextView) findViewById(R.id.view_phone);
        emailView = (TextView) findViewById(R.id.view_email);
        gradeView = (TextView) findViewById(R.id.view_grade);
        studentView = (TextView) findViewById(R.id.view_student);
        //go back
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editBtn != null)
                {editBtn.setEnabled(false);} //button which u want to disable
                Intent returnIntent = new Intent(getApplicationContext(), MainActivity.class);
                setResult(RESULT_CANCELED, returnIntent);
            }
        });

        //edit member, go to addMember page for now
        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(backBtn != null)
                {backBtn.setEnabled(false);} //button which u want to disable
                Intent intent = new Intent(getApplicationContext(), EditMember.class);
                intent.putExtra("name", nameView.getText().toString());
                intent.putExtra("phone", phoneView.getText().toString());
                intent.putExtra("email", emailView.getText().toString());
                intent.putExtra("studentId", studentView.getText().toString());
                intent.putExtra("grade", gradeView.getText().toString());
                setResult(RESULT_CANCELED, intent);
                startActivityForResult(intent, REQUEST_CODE_EDIT_MEMBER);
            }
        });
    }
}