package com.blakelassgmail.attendance;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;

import java.util.ArrayList;


public class AddMember extends AppCompatActivity {

    //Array of options --> ArrayAdapter --> ListView

    //ListView :{views, items.xml}

    private static final int REQUEST_CODE = 1;

    private Button addBtn;
    private Button backBtn;
    private EditText name;
    private EditText phone;
    private EditText email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_member);

        addBtn = (Button) findViewById(R.id.uploadMember);
        addBtn.addTextChangedListener(mTextEditorWatcher);
        backBtn = (Button) findViewById(R.id.back);
        name = (EditText) findViewById(R.id.edit_name);
        phone = (EditText) findViewById(R.id.edit_phone);
        email = (EditText) findViewById(R.id.edit_email);

        //go back
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addBtn.setEnabled(false);  //button which u want to disable
                Intent returnIntent = new Intent();
                setResult(RESULT_CANCELED, returnIntent);
                finish();
            }
        });

        //add member button
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backBtn.setEnabled(false);  //button which u want to disable
                Intent returnIntent = new Intent();
                returnIntent.putExtra("name", name.getText());
                //returnIntent.putExtra("phone", phone.getText());
                //returnIntent.putExtra("email", email.getText());
                setResult(RESULT_CANCELED, returnIntent);
                finish();
            }
        });
    }

    private boolean isExternalStorageAvailable() {
        return Environment.MEDIA_MOUNTED.equals
                (Environment.getExternalStorageState());
    }

    private final TextWatcher mTextEditorWatcher = new TextWatcher() {
        public synchronized void afterTextChanged(Editable arg0) {
            enableSubmitIfReady();
        }

        @Override
        public synchronized void beforeTextChanged(CharSequence s, int start, int count, int after) {
            if(AddMember.this.addBtn != null) {
                AddMember.this.addBtn.setEnabled(false);
                AddMember.this.addBtn.setClickable(false);
            }
        }

        @Override
        public synchronized void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        public void enableSubmitIfReady() {
            boolean isReady = name.getText().toString().length() > 1;
            AddMember.this.addBtn.setEnabled(isReady);
            AddMember.this.addBtn.setClickable(isReady);
        }
    };
}

