package com.amberleesmith.beautifulbulldog; /**
 * Created by AmberLee on 9/25/2017.
 */
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                BulldogListFragment tab1 = new BulldogListFragment();
                return tab1;
            case 1:
                RankingsFragment tab2 = new RankingsFragment();
                return tab2;
            default:
                return null;
        }
    }

    @Override
    public int getCount() { return mNumOfTabs; }

}
