package cn.xxyangyoulin.shiyue.data;

import java.util.List;

import cn.xxyangyoulin.shiyue.data.bean.Poem;

public class PoemsRepository implements PoemsDataSource {
    @Override
    public void getDayPoem() {

    }

    @Override
    public void getRandomPoem() {

    }

    @Override
    public List<Poem> getPoemsByAuthor(String authorName) {
        return null;
    }

    @Override
    public List<Poem> getPoemsByTitle(String title) {
        return null;
    }


}
