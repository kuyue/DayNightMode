package com.kuyue.daynightmode;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by sen young on 2017/2/16 10:46.
 * 邮箱:595327086@qq.com.
 */
public class AuthorActivity extends AppCompatActivity {

    private DayNightHelper mDayNightHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        initTheme();
        setContentView(R.layout.activity_author);
    }

    private void initData() {
        mDayNightHelper = new DayNightHelper(this);
    }

    private void initTheme() {
        if (mDayNightHelper.isDay()) {
            setTheme(R.style.DayTheme);
        } else {
            setTheme(R.style.NightTheme);
        }
    }

}
