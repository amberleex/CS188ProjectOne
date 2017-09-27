package com.amberleesmith.beautifulbulldog;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by AmberLee on 9/18/2017.
 */

class BulldogListActivity extends AppCompatActivity{
    private ListView bulldogList;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bulldog_list);

        ArrayList<Bulldog> bulldogs = new ArrayList<Bulldog>();
        bulldogList = (ListView) findViewById(R.id.bulldog_list);

        Bulldog bulldog1 = new Bulldog();
        bulldog1.setAge("9");
        bulldog1.setName("Porterhouse");

        Bulldog bulldog2 = new Bulldog();
        bulldog2.setAge("2");
        bulldog2.setName("Drake");

        bulldogs.add(bulldog1);
        bulldogs.add(bulldog2);

        final BulldogArrayAdapter adapter = new BulldogArrayAdapter(this, bulldogs);
        bulldogList.setAdapter(adapter);

        bulldogList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final Bulldog bulldog = (Bulldog) adapterView.getItemAtPosition(i);
                Intent intent = new Intent(view.getContext(), BulldogActivity.class);
                intent.putExtra("bulldog", (Serializable) bulldog);
                startActivity(intent);
            }
        });
    }
}
