package cn.xxyangyoulin.shiyue.widget;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

import cn.xxyangyoulin.shiyue.app.app;

public class FontTextView extends android.support.v7.widget.AppCompatTextView {
    public FontTextView(Context context) {
        super(context);
        init();
    }

    public FontTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public FontTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

        setTypeface(app.getTypeFace());
    }
}
