package cn.xxyangyoulin.shiyue.main;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

import cn.xxyangyoulin.shiyue.R;
import cn.xxyangyoulin.shiyue.base.BackHandlerHelper;
import cn.xxyangyoulin.shiyue.base.BaseActivity;
import cn.xxyangyoulin.shiyue.discovery.DiscoveryFragment;
import cn.xxyangyoulin.shiyue.home.HomeFragment;
import cn.xxyangyoulin.shiyue.main.adapter.HomeAdapter;

public class MainActivity extends BaseActivity {

    ViewPager mViewPager;

    private HomeAdapter mHomeAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        initViewPager();
    }

    private void initViewPager() {
        mViewPager = findViewById(R.id.view_pager);
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(MainFragment.newInstance());
        fragments.add(DiscoveryFragment.newInstance());
        fragments.add(HomeFragment.newInstance());
        mHomeAdapter = new HomeAdapter(getSupportFragmentManager(), fragments);
        mViewPager.setAdapter(mHomeAdapter);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_main:
                    mViewPager.setCurrentItem(0, false);
                    return true;

                case R.id.navigation_discovery:
                    mViewPager.setCurrentItem(1, false);
                    return true;

                case R.id.navigation_home:
                    mViewPager.setCurrentItem(2, false);
                    return true;
            }
            return false;
        }
    };

    @Override
    public void onBackPressed() {
        if (!BackHandlerHelper.handleBackPress(this)) {
            super.onBackPressed();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onSupportNavigateUp() {
        return super.onSupportNavigateUp();
    }
}

/*Disposable disposable = Client.getInstance()
                .create(PoemsService.class)
                .getPoemByTitle("西江月", 1, 1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Poem>() {
                    @Override
                    public void accept(Poem poem) throws Exception {
                        System.out.println(poem);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                    }
                });*/
