package com.seeksolution.altbalaji.Adapter;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.seeksolution.altbalaji.Model.SubscriptionModel;
import com.seeksolution.altbalaji.R;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;

public class SubcriptionAdapter extends RecyclerView.Adapter<SubcriptionAdapter.ViewHolder> {
    public Context context;
    public ArrayList<SubscriptionModel> arrayList;
    private RadioButton rb_checked=null;
    private int rb_position=0;

    public SubcriptionAdapter(Context context, ArrayList<SubscriptionModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;

        SharedPreferences sp2=context.getSharedPreferences("user_data",MODE_PRIVATE);
        SharedPreferences.Editor editor=sp2.edit();
        editor.remove("user_Package_id");
        editor.remove("user_Package_Name");
        editor.commit();
    }

    @NonNull
    @Override
    public SubcriptionAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.mycustom_subscription_package,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubcriptionAdapter.ViewHolder holder, int position) {
        final  int i=position;
      holder.textView_Package_name.setText(arrayList.get(position).getPackage_name());
       holder.textView_package_prise.setText(arrayList.get(position).getPackage_price());
       holder.textView_package_duration.setText(arrayList.get(position).getPackage_duration());
       holder.textView_package_discription.setText(arrayList.get(position).getPackage_desc());
        Picasso.get().load(Uri.parse(arrayList.get(position).package_pic)).into(new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                if (i>0){
                    holder.relativeLayout.setBackground(new BitmapDrawable(bitmap));
                }

            }

            @Override
            public void onBitmapFailed(Exception e, Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout relativeLayout;
        RadioButton button;
        TextView textView_Package_name,textView_package_prise,textView_package_duration,textView_package_discription;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView_Package_name=itemView.findViewById(R.id.tv_package_name);
            textView_package_prise=itemView.findViewById(R.id.tv_package_price);
            textView_package_duration=itemView.findViewById(R.id.tv_package_duration);
            textView_package_discription=itemView.findViewById(R.id.tv_package_desc);
            relativeLayout=itemView.findViewById(R.id.rl_package_background);
            button=itemView.findViewById(R.id.rb_package_button);


            if (rb_position == 0 && button.isChecked()){
                rb_checked= button;
                rb_position= 0;
            }
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    RadioButton rb = (RadioButton) v;
                    int clickedPos = getAdapterPosition();
                    if (rb.isChecked()){
                        if (rb_checked !=null){
                            rb_checked.setChecked(false);
                        }
                        rb_checked = rb;
                        rb_position= clickedPos;
                    }else {
                        rb_checked = null;
                        rb_position = 0;
                    }

                   //Toast.makeText(context, ""+arrayList.get(rb_position).getId(), Toast.LENGTH_LONG).show();
                  // Toast.makeText(context, ""+arrayList.get(rb_position).getPackage_name(), Toast.LENGTH_SHORT).show();
                    SharedPreferences sp=context.getSharedPreferences("user_data", MODE_PRIVATE);
                    SharedPreferences.Editor editor= sp.edit();
                    editor.putString("user_Package_id",arrayList.get(rb_position).getId());
                    editor.putString("user_Package_Name",arrayList.get(rb_position).getPackage_name());
                    editor.commit();

                }
            });
        }
    }
}
