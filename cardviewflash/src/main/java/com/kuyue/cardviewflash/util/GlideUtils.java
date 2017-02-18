package com.kuyue.cardviewflash.util;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.kuyue.cardviewflash.R;

/**
 * 图片处理工具类
 * Created by sen young on 2017/2/17 11:18.
 * 邮箱:59527086@qq.com.
 */

public class GlideUtils {

    private static GlideUtils instance = null;

    public static GlideUtils getInstance() {
        if (instance == null) {
            synchronized (GlideUtils.class) {
                if (instance == null) {
                    instance = new GlideUtils();
                }
            }
        }
        return instance;
    }

    protected GlideUtils() {
    }

    public void disCenterCrop(Context context, String str, ImageView imageView) {
        Log.e("test", "img: "+str);
        Glide.with(context).load(str).placeholder(R.color.c_F0F0F0).centerCrop().animate(R.anim.no_thing).into(imageView);
    }


}
