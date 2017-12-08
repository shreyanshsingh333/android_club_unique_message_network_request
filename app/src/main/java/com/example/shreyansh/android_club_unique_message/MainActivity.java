package com.example.shreyansh.android_club_unique_message;


import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Shreyansh on 12/6/2017.
 */

public class MainActivity extends AppCompatActivity {

    EditText mac;
    TextView your_mac,clickToChangeMac;
    String Android_club_server_url,macAdd;
    LinearLayout changeMacView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        mac = findViewById(R.id.mac_display);
        mac.setText("c8:25:e1:20:9c:a2");
        macAdd = mac.getText().toString();

        your_mac = findViewById(R.id.your_mac);
        your_mac.setText(macAdd);

        changeMacView = findViewById(R.id.change_mac_view);
        changeMacView.setVisibility(View.GONE);
        your_mac.setVisibility(View.VISIBLE);

        clickToChangeMac = findViewById(R.id.clickToChangeMac);
        clickToChangeMac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeMacView.setVisibility(View.VISIBLE);
                your_mac.setVisibility(View.GONE);
            }
        });

        Button macChange = findViewById(R.id.mac_change);
        macChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                macAdd=mac.getText().toString();
                your_mac.setText(macAdd);
                changeMacView.setVisibility(View.GONE);
                your_mac.setVisibility(View.VISIBLE);

            }
        });

        Button send = findViewById(R.id.send);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText reg =  findViewById(R.id.reg_no);
                String reg_no = reg.getText().toString();

                Android_club_server_url = "https://android-club-project.herokuapp.com/upload_details?";
                Android_club_server_url += "reg_no=";
                Android_club_server_url += reg_no;
                Android_club_server_url += "&mac=";
                Android_club_server_url += macAdd;

                //Performing HTTP request
                new ClubServerAsyncTask().execute(Android_club_server_url);
            }
        });

    }

    private class ClubServerAsyncTask extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... urls) {
            String result = Utils.fetchMessage(urls[0]);
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            updateUi(result);
        }
    }

    private void updateUi(String result){
        TextView display = findViewById(R.id.display);
        display.setText(result);
    }

}