package com.seeksolution.altbalaji;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.seeksolution.altbalaji.Adapter.SubcriptionAdapter;
import com.seeksolution.altbalaji.Api.RetrofitClient;
import com.seeksolution.altbalaji.Model.SubscriptionModel;
import com.seeksolution.altbalaji.Model.SubscriptionPackageResponse;
import com.seeksolution.altbalaji.Model.UpdatePackageResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Subscription extends AppCompatActivity implements View.OnClickListener {
    RecyclerView recyclerView;
    public Button button_Proceed;
    public  String intentData_userID,intentData_userName;

    public ArrayList<SubscriptionModel> arrayList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscription);
        recyclerView=(RecyclerView) findViewById(R.id.rc_Subscribe_Package);
        button_Proceed=(Button) findViewById(R.id.subscribe_Button);
        button_Proceed.setOnClickListener(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

           Bundle extras=getIntent().getExtras();
           intentData_userID=extras.getString("user_id",null);
           intentData_userName=extras.getString("user_name",null);

        ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setTitle("Loading...");
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        Call<SubscriptionPackageResponse> packageResponseCall= RetrofitClient.getInstance().getAPI()
                .getSubscriptionPackage();
        packageResponseCall.enqueue(new Callback<SubscriptionPackageResponse>() {
            @Override
            public void onResponse(Call<SubscriptionPackageResponse> call, Response<SubscriptionPackageResponse> response) {
                if (response.isSuccessful()){
                    SubscriptionPackageResponse responseModel=response.body();
                    if (responseModel.getCode().equals("201") && responseModel.isStatus()==true) {


                        int arraySize = responseModel.getData().size();
                        for (int i = 0; i < arraySize; i++) {
                            arrayList.add(new SubscriptionModel(
                                    responseModel.getData().get(i).getId(),
                                    responseModel.getData().get(i).getPackage_name(),
                                    responseModel.getData().get(i).getPackage_price(),
                                    responseModel.getData().get(i).getPackage_duration(),
                                    responseModel.getData().get(i).getPackage_desc(),
                                    responseModel.getData().get(i).getPackage_pic()
                                    ));
                        }

                        SubcriptionAdapter adapter=new SubcriptionAdapter(getApplicationContext(),arrayList);
                        recyclerView.setAdapter(adapter);


                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                progressDialog.dismiss();
                            }
                        },2000);

                    }else {
                        Toast.makeText(Subscription.this, ""+response.message(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<SubscriptionPackageResponse> call, Throwable t) {

            }
        });

    }

    @Override
    public void onClick(View v) {
        //get the Subscribe Package;
        //get SharedPreferences Code;
        SharedPreferences sp=getSharedPreferences("user_data",MODE_PRIVATE);
        String packages_id=sp.getString("user_Package_id",null);
        String packages_name=sp.getString("user_Package_Name",null);

        if (packages_id != null && packages_name != null){
//            Toast.makeText(this, ""+packages_id, Toast.LENGTH_SHORT).show();
//            Toast.makeText(this, ""+packages_name, Toast.LENGTH_SHORT).show();
            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            builder.setTitle("Confirm Proceed");
            builder.setMessage("Hi,"+intentData_userName+"+are going to subscribe now " +packages_name+" Press yes to continue");



            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //intent
//                    Toast.makeText(getApplicationContext(), ""+packages_id, Toast.LENGTH_SHORT).show();
//            Toast.makeText(getApplicationContext(), ""+packages_name, Toast.LENGTH_SHORT).show();

                    subscribeToPackageApi(intentData_userID,packages_id);


                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                   dialog.dismiss();
                }
            });
            AlertDialog dialog=builder.create();
            dialog.show();
        }
        else {
            Toast.makeText(this, "Please Subscribe the Package", Toast.LENGTH_SHORT).show();


        }
        SharedPreferences sp2=getSharedPreferences("user_data",MODE_PRIVATE);
        SharedPreferences.Editor editor=sp2.edit();
        editor.remove("user_Package_id");
        editor.remove("user_Package_Name");
        editor.commit();
    }

    private void subscribeToPackageApi(String user_id, String packages_id) {
        Call<UpdatePackageResponse> call=RetrofitClient.getInstance().getAPI().subscribeToPackage(
                "PUT",
                user_id, packages_id
        );
        call.enqueue(new Callback<UpdatePackageResponse>() {
            @Override
            public void onResponse(Call<UpdatePackageResponse> call, Response<UpdatePackageResponse> response) {
                if (response.isSuccessful()){
                    UpdatePackageResponse packageResponse=response.body();
                    if (packageResponse.isStatus()==true && packageResponse.getCode().equals("201")){

                        Toast.makeText(Subscription.this, ""+packageResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        String package_name=packageResponse.getData().getCurrent_package().getPackage_name();
                        String package_duration=packageResponse.getData().getCurrent_package().getPackage_duration();
                        String package_price=packageResponse.getData().getCurrent_package().getPackage_price();
                        Toast.makeText(Subscription.this, "Amount to be paid"+package_price, Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(getApplicationContext(),PaymentGateway.class);
                        startActivity(intent);
                        finish();

                    }else {
                        Toast.makeText(Subscription.this, ""+packageResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(Subscription.this, ""+response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UpdatePackageResponse> call, Throwable t) {

                Toast.makeText(Subscription.this, "Error in api"+t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("myOutputTag",t.getMessage());//unique KEY to for searching error;

            }
        });

    }
}