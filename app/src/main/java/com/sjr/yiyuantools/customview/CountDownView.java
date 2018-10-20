package com.sjr.yiyuantools.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;


public class CountDownView extends View {
    private RectF mRectF;
    private Paint mPaint;
    /**
     * 画笔颜色
     */
    private int paintColor;
    /**
     * 进度条圆弧宽度
     */
    private int mStrokeWidth = 6;
    /**
     * 最大进度
     */
    private float mMaxProgress = 100;
    /**
     * 当前进度
     */
    private float mProgress = 0;


    public CountDownView(Context context) {
        this(context, null);
    }

    public CountDownView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CountDownView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mRectF = new RectF();
        mPaint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = this.getWidth();
        int height = this.getHeight();

        if (width != height) {
            int min = Math.min(width, height);
            width = min;
            height = min;
        }

        //位置
        mRectF.left = mStrokeWidth / 2;
        mRectF.top = mStrokeWidth / 2;
        mRectF.right = width - mStrokeWidth / 2;
        mRectF.bottom = height - mStrokeWidth / 2;

        //设置画布为透明
        canvas.drawColor(Color.TRANSPARENT);

        //画个半透明的圆当背景
        mPaint.setColor(Color.parseColor("#33000000"));
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawOval(mRectF, mPaint);


        //设置画笔
        mPaint.setAntiAlias(true);
        mPaint.setColor(paintColor);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(mStrokeWidth);


        canvas.drawArc(mRectF, -90, -(mProgress / mMaxProgress) * 360, false, mPaint);
    }

    /**
     * 设置画笔颜色
     *
     * @param color
     */
    public void setPaintColor(String color) {
        paintColor = Color.parseColor(color);
    }

    /**
     * 设置到最大进度的时间
     *
     * @param time 倒计时时间 毫秒值
     */
    public void startDownTime(final long time, final OnFinishListener listener) {
        mMaxProgress = 100;
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = (int) mMaxProgress; i >= 0; i--) {
                    try {
                        Thread.sleep((long) (time / mMaxProgress));
                        //当倒计时结束时通知
                        if (i == 0 && listener != null) {
                            post(new Runnable() {
                                @Override
                                public void run() {
                                    listener.onFinish();
                                }
                            });
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    mProgress = (float) i;
                    CountDownView.this.postInvalidate();
                }
            }
        }).start();
    }

    /**
     * 进度完成监听接口
     * Created by xinyang on 2017/12/15.
     */

    public interface OnFinishListener {

        /**
         * 结束时回调
         */
        void onFinish();
    }
}
