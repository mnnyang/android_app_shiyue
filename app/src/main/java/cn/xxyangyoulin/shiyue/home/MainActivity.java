package cn.xxyangyoulin.shiyue.home;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.os.Bundle;
import android.view.MenuItem;

import cn.xxyangyoulin.shiyue.R;
import cn.xxyangyoulin.shiyue.base.BaseActivity;
import cn.xxyangyoulin.shiyue.data.api.PoemsService;
import cn.xxyangyoulin.shiyue.data.bean.Poem;
import cn.xxyangyoulin.shiyue.http.Client;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        initBackToolbar("首页");

        Disposable disposable = Client.getInstance()
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
                });

    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    return true;
                case R.id.navigation_dashboard:
                    return true;
                case R.id.navigation_notifications:
                    return true;
            }
            return false;
        }
    };

    @Override
    public boolean onSupportNavigateUp() {
        return super.onSupportNavigateUp();
    }
}
