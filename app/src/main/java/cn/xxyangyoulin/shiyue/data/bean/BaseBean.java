package cn.xxyangyoulin.shiyue.data.bean;

import android.os.Parcel;
import android.os.Parcelable;

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

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "BaseBean{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }
}
