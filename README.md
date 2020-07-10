# HorizontalGridView

![Release APK](https://github.com/gzu-liyujiang/HorizontalGridView/workflows/Release%20APK/badge.svg)
![Gradle Package](https://github.com/gzu-liyujiang/HorizontalGridView/workflows/Gradle%20Package/badge.svg)
[![jitpack](https://jitpack.io/v/gzu-liyujiang/HorizontalGridView.svg)](https://jitpack.io/#gzu-liyujiang/HorizontalGridView)

Android 水平滚动的九宫格列表视图，带滚动指示器，可用于首页金刚区。

![效果图](/ScreenGif.gif) 

```groovy
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}

dependencies {
    implementation 'com.github.gzu-liyujiang:HorizontalGridView:1.0.0.20200710'
}
```

### 示例

```xml
    <com.github.gzuliyujiang.hgv.HorizontalGridView
        android:id="@+id/main_horizontal_grid"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginTop="10dp"
        android:layout_marginBottom="10dp" />
```
```java
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
```

### 指示器
```text
    res/drawable/hgv_indicator_normal.xml
    res/drawable/hgv_indicator_thumb.xml
```
### 颜色
```xml
    <color name="hgv_indicator_normal_color">#CCCCCC</color>
    <color name="hgv_indicator_thumb_color">#03A9F4</color>
```

### 尺寸
```xml
    <dimen name="hgv_grid_vertical_spacing">14dp</dimen>
    <dimen name="hgv_indicator_margin_top">14dp</dimen>
    <dimen name="hgv_indicator_radius">2dp</dimen>
    <dimen name="hgv_indicator_width">80dp</dimen>
    <dimen name="hgv_indicator_height">4dp</dimen>
    <dimen name="hgv_indicator_thumb_width">20dp</dimen>
```

## License

```text
Copyright (c) 2019-2020 gzu-liyujiang <1032694760@qq.com>

The software is licensed under the Mulan PSL v1.
You can use this software according to the terms and conditions of the Mulan PSL v1.
You may obtain a copy of Mulan PSL v1 at:
    http://license.coscl.org.cn/MulanPSL
THIS SOFTWARE IS PROVIDED ON AN "AS IS" BASIS, WITHOUT WARRANTIES OF ANY KIND, EITHER EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO NON-INFRINGEMENT, MERCHANTABILITY OR FIT FOR A PARTICULAR
PURPOSE.
See the Mulan PSL v1 for more details.
```
