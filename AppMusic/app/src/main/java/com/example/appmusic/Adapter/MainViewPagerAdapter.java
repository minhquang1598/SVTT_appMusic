package com.example.appmusic.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class MainViewPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> fragmentArrayList = new ArrayList<>();
    private ArrayList<String> arraytitle = new ArrayList<>();


    public MainViewPagerAdapter( FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentArrayList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentArrayList.size();
    }
    public void addFragment(Fragment fragment,String title){
        fragmentArrayList.add(fragment);
        arraytitle.add(title);
    }

    public CharSequence getPageTitle(int position) {
        return arraytitle.get(position);
    }
}
