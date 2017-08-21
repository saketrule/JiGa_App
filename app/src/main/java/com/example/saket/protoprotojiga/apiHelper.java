package com.example.saket.protoprotojiga;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by saket on 8/13/17.
 */

public class apiHelper {
    String mainUrl;
    RequestQueue queue;
    Context context;
    JSONArray shows;
    RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public apiHelper(Context new_context,RecyclerView listview){
        mainUrl = "http://api.tvmaze.com";
        context = new_context;
        mRecyclerView = listview;
        queue = Volley.newRequestQueue(context);

    }

    public void createRecyclerView(JSONArray jsonshows){
        mLayoutManager = new LinearLayoutManager(context);
        mRecyclerView.setLayoutManager(mLayoutManager);
        final searchResultItem[] items = new searchResultItem[jsonshows.length()];
        for(int i = 0; i< jsonshows.length(); ++i){
            try {
                JSONObject shows = jsonshows
                    .getJSONObject(i)
                    .getJSONObject("show");
                items[i] = new searchResultItem();
                items[i].id = shows
                        .getInt("id");
                items[i].name = shows
                        .getString("name");
                items[i].summary = shows
                        .getString("summary")
                        .replaceAll("<.+?>","");
                items[i].imageurl = shows
                        .getJSONObject("image")
                        .getString("medium");
            } catch (JSONException e) {
                Toast.makeText(context, "Error parsing Data",Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        }
        mAdapter = new MyAdapter(items,context);
        mRecyclerView.setAdapter(mAdapter);
        RecyclerItemClickListener.addTo(mRecyclerView).setOnItemClickListener(
                new RecyclerItemClickListener.OnItemClickListener() {
                    public void onItemClicked(RecyclerView recycleView, int position, View v) {
                        Intent info_page = new Intent(recycleView.getContext(), infopage2.class);
                        info_page.putExtra("show_id",items[position].id);
                        recycleView.getContext().startActivity(info_page);
                    }
                }
        );
    }

    public void getInfo(String query){

        String searchUrl = "http://api.tvmaze.com/search/shows?q=" + query;
        Toast.makeText(context,searchUrl,Toast.LENGTH_SHORT).show();
        StringRequest newrequest = new StringRequest(Request.Method.GET, searchUrl,
                new Response.Listener<String> () {
                    @Override
                    public void onResponse(String response){
                        try {
                            Toast.makeText(context, "Got Response", Toast.LENGTH_SHORT).show();
                            shows = new JSONArray(response);
                            createRecyclerView(shows);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(context,"Error onResponse\n",Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error){
                        Toast.makeText(context,"Error: " + error.toString().substring(0,30),Toast.LENGTH_SHORT).show();
                    }
                });
        queue.add(newrequest);
    }

}
