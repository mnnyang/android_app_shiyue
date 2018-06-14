package cn.xxyangyoulin.shiyue.data.bean;

public abstract class BaseBean {

    /**
     * code : Number
     * msg : String
     */

    private int code;
    private String msg;


    public int getCode() {
        return code;
    }

    public BaseBean setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public BaseBean setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    @Override
    public String toString() {
        return "BaseBean{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }
}
