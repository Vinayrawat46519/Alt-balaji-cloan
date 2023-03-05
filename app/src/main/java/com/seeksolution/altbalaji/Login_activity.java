package com.seeksolution.altbalaji;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Login_activity extends AppCompatActivity implements View.OnClickListener {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        textView=(TextView) findViewById(R.id.textLoginView);
        textView.setOnClickListener(this);

        SharedPreferences sharedPreferences=getSharedPreferences("user_data",MODE_PRIVATE);
        String user_id=sharedPreferences.getString("user_id",null);
        String user_email=sharedPreferences.getString("user_email",null);


        if (user_email!=null && user_id!=null){
            Intent intent=new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public void loginButton(View view) {
        Intent intent=new Intent(getApplicationContext(),LoginWithEmail.class);
          startActivity(intent);

    }

    public void OtpButton(View view) {
        Intent intent=new Intent(getApplicationContext(),LoginWith_Otp.class);
        startActivity(intent);

    }

    @Override
    public void onClick(View v) {
        Intent intent=new Intent(getApplicationContext(),Registration_Page.class);
        startActivity(intent);

    }
}