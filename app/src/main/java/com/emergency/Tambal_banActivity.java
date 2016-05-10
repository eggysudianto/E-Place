package com.emergency;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Tambal_banActivity extends Activity {
    private TimePicker timePicker1;
    private TimePicker timePicker2;
    private Calendar calendar;
    private String format = "";
    private TextView textview1;
    private TextView textview2;
    final String TAG = "DEBUG";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambal_ban);
        //editTextLocation = (EditText) findViewById(R.id.editTextLocation);

        //ambil lokasi dari MainActivity
        Intent myIntent = getIntent(); // gets the previously created intent
        String Latitude = myIntent.getStringExtra("Latitude"); // will return "FirstKeyValue"
        String Longitude= myIntent.getStringExtra("Longitude"); // will return "SecondKeyValue"
        Toast.makeText(this, Latitude, Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onLocationChanged: " + Longitude);

        //editTextLocation.setText(Longitude+", "+Latitude);
        //ambil lokasi dari MainActivity

        //ambil id textview
        textview1 = (TextView) findViewById(R.id.textview1);
        textview2 = (TextView) findViewById(R.id.textview2);


        //time picker
        timePicker1 = (TimePicker) findViewById(R.id.timePicker1);
        timePicker2 = (TimePicker) findViewById(R.id.timePicker2);
        calendar = Calendar.getInstance();

        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int min = calendar.get(Calendar.MINUTE);
        showTime(hour, min);
        //timePicker1.setDescendantFocusability(TimePicker.FOCUSABLES_TOUCH_MODE);

        //time picker


        Button btnSimpan = (Button) findViewById(R.id.btnSimpan);
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //ambil lokasi dari MainActivity
                Intent myIntent = getIntent(); // gets the previously created intent
                String Latitude = myIntent.getStringExtra("Latitude"); // will return "FirstKeyValue"
                String Longitude= myIntent.getStringExtra("Longitude"); // will return "SecondKeyValue"
                //ambil lokasi dari MainActivity
                Log.d(TAG, "nih 2 " + Longitude);

                String jam_buka = String.valueOf(timePicker1.getCurrentHour());
                String menit_buka = String.valueOf(timePicker1.getCurrentMinute());
                String jam_tutup = String.valueOf(timePicker2.getCurrentHour());
                String menit_tutup = String.valueOf(timePicker2.getCurrentMinute());
                insertToDatabase(jam_buka,menit_buka,jam_tutup,menit_tutup,Latitude,Longitude);
            }
        });

    }

    private void insertToDatabase(
            String jam_buka, String menit_buka, String jam_tutup, String menit_tutup, String Latitude, String Longitude){
        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {
                //String paramUsername = params[0];
                //String paramAddress = params[1];

                //ambil lokasi dari MainActivity
                Intent myIntent = getIntent(); // gets the previously created intent
                String Latitude = myIntent.getStringExtra("Latitude"); // will return "FirstKeyValue"
                String Longitude= myIntent.getStringExtra("Longitude"); // will return "SecondKeyValue"
                //Toast.makeText(this, Latitude, Toast.LENGTH_SHORT).show();
                //editTextLocation.setText(Longitude+", "+Latitude);
                //ambil lokasi dari MainActivity
                Log.d(TAG, "nih 3 " + Longitude);


                String jam_buka = String.valueOf(timePicker1.getCurrentHour());
                String menit_buka = String.valueOf(timePicker1.getCurrentMinute());
                String jam_tutup = String.valueOf(timePicker2.getCurrentHour());
                String menit_tutup = String.valueOf(timePicker2.getCurrentMinute());

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("jam_buka", jam_buka));
                nameValuePairs.add(new BasicNameValuePair("menit_buka", menit_buka));
                nameValuePairs.add(new BasicNameValuePair("jam_tutup", jam_tutup));
                nameValuePairs.add(new BasicNameValuePair("menit_tutup", menit_tutup));
                nameValuePairs.add(new BasicNameValuePair("Latitude", Latitude));
                nameValuePairs.add(new BasicNameValuePair("Longitude", Longitude));


                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(
                            "http://192.168.3.50/e_place/android/InputTambalBan.php");
                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                    HttpResponse response = httpClient.execute(httpPost);

                    //HttpEntity entity = response.getEntity();


                } catch (ClientProtocolException e) {

                } catch (IOException e) {

                }
                return "success";
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);

                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
            }
        }
        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute(jam_buka, menit_buka, jam_tutup, menit_tutup, Latitude, Longitude);
    }



    //time picker
    public void setTime(View view) {
        int hour = timePicker1.getCurrentHour();
        int min = timePicker1.getCurrentMinute();
        showTime(hour, min);
    }
    //time picker

    //time picker
    public void showTime(int hour, int min) {
        if (hour == 0) {
            hour += 12;
            format = "AM";
        }
        else if (hour == 12) {
            format = "PM";
        } else if (hour > 12) {
            hour -= 12;
            format = "PM";
        } else {
            format = "AM";
        }
    }
    //time picker
}
