package com.blakelassgmail.attendance;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Button;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_VIEW_MEMBER = 2;
    private static final int REQUEST_CODE_ADD_MEMBER = 1;
    private Toolbar toolbar;

    private Button button;
    private ListView list;
    private ArrayList<String> nameList;
    private ArrayAdapter<String> adapter;
    private String name = "";
    private String email = "";
    private String phone = "";

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (requestCode) {
            case REQUEST_CODE_ADD_MEMBER:
                if (resultCode == RESULT_OK) {
                    name = data.getStringExtra("name");
                   // email = data.getStringExtra("email");
                    //phone = data.getStringExtra("phone");
                    updateMembers();
                }
                if (resultCode == RESULT_CANCELED) {
                    //Write your code if there's no result
                }
                break;
            case REQUEST_CODE_VIEW_MEMBER:
                if (resultCode == RESULT_OK) {
                    //name = data.getStringExtra("name");
                    // email = data.getStringExtra("email");
                    //phone = data.getStringExtra("phone");
                    finishActivity(REQUEST_CODE_VIEW_MEMBER);
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
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        button = (Button) findViewById(R.id.addBtn);
        nameList = new ArrayList<>();
        nameList.add("Blake");
        nameList.add("Lassiter");
        list = (ListView) findViewById(R.id.memberList);
        displayList();
        updateMembers();

    }

    //setup items from list. if they are clicked,
    //setOnItemClickListener will take content to display member
    private void displayList() {
        adapter = new ArrayAdapter<String>(this, R.layout.content_list_items, R.id.list_content, nameList);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new ItemList());
    }

    private void updateMembers() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddMember.class);
                startActivityForResult(intent, REQUEST_CODE_ADD_MEMBER);
            }
        });
        //check that member was added
        if (nameList.contains(name.toLowerCase())) {
            // <- look for item!
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this)
                    .setTitle("Failed to insert member")
                    .setMessage("Member already exists.")
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // if this button is clicked, just close
                            // the dialog box and do nothing
                            dialog.cancel();
                        }
                    });

            // create alert dialog
            AlertDialog alertDialog = alertDialogBuilder.create();
            //show it
            alertDialog.show();
        } else {
            if(name.length() > 1) {
                nameList.add(name.toLowerCase());
                adapter.add(name);
            }
        }
        adapter.notifyDataSetChanged();
    }
    class ItemList implements AdapterView.OnItemClickListener{

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            ViewGroup viewGroup = (ViewGroup)view;
            TextView textView = (TextView) viewGroup.findViewById(R.id.list_content);
            //Toast.makeText(MainActivity.this, textView.getText().toString(), Toast.LENGTH_SHORT).show();
            //go to display member with name of member
            Intent intent = new Intent(getApplicationContext(), DisplayMember.class);
            intent.putExtra("name", textView.getText().toString());
            setResult(RESULT_CANCELED, intent);
            startActivityForResult(intent, REQUEST_CODE_VIEW_MEMBER);
        }
    }
}

