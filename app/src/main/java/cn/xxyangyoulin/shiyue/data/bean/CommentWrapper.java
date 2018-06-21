package cn.xxyangyoulin.shiyue.data.bean;

import java.io.Serializable;
import java.util.List;

public class CommentWrapper extends BaseBean {


    private List<Comment> data;

    public List<Comment> getData() {
        return data;
    }

    public void setData(List<Comment> data) {
        this.data = data;
    }

    public static class Comment implements Serializable{
        /**
         * uid : 1
         * title :
         * nick_name :  ff
         * pid : 0
         * content : sdfsdf
         * tid : 1234
         * avator : upload/avator/IMG_20180404_151620.jpg
         * type : 0
         * id : 1
         * add_time : 2018-06-19 08:25:10
         */

        private int uid = 0;
        private String title = "";
        private String nick_name ="";
        private int pid =0;
        private String content = "";
        private int tid = 0;
        private int subcount = 0;
        private String avator = "";
        private int type = 0;
        private int id = 0;
        private int favour = 0;
        private String add_time = "";



        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getNick_name() {
            return nick_name;
        }

        public void setNick_name(String nick_name) {
            this.nick_name = nick_name;
        }

        public int getPid() {
            return pid;
        }

        public void setPid(int pid) {
            this.pid = pid;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getTid() {
            return tid;
        }

        public void setTid(int tid) {
            this.tid = tid;
        }

        public String getAvator() {
            return avator;
        }

        public int getSubcount() {
            return subcount;
        }

        public Comment setSubcount(int subcount) {
            this.subcount = subcount;
            return this;
        }

        public void setAvator(String avator) {
            this.avator = avator;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getFavour() {
            return favour;
        }

        public Comment setFavour(int favour) {
            this.favour = favour;
            return this;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }
    }
}
