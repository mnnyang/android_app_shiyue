package cn.xxyangyoulin.shiyue.poem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.xxyangyoulin.shiyue.data.api.CommentService;
import cn.xxyangyoulin.shiyue.data.bean.BaseBean;
import cn.xxyangyoulin.shiyue.data.bean.CommentWrapper;
import cn.xxyangyoulin.shiyue.data.http.Client;
import cn.xxyangyoulin.shiyue.util.LogUtil;
import cn.xxyangyoulin.shiyue.util.ToastUtils;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class PoemPresenter implements PoemContracts.PoemPresenter {

    PoemContracts.PoemView mView;

    public PoemPresenter(PoemContracts.PoemView view) {
        mView = view;
        mView.setPresenter(this);

    }

    @Override
    public void start() {

    }

    private boolean loadCommentsing;

    @Override
    public void loadComments(int type, int tid, String order, int onceCount, int page) {

        if (loadCommentsing) return;
        loadCommentsing = true;

        mView.startLoadComments();
        Disposable disposable = Client.getInstance()
                .create(CommentService.class)
                .getComments(type, tid, page, onceCount, order)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<CommentWrapper, ArrayList<CommentWrapper.Comment>>() {
                    @Override
                    public ArrayList<CommentWrapper.Comment> apply(CommentWrapper commentWrapper) throws Exception {
                        return (ArrayList<CommentWrapper.Comment>) commentWrapper.getData();
                    }
                })
                .subscribe(new Consumer<ArrayList<CommentWrapper.Comment>>() {
                    @Override
                    public void accept(ArrayList<CommentWrapper.Comment> comments) throws Exception {
                        loadCommentsing = false;
                        if (mView.isActive()) {
                            mView.loadCommentsSucceed(comments);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                        loadCommentsing = false;
                        if (mView.isActive()) {
                            mView.loadCommentsFailed();
                        }
                    }
                });
    }

    private boolean putCommenting;

    @Override
    public void putComment(CommentWrapper.Comment comment) {

        if (putCommenting) return;
        putCommenting = true;

        if (mView.isActive()) {
            mView.startPutComment();
        }

        HashMap<String, String> params = new HashMap<>();
        params.put("type", String.valueOf(comment.getType()));
        params.put("tid", String.valueOf(comment.getTid()));
        params.put("pid", String.valueOf(comment.getPid()));
        params.put("title", comment.getTitle());
        params.put("content", comment.getContent());


        Disposable disposable = Client.getInstance()
                .create(CommentService.class)
                .putComment(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BaseBean>() {
                    @Override
                    public void accept(BaseBean baseBean) throws Exception {
                        LogUtil.d(this, baseBean.getMsg());
                        putCommenting = false;

                        if (baseBean.getCode() == 1) {
                            if (mView.isActive()) {
                                mView.putCommentSucceed();
                            }
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                        putCommenting = false;

                        if (mView.isActive()) {
                            mView.putCommentFailed();
                        }
                    }
                });
    }
}
