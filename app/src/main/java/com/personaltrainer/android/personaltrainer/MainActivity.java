package com.personaltrainer.android.personaltrainer;

import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
    private Button loginButton;
    private EditText userName;
    private EditText passWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


       loginButton = findViewById(R.id.login_button);



    loginButton.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            userName = findViewById(R.id.userName);
            passWord = findViewById(R.id.passWord);
            if (userName.getText().toString().equals("jdoe") && passWord.getText().toString().equals("welcome1")) {
                Intent intentLogin = new Intent(MainActivity.this ,
                        Login_Screen.class);

                MainActivity.this.startActivity(intentLogin);
                Log.i("Content "," Login layout ");

               Toast.makeText(MainActivity.this, R.string.loginSuccess,
                        Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(MainActivity.this, R.string.loginFail,
                        Toast.LENGTH_SHORT).show();
            }
        }
    });

    }


}
