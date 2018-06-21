package cn.xxyangyoulin.shiyue.util;

import android.support.annotation.NonNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import cn.xxyangyoulin.shiyue.app.app;

public class SerializableUtil {
    public boolean put(Object object) {
        if (object == null) {
            return false;
        }

        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;

        String name = object.getClass().getSimpleName();

        File file = new File(getPathname(name));
        LogUtil.v(this, file.getAbsolutePath());

        try {
            if (!file.exists()) {
                file.createNewFile();
            }

            fileOutputStream = new FileOutputStream(file.toString());
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(object);

            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (objectOutputStream != null) {
                try {
                    objectOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public Object get(Class clazz) {
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;

        String name = clazz.getSimpleName();

        File file = new File(getPathname(name));

        if(!file.exists()){
            return null;
        }

        try {
            fileInputStream = new FileInputStream(file.toString());
            objectInputStream = new ObjectInputStream(fileInputStream);
            Object o = objectInputStream.readObject();

            return o;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (objectInputStream != null) {
                try {
                    objectInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 清除
     */
    public void clear(@NonNull Class clazz) {
        String name = clazz.getSimpleName();
        File file = new File(getPathname(name));

        if (file.exists()) {
            file.delete();
        }
    }

    @NonNull
    private String getPathname(String name) {
        return app.getContext().getExternalCacheDir().getAbsolutePath()
                + File.separator + name + "_data.dat";
    }

}
