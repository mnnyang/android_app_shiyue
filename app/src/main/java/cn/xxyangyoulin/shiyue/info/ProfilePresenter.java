package cn.xxyangyoulin.shiyue.info;

import java.io.File;
import java.util.HashMap;

import cn.xxyangyoulin.shiyue.UpdateUserEvent;
import cn.xxyangyoulin.shiyue.data.api.UserInfoService;
import cn.xxyangyoulin.shiyue.data.bean.BaseBean;
import cn.xxyangyoulin.shiyue.data.bean.UserWrapper;
import cn.xxyangyoulin.shiyue.data.http.Client;
import de.greenrobot.event.EventBus;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class ProfilePresenter implements ProfileContracts.ProfilePresenter {

    private ProfileContracts.ProfileView mView;
    private Disposable mDisposable;

    public ProfilePresenter(ProfileContracts.ProfileView view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void start() {
        mDisposable = Client.getInstance()
                .create(UserInfoService.class)
                .getUserInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<UserWrapper>() {
                    @Override
                    public void accept(UserWrapper userWrapper) throws Exception {
                        if (mView.isActive()) {
                            if (userWrapper != null) {
                                if (userWrapper.getCode() == 1 && userWrapper.getData() != null) {

                                    mView.fillDefault(userWrapper.getData());
                                }
                            } else {
                                mView.showErrorInfo("加载失败！");
                            }
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (mView.isActive()) {
                            mView.showErrorInfo("加载失败！");
                        }
                    }
                });
    }

    @Override
    public void stop() {
        if (mDisposable != null && !mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
    }

    @Override
    public void uploadAvator(File avatorFile) {

        if (avatorFile == null) {
            mView.showErrorInfo("文件为空");
            return;
        }
        final RequestBody requestFile =
                RequestBody.create(MediaType.parse("multipart/form-data"), avatorFile);
        MultipartBody.Part avator = MultipartBody.Part.createFormData("avator", avatorFile.getName(), requestFile);

        mView.showErrorInfo("上传图片中...");
        mDisposable = Client.getInstance()
                .create(UserInfoService.class)
                .updateUserInfo(1, avator)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BaseBean>() {
                    @Override
                    public void accept(BaseBean baseBean) throws Exception {
                        if (mView.isActive()) {
                            mView.hideProgress();
                            if (baseBean.getCode() == 1) {
                                mView.uploadAvatorSucceed();
                                EventBus.getDefault().post(new UpdateUserEvent());
                            } else {
                                mView.showErrorInfo(baseBean.getMsg());
                            }
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (mView.isActive()) {
                            mView.hideProgress();
                            mView.showErrorInfo("加载失败！");
                        }
                    }
                });
    }


    @Override
    public void save(UserWrapper.User user) {
        HashMap<String, String> params = new HashMap<>();
        params.put("nick_name", user.getNick_name());
        params.put("introduce", user.getIntroduce());


        mView.showErrorInfo("正在保存...");
        mDisposable = Client.getInstance()
                .create(UserInfoService.class)
                .updateUserInfo(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BaseBean>() {
                    @Override
                    public void accept(BaseBean baseBean) throws Exception {
                        if (mView.isActive()) {
                            mView.hideProgress();
                            if (baseBean.getCode() == 1) {
                                mView.saveSucceed();
                                EventBus.getDefault().post(new UpdateUserEvent());
                            } else {
                                mView.showErrorInfo(baseBean.getMsg());
                            }
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (mView.isActive()) {
                            mView.hideProgress();
                            mView.showErrorInfo("加载失败！");
                        }
                    }
                });
    }

}
