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
import com.seeksolution.altbalaji.DashBoard;
import com.seeksolution.altbalaji.Model.OriginalsModel;
import com.seeksolution.altbalaji.R;
import com.seeksolution.altbalaji.VideoPlayer;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class OriginalsAdapter extends RecyclerView.Adapter<OriginalsAdapter.viewHolder> {

    private Context context;
    private ArrayList<OriginalsModel>modelArrayList;

    public OriginalsAdapter(Context context, ArrayList<OriginalsModel> modelArrayList) {
        this.context = context;
        this.modelArrayList = modelArrayList;
    }

    @NonNull
    @Override
    public OriginalsAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(context).inflate(R.layout.mycustomlayout,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OriginalsAdapter.viewHolder holder, int position) {
       holder.imageView.setVisibility(View.INVISIBLE);
       holder.imageViewShimmer.setVisibility(View.VISIBLE);
       holder.shimmerFrameLayout.startShimmer();
           Picasso.get().load(Uri.parse(modelArrayList.get(position).getVedio_banner())).into(holder.imageView);

       new Handler().postDelayed(new Runnable() {
           @Override
           public void run() {
               holder.shimmerFrameLayout.stopShimmer();
               holder.imageViewShimmer.setVisibility(View.INVISIBLE);
               holder.imageView.setVisibility(View.VISIBLE);
           }
       },2000);

    }

    @Override
    public int getItemCount() {
        return modelArrayList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView imageView;
        public ImageView imageViewShimmer;
        public ShimmerFrameLayout shimmerFrameLayout;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.Image_view);
            shimmerFrameLayout=itemView.findViewById(R.id.trendingList_shimmer);
            imageViewShimmer=itemView.findViewById(R.id.image_shimmer);
            imageView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position=getAdapterPosition();
         OriginalsModel item=modelArrayList.get(position);
           //Toast.makeText(context, ""+item.getVedio_url(), Toast.LENGTH_SHORT).show();
           Intent intent=new Intent(context, VideoPlayer.class);
          intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("vedio_url",item.getVedio_url());
            intent.putExtra("vedio_category",item.getCategory());
            intent.putExtra("vedio_year",item.getYear());
          intent.putExtra("video_rating",item.getRating());
            intent.putExtra("vedio_desc",item.getVedio_description());
           context.startActivity(intent);

        }
    }
}
