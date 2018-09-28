package com.sjr.yiyuantools.api;


import com.sjr.yiyuantools.base.BaseResponse;
import com.sjr.yiyuantools.entity.Login;
import com.sjr.yiyuantools.entity.QueryMapPicture;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * api service
 */
public interface ApiService {

    //    @POST("query")
////    @POST("province-count")
//    Observable<BaseResponse<List<Login>>> login(@QueryMap Map<String, String> map);
//
    @FormUrlEncoded
    @POST("query")
    Observable<BaseResponse<List<Login>>> logout(@QueryMap Map<String, String> map);

    // 登录的请求
    @FormUrlEncoded
    @POST("loginManage/login")
    Observable<BaseResponse<Login>> login(@QueryMap Map<String, String> map);

    //    //上传图片
//    @POST("file/up")
//    @Multipart
//    Observable<BaseResponse<List<UploadFile>>> upload(@Part List<MultipartBody.Part> parts);

    @FormUrlEncoded
    @POST("1657-1")
    Observable<BaseResponse<QueryMapPicture>> query(@FieldMap Map<String, String> map);

}
