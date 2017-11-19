package com.fpharma.findpharma.Servicos;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class FindPharmaBaseService {

    private static final FindPharmaBaseService ourInstance = new FindPharmaBaseService();
    private final String BASE_URL = "http://mobile-aceite.tcu.gov.br/mapa-da-saude/rest/";


    private Retrofit retrofit;

    public static FindPharmaBaseService getInstance() {
        return ourInstance;
    }

    private FindPharmaBaseService() {
        retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
    }

    public PharmaService getPharmaService(){
        return retrofit.create(PharmaService.class);
    }
}
