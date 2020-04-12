package com.example.chatapp.Fragments;

import com.example.chatapp.Notifactions.MyResponse;
import com.example.chatapp.Notifactions.Sender;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

//accessing the api

public interface ApiService {


    @Headers(
            {
                    "Content-Type:application/json",
                    "Authorization:key=AAAAafdIDL4:APA91bFLukGWLzn9xDCgBhBlfdP7Qk2qaLi2FV19SBiL9D4VwwHKY9wgYdi3cNflFSWiPehxOaJNCsp9QQBjx8LpihnNJTDnxCBVo6vvRSA0J8phv4u5rN44FZHcg1nhxbIzDoK1rhwW"
            }
    )

    //sending the notification
    @POST("fcm/send")
    Call<MyResponse> sendNotification(@Body Sender body);
}
