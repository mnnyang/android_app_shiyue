package cn.xxyangyoulin.shiyue.poem;

import java.util.ArrayList;

import cn.xxyangyoulin.shiyue.base.BasePresenter;
import cn.xxyangyoulin.shiyue.base.BaseView;
import cn.xxyangyoulin.shiyue.data.bean.CommentWrapper;

public interface PoemContracts {
    interface PoemPresenter extends BasePresenter {
        void loadComments(int type, int tid, String order, int onceCount,int page);

        void putComment(CommentWrapper.Comment comment);
    }

    interface PoemView extends BaseView<PoemContracts.PoemPresenter> {

        boolean isActive();

        int getLastCommentId();

        void fillDefault();

        void loadMore();

        void startLoadComments();

        void loadCommentsSucceed(ArrayList<CommentWrapper.Comment> comments);

        void loadCommentsFailed();

        void startPutComment();

        void putCommentSucceed();

        void putCommentFailed();
    }
}
