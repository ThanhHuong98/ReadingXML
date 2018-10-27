package com.example.dell.readingxml;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
    static TextView txtMsg;
    Button btnGoParsePlayers;
    Button btnGoParseCourse;
    static Context context;
    @Override



        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            txtMsg = (TextView) findViewById(R.id.txtMsg);
            btnGoParsePlayers = (Button) findViewById(R.id.btnReadXmlPlayers);
            context = getApplicationContext();
            btnGoParsePlayers.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    btnGoParsePlayers.setEnabled(false);
                    TextView path= (TextView)findViewById(R.layout.golfers);
                    new BackgroundAsyncTask().doInBackground("D:\Code\MOBILE\Android\ReadXMl\app\src\main\res\layout, "Name", "Phone");
                }
            });

        }
}
