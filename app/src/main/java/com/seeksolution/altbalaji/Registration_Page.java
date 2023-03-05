package com.seeksolution.altbalaji;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.seeksolution.altbalaji.Api.RetrofitClient;
import com.seeksolution.altbalaji.Model.CreateUserResponce;

import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Registration_Page extends AppCompatActivity implements View.OnClickListener {
      EditText editTextName ,editTextEmail,editTextPassword,editTextMobile;
      TextView textViewName,textViewEmail,textViewPassword,textViewMobile;
      AppCompatButton button;

    private static final Pattern EMAIL_REGEX_PATTERN = Pattern.compile("^[a-zA-Z0-9.!#$%&’*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$");
    private static final Pattern PASSWORD_REGEX_PATTERN = Pattern.compile("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$");
    private static final Pattern NAME_PATTERN = Pattern.compile("^([A-Za-z]{2,}\\s[A-Za-z]{1,}'?-?[a-zA-Z]{2,}\\s?([a-zA-Z]{1,})?)");
    private static final Pattern MOBILE_PATTERN = Pattern.compile(("^(?:(?:\\+|0{0,2})91(\\s*|[\\-])?|[0]?)?([6789]\\d{2}([ -]?)\\d{3}([ -]?)\\d{4})$"));


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_page);
        getSupportActionBar().hide();
        editTextName=(EditText) findViewById(R.id.et_register_name);
        editTextEmail=(EditText) findViewById(R.id.et_register_email);
        editTextPassword=(EditText) findViewById(R.id.et_register_password);
        editTextMobile=(EditText) findViewById(R.id.et_register_mobile);
        textViewName=(TextView) findViewById(R.id.textViewRegister1);
        textViewEmail=(TextView) findViewById(R.id.textViewRegister2);
        textViewPassword=(TextView) findViewById(R.id.textViewRegister3);
        textViewMobile=(TextView) findViewById(R.id.textViewRegister4);
        button=(AppCompatButton) findViewById(R.id.RegisterButton);


        button.setOnClickListener(this);

    }

    public boolean ValidateName(String name){
        if (name.isEmpty()){
        textViewName.setText("Field is required");

        return false;
        }
        if (!NAME_PATTERN.matcher(name).matches()){
            textViewName.setText("Invalid Name ");
            return false;
        }
        textViewName.setText("");
        editTextName.setBackgroundResource(R.drawable.successerror);
        return true;
    }
    public boolean ValidateEmail(String email){
        if (email.isEmpty()){
            textViewEmail.setText("Field is required");
            return false;
        }
        if (!EMAIL_REGEX_PATTERN.matcher(email).matches()){
            textViewEmail.setText("Invalid Email ");
            return false;
        }
        textViewEmail.setText("");
        editTextEmail.setBackgroundResource(R.drawable.successerror);
        return true;
    }
    public boolean ValidatePassword(String password){
        if (password.isEmpty()){
            textViewPassword.setText("Field is required");
            return false;
        }
        if (!PASSWORD_REGEX_PATTERN.matcher(password).matches()){
            textViewPassword.setText("Invalid Password type ");
            return false;
        }
        textViewPassword.setText("");
        editTextPassword.setBackgroundResource(R.drawable.successerror);
        return true;
    }
    public boolean ValidateMobile(String mobile){
        if (mobile.isEmpty()){
            textViewMobile.setText("Field is required");
            return false;
        }
        if (!MOBILE_PATTERN.matcher(mobile).matches()){
            textViewMobile.setText("Invalid Type ");
            return false;
        }
        textViewMobile.setText("");
        editTextMobile.setBackgroundResource(R.drawable.successerror);
        return true;
    }

    @Override
    public void onClick(View v) {
        String name = editTextName.getText().toString();
        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();
        String mobile = editTextMobile.getText().toString();
        if (ValidateName(name) & ValidateEmail(email) & ValidatePassword(password) & ValidateMobile(mobile)) {
            Toast.makeText(this, "Register Successful", Toast.LENGTH_SHORT).show();

        }
            Call<CreateUserResponce> call = RetrofitClient
                    .getInstance().getAPI()
                    .createUser(
                            name,
                            email,
                            password,
                            mobile
                    );
            call.enqueue(new Callback<CreateUserResponce>() {
                @Override
                public void onResponse(Call<CreateUserResponce> call, Response<CreateUserResponce> response) {
                    if (response.isSuccessful()) {
                        CreateUserResponce userResponce = response.body();
                        if (userResponce.getCode().equals("201") && userResponce.isStatus() == true) {
                            Toast.makeText(Registration_Page.this, "" + userResponce.getMessage(), Toast.LENGTH_SHORT).show();

                            String newUserName = userResponce.getData().get(0).getName();
                            String newUserId = userResponce.getData().get(0).getId();


//                        SharedPreferences sp=getSharedPreferences("user_data",MODE_PRIVATE);
                            Intent intent = new Intent(getApplicationContext(), Subscription.class);
                            intent.putExtra("user_id", newUserId);
                            intent.putExtra("user_name", newUserName);
                            startActivity(intent);
                            finish();


                        } else {
                            Toast.makeText(Registration_Page.this, "" + userResponce.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }

                @Override
                public void onFailure(Call<CreateUserResponce> call, Throwable t) {
                    Toast.makeText(Registration_Page.this, "No internet or Api issue", Toast.LENGTH_SHORT).show();
                }
            });

        }

}