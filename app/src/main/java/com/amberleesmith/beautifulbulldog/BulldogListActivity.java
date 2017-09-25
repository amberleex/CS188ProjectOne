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

import io.realm.Realm;
import io.realm.SyncUser;

/**
 * Created by AmberLee on 9/18/2017.
 */

class BulldogListActivity extends AppCompatActivity{
    private ListView bulldogList;
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bulldog_list);

        bulldogList = (ListView) findViewById(R.id.bulldog_list);
        realm = Realm.getDefaultInstance();

        BulldogArrayAdapter adapter = new BulldogArrayAdapter(this, realm.where(Bulldog.class).findAll());
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

        if(SyncUser.currentUser() != null) {
            SyncUser.currentUser().logout();

            Realm realm = Realm.getDefaultInstance();
            if(realm != null) {
                realm.close();
                Realm.deleteRealm(realm.getConfiguration());
            }
        }

    }
    @Override
    protected void onDestroy(){
        super.onDestroy();

        realm.close();
    }
}
