package com.mnnyang.home.article;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mnnyang.R;
import com.mnnyang.utils.LogUtils;

/**
 * Created by mnnyang on 17-10-3.
 */

public class ArticlePageFragment extends Fragment {

    public static ArticlePageFragment newInstance() {
        
        Bundle args = new Bundle();
        
        ArticlePageFragment fragment = new ArticlePageFragment();
        fragment.setArguments(args);

        System.out.println("a p fragment");
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_article_page, container, false);

        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("2017/10/03");
        return root;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        LogUtils.d(this,"setUserVisibleHint("+isVisibleToUser+")");
    }
}
