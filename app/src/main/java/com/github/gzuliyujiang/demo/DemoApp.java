/*
 * Copyright (c) 2016-present, 贵州纳雍穿青人李裕江<1032694760@qq.com>, All Right Reserved.
 *
 */
package com.github.gzuliyujiang.demo;

import android.app.Application;

import com.github.gzuliyujiang.logger.Logger;

/**
 * Created by liyujiang on 2020/5/20.
 */
public class DemoApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Logger.useDefaultPrinter();
    }

}
