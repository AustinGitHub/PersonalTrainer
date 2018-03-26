package com.personaltrainer.android.personaltrainer;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

import static android.content.ContentValues.TAG;


/**
 * Created by Austin on 3/17/2018.
 */

public class listOfCustomers extends ListActivity implements android.view.View.OnClickListener {

    Button btnAdd,btnGetAll;
    TextView UserID;


    @Override
    public void onClick(View view) {
        if (view == findViewById(R.id.btnAdd)) {

            Intent intent = new Intent(this, newCustomer.class);
            intent.putExtra("UserID", 0);
            startActivity(intent);

        }
    }
     public void show(View view) {
        if(view == findViewById(R.id.btnGetAll)){

            UserRepo repo = new UserRepo(this);

            ArrayList<HashMap<String, String>> userList =  repo.getUserList();
            if(userList.size()!=0) {
                ListView lv = getListView();
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
                        UserID = (TextView) view.findViewById(R.id.UserID);
                        String UserId = UserID.getText().toString();
                        Intent objIndent = new Intent(getApplicationContext(),newCustomer.class);
                        objIndent.putExtra("UserID", Integer.parseInt( UserId ));
                        startActivity(objIndent);
                    }
                });
                ListAdapter adapter = new SimpleAdapter( listOfCustomers.this,userList, R.layout.user_entries, new String[] { "id","name"}, new int[] {R.id.UserID, R.id.name});
                setListAdapter(adapter);
            }else{
                Toast.makeText(this,"No users!",Toast.LENGTH_SHORT).show();
            }

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_of_customers);

        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);

        btnGetAll = (Button) findViewById(R.id.btnGetAll);
        btnGetAll.setOnClickListener(this);

        show(btnGetAll);

    }


}