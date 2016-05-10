package com.emergency;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by Eggy on 5/3/2016.
 */
public class WC_Activity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wc);

        //ambil lokasi dari MainActivity
        Intent myIntent = getIntent(); // gets the previously created intent
        String Latitude = myIntent.getStringExtra("Latitude"); // will return "FirstKeyValue"
        String Longitude= myIntent.getStringExtra("Longitude"); // will return "SecondKeyValue"
        Toast.makeText(this, Latitude, Toast.LENGTH_SHORT).show();
        //editTextLocation.setText(Longitude+", "+Latitude);
        //ambil lokasi dari MainActivity
    }
}
