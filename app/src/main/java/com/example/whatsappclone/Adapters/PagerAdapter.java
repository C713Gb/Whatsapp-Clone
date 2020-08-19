package com.example.whatsappclone.Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class PagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragmentList = new ArrayList<>();
    private List<String> titleList = new ArrayList<>();

    public PagerAdapter(@NonNull FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);

    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @NonNull
    @Override
    public CharSequence getPageTitle(int position){
        return titleList.get(position);
    }

    public void addFragment(Fragment fragment, String title){
        fragmentList.add(fragment);
        titleList.add(title);
    }
}
