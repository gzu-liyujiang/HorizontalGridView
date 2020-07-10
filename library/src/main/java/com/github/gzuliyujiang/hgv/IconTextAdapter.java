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

import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

/**
 * Created by liyujiang on 2020/7/8.
 */
public class IconTextAdapter extends AbsGridAdapter<IconTextItem> {

    public IconTextAdapter() {
        super();
    }

    public IconTextAdapter(List<IconTextItem> data) {
        super(data);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent) {
        View view = View.inflate(parent.getContext(), R.layout.hgv_item, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        IconTextItem item = data.get(position);
        String iconUrl = item.getIconUrl();
        int iconRes = item.getIconRes();
        ImageView imageView = holder.findView(R.id.hgv_item_icon);
        if (TextUtils.isEmpty(iconUrl)) {
            imageView.setImageResource(iconRes);
        } else if (imageLoader != null) {
            imageLoader.displayImage(imageView, iconUrl);
        }
        TextView textView = holder.findView(R.id.hgv_item_text);
        textView.setText(item.getText());
    }

}
