package com.grasslever.florim.a_keni_ligjerata;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.grasslever.florim.a_keni_ligjerata.R;

public class Kati3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Activity activity = this;
        activity.setTitle("Kati 3");
        setContentView(R.layout.activity_kati3);
    }
}
