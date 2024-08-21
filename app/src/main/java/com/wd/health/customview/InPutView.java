package com.wd.health.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * <p>项目名称:Healtha</p>
 * <p>包名:com.wd.health.customview</p>
 * <p>简述:自定义流失布局</p>
 *
 * @author 张凯涛
 * @date 2022/7/21
 */
public class InPutView extends ViewGroup {
    public InPutView(Context context) {
        super(context);
    }

    public InPutView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        //设置左边和上边距离的距离
        int left=10;
        int top=10;

        for(int i=0;i<getChildCount();i++){
            View childAt = getChildAt(i);
            //获取父类的宽度
            int width = getWidth();
            //获取子布局的高度
            int measuredHeight = childAt.getMeasuredHeight();
            //获取父布局的宽度
            int measuredWidth = childAt.getMeasuredWidth();
            //进行判断是否超过父类的宽度
            if(left+measuredWidth>width){
                top+=measuredHeight;
                left=10;
            }
            //确定子布局的
            childAt.layout(left,top,left+measuredWidth,top+measuredHeight);
            left+=measuredWidth;
        }
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    measureChildren(widthMeasureSpec, heightMeasureSpec);

    }
}
