package com.example.saket.protoprotojiga;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;

public class listactivity extends AppCompatActivity {

    // Recycler View
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    String myDataset[] = {"whoa","hello","mind blown","Brilliant","Holy Moly","ewfw","dvcsc"};
    Bitmap mImageset[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listactivity);

        //  ToolBar Disabled
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Linear Layout Manager
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);


        //Getting mImageset
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        String imageurl = "http://static.tvmaze.com/uploads/images/medium_portrait/101/254529.jpg";
        final ImageView imageView = (ImageView) findViewById(R.id.imageView2);
        mImageset = new Bitmap[7];
        /*
        int pos =0;
        ImageRequest imageRequest = new ImageRequest(imageurl,
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap myimage) {
                        //for(int i=0;i<7;++i)
                        mImageset[pos] = myimage;
                        mRecyclerView.getAdapter().notifyItemChanged(pos);
                        pos++;

                    }
                }, 0, 0, null,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        myDataset[0] = (error.toString());
                    }
                });

        for(int i=0;i<7;++i)
        queue.add(imageRequest);*/

        //Adapter
        //// Creating new Adapter MyAdapter, Create Dataset
        //mAdapter = new MyAdapter(myDataset,mImageset);
        //mRecyclerView.setAdapter(mAdapter);

        //Creating Click Supporter
        //RecyclerItemClickListener ItemClickSupport = new RecyclerItemClickListener(mRecyclerView);
        RecyclerItemClickListener.addTo(mRecyclerView).setOnItemClickListener(
                new RecyclerItemClickListener.OnItemClickListener() {
                    public void onItemClicked(RecyclerView recycleView, int position, View v){
                        Toast.makeText(getApplicationContext(),Integer.toString(position),Toast.LENGTH_SHORT).show();
                    }
                }
        );

        /*
        mRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this,
                new RecyclerItemClickListener.onItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position){
                        Toast.makeText(getApplicationContext(),"Working ! " + Integer.toString(position),Toast.LENGTH_SHORT).show();
                    }
                }));
        */

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
