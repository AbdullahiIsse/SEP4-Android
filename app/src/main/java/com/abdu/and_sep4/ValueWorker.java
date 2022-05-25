package com.abdu.and_sep4;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.abdu.and_sep4.API.ServiceGenerator;
import com.abdu.and_sep4.API.TerrariumApi;
import com.abdu.and_sep4.Repository.MeasurementsRepository;
import com.abdu.and_sep4.Shared.Measurements;
import com.abdu.and_sep4.View.Main.MainActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ValueWorker extends Worker {

    private static final String CHANNEL_ID = "dsd";
    private MeasurementsRepository measurementsRepository;


    public ValueWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        measurementsRepository = MeasurementsRepository.getInstance();
    }

    @NonNull
    @Override
    public Result doWork() {
        TerrariumApi terrariumApi = ServiceGenerator.getTerrariumApi();
        Call<List<Measurements>> call = terrariumApi.getMeasurementsByTerrariumId(1);

        call.enqueue(new Callback<List<Measurements>>() {
            @Override
            public void onResponse(Call<List<Measurements>> call, Response<List<Measurements>> response) {

                if (response.isSuccessful()) {
                    Log.e("worker", response.body().toString());
                    if (response.body().get( response.body().size()-1).getMeasurement_temp() == 41.14)
                   notification("Kritisk Temperatur tilstand",  "Temperaturen har overstreget din grænse" );


                }

            }

            @Override
            public void onFailure(Call<List<Measurements>> call, Throwable t) {
                Log.e("Retrofit", "Something went wrong getting Temperature by Terrarium id :(");

            }
        });


        return Result.success();
    }


    public void notification(String title,String text) {


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
          createNotificationChannel(getApplicationContext());
        }


        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_baseline_notifications_24)
                .setContentTitle(title)
                .setContentText(text)
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText(text))
                .setPriority(NotificationCompat.PRIORITY_HIGH);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplicationContext());

        notificationManager.notify(1, builder.build());

    }

    private void createNotificationChannel(Context context) {


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "ch½";
            String description = "test";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}
