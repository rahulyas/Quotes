package com.example.quotes;

import android.content.Context;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class RequestManager {
    Context context;
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://type.fit/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public RequestManager(Context context) {
        this.context = context;
    }

    public void GetALlQutoes(QuoteResponseListener listener){
        CallQutoes callQutoes = retrofit.create(CallQutoes.class);
        Call<List<QutoeResponse>> call = callQutoes.callQutoes();
        call.enqueue(new Callback<List<QutoeResponse>>() {
            @Override
            public void onResponse(Call<List<QutoeResponse>> call, Response<List<QutoeResponse>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(context, "Request not Successful !", Toast.LENGTH_SHORT).show();
                    return;
                }
                listener.didFetch(response.body(), response.message());
            }

            @Override
            public void onFailure(Call<List<QutoeResponse>> call, Throwable t) {
                listener.didError(t.getMessage());

            }
        });
    }

    private interface CallQutoes{
        @GET("api/quotes")
        Call<List<QutoeResponse>> callQutoes();
    }
}
