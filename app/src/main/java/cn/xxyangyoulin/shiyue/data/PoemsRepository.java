package cn.xxyangyoulin.shiyue.data;

import java.util.List;

import cn.xxyangyoulin.shiyue.data.bean.PoemWrapper;

public class PoemsRepository implements PoemsDataSource {


    @Override
    public void getDayPoem() {

    }

    @Override
    public void getRandomPoem() {

    }

    @Override
    public List<PoemWrapper> getPoemsByAuthor(String authorName) {
        return null;
    }

    @Override
    public List<PoemWrapper> getPoemsByTitle(String title) {
        return null;
    }


}
