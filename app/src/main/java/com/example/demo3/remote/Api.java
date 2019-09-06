package com.example.demo3.remote;


import com.example.demo3.entity.MedicalRecord;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * 2016/11/2 11
 */
public interface Api {

    String BASE_URL = "http://192.168.199.22/dthealth/web/";

    //病历
    @GET("web.DHCTools.CspPage.PDF.cls")
    Observable<Response<List<MedicalRecord>>> getMedicalRecord(@Query("EpisodeID") String EpisodeID);

}
