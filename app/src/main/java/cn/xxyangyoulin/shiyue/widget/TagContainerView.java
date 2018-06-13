package cn.xxyangyoulin.shiyue.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by xxyangyoulin on 2018/4/24.
 */
public class TagContainerView extends ViewGroup {

    private int parentWidth;
    private int totalLeft = 0;
    private int totalTop = 0;
    private int margin = dp2px(getContext(), 4);
    private int maxChildHeight = 0;
    private int totalRight = 0;

    public TagContainerView(Context context) {
        super(context);
    }

    public TagContainerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TagContainerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public static int dp2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        /* 测量父布局 */
        parentWidth = measureSize(widthMeasureSpec, dp2px(getContext(), 240));

        int count = getChildCount();
        int tempMaxChildHeight = 0;
        int tempTotalHeight = 0;
        int tempTotalRight = 0;

        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            if (child.getVisibility() != View.GONE) {

                /* 测量子布局 */
                if (child.getMeasuredWidth() > parentWidth) {
                    //控制child的最大宽度不超过父布局的宽度
                    child.measure(parentWidth, child.getMeasuredHeight());
                } else {
                    child.measure(child.getMeasuredWidth(), child.getMeasuredHeight());
                }

                tempMaxChildHeight = Math.max(tempMaxChildHeight, child.getMeasuredHeight());

                tempTotalRight += child.getMeasuredWidth();
                if (tempTotalRight > parentWidth) {
                    tempTotalHeight += tempMaxChildHeight;
                    tempTotalHeight += margin;
                    tempMaxChildHeight = child.getMeasuredHeight();
                    tempTotalRight = child.getMeasuredWidth();
                }
            }
        }

        /* 获取适配子布局后的高度 */
        int parentHeight = tempTotalHeight + tempMaxChildHeight + margin;
        setMeasuredDimension(parentWidth, parentHeight);
    }

    private int measureSize(int measureSpec, int defaultSize) {
        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);
        int result = defaultSize;

        if (mode == MeasureSpec.EXACTLY) {
            result = size;
        } else if (mode == MeasureSpec.AT_MOST) {
            result = Math.max(size, result);
        }

        return result;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        int count = getChildCount();
        int lineViewCount = 0;

        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            if (child.getVisibility() != View.GONE) {

                if (i != 0) {
                    /* child 的 left 是上个子 view 的宽加上 margin */
                    totalLeft += getChildAt(i - 1).getMeasuredWidth() + margin;
                } else {
                    totalLeft = 0;
                    totalTop = 0;
                    maxChildHeight = child.getMeasuredHeight();
                }

                /* child 的 right */
                totalRight = totalLeft + child.getMeasuredWidth();
                /* 如果 right 大于 父布局的宽， 则换行 */
                if (totalRight > parentWidth) {
                    adjustLine(lineViewCount, i); // 调整这一行的子布局的位置
                    lineViewCount = 0;  // 这一行的子 child 的数量充值
                    totalTop += maxChildHeight;
                    totalTop += margin;
                    totalLeft = 0;
                    maxChildHeight = child.getMeasuredHeight();
                    totalRight = child.getMeasuredWidth();
                } else {
                    maxChildHeight = Math.max(maxChildHeight, child.getMeasuredHeight());
                }


//                child.layout(totalLeft, totalTop, totalRight,
//                        totalTop + child.getMeasuredHeight());

                /* 统计这一行的子view的数量 */
                lineViewCount++;
            }
        }

//        totalLeft = totalRight + margin;
        adjustLine(lineViewCount, count);

    }

    private void adjustLine(int lineViewCount, int i) {
        totalLeft = 0;
        int marginTop = 0;

        for (int count = lineViewCount; count > 0; count--) {
            View lineViewChild = getChildAt(i - count);

            totalRight = totalLeft + lineViewChild.getMeasuredWidth();

            if (lineViewChild.getMeasuredHeight() != maxChildHeight) {
//                marginTop = (maxChildHeight - lineViewChild.getMeasuredHeight()) / 2 + margin;
            } else {
                marginTop = margin;
            }
            lineViewChild.layout(totalLeft, totalTop + marginTop, totalRight, totalTop + marginTop + lineViewChild.getMeasuredHeight());
            totalLeft += lineViewChild.getMeasuredWidth() + margin;
        }
    }

}