package com.amberleesmith.beautifulbulldog;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import io.realm.Realm;

//fixed
public class BulldogActivity extends AppCompatActivity {
    private TextView nameView;
    private Spinner rating;
    private ImageButton bulldogImage;
    private Button voteButton;
    private Realm realm;
    public User owner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bulldog);

        nameView = (TextView) findViewById(R.id.textView);
        rating = (Spinner) findViewById(R.id.spinner);
        bulldogImage = (ImageButton) findViewById(R.id.imageButton);
        voteButton = (Button) findViewById(R.id.voteButton);

        realm = Realm.getDefaultInstance();

        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add("0");
        arrayList.add("1");
        arrayList.add("2");
        arrayList.add("3");
        arrayList.add("4");
        arrayList.add("5");

        String id = (String) getIntent().getStringExtra("bulldog");
        Bulldog bulldog = realm.where(Bulldog.class).equalTo("id", id).findFirst();

        String username = (String) getIntent().getStringExtra("username");
        owner = realm.where(User.class).equalTo("username", username).findFirst();

        if(bulldog.getImage() != null) {
            Bitmap bmp = BitmapFactory.decodeByteArray(bulldog.getImage(), 0, bulldog.getImage().length);
            bulldogImage.setImageBitmap(bmp);
        }
        nameView.setText(bulldog.getName());

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        rating.setAdapter(adapter);

        rating.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }

        });
        voteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                realm.executeTransaction(new Realm.Transaction(){
                    @Override
                    public void execute(Realm realm){
                        Vote vote = new Vote();
                        vote.setOwner(owner);
                        vote.setRating(Integer.valueOf(rating.getSelectedItem().toString()));

                        finish();
                    }
                });
            }
        });
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();

        realm.close();
    }
}