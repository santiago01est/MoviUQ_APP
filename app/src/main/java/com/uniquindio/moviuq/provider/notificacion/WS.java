package com.uniquindio.moviuq.provider.notificacion;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface WS {

    @Headers({"Content-Type:application/json","Authorization:key=AAAAp1QX8SY:APA91bEaBh3YUYLtyPjwABa37KFomH2_nMNM6ny3PuJkVBfhqxdrp1bsCg4HZlB7SS3kMTu1jDA_st_k2R9F41h_XVTe9Xy0EcUIeso3gHyiL9szZkb652quMbWhQkjTw0GvtblZONw-"})
    @POST("fcm/send")
    Call<Response> enviarNotificacion(@Body Sender body);
}
