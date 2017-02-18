package com.kuyue.cardviewflash.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sen young on 2017/2/16 17:36.
 * 邮箱:595327086@qq.com.
 */

public abstract class BasePagerAdapter<T> extends PagerAdapter {
    public List<T> mList = new ArrayList();
    public int mSize;
    public Context mContext;

    public abstract View initItem(int i);

    public BasePagerAdapter(Context context, List<T> list) {
        this.mContext = context;
        setList(list);
    }

    public void setList(List<T> list) {
        if (list != null) {
            this.mList.clear();
            this.mList.addAll(list);
            notifyDataSetChanged();
        }
    }

    @Override
    public int getCount() {
        int size = this.mList.size();
        this.mSize = size;
        return size;
    }

    @Override
    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    @Override
    public Object instantiateItem(ViewGroup viewGroup, int i) {
        View initItem = initItem(i);
        viewGroup.addView(initItem);
        return initItem;
    }

    @Override
    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        viewGroup.removeView((View) obj);
    }
}