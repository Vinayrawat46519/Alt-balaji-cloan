package com.seeksolution.altbalaji.Adapter;

import android.content.Context;
import android.net.Uri;
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

public class SliderAdapter extends SliderViewAdapter<SliderAdapter.hold> {

    public Context context;
    public ArrayList<SliderModel> sliderModelArrayList;

    public SliderAdapter(Context context, ArrayList<SliderModel> sliderModelArrayList) {
        this.context = context;
        this.sliderModelArrayList = sliderModelArrayList;
    }

    @Override
    public hold onCreateViewHolder(ViewGroup parent) {
        View view=LayoutInflater.from(context).inflate(R.layout.custom_layout,null);
        return new hold(view);
    }

    @Override
    public void onBindViewHolder( hold viewHolder, int position) {
        Picasso.get().load(sliderModelArrayList.get(position).getSlider_pic()).into(viewHolder.imageView);

    }

    @Override
    public int getCount() {
        return sliderModelArrayList.size();
    }

    public class hold extends SliderViewAdapter.ViewHolder {
        public ImageView imageView;
        public hold(View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.iv_imageSliderView);
        }
    }
}
