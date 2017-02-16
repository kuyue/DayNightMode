package com.kuyue.cardviewflash;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kuyue.cardviewflash.adapter.BasePagerAdapter;
import com.kuyue.cardviewflash.adapter.BaseViewHolder;
import com.kuyue.cardviewflash.entity.NewsHomePager;
import com.kuyue.cardviewflash.util.DensityUtils;
import com.kuyue.cardviewflash.view.AutoScrollViewPager;
import com.kuyue.cardviewflash.view.ZoomOutPageTransformer;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by sen young on 2017/2/16 17:32.
 * 邮箱:595327086@qq.com.
 */

public class NewsHomePagerViewHolder extends BaseViewHolder<List<NewsHomePager>> {
    private ViewAdapter a;
    private List<View> b;
    private List<NewsHomePager> c = new ArrayList();
    private int f;

    @Bind(R.id.view_pager)
    AutoScrollViewPager viewPager;

    public class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {
        final /* synthetic */ NewsHomePagerViewHolder a;

        public MyOnPageChangeListener(NewsHomePagerViewHolder newsHomePagerViewHolder) {
            this.a = newsHomePagerViewHolder;
        }

        public void onPageSelected(int i) {
            if (this.a.a.getCount() != 0) {
                this.a.f = i;
                if (i == 0) {
                    this.a.f = this.a.a.getCount() - 2;
                } else if (i == this.a.a.getCount() - 1) {
                    this.a.f = 1;
                }
            }
        }

        public void onPageScrolled(int i, float f, int i2) {
        }

        public void onPageScrollStateChanged(int i) {
            if (this.a.a.getCount() != 0) {
                if (i == 1) {
                    this.a.stopAutoScroll();
                } else if (i == 0) {
                    this.a.viewPager.setCurrentItem(this.a.f, false);
                    this.a.startAutoScroll();
                }
            }
        }
    }

    public static class ViewAdapter extends BasePagerAdapter<View> {
        public ViewAdapter(Context context, List<View> list) {
            super(context, list);
        }

        public View initItem(int i) {
            return (View) this.d.get(i);
        }

        public int getItemPosition(Object obj) {
            return -2;
        }
    }

    public NewsHomePagerViewHolder(Context context, ViewGroup viewGroup, View.OnClickListener onClickListener) {
        super(context, R.layout.item_article_view_pager, viewGroup, onClickListener, false);
    }

    public void bind(List<NewsHomePager> list) {
        List temps = (List) list;
        if (temps != null && !temps.isEmpty()) {
            if (this.b == null) {
                this.b = new ArrayList();
            } else {
                this.b.clear();
            }
            if (list.size() > 1) {
                a((NewsHomePager) list.get(list.size() - 1));
            }
            for (int i = 0; i < list.size(); i++) {
                a((NewsHomePager) list.get(i));
            }
            if (list.size() > 1) {
                a((NewsHomePager) list.get(0));
            }
            this.a = new ViewAdapter(this.e, this.b);
            this.viewPager.setAdapter(new ViewAdapter(this.e, this.b));
            this.viewPager.setPageMargin((int) DensityUtils.px2dp(e, 10));
            this.viewPager.setPageTransformer(true, new ZoomOutPageTransformer(this.viewPager));
            this.viewPager.setOnPageChangeListener(new MyOnPageChangeListener(this));
            this.viewPager.setInterval(4000);
            this.viewPager.setOffscreenPageLimit(this.b.size());
            this.viewPager.setCurrentItem(1, true);
            this.viewPager.startAutoScroll();
        }
    }

    private void a(NewsHomePager newsHomePager) {
        View inflate = LayoutInflater.from(e).inflate(R.layout.item_home_pager_view, null, false);
        ImageView imageView = (ImageView) inflate.findViewById(R.id.iv_home_pager);
        TextView textView = (TextView) inflate.findViewById(R.id.tv_title);
        TextView textView2 = (TextView) inflate.findViewById(R.id.tv_adv);
        if (TextUtils.isEmpty(newsHomePager.getTitle())) {
            textView.setVisibility(View.GONE);
        } else {
            textView.getPaint().setFakeBoldText(true);
            textView.setVisibility(View.VISIBLE);
            textView.setText(newsHomePager.getTitle());
        }
        if (TextUtils.isEmpty(newsHomePager.getAdUrl())) {//不是广告
            // TODO: 2017/2/16
//            p.instance().disCenterCrop(this.e, newsHomePager.getImg(), imageView);
//            imageView.setId(R.id.image_pager_cover);
//            imageView.setTag(R.id.image_pager_cover, newsHomePager.getUrl());
//            imageView.setTag(R.id.image_pager_cover_article, Integer.valueOf(newsHomePager.getId()));
//            textView2.setVisibility(View.GONE);
        } else {//有可能是广告
//            if (newsHomePager.getAdUrl().contains("36kr.com/topic")) {
//                p.instance().disCenterCrop(this.e, newsHomePager.getImg(), imageView);
//            } else {
//                p.instance().disCenterCrop(this.e, newsHomePager.getImgUrl(), imageView);
//            }
//            imageView.setId(R.id.image_pager_cover);
//            imageView.setTag(R.id.image_pager_cover, newsHomePager.getAdUrl());
//            imageView.setTag(R.id.image_pager_cover_article, Integer.valueOf(0));
//            Column column = newsHomePager.getColumn();
//            if (column == null || TextUtils.isEmpty(column.name)) {
//                textView2.setVisibility(8);
//            } else {
//                textView2.setVisibility(0);
//                textView2.setText(column.name);
//            }
        }
        imageView.setOnClickListener(this.d);
        this.b.add(inflate);
    }

    public void stopAutoScroll() {
        if (this.viewPager != null) {
            this.viewPager.stopAutoScroll();
        }
    }

    public void startAutoScroll() {
        if (this.viewPager != null) {
            this.viewPager.startAutoScroll();
        }
    }
}
