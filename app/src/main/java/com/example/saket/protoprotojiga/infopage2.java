package com.example.saket.protoprotojiga;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class infopage2 extends AppCompatActivity {

    Integer seriesId = 1;
    String mainUrl = "http://api.tvmaze.com/shows";
    String seriesName = "", genres = "", status = "";
    Double ratings = 0.0;
    searchResultItem info;// = new searchResultItem();


    ImageView imageView;

    RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infopage2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Get Bundle Information
        seriesId = getIntent().getIntExtra("show_id",-1);
        if(seriesId == -1){
            Toast.makeText(this,"Series Not Found",Toast.LENGTH_SHORT).show();
        }

        //Temporary output View
        imageView = (ImageView) findViewById(R.id.imageViewId);
        queue = Volley.newRequestQueue(getApplicationContext());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        info = new searchResultItem();
        getData();
    }

    protected void getData(){
        StringRequest newrequest = new StringRequest(Request.Method.GET, mainUrl+"/"+seriesId,
                new Response.Listener<String> () {
                    @Override
                    public void onResponse(String response){
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            info.name = jsonResponse.getString("name");
                            JSONArray genresArray =  jsonResponse.getJSONArray("genres");
                            for (int i=0; i<genresArray.length();++i){
                                info.genres = info.genres + genresArray.getString(i);
                                if(i!=genresArray.length()-1)
                                    info.genres = info.genres + ",";
                            }
                            info.imageurl = jsonResponse
                                    .getJSONObject("image")
                                    .getString("medium")
                                    .replace("\\","");

                            info.summary = jsonResponse
                                    .getString("summary")
                                    .replaceAll("<.+?>","");

                            info.rating = jsonResponse
                                    .getJSONObject("rating")
                                    .getDouble("average");
                            info.status = jsonResponse
                                    .getString("status");
                            updateData();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        getImage();

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error){
                        Toast.makeText(getApplicationContext(),"InfoPage2 Response Error",Toast.LENGTH_SHORT).show();
                    }
                });

        queue.add(newrequest);
    }

    protected void getImage(){
        String imageurl = info.imageurl;

        final ImageView imageView = (ImageView) findViewById(R.id.imageViewId);
        ImageRequest imageRequest = new ImageRequest(imageurl,
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap myimage) {
                        imageView.setImageBitmap(myimage);
                    }
                }, 0, 0, null,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                });
        queue.add(imageRequest);
    }

    protected void updateData(){
        ((TextView) findViewById(R.id.nameTextId)).setText(info.name);
        ((TextView) findViewById(R.id.genresTextId)).setText(info.genres);
        ((TextView) findViewById(R.id.summaryTextId)).setText(info.summary);
        ((TextView) findViewById(R.id.statusTextId)).setText("Status : " + info.status);
    }



    public void click_summary(View view){
        Integer vis = findViewById(R.id.summaryTextId).getVisibility();
        if(vis == View.VISIBLE) {
            findViewById(R.id.summaryTextId).setVisibility(View.GONE);
            Integer on_image_id = this.getResources().getIdentifier("button_onoff_indicator_on","drawable",getPackageName());
            ((ImageView) findViewById(R.id.summaryiconId)).setImageResource(on_image_id);
        }
        else {
            findViewById(R.id.summaryTextId).setVisibility(View.VISIBLE);
            Integer off_image_id = this.getResources().getIdentifier("button_onoff_indicator_off", "drawable", getPackageName());
            ((ImageView) findViewById(R.id.summaryiconId)).setImageResource(off_image_id);
        }
    }


}
