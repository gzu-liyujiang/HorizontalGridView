/*
 * Copyright (c) 2019-2020 gzu-liyujiang <1032694760@qq.com>
 *
 * The software is licensed under the Mulan PSL v1.
 * You can use this software according to the terms and conditions of the Mulan PSL v1.
 * You may obtain a copy of Mulan PSL v1 at:
 *     http://license.coscl.org.cn/MulanPSL
 * THIS SOFTWARE IS PROVIDED ON AN "AS IS" BASIS, WITHOUT WARRANTIES OF ANY KIND, EITHER EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO NON-INFRINGEMENT, MERCHANTABILITY OR FIT FOR A PARTICULAR
 * PURPOSE.
 * See the Mulan PSL v1 for more details.
 *
 */

package com.github.gzuliyujiang.hgv;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.HorizontalScrollView;

import androidx.annotation.RestrictTo;

/**
 * Created by liyujiang on 2020/7/8.
 */
@SuppressWarnings("unused")
@RestrictTo(RestrictTo.Scope.LIBRARY)
public class ExHorizontalScrollView extends HorizontalScrollView implements Runnable {
    private int currentScroll;
    private boolean firstOldL;
    private OnScrollStateListener mListener;
    private int oldL;
    private Handler mHandler = new Handler(Looper.getMainLooper());
    private int scrollDelay = 1;

    public ExHorizontalScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public ExHorizontalScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ExHorizontalScrollView(Context context) {
        super(context);
    }

    public void setOnScrollStateListener(OnScrollStateListener listener) {
        mListener = listener;
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        if (!firstOldL) {
            oldL = oldl;
            firstOldL = true;
        }
        if (mListener != null) {
            mListener.onScrollChanged(l, t, oldl, oldt);

        }
        super.onScrollChanged(l, t, oldl, oldt);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                firstOldL = false;
                break;
            case MotionEvent.ACTION_UP:
                currentScroll = this.getScrollX();
                if (mHandler != null) {
                    mHandler.postDelayed(this, scrollDelay);
                }
                break;

            default:
                break;
        }
        return super.onTouchEvent(ev);
    }

    @Override
    public void run() {
        int newScroll = getScrollX();
        if (currentScroll == newScroll) {
            firstOldL = false;
            mListener.onScrollStopped(currentScroll - oldL);
            mHandler.removeCallbacks(this);
        } else {
            currentScroll = getScrollX();
            postDelayed(this, scrollDelay);
        }
    }

    public interface OnScrollStateListener {

        void onScrollChanged(int l, int t, int oldl, int oldt);

        void onScrollStopped(int scrollDistance);

    }

}
