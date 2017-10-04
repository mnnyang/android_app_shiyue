package com.mnnyang.data.bean;

import java.util.List;

/**
 * 日更诗
 * Created by mnnyang on 17-10-4.
 */

public class DayPoetry {

    /**
     * code : 200
     * message : success
     * data : [{"id":"1","p_c_id":"1","date":"2017-10-03","img":"","collect":"23","praise":"15","title":"面朝大海，春暖花开","author":"海子","from":"1989年1月","content":"从明天起，做一个幸福的人 喂马，劈柴，周游世界 从明天起，关心粮食和蔬菜 我有一所房子，面朝大海，春暖花开 从明天起，和每一个亲人通信 告诉他们我的幸福 那幸福的闪电告诉我的 我将告诉每一个人 给每一条河每一座山取一个温暖的名字 陌生人，我也为你祝福 愿你有一个灿烂的前程 愿你有情人终成眷属 愿你在尘世获得幸福 我只愿面朝大海，春暖花开"},{"id":"2","p_c_id":"2","date":"2017-10-04","img":"","collect":"23","praise":"676","title":"我期待有一场美丽的爱情","author":"手机诗人恒星","from":"baidu","content":"我期待有一场美丽的爱情 在这个百花飞舞的时候 有那么一个可爱的女孩 与我相遇在古长安的街上 她能在我无助的时候鼓励我 说我相信你，你行的 我期待有一场美丽的爱情 在这个青春将逝的时候 与我相互偎依在沙井村的筒子楼里 她能一件件细数我曾经的辉煌 说没关系，一切会好的 她不嫌弃我的木讷 不嫌弃我的不解风情 说只要有你，就很足够了 奔三的人了 再谈爱情 别说朋友不信 甚至连我自己也不信 可是 我依然期待有那么一场美丽的爱情 让我能为之热血沸腾的爱情 期待那个人的出现 能让我把我最好的都给她"},{"id":"3","p_c_id":"3","date":"2017-10-05","img":"","collect":"0","praise":"0","title":"想起","author":"沽酒三重","from":"baidu","content":"寒假的时候吃香蕉 一根香蕉 我只要两口就吃掉 拨一根香蕉 总得花个八九秒 母亲给我拨了一根又一根 我就这样 蚕食掉她的青春 不客气也不犹豫"},{"id":"4","p_c_id":"4","date":"2017-10-06","img":"","collect":"0","praise":"0","title":"追求","author":"落叶无言梦","from":"baidu","content":"一间房子里面 一张床 一张窗 最好没有出口的门 可以睡 可以醉 空杯子盛着酒味 如果再有一块时钟就好了 不要分针 不要秒针 更不要时针 就挂到举起双手踮起脚尖 摘不下来的地方 "},{"id":"5","p_c_id":"5","date":"2017-10-07","img":"","collect":"0","praise":"0","title":"等","author":"落叶无言梦","from":"baidu","content":"一秒太长 如果距离必须是三年 我用了两年多半 一辈子太少 如果前世注定了 今生的缺陷 谎话太难说出口 千万句的追求 都抵不过一句面对面的晚安"}]
     */

    private int code;
    private String message;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1
         * p_c_id : 1
         * date : 2017-10-03
         * img :
         * collect : 23
         * praise : 15
         * title : 面朝大海，春暖花开
         * author : 海子
         * from : 1989年1月
         * content : 从明天起，做一个幸福的人 喂马，劈柴，周游世界 从明天起，关心粮食和蔬菜 我有一所房子，面朝大海，春暖花开 从明天起，和每一个亲人通信 告诉他们我的幸福 那幸福的闪电告诉我的 我将告诉每一个人 给每一条河每一座山取一个温暖的名字 陌生人，我也为你祝福 愿你有一个灿烂的前程 愿你有情人终成眷属 愿你在尘世获得幸福 我只愿面朝大海，春暖花开
         */

        private String id;
        private String p_c_id;
        private String date;
        private String img;
        private String collect;
        private String praise;
        private String title;
        private String author;
        private String from;
        private String content;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getP_c_id() {
            return p_c_id;
        }

        public void setP_c_id(String p_c_id) {
            this.p_c_id = p_c_id;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getCollect() {
            return collect;
        }

        public void setCollect(String collect) {
            this.collect = collect;
        }

        public String getPraise() {
            return praise;
        }

        public void setPraise(String praise) {
            this.praise = praise;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getFrom() {
            return from;
        }

        public void setFrom(String from) {
            this.from = from;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
