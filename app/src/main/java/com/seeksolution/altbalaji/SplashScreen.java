package com.seeksolution.altbalaji;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

public class SplashScreen extends AppCompatActivity {
    private CardView cardView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        getSupportActionBar().hide();
        cardView=(CardView) findViewById(R.id.Card_view);

        Animation move= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.my_animation);
        cardView.startAnimation(move);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(SplashScreen.this,Login_activity.class);
                startActivity(intent);
                finish();
            }
        },3000);


    }
}