package com.mnnyang.home.home;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mnnyang.R;
import com.mnnyang.app.Constant;
import com.mnnyang.data.bean.DayPoetry;
import com.mnnyang.data.source.DayPoetryDataSource;
import com.mnnyang.data.source.DayPoetryRepository;
import com.mnnyang.data.source.Injection;
import com.mnnyang.utils.LogUtils;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

/**
 * Created by mnnyang on 17-10-3.
 */

public class HomePageFragment extends Fragment {

    private static Typeface mTypeFace;
    private Calendar mDate;

    public HomePageFragment() {

    }

    public static HomePageFragment newInstance(Calendar date) {

        Bundle args = new Bundle();
        args.putSerializable(Constant.KEY_DATE, date);

        HomePageFragment fragment = new HomePageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtils.d(this, "onCreate");

        mDate = (Calendar) getArguments().getSerializable(Constant.KEY_DATE);

        if (mTypeFace == null) {
            mTypeFace = Typeface.createFromAsset(getActivity().getAssets(), "fonts/方正中宋简体.ttf");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home_page, container, false);
        final ImageView ivPageImage = root.findViewById(R.id.iv_page_image);
        final TextView tvPoetry = root.findViewById(R.id.tv_poetry);
        final TextView tvTitle = root.findViewById(R.id.tv_poetry_title);
        TextView tvNongli = root.findViewById(R.id.tv_nongli);
        TextView tvGongli = root.findViewById(R.id.tv_gongli);


        final ImageView ivCollect = root.findViewById(R.id.iv_collect);
        final ImageView ivPraise = root.findViewById(R.id.iv_praise);

        ivCollect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivCollect.setSelected(!ivCollect.isSelected());
            }
        });

        ivPraise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivPraise.setSelected(!ivPraise.isSelected());
            }
        });

        tvPoetry.setTypeface(mTypeFace);
        tvTitle.setTypeface(mTypeFace);
        tvNongli.setTypeface(mTypeFace);
        tvGongli.setTypeface(mTypeFace);
//        ivPageImage.setImageResource(R.drawable.img1);

        Injection.provideDayPoetryRepository()
                .getDayPoetry(mDate, new DayPoetryDataSource.GetTaskCallback() {
                    @Override
                    public void onLoaded(DayPoetry dayPoetry) {
                        if (dayPoetry.getCode() == 200) {
                            List<DayPoetry.DataBean> data = dayPoetry.getData();
                            tvTitle.setText(data.get(0).getTitle());
                            tvPoetry.setText(data.get(0).getContent());
                            Glide.with(getContext())
                                    .load(data.get(0).getImg())
                                    .into(ivPageImage);
                        }
                    }

                    @Override
                    public void onFail(Exception e) {
                        e.printStackTrace();
                    }
                });
        setHasOptionsMenu(true);
        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}
