package com.example.hp.week8homework;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<People> list = new ArrayList<>();
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addPeople();
        if (getArrayList()!= null){
            list = getArrayList();
        }
        startPeoplesList();
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveArrayList(list);
    }

    private void startPeoplesList() {
        final RecyclerView recyclerPeoples = (RecyclerView) findViewById(R.id.recyceler_people);
        recyclerPeoples.setHasFixedSize(true);
        recyclerPeoples.setLayoutManager(new LinearLayoutManager(this));
        myAdapter = new MyAdapter(this, list);
        recyclerPeoples.setAdapter(myAdapter);

    }

    public void addPeople() {
        final FloatingActionButton add = findViewById(R.id.fab);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDialog add = new MyDialog();
                add.show(getFragmentManager(), "tag");
            }
        });
    }

    public void addPeople(final String name, final String surname){
        list.add(new People(name, surname));
        myAdapter.notifyDataSetChanged();
    }

    private void saveArrayList(final List<People> list) {
        final SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        final Gson gson = new Gson();
        final String json = gson.toJson(list);
        editor.putString("key", json);
        editor.apply();
    }

    private ArrayList<People> getArrayList() {
        final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        final Gson gson = new Gson();
        final String json = prefs.getString("key", null);
        final Type type = new TypeToken<ArrayList<People>>() {
        }.getType();
        return gson.fromJson(json, type);
    }





}
