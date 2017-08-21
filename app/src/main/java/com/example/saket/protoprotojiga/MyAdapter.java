package com.example.saket.protoprotojiga;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;

import org.w3c.dom.Text;

/**
 * Created by saket on 8/12/17.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private String[] mDataset;
    private String[] mimageset;
    private searchResultItem[] items;
    Context context;

    //QUEUE
    RequestQueue queue;

    /*
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;
        public ViewHolder(TextView v) {
            super(v);
            mTextView = v;
        }
    }
    */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;
        public ImageView mImageView;
        public TextView mSummaryView;
        public ViewHolder(LinearLayout v) {
            super(v);
            //mTextView = v.findViewById(R.id.txt);
            mSummaryView = v.findViewById(R.id.summaryViewId);
            mTextView = v.findViewById(R.id.textView3);
            mImageView = v.findViewById(R.id.imageView);

        }
    }

    public MyAdapter(searchResultItem[] results, Context orig_context){
        //mimageset = images_url;
        //mDataset = myDataset;
        items = results;
        context = orig_context;
        queue = Volley.newRequestQueue(context);
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        LinearLayout v1 = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.mygroupview,parent,false);
        //TextView v = (TextView) LayoutInflater.from(parent.getContext())
         //       .inflate(R.layout.my_text_view, parent, false);
        ViewHolder vh = new ViewHolder(v1);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.mTextView.setText(items[position].name);
        holder.mSummaryView.setText(items[position].summary);
        // FOR IMAGES
        String imageurl = items[position].imageurl;
        //String imageurl = "http://static.tvmaze.com/uploads/images/medium_portrait/101/254529.jpg";

        ImageRequest imageRequest = new ImageRequest(imageurl,
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap myimage) {
                        holder.mImageView.setImageBitmap(myimage);
                    }
                }, 0, 0, null,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context,"Image Retrieval Error",Toast.LENGTH_SHORT).show();
                    }
                });

        queue.add(imageRequest);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return items.length;
    }

}
