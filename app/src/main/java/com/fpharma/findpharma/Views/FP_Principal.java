package com.fpharma.findpharma.Views;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.fpharma.findpharma.Adaptadores.Adapter;
import com.fpharma.findpharma.Model.Pharma;
import com.fpharma.findpharma.R;
import com.fpharma.findpharma.Servicos.FindPharmaBaseService;
import com.fpharma.findpharma.Servicos.PharmaService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FP_Principal extends Activity {
    private RecyclerView recyclerView;
    private Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private PharmaService pharmaService;
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fp__principal);

        gson = new GsonBuilder().create();

        recyclerView = (RecyclerView) findViewById(R.id.listaMedicamentos);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new Adapter(new ArrayList<Pharma>(), this);
        recyclerView.setAdapter(mAdapter);


        pharmaService = FindPharmaBaseService.getInstance().getPharmaService();

        pharmaService.fetchEstabelecimentos().enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    JSONArray jsonArray = new JSONArray(response.body().string());
                    List<Pharma> farmacias = new ArrayList<>();
                    for(int i = 0; i < jsonArray.length(); i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        Pharma farmacia = gson.fromJson(jsonObject.toString(), Pharma.class);
                        farmacias.add(farmacia);
                    }

                    mAdapter.addAll(farmacias);

                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

}
