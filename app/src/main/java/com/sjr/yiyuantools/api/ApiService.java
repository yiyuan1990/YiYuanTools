package com.sjr.yiyuantools.api;


import com.sjr.yiyuantools.base.BaseResponse;
import com.sjr.yiyuantools.entity.Login;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * api service
 */
public interface ApiService {
//    @FormUrlEncoded
//    @POST("1657-1")

//    //上传图片
//    @POST("file/up")
//    @Multipart
//    Observable<BaseResponse<List<UploadFile>>> upload(@Part List<MultipartBody.Part> parts);
    /**
     * 登录
     *
     * @param map
     * @return
     */
    @GET(Constant.APP_USER_LOGIN)
    Observable<BaseResponse<Login>> login(@QueryMap Map<String, String> map);


}
