package com.kuyue.cardviewflash;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.kuyue.cardviewflash.adapter.BasePagerAdapter;
import com.kuyue.cardviewflash.entity.NewsFlash;
import com.kuyue.cardviewflash.entity.NewsHomePager;
import com.kuyue.cardviewflash.util.DensityUtils;
import com.kuyue.cardviewflash.util.GlideUtils;
import com.kuyue.cardviewflash.view.AutoScrollViewPager;
import com.kuyue.cardviewflash.view.GoodView;
import com.kuyue.cardviewflash.view.ZoomOutPageTransformer;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.view_pager)
    AutoScrollViewPager mViewPager;
    @Bind(R.id.flipper_flash)
    ViewFlipper mFlipperFlash;
    @Bind(R.id.img_btn)
    ImageButton mImgBtn;

    private ViewAdapter mViewAdapter;
    private List<View> mViews;
    private int mCurrentItem;
    private List<NewsHomePager> mNewsHomePagers = new ArrayList<>();
    private Context mContext;
    private MyCarOnClickListener mCarOnClickListener = new MyCarOnClickListener();

    //快讯
    private List<NewsFlash> mNewsFlashes = new ArrayList<>();

    //点赞
    private GoodView mGoodView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mContext = getApplicationContext();
        initData();
    }

    /**
     * 初始化数据
     */
    private void initData() {
        NewsHomePager newsHomePager = new NewsHomePager();
        newsHomePager.setImgUrl("/image/banner/a4c2a4b8-78b1-4fd5-8a8c-85048f6a5f7e.png");//http://www.appganhuo.com
        newsHomePager.setTitle("明年就不用再为追热点发愁了！");
        newsHomePager.setUrl("http://www.appganhuo.com/valentines-day/");
        mNewsHomePagers.add(newsHomePager);

        newsHomePager = new NewsHomePager();
        newsHomePager.setImgUrl("/image/banner/b58ab4e5-3a57-4b87-a1c9-f4c34eb0a0f4.png");
        newsHomePager.setTitle("微博报告");
        newsHomePager.setUrl("http://t.cn/RJ5wf8S");
        mNewsHomePagers.add(newsHomePager);

        newsHomePager = new NewsHomePager();
        newsHomePager.setImgUrl("/image/banner/424cddbf-ce8d-499a-9be5-86d3205d7230.png");
        newsHomePager.setTitle("粉丝经济");
        newsHomePager.setUrl("http://www.appganhuo.com/fans-pass/");
        mNewsHomePagers.add(newsHomePager);

        List temps = (List) mNewsHomePagers;
        if (temps != null && !temps.isEmpty()) {
            if (this.mViews == null) {
                this.mViews = new ArrayList<>();
            } else {
                this.mViews.clear();
            }
            if (mNewsHomePagers.size() > 1) {
                addView(mNewsHomePagers.get(mNewsHomePagers.size() - 1));
            }
            for (int i = 0; i < mNewsHomePagers.size(); i++) {
                addView(mNewsHomePagers.get(i));
            }
            if (mNewsHomePagers.size() > 1) {
                addView(mNewsHomePagers.get(0));
            }
            Log.e("test", "mViews:" + mViews.size());
            mViewAdapter = new ViewAdapter(mContext, this.mViews);
            mViewPager.setAdapter(mViewAdapter);
            mViewPager.setPageMargin(DensityUtils.dp2px(mContext, 10));//控制卡片之间的空白间隙
            mViewPager.setPageTransformer(true, new ZoomOutPageTransformer(mViewPager));
            mViewPager.setOnPageChangeListener(new MyOnPageChangeListener());
            mViewPager.setCardSlidingInterval(4000);//设置滑动间隔
            mViewPager.setOffscreenPageLimit(mViews.size());
            mViewPager.setCurrentItem(1, true);
            mViewPager.startAutoScroll();
//            mViewPager.setStopScrollWhenTouch(true);

            //快讯
            NewsFlash newsFlash = new NewsFlash();
            newsFlash.setTitle("23333");
            newsFlash.setUrl("1111url");
            mNewsFlashes.add(newsFlash);

            newsFlash = new NewsFlash();
            newsFlash.setTitle("66666");
            newsFlash.setUrl("2222url");
            mNewsFlashes.add(newsFlash);

            initNewsFlash();

            mGoodView = new GoodView(mContext);
        }
    }

    private void initNewsFlash() {
        for (int i = 0; i < mNewsFlashes.size(); i++) {
            View inflate = getLayoutInflater().inflate(R.layout.item_broad_cast, null, false);
            TextView textView = (TextView) inflate.findViewById(R.id.tv_flash_content);
            textView.setText(mNewsFlashes.get(i).getTitle());
            textView.setTag(mNewsFlashes.get(i).getUrl());
            textView.setOnClickListener(mCarOnClickListener);
            mFlipperFlash.addView(inflate);
        }
        mFlipperFlash.setInAnimation(AnimationUtils.loadAnimation(mContext, R.anim.bottom_in_600));
        mFlipperFlash.setOutAnimation(AnimationUtils.loadAnimation(mContext, R.anim.out_top_600));
        mFlipperFlash.startFlipping();
    }

    private void addView(NewsHomePager newsHomePager) {
        View inflate = getLayoutInflater().inflate(R.layout.item_home_pager_view, null, false);
        ImageView imageView = (ImageView) inflate.findViewById(R.id.iv_home_pager);
        TextView tvTitle = (TextView) inflate.findViewById(R.id.tv_title);
        if (TextUtils.isEmpty(newsHomePager.getTitle())) {
            tvTitle.setVisibility(View.GONE);
        } else {
            tvTitle.getPaint().setFakeBoldText(true);
            tvTitle.setVisibility(View.VISIBLE);
            tvTitle.setText(newsHomePager.getTitle());
        }
        GlideUtils.getInstance().disCenterCrop(mContext, "http://www.appganhuo.com" + newsHomePager.getImgUrl(), imageView);
        imageView.setTag(newsHomePager.getUrl());
        imageView.setOnClickListener(mCarOnClickListener);//添加点击事件
        mViews.add(inflate);
    }

    @OnClick(R.id.img_btn)
    public void onClick() {
        mImgBtn.setImageResource(R.mipmap.good_checked);
        mGoodView.setText("+1");
        mGoodView.show(mImgBtn);
    }

    class MyCarOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Toast.makeText(mContext, view.getTag().toString(), Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * adapter
     */
    public static class ViewAdapter extends BasePagerAdapter<View> {
        public ViewAdapter(Context context, List<View> list) {
            super(context, list);
        }

        @Override
        public View initItem(int position) {
            return this.mList.get(position);
        }

        @Override
        public int getItemPosition(Object obj) {
            return POSITION_NONE;
        }
    }

    /**
     * 监听
     */
    public class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageSelected(int position) {
            if (mViewAdapter.getCount() != 0) {
                mCurrentItem = position;
                if (position == 0) {
                    mCurrentItem = mViewAdapter.getCount() - 2;
                } else if (position == mViewAdapter.getCount() - 1) {
                    mCurrentItem = 1;
                }
            }
        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            if (mViewAdapter.getCount() != 0) {
                if (state == 1) {
                    stopAutoScroll();
                } else if (state == 0) {
                    mViewPager.setCurrentItem(mCurrentItem, false);
                    startAutoScroll();
                }
            }
        }
    }

    public void stopAutoScroll() {
        if (mViewPager != null) {
            mViewPager.stopAutoScroll();
        }
    }

    public void startAutoScroll() {
        if (mViewPager != null) {
            mViewPager.startAutoScroll();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopAutoScroll();
    }

    @Override
    protected void onResume() {
        super.onResume();
        startAutoScroll();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
