package com.seeksolution.altbalaji;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class DashBoard extends AppCompatActivity {
    TextView textViewEmail,textViewLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        textViewEmail=(TextView) findViewById(R.id.textView_Email1);
        textViewLogout=(TextView) findViewById(R.id.textView_Logout);


        SharedPreferences sharedPreferences=getSharedPreferences("user_data",MODE_PRIVATE);
        String user_id=sharedPreferences.getString("user_id",null);
        String user_email=sharedPreferences.getString("user_email",null);


        if (user_email!=null && user_id!=null){
            textViewEmail.setText(user_email);
        }



        textViewLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sp=getSharedPreferences("user_data",MODE_PRIVATE);
                SharedPreferences.Editor editor=sp.edit();
                editor.clear();
                editor.commit();
                Toast.makeText(DashBoard.this, "Log Out Successful", Toast.LENGTH_SHORT).show();

                Intent intent=new Intent(DashBoard.this,Login_activity.class);
                startActivity(intent);
               finish();

            }
        });
    }
}