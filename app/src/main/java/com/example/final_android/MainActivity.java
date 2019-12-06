package com.example.final_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button add;
    EditText editText;
    RecyclerView rv;
    List<String> subredit = new ArrayList<>();
    RecyclerAdapter recyclerAdapter;
    String sub;
//    Context con;
    private String prefFile = "com.example.final_android.userdata";
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        add = findViewById(R.id.add);
        editText = findViewById(R.id.editText);
        rv = findViewById(R.id.rv);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sub = editText.getText().toString();
                if(subredit.contains(sub)){
                    Log.d(TAG, "cannot add");
                }else{
                    subredit.add(sub);
                    recyclerAdapter = new RecyclerAdapter(subredit);
                    rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    rv.setAdapter(recyclerAdapter);
                }
//                saveData(sub);
            }
        });
    }

//    private void saveData(String sub){
//        SharedPreferences sp = getSharedPreferences("prefFile", Context.MODE_PRIVATE);
//        sp.edit().putString("subredit",sub).apply();
//        recyclerAdapter = new RecyclerAdapter(sp.getString("subredit",""));
//        rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//        rv.setAdapter(recyclerAdapter);
//    }
}
