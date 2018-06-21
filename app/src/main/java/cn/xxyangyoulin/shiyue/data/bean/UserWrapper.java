package cn.xxyangyoulin.shiyue.data.bean;

import java.io.Serializable;

public class UserWrapper extends BaseBean {

    /**
     * data : {"username":"admin","blogurl":"http://xxyangyoulin.cn","first_name":"","last_name":"","backend":null,"nick_name":"f","gender":1,"is_active":true,"introduce":"f","email":"admin@qq.com","avator":"/file/upload/avator/201806/AppIcon57x572x.png","is_superuser":true,"is_staff":true,"last_login":1.529143805E9,"password":"pbkdf2_sha256$24000$uMjS2tLNflhX$qIeuzU6qrIG46PRn3cAfYVKXngNX1/kP/ZF159kNPcs=","id":1,"date_joined":1.52913996E9}
     */

    private User data;

    public User getData() {
        return data;
    }

    public void setData(User data) {
        this.data = data;
    }

    public static class User implements Serializable{
        /**
         * username : admin
         * blogurl : http://xxyangyoulin.cn
         * first_name :
         * last_name :
         * backend : null
         * nick_name : f
         * gender : 1
         * is_active : true
         * introduce : f
         * email : admin@qq.com
         * avator : /file/upload/avator/201806/AppIcon57x572x.png
         * is_superuser : true
         * is_staff : true
         * last_login : 1.529143805E9
         * password : pbkdf2_sha256$24000$uMjS2tLNflhX$qIeuzU6qrIG46PRn3cAfYVKXngNX1/kP/ZF159kNPcs=
         * id : 1
         * date_joined : 1.52913996E9
         */

        private String username;
        private String first_name;
        private String last_name;
        private Object backend;
        private String nick_name;
        private int gender;
        private boolean is_active;
        private String introduce;
        private String email;
        private String avator;
        private boolean is_superuser;
        private boolean is_staff;
        private double last_login;
        private String password;
        private int id;
        private double date_joined;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getFirst_name() {
            return first_name;
        }

        public void setFirst_name(String first_name) {
            this.first_name = first_name;
        }

        public String getLast_name() {
            return last_name;
        }

        public void setLast_name(String last_name) {
            this.last_name = last_name;
        }

        public Object getBackend() {
            return backend;
        }

        public void setBackend(Object backend) {
            this.backend = backend;
        }

        public String getNick_name() {
            return nick_name;
        }

        public void setNick_name(String nick_name) {
            this.nick_name = nick_name;
        }

        public int getGender() {
            return gender;
        }

        public void setGender(int gender) {
            this.gender = gender;
        }

        public boolean isIs_active() {
            return is_active;
        }

        public void setIs_active(boolean is_active) {
            this.is_active = is_active;
        }

        public String getIntroduce() {
            return introduce;
        }

        public void setIntroduce(String introduce) {
            this.introduce = introduce;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getAvator() {
            return avator;
        }

        public void setAvator(String avator) {
            this.avator = avator;
        }

        public boolean isIs_superuser() {
            return is_superuser;
        }

        public void setIs_superuser(boolean is_superuser) {
            this.is_superuser = is_superuser;
        }

        public boolean isIs_staff() {
            return is_staff;
        }

        public void setIs_staff(boolean is_staff) {
            this.is_staff = is_staff;
        }

        public double getLast_login() {
            return last_login;
        }

        public void setLast_login(double last_login) {
            this.last_login = last_login;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public double getDate_joined() {
            return date_joined;
        }

        public void setDate_joined(double date_joined) {
            this.date_joined = date_joined;
        }

        @Override
        public String toString() {
            return "User{" +
                    "username='" + username + '\'' +
                    ", first_name='" + first_name + '\'' +
                    ", last_name='" + last_name + '\'' +
                    ", backend=" + backend +
                    ", nick_name='" + nick_name + '\'' +
                    ", gender=" + gender +
                    ", is_active=" + is_active +
                    ", introduce='" + introduce + '\'' +
                    ", email='" + email + '\'' +
                    ", avator='" + avator + '\'' +
                    ", is_superuser=" + is_superuser +
                    ", is_staff=" + is_staff +
                    ", last_login=" + last_login +
                    ", password='" + password + '\'' +
                    ", id=" + id +
                    ", date_joined=" + date_joined +
                    '}';
        }
    }
}
