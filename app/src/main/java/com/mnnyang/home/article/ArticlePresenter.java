package com.mnnyang.home.article;

import static com.mnnyang.utils.MainUtils.checkNotNull;

/**
 * Created by mnnyang on 17-10-3.
 */

public class ArticlePresenter implements ArticleContract.Presenter {
    private ArticleContract.View mArticleView;

    public ArticlePresenter(ArticleContract.View articleView) {
        mArticleView = checkNotNull(articleView, "tasksView cannot be null!");
        mArticleView.setPresenter(this);
    }

    @Override
    public void start() {

    }
}
