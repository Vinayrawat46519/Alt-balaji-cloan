package com.seeksolution.altbalaji;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.seeksolution.altbalaji.Api.RetrofitClient;
import com.seeksolution.altbalaji.Model.LoginResponse;

import org.w3c.dom.Text;

import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginWithEmail extends AppCompatActivity implements View.OnClickListener {

    private static final Pattern EMAIL_REGEX_PATTERN = Pattern.compile("^[a-zA-Z0-9.!#$%&’*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$");
    private static final Pattern PASSWORD_REGEX_PATTERN = Pattern.compile("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$");
    TextInputEditText editTextEmail,editTextPassword;
     AppCompatButton compatButton;
     TextView textView1,textView2;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_with_email);
        getSupportActionBar().hide();
        editTextEmail=(TextInputEditText) findViewById(R.id.Et_Email);
        editTextPassword=(TextInputEditText) findViewById(R.id.et_password);
        compatButton=(AppCompatButton) findViewById(R.id.button_Submit);
        textView1=(TextView)findViewById(R.id.tv_Login_error);
        textView2=(TextView) findViewById(R.id.tv_Login_error2);

        compatButton.setOnClickListener(LoginWithEmail.this);

        //Validation for,


    }

    public void Register_onclick(View view) {
        Intent intent=new Intent(getApplicationContext(),Registration_Page.class);
        startActivity(intent);
        finish();
    }

    public boolean ValidateEmail(String email){
        if(email.isEmpty()){
            textView1.setText("Field is required");
            return false;
        }
    if (!EMAIL_REGEX_PATTERN.matcher(email).matches()){
        textView1.setText("Invalid Email");
        return false;
    }
    textView1.setText("");
    editTextEmail.setBackgroundResource(R.drawable.successerror);
    return true;
    }




    public boolean ValidatePassword(String password){
        if(password.isEmpty()){
            textView2.setText("Field is required");
            return false;
        }
        if (!PASSWORD_REGEX_PATTERN.matcher(password).matches()){
            textView2.setText("Invalid Password Type");
            return false;
        }
        textView2.setText("");
        editTextPassword.setBackgroundResource(R.drawable.successerror);
        return true;
    }

    public void GoogleOnclick(View view) {
        Intent intent =new Intent(getApplicationContext(),Login_With_Google.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onClick(View v) {
        String email=editTextEmail.getText().toString().trim();
        String password=editTextPassword.getText().toString().trim();
        if (ValidateEmail(email) & ValidatePassword(password)) {

        }
            Call<LoginResponse> call = RetrofitClient.getInstance().getAPI().userLogin(email, password);
            call.enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                    if (response.isSuccessful()) {
                        LoginResponse loginResponse = response.body();
                        if (loginResponse.getCode().equals("201") && loginResponse.isStatus() == true) {
                            Toast.makeText(LoginWithEmail.this, "" +loginResponse.getMessage(), Toast.LENGTH_SHORT).show();

                            SharedPreferences sharedPreferences=getSharedPreferences("user_data",MODE_PRIVATE);
                            SharedPreferences.Editor editor=sharedPreferences.edit();
                            editor.putString("user_id",loginResponse.getData().get(0).getId());
                            editor.putString("user_name",loginResponse.getData().get(0).getName());
                            editor.putString("user_email",loginResponse.getData().get(0).getEmail());
                            editor.putString("user_token",loginResponse.getData().get(0).getToken());

                            editor.commit();

                            Intent intent=new Intent(LoginWithEmail.this,MainActivity.class);
                            startActivity(intent);
                            finish();


                        } else {
                            Toast.makeText(LoginWithEmail.this, "" + loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                    Toast.makeText(LoginWithEmail.this, "No internet Or Api issue", Toast.LENGTH_SHORT).show();
                }
            });

        }
    }
