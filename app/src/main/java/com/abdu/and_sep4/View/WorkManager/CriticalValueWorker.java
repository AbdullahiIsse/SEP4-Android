package com.abdu.and_sep4.View.WorkManager;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.abdu.and_sep4.API.ServiceGenerator;
import com.abdu.and_sep4.API.TerrariumApi;
import com.abdu.and_sep4.R;
import com.abdu.and_sep4.Shared.TemperatureMeasurement;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CriticalValueWorker extends Worker {


    private static final String CHANNEL_ID = "channel-1";



    public CriticalValueWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);

    }

    @NonNull
    @Override
    public Result doWork() {
        SharedPreferences sharedPreferencesTerrarium = getApplicationContext().getSharedPreferences("terrariumId", Context.MODE_PRIVATE);
        SharedPreferences sharedPreferencesCriticalValues = getApplicationContext().getSharedPreferences("CriticalValues", Context.MODE_PRIVATE);
        String id = sharedPreferencesTerrarium.getString("id", "");


        double tempMax = Double.parseDouble(sharedPreferencesCriticalValues.getString("tempMax", "0"));
        double tempMin = Double.parseDouble(sharedPreferencesCriticalValues.getString("tempMin", "0"));
//        double humMax = Double.parseDouble(sharedPreferencesCriticalValues.getString("humMax", "0"));
//        double humMin = Double.parseDouble(sharedPreferencesCriticalValues.getString("humMin", "0"));
//        double co2Max = Double.parseDouble(sharedPreferencesCriticalValues.getString("co2Max", "0"));
//        double co2Min = Double.parseDouble(sharedPreferencesCriticalValues.getString("co2Min", "0"));


        TerrariumApi terrariumApi = ServiceGenerator.getTerrariumApi();
        Call<List<TemperatureMeasurement>> call = terrariumApi.getTemperatureMeasurementByUserId("jack",id);

        call.enqueue(new Callback<List<TemperatureMeasurement>>() {
            @Override
            public void onResponse(Call<List<TemperatureMeasurement>> call, Response<List<TemperatureMeasurement>> response) {

                if (response.isSuccessful()) {
                    Log.e("worker", response.body().toString());
                    if (response.body() != null && !response.body().isEmpty()) {
                        if (response.body().get(response.body().size() - 1).getMeasurement() > tempMax) {
                            notification("Kritisk Temperatur tilstand", "Temperaturen har overstreget din grænse");
                        }

                        if (response.body().get( response.body().size()-1).getMeasurement() < tempMin){
                            notification("Kritisk Temperatur tilstand",  "Temperaturen har understeget din grænse");
                        }
                    }

                }

            }

            @Override
            public void onFailure(Call<List<TemperatureMeasurement>> call, Throwable t) {
                Log.e("Retrofit", "Something went wrong getting Temperature by Terrarium id :(");

            }
        });


        return Result.success();
    }

    public void notification(String title, String text) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel(getApplicationContext());
        }


        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_baseline_notifications_24)
                .setContentTitle(title)
                .setContentText(text)
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText(text))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
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
