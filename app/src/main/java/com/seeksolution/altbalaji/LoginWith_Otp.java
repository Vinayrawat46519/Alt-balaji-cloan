package com.seeksolution.altbalaji;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class LoginWith_Otp extends AppCompatActivity {

    private AppCompatButton btn_Verify,btn_Proceed,btn_Resend;
    private EditText et_Mobile,et_Otp;
    private FirebaseAuth mAuth;
     private String  VerificationCode;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_with_otp);
        btn_Proceed=(AppCompatButton) findViewById(R.id.otp_btn_proceed);
        btn_Verify=(AppCompatButton) findViewById(R.id.otp_btn_verify);
        btn_Resend=(AppCompatButton) findViewById(R.id.otp_btn_Resend);
        et_Mobile=(EditText) findViewById(R.id.otp_et_mobile);
        et_Otp=(EditText) findViewById(R.id.otp_et_otp);

        mAuth=FirebaseAuth.getInstance();

        et_Otp.setVisibility(View.INVISIBLE);
        btn_Resend.setVisibility(View.INVISIBLE);
        btn_Verify.setVisibility(View.INVISIBLE);

        btn_Proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone=et_Mobile.getText().toString().trim();
                  sendVerification("+91"+phone);
            }
        });

        btn_Verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btn_Resend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void sendVerification(String phoneNumber) {
        PhoneAuthOptions phoneAuthOptions=PhoneAuthOptions
                .newBuilder(mAuth)
                .setPhoneNumber(phoneNumber)
                .setTimeout(60L, TimeUnit.SECONDS)
                .setActivity(this)
                .setCallbacks(mCallback).build();
        PhoneAuthProvider.verifyPhoneNumber(phoneAuthOptions);
    }
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallback=new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
            String OTP=phoneAuthCredential.getSmsCode();
            Toast.makeText(LoginWith_Otp.this, ""+OTP, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {

        }

        @Override
        public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            VerificationCode = s;
        }
    };
}