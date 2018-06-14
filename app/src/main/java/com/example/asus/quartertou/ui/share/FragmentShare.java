package com.example.asus.quartertou.ui.share;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.asus.quartertou.R;

import java.util.ArrayList;

/**
 * Created by asus on 2018/6/4.
 */

public class FragmentShare extends Fragment {

    private TabLayout mTb;
    private ViewPager mVp;
    private MyVpAdapter myPagerAdapter;
    private ArrayList<String> tabIndicators ;
    private ArrayList<Fragment> tabFragments;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.share, container, false);
        initView(view);
        initContent();
        return view;
    }

    private void initContent(){
        tabIndicators =new ArrayList<>();
        tabIndicators.add("推荐");
        tabIndicators.add("关注");
        tabFragments = new ArrayList<>();
        tabFragments.add(new MyFragment());
        tabFragments.add(new MyFragment2());
        myPagerAdapter = new MyVpAdapter(getChildFragmentManager());
        mVp.setAdapter(myPagerAdapter);
        mTb.setupWithViewPager(mVp);
    }
    class MyVpAdapter extends FragmentPagerAdapter {

        public MyVpAdapter(FragmentManager fm) {
            super(fm);
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return tabIndicators.get(position);
        }

        @Override
        public Fragment getItem(int position) {
            return tabFragments.get(position);
        }

        @Override
        public int getCount() {
            return tabIndicators.size();
        }
    }

    public void initView(View view) {
        mTb = (TabLayout) view.findViewById(R.id.mTb);
        mVp = (ViewPager) view.findViewById(R.id.mVp);
    }

}
