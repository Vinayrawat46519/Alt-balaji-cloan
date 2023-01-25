package com.seeksolution.altbalaji;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.seeksolution.altbalaji.Adapter.AdapterRecommended;
import com.seeksolution.altbalaji.Adapter.OriginalsAdapter;
import com.seeksolution.altbalaji.Adapter.UserAdapter;
import com.seeksolution.altbalaji.Model.ModelRecommended;
import com.seeksolution.altbalaji.Model.News;
import com.seeksolution.altbalaji.Model.OriginalsModel;
import com.seeksolution.altbalaji.Model.UserModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView ;

    private String[] ImageUrl={
            "https://img1.hotstarext.com/image/upload/f_auto,t_web_vl_1_5x/sources/r1/cms/prod/928/1360928-v-b808273e5b54",
            "https://img1.hotstarext.com/image/upload/f_auto,t_web_vl_1_5x/sources/r1/cms/prod/375/1350375-v-108376acc65b",
            "https://img1.hotstarext.com/image/upload/f_auto,t_web_vl_1_5x/sources/r1/cms/prod/372/1420372-v-c4dc9b7e307f",
            "https://img1.hotstarext.com/image/upload/f_auto,t_web_vl_1_5x/sources/r1/cms/prod/1158/1451158-v-60ae0c6f2c82",
            "https://img1.hotstarext.com/image/upload/f_auto,t_web_vl_1_5x/sources/r1/cms/prod/1248/1431248-v-af62a54a6d8d",
    };
    private String[] ImageUrl1={
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTkh130rCdNUDItzNzRLhkBFQNKywSX6zLXWw&usqp=CAU",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSwiB2eqPZjIw_1DZg5SpjkUoxkaIxlsSVNQQ&usqp=CAU",
            "https://cdn.cloud.altbalaji.com/thumbnails/2022-08/1659878083038244100_73.jpg",
            "https://img1.hotstarext.com/image/upload/f_auto,t_web_vl_1_5x/sources/r1/cms/prod/1327/441327-v",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSwiB2eqPZjIw_1DZg5SpjkUoxkaIxlsSVNQQ&usqp=CAU",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSwiB2eqPZjIw_1DZg5SpjkUoxkaIxlsSVNQQ&usqp=CAU",
    };

    private String[] ImageUrl2={
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS9Rp1CkE8tioekkh264aGyaBhkTe_Vgi0eXVOgW1oIaxkTP5N1o7ZKFNEnyuHr26kVWeg&usqp=CAU",
      "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSw-JivuG45O0D0FjZE9DFEzQpsCiemBK_KBC5vFRxQTQyu3BoeIq_pUdPxv2LhHbr8qps&usqp=CAU",
      "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTUpQN0iyjum76SE8gixrWHxpsqtg5oJly26CUMioixoIjq4X4ccYzVqwh7YDeCPIUbI3o&usqp=CAU",
      "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ8VLbeYocqPX72gw-YxktcArJUCkz8qs9Jgw&usqp=CAU",
      "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRGhQwbADvuw7twVauT7TP-bSOkfhE9MLzgtw&usqp=CAU",
      "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSG1y8I2AM1iyyWb67VffJgV65f_Hkz7hoojg&usqp=CAU",
    };
    private String[] ImageUrl3={
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ8VLbeYocqPX72gw-YxktcArJUCkz8qs9Jgw&usqp=CAU",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRGhQwbADvuw7twVauT7TP-bSOkfhE9MLzgtw&usqp=CAU",
//            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSG1y8I2AM1iyyWb67VffJgV65f_Hkz7hoojg&usqp=CAU",
//            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS9Rp1CkE8tioekkh264aGyaBhkTe_Vgi0eXVOgW1oIaxkTP5N1o7ZKFNEnyuHr26kVWeg&usqp=CAU",
//            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSw-JivuG45O0D0FjZE9DFEzQpsCiemBK_KBC5vFRxQTQyu3BoeIq_pUdPxv2LhHbr8qps&usqp=CAU",
//            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTUpQN0iyjum76SE8gixrWHxpsqtg5oJly26CUMioixoIjq4X4ccYzVqwh7YDeCPIUbI3o&usqp=CAU",

    };
    private RecyclerView recyclerView1,recyclerView2,recyclerView3,recyclerView4,recyclerView5,recyclerView6,recyclerView7,recyclerView8;
    //First Array List

    ArrayList<UserModel>user_arr=new ArrayList<UserModel>();
    //second Array List start
    ArrayList<OriginalsModel>originalsModels_arr=new ArrayList<OriginalsModel>();
    //Third ArrayList Start;
    ArrayList<ModelRecommended>modelRecommended_arr=new ArrayList<ModelRecommended>();
    //fourth array list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        recyclerView=(RecyclerView) findViewById(R.id.rc_view_1);
        recyclerView1=(RecyclerView)findViewById(R.id.rc_view_2);
        recyclerView2=(RecyclerView)findViewById(R.id.rc_view_3);
        recyclerView3=(RecyclerView)findViewById(R.id.rc_view_4);
        recyclerView4=(RecyclerView)findViewById(R.id.rc_view_5);
        recyclerView5=(RecyclerView)findViewById(R.id.rc_view_6);
        recyclerView6=(RecyclerView)findViewById(R.id.rc_view_7);
        recyclerView7=(RecyclerView)findViewById(R.id.rc_view_8);
        recyclerView8=(RecyclerView)findViewById(R.id.rc_view_9);

//First Array data
     for (int i=0;i<ImageUrl.length;i++){
         user_arr.add(new UserModel(ImageUrl[i]));
     }
  //second Array data
       for (int i=0;i<ImageUrl1.length;i++){
           originalsModels_arr.add(new OriginalsModel(ImageUrl1[i]));
       }
   //Third Array data;
       for (int i=0;i<ImageUrl2.length;i++){
           modelRecommended_arr.add(new ModelRecommended(ImageUrl2[i]));
       }
       //fourth Array data;
        for (int i=0;i<ImageUrl3.length;i++){
            modelRecommended_arr.add(new ModelRecommended(ImageUrl3[i]));
        }

        //First Adapter;

        UserAdapter user=new UserAdapter(getApplicationContext(),user_arr);
        //Second Adapter;
        OriginalsAdapter originalsAdapter=new OriginalsAdapter(getApplicationContext(),originalsModels_arr);
        //third Adapter Start;
        AdapterRecommended adapterRecommended=new AdapterRecommended(getApplicationContext(),modelRecommended_arr);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false));
        recyclerView.setAdapter(user);
        recyclerView1.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false));
        recyclerView1.setAdapter(originalsAdapter);
        recyclerView2.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false));
        recyclerView2.setAdapter(adapterRecommended);
        recyclerView3.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false));
        recyclerView3.setAdapter(originalsAdapter);
        recyclerView4.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false));
        recyclerView4.setAdapter(user);
        recyclerView5.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false));
        recyclerView5.setAdapter(adapterRecommended);
        recyclerView6.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false));
        recyclerView6.setAdapter(originalsAdapter);
        recyclerView7.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false));
        recyclerView7.setAdapter(adapterRecommended);
        recyclerView8.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false));
        recyclerView8.setAdapter(user);
    }
}