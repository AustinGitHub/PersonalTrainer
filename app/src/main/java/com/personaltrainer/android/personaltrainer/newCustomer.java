package com.personaltrainer.android.personaltrainer;

import android.app.Activity;
import android.content.Intent;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;
import android.widget.ListView;
/**
 * Created by Austin on 3/17/2018.
 */

public class newCustomer extends Activity implements android.view.View.OnClickListener {


    FrameLayout frameLayout;
    Button btnSave, btnDelete;
    Button btnCancel;
    EditText Firstname;
    EditText Lastname;
    EditText EditPhonenumber;
    EditText City;
    EditText State;
    Camera camera;
    CameraView showCamera;


    private int UserID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_customer);

        // Code is commented for AVD usage
//        frameLayout = findViewById(R.id.frameLayout1);
//
//        camera = Camera.open();
//        showCamera = new CameraView(this, camera);
//        frameLayout.addView(showCamera);

        btnSave = findViewById(R.id.btnSave);
        btnDelete = findViewById(R.id.btnDelete);
        btnCancel = findViewById(R.id.cancel);

        Firstname = findViewById(R.id.name);
        Lastname = findViewById(R.id.lname);
        EditPhonenumber = findViewById(R.id.Phonenumber);
        City = findViewById(R.id.City);
        State = findViewById(R.id.State);

        btnSave.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnCancel.setOnClickListener(this);

        UserID = 0;
        Intent intent = getIntent();
        UserID = intent.getIntExtra("UserID", 0);
        UserRepo repo = new UserRepo(this);
        UserInfo user = new UserInfo();
        user = repo.getUserById(UserID);

        Firstname.setText(user.name);
        Lastname.setText(user.lname);
        EditPhonenumber.setText(String.valueOf(user.Phonenumber));
        City.setText(user.City);
        State.setText(user.State);

    }
    // Code is commented for AVD usage
//
//    Camera.PictureCallback mPictureCallback = new Camera.PictureCallback() {
//        @Override
//        public void onPictureTaken(byte[] data, Camera camera) {
//
//        }
//    };
//
//    public void captureImage(View v) {
//        if (camera != null) {
//            camera.takePicture(null, null, mPictureCallback);
//        }
//    }




    public void onClick(View view) {
        if (view == findViewById(R.id.btnSave)){
            UserRepo repo = new UserRepo(this);
            UserInfo user = new UserInfo();
            user.lname=Lastname.getText().toString();
            user.name=Firstname.getText().toString();
            user.Phonenumber=Integer.parseInt(EditPhonenumber.getText().toString());
            user.City=City.getText().toString();
            user.State=State.getText().toString();
            user.user_ID=UserID;



            if (UserID==0){
               UserID = repo.insert(user);

                Toast.makeText(this,"User Insert",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this,Login_Screen.class);
                startActivity(intent);
            }else{
                btnSave.setText("Update");
                repo.update(user);

                Toast.makeText(this,"User Record updated",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this,listOfCustomers.class);
                startActivity(intent);
            }
        }else if (view== findViewById(R.id.btnDelete)){
            UserRepo repo = new UserRepo(this);
            repo.delete(UserID);
            Toast.makeText(this, "User Record Deleted", Toast.LENGTH_SHORT);
            Intent intent = new Intent(this,listOfCustomers.class);
            startActivity(intent);

        } else if(view == findViewById(R.id.cancel)) {
            Intent intent = new Intent(this,Login_Screen.class);
            startActivity(intent);
        }


    }


}

