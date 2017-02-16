package com.kuyue.daynightmode;

/**
 * Created by sen young on 2017/2/16 10:27.
 * 邮箱:595327086@qq.com.
 */

public enum DayNight {

    DAY("DAY", 0),
    NIGHT("NIGHT", 1);

    private String name;
    private int code;

    private DayNight(String name, int code) {
        this.name = name;
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
