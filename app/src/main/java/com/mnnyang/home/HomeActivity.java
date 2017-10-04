package com.mnnyang.home;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.mnnyang.BaseActivity;
import com.mnnyang.R;
import com.mnnyang.about.AboutActivity;
import com.mnnyang.account.AccountActivity;
import com.mnnyang.app.Constant;
import com.mnnyang.home.article.ArticleFragment;
import com.mnnyang.home.home.HomeContract;
import com.mnnyang.home.home.HomeFragment;
import com.mnnyang.utils.ActivityUtils;
import com.mnnyang.utils.StatusBarUtil;

import java.util.ArrayList;

public class HomeActivity extends BaseActivity {

    private HomeContract.Presenter mPresenter;
    private DrawerLayout mDrawerLayout;

    private HomeFragment mHomeFragment;
    private ArticleFragment mArticleFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set up the toolbar.
        setToolbar();
        setDrawerLayout();

        StatusBarUtil.setTranslucentForDrawerLayout(this, mDrawerLayout, 0);

        mHomeFragment = new HomeFragment();
        ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                mHomeFragment, R.id.contentFrame);
    }

    private void setDrawerLayout() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerLayout.setStatusBarBackground(R.color.colorPrimaryDark);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView != null) {
            setupDrawerContent(navigationView);
        }
    }

    private void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setTitle("");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // Open the navigation drawer when the home icon is selected from the toolbar.
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupDrawerContent(NavigationView view) {
        view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_menu_home:
                        getSupportActionBar().setTitle("");
                        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(
                                getResources().getColor(android.R.color.transparent)));

                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        if (mArticleFragment != null) {
                            transaction.hide(mArticleFragment);
                        }
                        if (mHomeFragment == null) {
                            mHomeFragment = HomeFragment.newInstance();
                            transaction.add(R.id.contentFrame, mHomeFragment);
                        } else {
                            transaction.show(mHomeFragment);
                        }
                        transaction.commit();
                        break;

                    case R.id.nav_menu_day_article:
                        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(
                                getResources().getColor(R.color.colorPrimary)));

                        FragmentTransaction transaction1 = getSupportFragmentManager().beginTransaction();
                        transaction1.hide(mHomeFragment);
                        if (mArticleFragment == null) {
                            mArticleFragment = ArticleFragment.newInstance();
                            transaction1.add(R.id.contentFrame, mArticleFragment);
                        } else {
                            transaction1.show(mArticleFragment);
                        }
                        transaction1.commit();
                        break;

                    case R.id.nav_menu_gushici:
                        break;

                    case R.id.nav_menu_sswj:
                        break;

                    case R.id.nav_menu_user:
                        Intent accountIntent = new Intent(HomeActivity.this, AccountActivity.class);
                        startActivity(accountIntent);
                        break;
                    case R.id.nav_menu_about:
                        Intent aboutIntent = new Intent(HomeActivity.this, AboutActivity.class);
                        startActivity(aboutIntent);
                        break;
                }
                mDrawerLayout.closeDrawers();
                return true;
            }
        });
    }


}
