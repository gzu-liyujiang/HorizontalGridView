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

import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.HorizontalScrollView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;

import androidx.annotation.DrawableRes;
import androidx.annotation.RequiresApi;

import java.util.ArrayList;

/**
 * Created by liyujiang on 2020/7/10.
 */
@SuppressWarnings("unused")
public class HorizontalGridView extends RelativeLayout {
    private static final int DEFAULT_ROWS = 2;
    private static final int DEFAULT_COLUMNS = 5;
    private ExHorizontalScrollView scrollView;
    private NoScrollGridView gridView;
    private SeekBar seekBar;

    public HorizontalGridView(Context context) {
        super(context);
        inti(context);
    }

    public HorizontalGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
        inti(context);
    }

    public HorizontalGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inti(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public HorizontalGridView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        inti(context);
    }

    private void inti(Context context) {
        View.inflate(context, R.layout.hgv_layout, this);
        scrollView = findViewById(R.id.hgv_scroll);
        gridView = findViewById(R.id.hgv_grid);
        seekBar = findViewById(R.id.hgv_indicator);
        if (isInEditMode()) {
            int length = 12;
            int[] icons = new int[length];
            CharSequence[] texts = new CharSequence[length];
            for (int i = 0; i < length; i++) {
                icons[i] = android.R.drawable.sym_def_app_icon;
                texts[i] = getContext().getString(android.R.string.untitled) + i;
            }
            setData(icons, texts);
        }
    }

    public final HorizontalScrollView getScrollView() {
        return scrollView;
    }

    public final GridView getGridView() {
        return gridView;
    }

    public final SeekBar getSeekBar() {
        return seekBar;
    }

    public void setAdapter(AbsGridAdapter<?> adapter) {
        setAdapter(adapter, DEFAULT_ROWS, DEFAULT_COLUMNS);
    }

    public void setAdapter(AbsGridAdapter<?> adapter, int rows, int columns) {
        if (adapter == null) {
            throw new IllegalArgumentException("adapter may not be null");
        }
        int totalCount = adapter.getCount();
        int numColumns = (totalCount % rows == 0) ? totalCount / rows : totalCount / rows + 1;
        if (numColumns < columns) {
            numColumns = columns;
        }
        int itemWidth = getScreenWidth() / columns;
        ViewGroup.LayoutParams params = gridView.getLayoutParams();
        params.width = numColumns * itemWidth;
        gridView.setLayoutParams(params);
        gridView.setColumnWidth(itemWidth);
        gridView.setNumColumns(numColumns);
        gridView.setStretchMode(NoScrollGridView.NO_STRETCH);
        gridView.setAdapter(adapter);
        int canMoveDistance = (numColumns - columns) * itemWidth;
        if (totalCount > columns * rows) {
            seekBar.setVisibility(View.VISIBLE);
        } else {
            seekBar.setVisibility(View.GONE);
        }
        seekBar.setMax(canMoveDistance);
        int finalNumColumns = numColumns;
        scrollView.setOnScrollStateListener(new ExHorizontalScrollView.OnScrollStateListener() {
            @Override
            public void onScrollChanged(int l, int t, int oldl, int oldt) {
                if (finalNumColumns > columns) {
                    seekBar.setProgress(l);
                }
            }

            @Override
            public void onScrollStopped(int scrollDistance) {
            }
        });
    }

    public void setData(@DrawableRes int[] icons, CharSequence[] texts) {
        setData(icons, texts, DEFAULT_ROWS, DEFAULT_COLUMNS);
    }

    public void setData(@DrawableRes int[] icons, CharSequence[] texts, int rows, int columns) {
        if (icons == null || icons.length == 0 || texts == null || icons.length != texts.length) {
            throw new IllegalArgumentException("icons and texts invalid");
        }
        ArrayList<IconTextItem> data = new ArrayList<>();
        for (int i = 0, n = icons.length; i < n; i++) {
            data.add(new IconTextItem(icons[i], texts[i]));
        }
        setAdapter(new IconTextAdapter(data), rows, columns);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        if (listener == null) {
            return;
        }
        //noinspection Convert2Lambda
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listener.onItemClick(gridView, view, position);
            }
        });
    }

    private int getScreenWidth() {
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        if (wm == null) {
            return -1;
        }
        Point point = new Point();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            wm.getDefaultDisplay().getRealSize(point);
        } else {
            wm.getDefaultDisplay().getSize(point);
        }
        return point.x;
    }

    public interface OnItemClickListener {
        void onItemClick(GridView parent, View view, int position);
    }

}
