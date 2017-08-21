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

public class proto1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proto1);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final TextView outputtext = (TextView) findViewById(R.id.textView);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Getting Data", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();
                outputtext.setText("Hello");
                String url = "http://api.tvmaze.com/search/shows?q=sherlock";
                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

                StringRequest newrequest = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String> () {
                            @Override
                            public void onResponse(String response){
                                try {
                                    String out = "";
                                    for(int i=0;i<3;++i) {
                                        JSONObject jsonResponse = new JSONObject(
                                                new JSONArray(response).get(i).toString());
                                        out = out.concat(jsonResponse.getString("score")+"\n");
                                    }
                                    outputtext.setText(out);
                                    //JSONArray search_list = new JSONArray(response);
                                    //JSON
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                                //outputtext.setText(response.substring(0,200));
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error){
                                outputtext.setText("Error: " + error.toString());
                                //outputtext.setText("Did not Work");
                            }
                        });
                String imageurl = "http://static.tvmaze.com/uploads/images/medium_portrait/101/254529.jpg";
                final ImageView imageView = (ImageView) findViewById(R.id.imageView2);
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
                                outputtext.setText(error.toString());
                            }
                        });

                queue.add(newrequest);
                //queue.add(imageRequest);
            }
        });
    }

}
