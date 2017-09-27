package com.amberleesmith.beautifulbulldog;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;


/**
 * A simple {@link Fragment} subclass.
 */
public class RankingsFragment extends Fragment {
        private ListView bulldogList;


        public RankingsFragment() {
            // Required empty public constructor
        }


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
            View view = inflater.inflate(R.layout.fragment_bulldog_list, container, false);
            bulldogList = (ListView) view.findViewById(R.id.bulldog_list);


            RankingsArrayAdapter adapter = new RankingsArrayAdapter(this.getActivity(), this.getRankings());
            bulldogList.setAdapter(adapter);

            return view;
        }

        @Override
        public void onResume(){
            super.onResume();

            RankingsArrayAdapter adapter = new RankingsArrayAdapter(this.getActivity(), this.getRankings());
            bulldogList.setAdapter(adapter);
        }
        public ArrayList<Bulldog> getRankings(){
            MainActivity mainActivity = (MainActivity) this.getActivity();
            ArrayList<Bulldog> bulldogs = new ArrayList(mainActivity.realm.where(Bulldog.class).findAll());

            Collections.sort(bulldogs, new Comparator<Bulldog>() {
                @Override
                public int compare(Bulldog bulldog, Bulldog bulldog2) {
                    return ((Double) bulldog2.getVotes().average("rating"))
                            .compareTo((Double) bulldog.getVotes().average("rating"));
                }
            });
        return bulldogs;
    }


    }