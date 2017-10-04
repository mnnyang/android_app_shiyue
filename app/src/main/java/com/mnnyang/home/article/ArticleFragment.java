package com.mnnyang.home.article;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.mnnyang.R;
import com.mnnyang.adapter.HomeAdapter;
import com.mnnyang.utils.ScreenUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mnnyang on 17-10-3.
 */

public class ArticleFragment extends Fragment {

    public static ArticleFragment newInstance() {
        return new ArticleFragment();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_article, container, false);

        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) root.getLayoutParams();
        params.topMargin = ((AppCompatActivity)getActivity()).getSupportActionBar().getHeight();
        root.setLayoutParams(params);
        setViewPager(root);
        setHasOptionsMenu(true);

        ActionBar bar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(getActivity().getResources().getColor(R.color.colorPrimary)));
        return root;
    }

    private void setViewPager(View root) {
        ViewPager viewPager = root.findViewById(R.id.view_pager);
        List<Fragment> fragments = new ArrayList<Fragment>();

        for (int i = 0; i < 5; i++) {
            fragments.add(ArticlePageFragment.newInstance());
        }

        HomeAdapter homeAdapter = new HomeAdapter(getChildFragmentManager(), fragments, null);
        viewPager.setAdapter(homeAdapter);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.article_fragment_menu, menu);
    }
}
