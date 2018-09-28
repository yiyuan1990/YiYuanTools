package com.sjr.yiyuantools.entity;

public class Login {
    private String time;
    private String ftime;
    private String context;
    private Object location;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getFtime() {
        return ftime;
    }

    public void setFtime(String ftime) {
        this.ftime = ftime;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public Object getLocation() {
        return location;
    }

    public void setLocation(Object location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Login{" +
                "time='" + time + '\'' +
                ", ftime='" + ftime + '\'' +
                ", context='" + context + '\'' +
                ", location=" + location +
                '}';
    }
}
