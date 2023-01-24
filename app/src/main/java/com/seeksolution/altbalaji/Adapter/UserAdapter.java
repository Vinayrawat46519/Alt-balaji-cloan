package com.seeksolution.altbalaji.Adapter;

import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.seeksolution.altbalaji.Model.UserModel;
import com.seeksolution.altbalaji.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    private Context context;
    private ArrayList<UserModel>user_arr;
    public UserAdapter(Context context, ArrayList<UserModel> user_arr) {
        this.context=context;
        this.user_arr=user_arr;
    }

    @NonNull
    @Override
    public UserAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.custom_layout,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.ViewHolder holder, int position) {
        final  int i=position;

        holder.imageView.setVisibility(View.GONE);
        holder.shimmer_image.setVisibility(View.VISIBLE);
        holder.shimmerFrameLayout.startShimmer();
        Picasso.get().load(Uri.parse(user_arr.get(i).getImgurls())).into(holder.imageView);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                holder.shimmerFrameLayout.stopShimmer();
                holder.shimmer_image.setVisibility(View.GONE);
                holder.imageView.setVisibility(View.VISIBLE );
            }
        },4000);



    }

    @Override
    public int getItemCount() {
        return user_arr.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public ShimmerFrameLayout shimmerFrameLayout;
        public ImageView shimmer_image;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.image_view);
            shimmerFrameLayout=itemView.findViewById(R.id.trendingList_shimmer);
            shimmer_image=itemView.findViewById(R.id.image_shimmer);
        }
    }
}
