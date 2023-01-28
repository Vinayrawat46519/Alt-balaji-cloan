package com.seeksolution.altbalaji.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.seeksolution.altbalaji.Model.SliderModel;
import com.seeksolution.altbalaji.R;
import com.smarteist.autoimageslider.SliderViewAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.PrimitiveIterator;

public class SliderAdapter extends SliderViewAdapter<SliderAdapter.MyView>{

    private  Context context;
    private ArrayList<SliderModel>arrayList;

    public SliderAdapter(Context context, ArrayList<SliderModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public SliderAdapter.MyView onCreateViewHolder(ViewGroup parent) {
        View view=LayoutInflater.from(context).inflate(R.layout.layout_custom,parent,false);
        return new MyView(view);
    }

    @Override
    public void onBindViewHolder(MyView viewHolder, int position) {
        Picasso.get().load(arrayList.get(position).getImageUrl3()).into(viewHolder.imageView);

    }



    @Override
    public int getCount() {
        return arrayList.size();
    }

    public class MyView extends ViewHolder {
        public ImageView imageView;
        public MyView(View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.iv_slider);
        }

    }
}
