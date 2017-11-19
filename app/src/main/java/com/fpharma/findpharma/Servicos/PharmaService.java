package com.fpharma.findpharma.Servicos;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface PharmaService {
    @GET("estabelecimentos/?categoria=FARMACIA")
    Call<ResponseBody> fetchEstabelecimentos();
}
