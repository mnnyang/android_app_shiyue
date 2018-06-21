package cn.xxyangyoulin.shiyue.main.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * viewpager <br>
 * Created by mnnyang on 17-4-12.
 */

public class PageAdapter extends FragmentPagerAdapter {

    protected List<Fragment> fragments;

    public PageAdapter(FragmentManager manager) {
        super(manager);
    }

    public PageAdapter(FragmentManager manager, List<Fragment> fragments) {
        super(manager);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    public void addFragment(Fragment fragment) {
        if (fragments == null) {
            fragments = new ArrayList<>();
        }
        fragments.add(fragment);
    }
}