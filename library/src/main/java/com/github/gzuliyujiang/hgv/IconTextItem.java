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

import androidx.annotation.DrawableRes;

/**
 * Created by liyujiang on 2020/7/10.
 */
@SuppressWarnings("unused")
public class IconTextItem {
    private int iconRes;
    private String iconUrl;
    private CharSequence text;

    public IconTextItem() {
        super();
    }

    public IconTextItem(@DrawableRes int iconRes, CharSequence text) {
        this.iconRes = iconRes;
        this.text = text;
    }

    public IconTextItem(String iconUrl, CharSequence text) {
        this.iconUrl = iconUrl;
        this.text = text;
    }

    @DrawableRes
    public int getIconRes() {
        return iconRes;
    }

    public void setIconRes(@DrawableRes int iconRes) {
        this.iconRes = iconRes;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public CharSequence getText() {
        return text;
    }

    public void setText(CharSequence text) {
        this.text = text;
    }

}
