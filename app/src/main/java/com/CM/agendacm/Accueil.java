package com.CM.agendacm;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

public class Accueil extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acceuil_content);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                final Intent mainIntent = new Intent(Accueil.this, MainActivity.class);
               Accueil.this.startActivity(mainIntent);
                Accueil.this.finish();
            }
        }, 1001);
    }
}
