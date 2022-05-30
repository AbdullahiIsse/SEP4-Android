package com.abdu.and_sep4.API;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.core.os.HandlerCompat;

import com.microsoft.signalr.HubConnection;
import com.microsoft.signalr.HubConnectionBuilder;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import io.reactivex.Completable;
import io.reactivex.Single;

public class SignalRApi {

    private static SignalRApi instance;
    ExecutorService executorService = Executors.newFixedThreadPool(1);
    Handler mainThreadHandler = HandlerCompat.createAsync(Looper.getMainLooper());
    private HubConnection hubConnection;


    public static SignalRApi getInstance() {
        if (instance == null) {
            instance = new SignalRApi();
        }
        return instance;
    }


    public void feedAnimal(String eui) {
        executorService.execute(() -> {
            hubConnection = HubConnectionBuilder.create("https://terraeyes.azurewebsites.net/AppHub").build();

            hubConnection.start().blockingAwait();

            hubConnection.invoke(Void.class, "Feed", eui);


            hubConnection.close();
        });

    }


}
