package com.seeksolution.altbalaji;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.seeksolution.altbalaji.Adapter.OriginalsAdapter;

import com.seeksolution.altbalaji.Adapter.SliderAdapter;
import com.seeksolution.altbalaji.Api.RetrofitClient;
import com.seeksolution.altbalaji.Model.OriginalsModel;
import com.seeksolution.altbalaji.Model.OriginalsModelRespoce;
import com.seeksolution.altbalaji.Model.SliderModel;
import com.seeksolution.altbalaji.Model.SliderModelResponse;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView ,recyclerView1,recyclerView2,recyclerView3,recyclerView4,recyclerView5;
    SliderView sliderView;

       ArrayList<SliderModel>sliderModelArrayList=new ArrayList<SliderModel>();
       ArrayList<OriginalsModel>arrayList=new ArrayList<>();
       ArrayList<OriginalsModel>CSS;
       ArrayList<OriginalsModel>Holly_would,HindiSong,Html,HindiMovie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        recyclerView=(RecyclerView) findViewById(R.id.rc_view_1);
        recyclerView1=(RecyclerView) findViewById(R.id.rc_view_2);
        recyclerView2=(RecyclerView) findViewById(R.id.rc_view_3);
        recyclerView3=(RecyclerView) findViewById(R.id.rc_view_4);
        recyclerView4=(RecyclerView) findViewById(R.id.rc_view_5);
        recyclerView5=(RecyclerView) findViewById(R.id.rc_view_6);

        sliderView= (SliderView) findViewById(R.id.ImageSlider);



        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false));
     recyclerView1.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false));
        recyclerView2.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false));
       recyclerView3.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false));
       recyclerView4.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false));
      recyclerView5.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false));


