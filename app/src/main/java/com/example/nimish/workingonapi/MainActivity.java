package com.example.nimish.workingonapi;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import Tabs.SlidingTabLayout;


public class MainActivity extends AppCompatActivity {
    private Toolbar toolBar;
    private ViewPager mPager;
    private SlidingTabLayout mTabs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolBar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolBar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        mTabs = (SlidingTabLayout) findViewById(R.id.tabs);
       // mTabs.setDistributeEvenly(true);
        mTabs.setCustomTabView(R.layout.custom_tab_view,R.id.tabText);
        mTabs.setSelectedIndicatorColors(getResources().getColor(R.color.white));
        mTabs.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        mTabs.setViewPager(mPager);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    class MyPagerAdapter extends FragmentStatePagerAdapter {

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch(position){
                case 0 : return new HomeFragment();
                case 1 : return new SearchFragment();
            }
            return null;
        }

        @Override
        public CharSequence getPageTitle(int position) {
         String Tab[] = new String[3];
            Tab[0] = new String("TAB1");
            Tab[1] = new String("TAB2");
            Tab[2] = new String("TAB3");
            return Tab[position%3];
        }

        @Override
        public int getCount() {
            return 2;
        }
    }

    public static class MyFragment extends Fragment{
        public static MyFragment getInstance(int position){
            MyFragment myFragment = new MyFragment();
            return myFragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
            View layout = inflater.inflate(R.layout.fragment_my,container,false);
            return layout;
        }
    }
}
