package com.example.saket.protoprotojiga;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button butproto1 = (Button) findViewById(R.id.but_proto1);
        Button butlist = (Button) findViewById(R.id.but_listactivity);
        Button butinfo = (Button) findViewById(R.id.butinfo);
        Button butproto2 = (Button) findViewById(R.id.butproto2);


        butproto1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nextpage = new Intent(getApplicationContext(),proto1.class);
                startActivity(nextpage);
            }
        });

        butlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nextpage = new Intent(getApplicationContext(),listactivity.class);
                startActivity(nextpage);
            }
        });

        butinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nextinfopage = new Intent(getApplicationContext(),infopage2.class);
                startActivity(nextinfopage);
            };
        });

        butproto2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nextinfopage = new Intent(getApplicationContext(),proto2.class);
                startActivity(nextinfopage);
            }
        });
    }
}
