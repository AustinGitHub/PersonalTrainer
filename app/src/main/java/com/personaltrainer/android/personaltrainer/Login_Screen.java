package com.personaltrainer.android.personaltrainer;

import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import static android.content.ContentValues.TAG;


public class Login_Screen extends Activity {
    private Button addCustomer;
    private Button editCustomer;
    private Button logOut;

    private static final String TAG = "login screen";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);


        addCustomer = findViewById(R.id.addCustomer);
        editCustomer = findViewById(R.id.EditCustomers);
        logOut = findViewById(R.id.Logout);

        addCustomer.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {


                    Intent intentLogin = new Intent(Login_Screen.this ,
                            newCustomer.class);

                    Login_Screen.this.startActivity(intentLogin);
                    Log.i("Content "," New Customer layout ");



                }

        });
        editCustomer.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {



                Intent intentLogin = new Intent(Login_Screen.this,listOfCustomers.class);

                Login_Screen.this.startActivity(intentLogin);
                Log.d(TAG, "Content Edit Customer layout ");



            }

        });
        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentLogin = new Intent(Login_Screen.this,MainActivity.class);

                Login_Screen.this.startActivity(intentLogin);
                Toast.makeText(Login_Screen.this, R.string.loggingOff,
                        Toast.LENGTH_SHORT).show();
            }
        });


    }



}