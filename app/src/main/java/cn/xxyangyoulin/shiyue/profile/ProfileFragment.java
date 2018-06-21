package cn.xxyangyoulin.shiyue.profile;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.io.File;

import cn.xxyangyoulin.shiyue.R;
import cn.xxyangyoulin.shiyue.app.Constants;
import cn.xxyangyoulin.shiyue.base.BaseFragment;
import cn.xxyangyoulin.shiyue.base.FragmentBackHandler;
import cn.xxyangyoulin.shiyue.data.bean.UserWrapper;
import cn.xxyangyoulin.shiyue.data.http.HandleSubpath;
import cn.xxyangyoulin.shiyue.util.DialogHelper;
import cn.xxyangyoulin.shiyue.util.FileUtil;
import cn.xxyangyoulin.shiyue.util.LogUtil;
import cn.xxyangyoulin.shiyue.util.RequestPermission;

public class ProfileFragment extends BaseFragment implements ProfileContracts.ProfileView, FragmentBackHandler {

    Toolbar mToolbar;
    ProfileContracts.ProfilePresenter mProfilePresenter;
//    private CircleImageView mCivAvator;
    private TextView mEtNickName;
    private TextView mEtIntroduce;
    private View mViewAvator;

    private File avatorFile;
    private DialogHelper mDialogHelper;

    public static ProfileFragment newInstance() {

        Bundle args = new Bundle();

        ProfileFragment fragment = new ProfileFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected int getLayout() {
        return R.layout.fragment_info;
    }

    @Override
    public void initView() {
        mViewAvator = mRootView.findViewById(R.id.layout_avator);
        mToolbar = mRootView.findViewById(R.id.toolbar);
//        mCivAvator = mRootView.findViewById(R.id.civ_avator);
        mEtNickName = mRootView.findViewById(R.id.et_nickname);
        mEtIntroduce = mRootView.findViewById(R.id.et_introduce);

        initToolbar();
    }

    private void initToolbar() {
        backToolbar(mToolbar);
        mToolbar.inflateMenu(R.menu.toolbar_profile);
    }

    @Override
    public void initListener() {
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.toolbar_save:
                        save();
                        return true;
                }
                return false;
            }
        });

        mViewAvator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPhoto();
            }
        });
    }

    private void openPhoto() {
        RequestPermission
                .with(this)
                .permissions(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .request(new RequestPermission.Callback() {
                    @Override
                    public void onGranted() {
                        toOpenPhoto();
                    }

                    @Override
                    public void onDenied() {
                        toast("请开启读取SD卡权限！");
                    }
                });
    }

    private void toOpenPhoto() {
        Intent intentToPickPic = new Intent(Intent.ACTION_PICK, null);
        // 如果限制上传到服务器的图片类型时可以直接写如："image/jpeg 、 image/png等的类型" 所有类型则写 "image/*"
        intentToPickPic.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/jpeg");
        startActivityForResult(intentToPickPic, Constants.REQUEST_CODE_PHOTO);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Constants.REQUEST_CODE_PHOTO) {
            // 获得图片
            try {
                Uri imageUri = data.getData();
                LogUtil.e(this, imageUri.toString());

                FileUtil fileUtil = new FileUtil();
                String pathFromURI = fileUtil.getRealPathFromURI(getContext(), imageUri);
                avatorFile = new File(pathFromURI);
                LogUtil.e(this, avatorFile.getAbsolutePath());

                //该uri就是照片文件夹对应的uri
                Bitmap bit = BitmapFactory.decodeStream(getContext().getContentResolver().openInputStream(imageUri));

                // 给相应的ImageView设置图片 未裁剪
//                mCivAvator.setImageBitmap(bit);

                /*触发上传*/
                uploadAvator();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        RequestPermission.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void initData() {
        super.initData();
        new ProfilePresenter(this).start();
    }

    @Override
    public void save() {
        String nickName = mEtNickName.getText().toString();
        String introduce = mEtIntroduce.getText().toString();

        UserWrapper.User user = new UserWrapper.User();
        user.setNick_name(nickName);
        user.setIntroduce(introduce);

        mProfilePresenter.save(user);
    }

    @Override
    public void uploadAvatorSucceed() {
        toast("头像修改成功！");
    }

    @Override
    public void saveSucceed() {
        toast("修改成功！");

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                close();
            }
        }, 500);
    }

    @Override
    public void close() {
        super.close();

        if (!isAdded()) {
            return;
        }

        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(0, R.anim.anim_fragment_down);
        transaction.hide(ProfileFragment.this);
        transaction.commitAllowingStateLoss();
    }

    @Override
    public boolean onBackPressed() {
        close();
        return true;
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public void exit() {
        close();
    }

    @Override
    public void fillDefault(UserWrapper.User user) {
        mEtNickName.setText(user.getNick_name());
        mEtIntroduce.setText(user.getIntroduce());

        RequestOptions options = new RequestOptions()
                .optionalCircleCrop()
                .placeholder(R.drawable.image_default_avator);
        Glide.with(this)
                .applyDefaultRequestOptions(options)
                .load(HandleSubpath.handle(user.getAvator(),true))
                .into((ImageView) mRootView.findViewById(R.id.civ_avator));
    }

    @Override
    public void showErrorInfo(String s) {
        toast(s);
    }

    @Override
    public void showProgress(String msg) {
        hideProgress();
        mDialogHelper = new DialogHelper();
        mDialogHelper.showProgressDialog(getContext(), "请稍等", msg, false);
    }

    @Override
    public void hideProgress() {
        if (mDialogHelper != null) {
            mDialogHelper.hideProgressDialog();
        }
    }

    @Override
    public void uploadAvator() {
        mProfilePresenter.uploadAvator(avatorFile);
    }

    @Override
    public void setPresenter(ProfileContracts.ProfilePresenter presenter) {
        mProfilePresenter = presenter;
    }
}
