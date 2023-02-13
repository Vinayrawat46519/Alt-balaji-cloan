package com.seeksolution.altbalaji.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.seeksolution.altbalaji.Model.ModelRecommended;
import com.seeksolution.altbalaji.R;
import com.seeksolution.altbalaji.VideoMainActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterRecommended extends RecyclerView.Adapter<AdapterRecommended.ViewHolder> {
    private Context context;

    public AdapterRecommended(Context context, ArrayList<ModelRecommended> modelRecommended_arr) {
        this.context = context;
        this.modelRecommended_arr = modelRecommended_arr;
    }

    private ArrayList<ModelRecommended>modelRecommended_arr;

    @NonNull
    @Override
    public AdapterRecommended.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.custom_layout,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterRecommended.ViewHolder holder, int position) {
     final int i=position;
     holder.imageView.setVisibility(View.GONE);
     holder.imageView1.setVisibility(View.VISIBLE);
     holder.shimmerFrameLayout.startShimmer();
        Picasso.get().load(Uri.parse(modelRecommended_arr.get(i).getImages())).into(holder.imageView);
     new Handler().postDelayed(new Runnable() {
         @Override
         public void run() {
             holder.shimmerFrameLayout.stopShimmer();
             holder.imageView1.setVisibility(View.GONE);
             holder.imageView.setVisibility(View.VISIBLE);
         }
     },4000);
    }

    @Override
    public int getItemCount() {
        return modelRecommended_arr.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ShimmerFrameLayout shimmerFrameLayout;
        public ImageView imageView1;
        public ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView1=itemView.findViewById(R.id.image_shimmer);
            shimmerFrameLayout=itemView.findViewById(R.id.trendingList_shimmer);
            imageView=itemView.findViewById(R.id.image_view);
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
           ModelRecommended item= modelRecommended_arr.get(getAdapterPosition());
            Toast.makeText(context, ""+item.getImages(), Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(context, VideoMainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);

        }
    }
}
