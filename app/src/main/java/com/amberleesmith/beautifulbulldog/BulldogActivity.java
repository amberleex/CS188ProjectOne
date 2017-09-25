package com.amberleesmith.beautifulbulldog;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import io.realm.Realm;
import io.realm.SyncUser;

public class BulldogActivity extends AppCompatActivity {
    private TextView textView;
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bulldog);

        textView = (TextView) findViewById(R.id.textView);
        realm = Realm.getDefaultInstance();

        String id = (String) getIntent().getStringExtra("bulldog");
        Bulldog bulldog = realm.where(Bulldog.class).equalTo("id", id).findFirst();
        textView.setText(bulldog.getName());

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