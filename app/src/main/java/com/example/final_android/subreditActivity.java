package com.example.final_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class subreditActivity extends AppCompatActivity {

    OkHttpClient client = new OkHttpClient();
    private String url = "https://reddit.com/r/";
    private static final String TAG = "subreditActivity";
    String title;
    List<String> titles = new ArrayList<>();
    RecyclerAdapter2 recyclerAdapter2;
    LinearLayoutManager linearLayoutManager;
    RecyclerView rv2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subredit);

        Intent intent = getIntent();
        if(intent!=null){
//            Log.d(TAG, "onCreate: "+"here");
            String subredit = intent.getStringExtra("subredit");
            callRedit(subredit);
        }
    }


    private void callRedit(String subredit){
        rv2 = findViewById(R.id.rv2);

        final Request request = new Request.Builder()
                .url(url+subredit+"/.json")
                .addHeader("Content-Type","application/json")
                .addHeader("User-Agent", "OkHttp")
                .build();

//        Log.d(TAG, "callRedit: "+url+subredit);

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.d("Error in failure is ->", "error"+e.getMessage());
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull final Response response) throws IOException {

                if(!response.isSuccessful()){
                    Log.d("response code ->", ""+response.code());
                    return;
                }
//                Log.d(TAG, "onResponse: here");
//                Log.d(TAG, "onResponse: "+response.body().string());
//                Log.d(TAG, "onResponse: "+response.code());
                runOnUiThread(new Runnable() {
                    @NonNull
                    @Override
                    public void run() {
//                String responseData = response.body().string();
//                Log.d(TAG, ""+responseData);
                        try {
                            JSONObject urlObj = new JSONObject(response.body().string());
//                            Log.d(TAG, ""+urlObj);
                            JSONArray urlObjArray = urlObj.getJSONObject("data").getJSONArray("children");
                            for(int i=0;i<urlObjArray.length(); i++){
//                                title = urlObjArray.getJSONObject(i).getJSONObject("data").getString("title");
//                                Log.d(TAG, "onResponse: "+urlObjArray.getJSONObject(i).getJSONObject("data").getString("title"));
//                                titles.add(title);
                                titles.add(urlObjArray.getJSONObject(i).getJSONObject("data").getString("title"));
//                                Log.d("titlesList:", ""+titles.get(i));
                            }

                            recyclerAdapter2 = new RecyclerAdapter2(titles);

                            rv2.setAdapter(recyclerAdapter2);
//                            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(subreditActivity.this, DividerItemDecoration.VERTICAL);
                            rv2.addItemDecoration(new DividerItemDecoration(rv2.getContext(), DividerItemDecoration.VERTICAL));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });

            }
        });
    }

}
