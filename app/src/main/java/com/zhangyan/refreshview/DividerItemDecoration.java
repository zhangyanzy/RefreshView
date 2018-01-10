package com.zhangyan.refreshview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.view.View;

/**
 * Created by Administrator on 2018/1/10.
 */

public class DividerItemDecoration extends ItemDecoration {

    private int mOrientation = LinearLayoutManager.VERTICAL;
    private Drawable mDivider;
    private int[] attrs = new int[]{android.R.attr.listDivider};

    public DividerItemDecoration(Context context, int orientation) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs);
        mDivider = typedArray.getDrawable(0);
        typedArray.recycle();
        setOrientation(orientation);

    }

    public void setOrientation(int orientation) {
        if (mOrientation != LinearLayoutManager.HORIZONTAL && mOrientation != LinearLayoutManager.VERTICAL) {
        } else {
            this.mOrientation = orientation;
        }
    }


    //2、 RecyclerView会回调该绘制方法，需要自己去绘制条目的间隔线
    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        if (mOrientation == LinearLayoutManager.VERTICAL) {
            drawVertical(c, parent);
        } else {
            drawHorizontal(c, parent);
        }
        super.onDraw(c, parent, state);
    }

    private void drawHorizontal(Canvas c, RecyclerView parent) {
        int top = parent.getPaddingTop();
        int bottom = parent.getHeight() - parent.getPaddingBottom();
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            int lift  = (int) (child.getBottom() + params.rightMargin + ViewCompat.getTranslationX(child));
            int right = lift + mDivider.getIntrinsicHeight();
            mDivider.setBounds(lift, top, right, bottom);
            mDivider.draw(c);
        }

    }

    private void drawVertical(Canvas c, RecyclerView parent) {
        //水平线
        int lift = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            int top = (int) (child.getBottom() + params.bottomMargin + ViewCompat.getTranslationY(child));
            int bottom = top + mDivider.getIntrinsicHeight();
            mDivider.setBounds(lift, top, right, bottom);
            mDivider.draw(c);
        }
    }


    /**
     * 1、获取条目的偏移量（所有的条目都会调用）
     * 首先会先获取条目之间的间隙的宽度--Rect矩形区域
     */
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State
            state) {
        if (mOrientation == LinearLayoutManager.VERTICAL) {//垂直
            outRect.set(0, 0, 0, mDivider.getIntrinsicHeight());
        } else {//水平
            outRect.set(0, 0, mDivider.getIntrinsicWidth(), 0);
        }
    }
}
