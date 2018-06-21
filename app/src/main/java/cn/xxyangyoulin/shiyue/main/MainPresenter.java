package cn.xxyangyoulin.shiyue.main;

import java.util.List;

import cn.xxyangyoulin.shiyue.app.Cache;
import cn.xxyangyoulin.shiyue.data.api.PoemsService;
import cn.xxyangyoulin.shiyue.data.bean.DailyWrapper;
import cn.xxyangyoulin.shiyue.data.bean.PoemWrapper;
import cn.xxyangyoulin.shiyue.data.http.Client;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class MainPresenter implements MainContracts.MainPresenter {
    public static int LOAD_NORMAL_COUNT = 10;
    MainContracts.MainView mView;

    public MainPresenter(MainContracts.MainView view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void start() {
        load(LOAD_NORMAL_COUNT);
    }

    private boolean loading;

    @Override
    public void load(int count) {
        if (loading) return;
        loading = true;

        Disposable disposable = Client.getInstance()
                .create(PoemsService.class)
                .getDailyPoems(1, LOAD_NORMAL_COUNT)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<DailyWrapper, List<DailyWrapper.Daily>>() {
                    @Override
                    public List<DailyWrapper.Daily> apply(DailyWrapper dailyWrapper) throws Exception {
                        return dailyWrapper.getData();
                    }
                }).subscribe(new Consumer<List<DailyWrapper.Daily>>() {
                    @Override
                    public void accept(List<DailyWrapper.Daily> dailies) throws Exception {
                        loading = false;
                        Cache.newInstance().getDailyList().addAll(dailies);

                        if (mView.isActive()) {
                            mView.loadSucceed();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        loading = false;
                        if (mView.isActive()) {
                            mView.refreshFailed();
                            mView.showErrorMsg("加载失败");
                        }
                    }
                });
    }

    private boolean refreshing;

    @Override
    public void swipeRefresh() {
        final List<DailyWrapper.Daily> cacheDailyList = Cache.newInstance().getDailyList();

        if (cacheDailyList.isEmpty()) {
            load(10);
            return;
        }

        if (refreshing) return;
        refreshing = true;

        String ltime = cacheDailyList.get(0).getDailytime();

        Disposable disposable = Client.getInstance()
                .create(PoemsService.class)
                .getDailyPoems(ltime)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<DailyWrapper, List<DailyWrapper.Daily>>() {
                    @Override
                    public List<DailyWrapper.Daily> apply(DailyWrapper dailyWrapper) throws Exception {
                        return dailyWrapper.getData();
                    }
                }).subscribe(new Consumer<List<DailyWrapper.Daily>>() {
                    @Override
                    public void accept(List<DailyWrapper.Daily> dailies) throws Exception {
                        refreshing = false;

                        if (dailies != null && !dailies.isEmpty()) {
                            cacheDailyList.addAll(dailies);

                            for (int i = dailies.size() - 1; i >= dailies.size(); i--) {
                                cacheDailyList.set(i, cacheDailyList.get(i - dailies.size()));
                            }

                            for (int i = 0; i < dailies.size(); i++) {
                                cacheDailyList.add(i, dailies.get(i));
                            }

                            if (mView.isActive()) {
                                mView.refreshSucceed(true);
                            }
                        } else {
                            if (mView.isActive()) {
                                mView.refreshSucceed(false);
                            }
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                        refreshing = false;

                        if (mView.isActive()) {
                            mView.refreshFailed();
                            mView.showErrorMsg("刷新失败");
                        }
                    }
                });
    }

    private boolean pullLoading;

    @Override
    public void pullLoad() {
        final List<DailyWrapper.Daily> cacheDailyList = Cache.newInstance().getDailyList();

        if (cacheDailyList.isEmpty()) {
            load(10);
            return;
        }

        if (pullLoading) return;
        pullLoading = true;

        mView.showLoadingMore();

        String ltime = cacheDailyList.get(cacheDailyList.size() - 1).getDailytime();

        Disposable disposable = Client.getInstance()
                .create(PoemsService.class)
                .getMoreDaily(ltime)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<DailyWrapper, List<DailyWrapper.Daily>>() {
                    @Override
                    public List<DailyWrapper.Daily> apply(DailyWrapper dailyWrapper) throws Exception {
                        return dailyWrapper.getData();
                    }
                }).subscribe(new Consumer<List<DailyWrapper.Daily>>() {
                    @Override
                    public void accept(List<DailyWrapper.Daily> dailies) throws Exception {
                        pullLoading = false;

                        if (dailies != null && !dailies.isEmpty()) {
                            cacheDailyList.addAll(dailies);

                            if (mView.isActive()) {
                                mView.pullLoadSucceed(true);
                            }
                        } else {
                            if (mView.isActive()) {
                                mView.pullLoadSucceed(false);
                            }
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                        pullLoading = false;

                        if (mView.isActive()) {
                            mView.pullLoadFailed();
                            mView.showErrorMsg("加载失败");
                        }
                    }
                });
    }
}
