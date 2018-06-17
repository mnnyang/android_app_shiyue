package cn.xxyangyoulin.shiyue;

/**
 * 登录事件
 */
public class LoginEvent {
    public static final int TYPE_LOGIN = 0, TYPE_LOGOUT = 1;
    private int type = 0;

    public int getType() {
        return type;
    }

    public LoginEvent setType(int type) {
        this.type = type;
        return this;
    }
}
