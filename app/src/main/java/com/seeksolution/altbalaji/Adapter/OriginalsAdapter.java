package com.seeksolution.altbalaji.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.seeksolution.altbalaji.Model.OriginalsModel;
import com.seeksolution.altbalaji.R;
import com.seeksolution.altbalaji.VideoMainActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class OriginalsAdapter extends RecyclerView.Adapter<OriginalsAdapter.ViewHolder> {
private Context context;

    public OriginalsAdapter(Context context, ArrayList<OriginalsModel> originalsModels_arr) {
        this.context = context;
        this.originalsModels_arr = originalsModels_arr;
    }

    private ArrayList<OriginalsModel>originalsModels_arr;
    @NonNull
    @Override
    public OriginalsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.custom_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OriginalsAdapter.ViewHolder holder, int position) {
        final int i=position;
      holder.imageView.setVisibility(View.GONE);
      holder.image.setVisibility(View.VISIBLE);
      holder.shimmerFrame.startShimmer();
        Picasso.get().load(Uri.parse(originalsModels_arr.get(i).getImages())).into(holder.imageView);
      new Handler().postDelayed(new Runnable() {
          @Override
          public void run() {
              holder.shimmerFrame.stopShimmer();
              holder.image.setVisibility(View.GONE);
              holder.imageView.setVisibility(View.VISIBLE);
          }
      },4000);


    }

    @Override
    public int getItemCount() {
        return originalsModels_arr.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ShimmerFrameLayout shimmerFrame;
        public ImageView image;
        public ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            shimmerFrame=itemView.findViewById(R.id.trendingList_shimmer);
            image=itemView.findViewById(R.id.image_shimmer);
            imageView=itemView.findViewById(R.id.image_view);
            imageView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent=new Intent(context, VideoMainActivity.class);
            context.startActivity(intent);
        }
    }
}
