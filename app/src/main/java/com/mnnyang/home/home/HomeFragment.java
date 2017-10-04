package com.mnnyang.home.home;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mnnyang.R;
import com.mnnyang.adapter.HomeAdapter;
import com.mnnyang.utils.LogUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by mnnyang on 17-10-2.
 */

public class HomeFragment extends Fragment {

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LogUtils.d(this, "onCreateView");
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        setViewPager(root);
        //使fragment可以操作toolbar
        setHasOptionsMenu(true);

        ActionBar bar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(getActivity().getResources().getColor(android.R.color.transparent)));
        return root;
    }

    private void setViewPager(View root) {
        ViewPager viewPager = root.findViewById(R.id.view_pager);
        List<Fragment> fragments = new ArrayList<Fragment>();

        LogUtils.v(this, "setViewPager");
        for (int i = 0; i < 5; i++) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(2017,10,3+i);
            fragments.add(HomePageFragment.newInstance(calendar));
        }

        HomeAdapter homeAdapter = new HomeAdapter(getChildFragmentManager(), fragments, null);
        viewPager.setAdapter(homeAdapter);
    }
}
