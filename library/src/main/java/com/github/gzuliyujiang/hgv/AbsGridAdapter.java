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

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liyujiang on 2020/7/8.
 */
@SuppressWarnings("unused")
public abstract class AbsGridAdapter<T> extends BaseAdapter {
    protected List<T> data;
    protected ImageLoader imageLoader;

    public AbsGridAdapter() {
        this(new ArrayList<>());
    }

    public AbsGridAdapter(List<T> data) {
        this.data = data;
    }

    public final List<T> getData() {
        return data;
    }

    public final void setData(List<T> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public void setImageLoader(ImageLoader imageLoader) {
        this.imageLoader = imageLoader;
    }

    public ImageLoader getImageLoader() {
        return imageLoader;
    }

    @NonNull
    public abstract ViewHolder onCreateViewHolder(@NonNull ViewGroup parent);

    public abstract void onBindViewHolder(@NonNull ViewHolder holder, int position);

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public T getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = onCreateViewHolder(parent);
            convertView = holder.itemView;
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        onBindViewHolder(holder, position);
        return convertView;
    }

    public static class ViewHolder {
        public View itemView;

        public ViewHolder(View itemView) {
            if (itemView == null) {
                throw new IllegalArgumentException("itemView may not be null");
            }
            this.itemView = itemView;
        }

        public <V extends View> V findView(@IdRes int id) {
            return itemView.findViewById(id);
        }

    }

    public interface ImageLoader {
        void displayImage(ImageView imageView, String url);
    }

}
