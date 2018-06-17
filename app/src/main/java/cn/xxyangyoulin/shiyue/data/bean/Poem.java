package cn.xxyangyoulin.shiyue.data.bean;

import java.util.List;

public class Poem extends BaseBean {



    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * author : 柳永
         * title : 西江月
         * dynasty : null
         * content : 凤额绣帘高卷，兽环朱户频摇。
         * 两竿红日上花梢。
         * 春睡厌厌难觉。
         * 好梦狂随飞絮，闲愁浓胜香醪。
         * 不成雨暮与云朝。
         * 又是韶光过了。
         * author_id : 2
         * id : 64
         */

        private String author;
        private String title;
        private Object dynasty;
        private String content;
        private int author_id;
        private int id;

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Object getDynasty() {
            return dynasty;
        }

        public void setDynasty(Object dynasty) {
            this.dynasty = dynasty;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getAuthor_id() {
            return author_id;
        }

        public void setAuthor_id(int author_id) {
            this.author_id = author_id;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
