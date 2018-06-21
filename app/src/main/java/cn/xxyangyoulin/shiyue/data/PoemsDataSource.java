package cn.xxyangyoulin.shiyue.data;

import java.util.List;

import cn.xxyangyoulin.shiyue.data.bean.PoemWrapper;

public interface PoemsDataSource {

    void getDayPoem();

    void getRandomPoem();

    List<PoemWrapper> getPoemsByAuthor(String authorName);

    List<PoemWrapper> getPoemsByTitle(String title);

}
