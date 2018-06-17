package br.com.unibratec.unibratecheros.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();
    private boolean onlyIcons;

    public ViewPagerAdapter(FragmentManager manager) {
        super(manager);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }


    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    public void addFragment(Fragment fragment, String title) {
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (isOnlyIcons())
            return null;
        return mFragmentTitleList.get(position);
    }

    public CharSequence getTitle(int position) {
        return mFragmentTitleList.get(position);
    }


    public boolean isOnlyIcons() {
        return onlyIcons;
    }

    public void setOnlyIcons(boolean onlyIcons) {
        this.onlyIcons = onlyIcons;
    }
}

