package com.sjr.yiyuantools.entity;

public class QueryMapPicture {

    private String ret_code;
    private String remark;
    private String map_path;

    public String getRet_code() {
        return ret_code;
    }

    public void setRet_code(String ret_code) {
        this.ret_code = ret_code;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getMap_path() {
        return map_path;
    }

    public void setMap_path(String map_path) {
        this.map_path = map_path;
    }

    @Override
    public String toString() {
        return "QueryMapPicture{" +
                "ret_code='" + ret_code + '\'' +
                ", remark='" + remark + '\'' +
                ", map_path='" + map_path + '\'' +
                '}';
    }
}
