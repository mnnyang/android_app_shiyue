package cn.xxyangyoulin.shiyue.data;

import java.util.List;

import cn.xxyangyoulin.shiyue.data.bean.Poem;

public interface PoemsDataSource {

    void getDayPoem();

    void getRandomPoem();

    List<Poem> getPoemsByAuthor(String authorName);

    List<Poem> getPoemsByTitle(String title);

}