//first cartoon response

        Call<OriginalsModelRespoce> call=RetrofitClient.getInstance().getAPI().getVedios();
     call.enqueue(new Callback<OriginalsModelRespoce>() {
         @Override
         public void onResponse(Call<OriginalsModelRespoce> call, Response<OriginalsModelRespoce> response) {
             if (response.isSuccessful()){
                 OriginalsModelRespoce modelRespoce=response.body();
                 int arrayRa=modelRespoce.getData().size();
                 for (int i=0;i<arrayRa;i++){
                     if (modelRespoce.getData().get(i).getCategory().equals("cartoon")) {
                         arrayList.add(new OriginalsModel(
                                 modelRespoce.getData().get(i).getId(),
                                 modelRespoce.getData().get(i).getVedio_url(),
                                 modelRespoce.getData().get(i).getVedio_banner(),
                                 modelRespoce.getData().get(i).getVedio_description(),
                                 modelRespoce.getData().get(i).getYear(),
                                 modelRespoce.getData().get(i).getRating(),
                                 modelRespoce.getData().get(i).getCategory()


                         ));
                     }
                 }
                 OriginalsAdapter adapter=new  OriginalsAdapter(getApplicationContext(),arrayList);
                 recyclerView.setAdapter(adapter);

             }
         }

         @Override
         public void onFailure(Call<OriginalsModelRespoce> call, Throwable t) {

         }
     });

     //second css response
        CSS=new ArrayList<>();

        Call<OriginalsModelRespoce> css=RetrofitClient.getInstance().getAPI().getVedios();
        css.enqueue(new Callback<OriginalsModelRespoce>() {
            @Override
            public void onResponse(Call<OriginalsModelRespoce> call, Response<OriginalsModelRespoce> response) {
                if (response.isSuccessful()){
                    OriginalsModelRespoce modelRespoce=response.body();
                    int arrayRa=modelRespoce.getData().size();
                    for (int i=0;i<arrayRa;i++){
                        if (modelRespoce.getData().get(i).getCategory().equals("CSS")) {
                            CSS.add(new OriginalsModel(
                                    modelRespoce.getData().get(i).getId(),
                                    modelRespoce.getData().get(i).getVedio_url(),
                                    modelRespoce.getData().get(i).getVedio_banner(),
                                    modelRespoce.getData().get(i).getVedio_description(),
                                    modelRespoce.getData().get(i).getYear(),
                                    modelRespoce.getData().get(i).getRating(),
                                    modelRespoce.getData().get(i).getCategory()


                            ));
                        }
                    }
                    OriginalsAdapter adapter=new  OriginalsAdapter(getApplicationContext(),CSS);
                    recyclerView1.setAdapter(adapter);

                }
            }

            @Override
            public void onFailure(Call<OriginalsModelRespoce> call, Throwable t) {

            }
        });

        //third Holly wood
        Holly_would=new ArrayList<>();

        Call<OriginalsModelRespoce> Holly=RetrofitClient.getInstance().getAPI().getVedios();
        Holly.enqueue(new Callback<OriginalsModelRespoce>() {
            @Override
            public void onResponse(Call<OriginalsModelRespoce> call, Response<OriginalsModelRespoce> response) {
                if (response.isSuccessful()){
                    OriginalsModelRespoce modelRespoce=response.body();
                    int arrayRa=modelRespoce.getData().size();
                    for (int i=0;i<arrayRa;i++){
                        if (modelRespoce.getData().get(i).getCategory().equals("hollywood-movies")) {
                            Holly_would.add(new OriginalsModel(
                                    modelRespoce.getData().get(i).getId(),
                                    modelRespoce.getData().get(i).getVedio_url(),
                                    modelRespoce.getData().get(i).getVedio_banner(),
                                    modelRespoce.getData().get(i).getVedio_description(),
                                    modelRespoce.getData().get(i).getYear(),
                                    modelRespoce.getData().get(i).getRating(),
                                    modelRespoce.getData().get(i).getCategory()


                            ));
                        }
                    }
                    OriginalsAdapter adapter=new  OriginalsAdapter(getApplicationContext(),Holly_would);
                    recyclerView2.setAdapter(adapter);

                }
            }

            @Override
            public void onFailure(Call<OriginalsModelRespoce> call, Throwable t) {

            }
        });

        //Forth HindiSongs;
        HindiSong=new ArrayList<>();
        Call<OriginalsModelRespoce> Hindi=RetrofitClient.getInstance().getAPI().getVedios();
        Hindi.enqueue(new Callback<OriginalsModelRespoce>() {
            @Override
            public void onResponse(Call<OriginalsModelRespoce> call, Response<OriginalsModelRespoce> response) {
                if (response.isSuccessful()){
                    OriginalsModelRespoce modelRespoce=response.body();
                    int arrayRa=modelRespoce.getData().size();
                    for (int i=0;i<arrayRa;i++){
                        if (modelRespoce.getData().get(i).getCategory().equals("Hindi-song")) {
                            HindiSong.add(new OriginalsModel(
                                    modelRespoce.getData().get(i).getId(),
                                    modelRespoce.getData().get(i).getVedio_url(),
                                    modelRespoce.getData().get(i).getVedio_banner(),
                                    modelRespoce.getData().get(i).getVedio_description(),
                                    modelRespoce.getData().get(i).getYear(),
                                    modelRespoce.getData().get(i).getRating(),
                                    modelRespoce.getData().get(i).getCategory()


                            ));
                        }
                    }
                    OriginalsAdapter adapter=new  OriginalsAdapter(getApplicationContext(),HindiSong);
                    recyclerView3.setAdapter(adapter);

                }
            }

            @Override
            public void onFailure(Call<OriginalsModelRespoce> call, Throwable t) {

            }
        });

        //Fifth Html video;
        Html=new ArrayList<>();
        Call<OriginalsModelRespoce> HTML=RetrofitClient.getInstance().getAPI().getVedios();
        HTML.enqueue(new Callback<OriginalsModelRespoce>() {
            @Override
            public void onResponse(Call<OriginalsModelRespoce> call, Response<OriginalsModelRespoce> response) {
                if (response.isSuccessful()){
                    OriginalsModelRespoce modelRespoce=response.body();
                    int arrayRa=modelRespoce.getData().size();
                    for (int i=0;i<arrayRa;i++){
                        if (modelRespoce.getData().get(i).getCategory().equals("Html-videos")) {
                            Html.add(new OriginalsModel(
                                    modelRespoce.getData().get(i).getId(),
                                    modelRespoce.getData().get(i).getVedio_url(),
                                    modelRespoce.getData().get(i).getVedio_banner(),
                                    modelRespoce.getData().get(i).getVedio_description(),
                                    modelRespoce.getData().get(i).getYear(),
                                    modelRespoce.getData().get(i).getRating(),
                                    modelRespoce.getData().get(i).getCategory()


                            ));
                        }
                    }
                    OriginalsAdapter adapter=new  OriginalsAdapter(getApplicationContext(),Html);
                    recyclerView4.setAdapter(adapter);

                }
            }

            @Override
            public void onFailure(Call<OriginalsModelRespoce> call, Throwable t) {

            }
        });

        //Six ArrayList ;
        HindiMovie=new ArrayList<>();

        Call<OriginalsModelRespoce> HindiMovies=RetrofitClient.getInstance().getAPI().getVedios();
        HindiMovies.enqueue(new Callback<OriginalsModelRespoce>() {
            @Override
            public void onResponse(Call<OriginalsModelRespoce> call, Response<OriginalsModelRespoce> response) {
                if (response.isSuccessful()){
                    OriginalsModelRespoce modelRespoce=response.body();
                    int arrayRa=modelRespoce.getData().size();
                    for (int i=0;i<arrayRa;i++){
                        if (modelRespoce.getData().get(i).getCategory().equals("hindi-movie")) {
                            HindiMovie.add(new OriginalsModel(
                                    modelRespoce.getData().get(i).getId(),
                                    modelRespoce.getData().get(i).getVedio_url(),
                                    modelRespoce.getData().get(i).getVedio_banner(),
                                    modelRespoce.getData().get(i).getVedio_description(),
                                    modelRespoce.getData().get(i).getYear(),
                                    modelRespoce.getData().get(i).getRating(),
                                    modelRespoce.getData().get(i).getCategory()


                            ));
                        }
                    }
                    OriginalsAdapter adapter=new  OriginalsAdapter(getApplicationContext(),HindiMovie);
                    recyclerView5.setAdapter(adapter);

                }
            }

            @Override
            public void onFailure(Call<OriginalsModelRespoce> call, Throwable t) {

            }
        });

     Call<SliderModelResponse> sliders=RetrofitClient.getInstance().getAPI().getSliders();
     sliders.enqueue(new Callback<SliderModelResponse>() {
         @Override
         public void onResponse(Call<SliderModelResponse> call, Response<SliderModelResponse> response) {
             if (response.isSuccessful()){
                 SliderModelResponse sliderModelResponse=response.body();
                 int arraySize=sliderModelResponse.getData().size();
                 for (int i=0;i<arraySize;i++){
                     sliderModelArrayList.add(new SliderModel(
                             sliderModelResponse.getData().get(i).getId(),
                             sliderModelResponse.getData().get(i).getSlider_name(),
                             sliderModelResponse.getData().get(i).getSlider_pic()
                     ));
                 }
                 SliderAdapter sliderAdapter=new SliderAdapter(MainActivity.this,sliderModelArrayList);
                 sliderView.setSliderAdapter(sliderAdapter);
                 sliderView.startAutoCycle();
                 sliderView.setScrollTimeInSec(2);
             }
         }

         @Override
         public void onFailure(Call<SliderModelResponse> call, Throwable t) {

         }
     });

    }
}