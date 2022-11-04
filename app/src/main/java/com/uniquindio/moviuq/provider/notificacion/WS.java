package com.uniquindio.moviuq.provider.notificacion;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface WS {

    @Headers({"Content-Type:application/json","Authorization:key=AIzaSyCPX_cbwOc6WH7Er_RgzSZ9brwAY3X2uBQ"})
    @POST("fcm/send")
    Call<Response> enviarNotificacion(@Body Sender body);
}
