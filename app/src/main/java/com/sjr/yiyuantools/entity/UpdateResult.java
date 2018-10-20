package com.sjr.yiyuantools.entity;

import java.util.Date;

public class UpdateResult {

    /**
     * ret : 200
     * data : {"err_code":"0","err_msg":"","data":{"id":"1","uuid":"","add_time":"2018-10-01 17:29:10","update_time":{},"ext_data":{},"isUpdate":"1","newVersion":"1.0.1","apkSize":"5 M","isConstraint":"true","updateLog":"1，添加扫描功能。\\r\\n2，添加vip认证。\\r\\n3，新增了用户商店模块。\\r\\n4，添加地图导航功能。\\r\\n5，消费积分系统上线。","apkFileUrl":"https://raw.githubusercontent.com/WVector/AppUpdateDemo/master/apk/sample-debug.apk"}}
     * msg : 当前请求接口：App.Table.Get
     */

    private String ret;
    private DataBeanX data;
    private String msg;

    public String getRet() {
        return ret;
    }

    public void setRet(String ret) {
        this.ret = ret;
    }

    public DataBeanX getData() {
        return data;
    }

    public void setData(DataBeanX data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class DataBeanX {
        /**
         * err_code : 0
         * err_msg :
         * data : {"id":"1","uuid":"","add_time":"2018-10-01 17:29:10","update_time":{},"ext_data":{},"isUpdate":"1","newVersion":"1.0.1","apkSize":"5 M","isConstraint":"true","updateLog":"1，添加扫描功能。\\r\\n2，添加vip认证。\\r\\n3，新增了用户商店模块。\\r\\n4，添加地图导航功能。\\r\\n5，消费积分系统上线。","apkFileUrl":"https://raw.githubusercontent.com/WVector/AppUpdateDemo/master/apk/sample-debug.apk"}
         */

        private String err_code;
        private String err_msg;
        private DataBean data;

        public String getErr_code() {
            return err_code;
        }

        public void setErr_code(String err_code) {
            this.err_code = err_code;
        }

        public String getErr_msg() {
            return err_msg;
        }

        public void setErr_msg(String err_msg) {
            this.err_msg = err_msg;
        }

        public DataBean getData() {
            return data;
        }

        public void setData(DataBean data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * id : 1
             * uuid :
             * add_time : 2018-10-01 17:29:10
             * update_time : {}
             * ext_data : {}
             * isUpdate : 1
             * newVersion : 1.0.1
             * apkSize : 5 M
             * isConstraint : true
             * updateLog : 1，添加扫描功能。\r\n2，添加vip认证。\r\n3，新增了用户商店模块。\r\n4，添加地图导航功能。\r\n5，消费积分系统上线。
             * apkFileUrl : https://raw.githubusercontent.com/WVector/AppUpdateDemo/master/apk/sample-debug.apk
             */

            private String id;
            private String uuid;
//            private UpdateTimeBean add_time;
//            private UpdateTimeBean update_time;
            private ExtDataBean ext_data;
            private String isUpdate;
            private String newVersion;
            private String apkSize;
            private String isConstraint;
            private String updateLog;
            private String apkFileUrl;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getUuid() {
                return uuid;
            }

            public void setUuid(String uuid) {
                this.uuid = uuid;
            }


            public ExtDataBean getExt_data() {
                return ext_data;
            }

            public void setExt_data(ExtDataBean ext_data) {
                this.ext_data = ext_data;
            }

            public String getIsUpdate() {
                return isUpdate;
            }

            public void setIsUpdate(String isUpdate) {
                this.isUpdate = isUpdate;
            }

            public String getNewVersion() {
                return newVersion;
            }

            public void setNewVersion(String newVersion) {
                this.newVersion = newVersion;
            }

            public String getApkSize() {
                return apkSize;
            }

            public void setApkSize(String apkSize) {
                this.apkSize = apkSize;
            }

            public String getIsConstraint() {
                return isConstraint;
            }

            public void setIsConstraint(String isConstraint) {
                this.isConstraint = isConstraint;
            }

            public String getUpdateLog() {
                return updateLog;
            }

            public void setUpdateLog(String updateLog) {
                this.updateLog = updateLog;
            }

            public String getApkFileUrl() {
                return apkFileUrl;
            }

            public void setApkFileUrl(String apkFileUrl) {
                this.apkFileUrl = apkFileUrl;
            }

            public static class UpdateTimeBean {
            }

            public static class ExtDataBean {
            }
        }
    }
}
