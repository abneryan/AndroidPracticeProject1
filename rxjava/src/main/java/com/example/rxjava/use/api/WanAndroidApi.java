package com.example.rxjava.use.api;

import com.example.rxjava.use.bean.ProjectBean;
import com.example.rxjava.use.bean.ProjectItemBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * @Auther: yanguoqing
 * @Date: 2023/8/12 00:58
 * @Description:
 */
public interface WanAndroidApi {
    @GET("project/tree/json")
    Observable<ProjectBean> getProject();

    @GET("project/list/{pageIndex}/json")
    Observable<ProjectItemBean> getProjectItem(@Path("pageIndex") int pageIndex, @Query("cid") int cid);
}
