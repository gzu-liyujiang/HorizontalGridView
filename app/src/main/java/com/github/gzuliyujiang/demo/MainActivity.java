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
package com.github.gzuliyujiang.demo;

import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.github.gzuliyujiang.hgv.HorizontalGridView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        HorizontalGridView horizontalGridView = findViewById(R.id.main_horizontal_grid);
        int length = 14;
        int[] icons = new int[length];
        CharSequence[] texts = new CharSequence[length];
        for (int i = 0; i < length; i++) {
            icons[i] = android.R.drawable.sym_def_app_icon;
            texts[i] = "测试" + i;
        }
        horizontalGridView.setData(icons, texts, 2, 6);
        //noinspection Convert2Lambda
        horizontalGridView.setOnItemClickListener(new HorizontalGridView.OnItemClickListener() {
            @Override
            public void onItemClick(GridView parent, View view, int position) {
                Toast.makeText(parent.getContext(), "position=" + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

}
