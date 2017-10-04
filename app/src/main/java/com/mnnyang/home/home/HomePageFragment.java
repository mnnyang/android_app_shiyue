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

import com.mnnyang.R;
import com.mnnyang.app.Constant;
import com.mnnyang.utils.LogUtils;

/**
 * Created by mnnyang on 17-10-3.
 */

public class HomePageFragment extends Fragment {

    private Bitmap mBitmap;
    private int mId;
    private static Typeface mTypeFace;

    public HomePageFragment() {

    }

    public static HomePageFragment newInstance(int id) {

        Bundle args = new Bundle();
        args.putInt(Constant.KEY_PAGE_ID, id);

        HomePageFragment fragment = new HomePageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtils.d(this, "onCreate");

        Bundle arguments = getArguments();
        mId = arguments.getInt(Constant.KEY_PAGE_ID);
        if (false) {

            switch (mId) {
                case 0:
                    mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.img1);
                    break;
                case 2:
                    mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.img1);
                    break;
                case 3:
                    mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.img3);
                    break;
                case 4:
                    mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.img4);
                    break;
                case 5:
                    mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.img5);
                    break;
            }
        }
        if (mTypeFace == null) {
            mTypeFace = Typeface.createFromAsset(getActivity().getAssets(), "fonts/方正中宋简体.ttf");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home_page, container, false);
        ImageView iv = root.findViewById(R.id.iv_page_image);
        TextView tvPoetry = root.findViewById(R.id.tv_poetry);
        TextView tvTitle = root.findViewById(R.id.tv_poetry_title);
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

        iv.setImageResource(R.drawable.img1);
        setHasOptionsMenu(true);
        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}
