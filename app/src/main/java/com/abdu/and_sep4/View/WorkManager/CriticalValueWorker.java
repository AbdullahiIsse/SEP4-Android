package com.abdu.and_sep4.View.WorkManager;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
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
import com.abdu.and_sep4.Repository.MeasurementsRepository;
import com.abdu.and_sep4.Shared.Measurements;
import com.abdu.and_sep4.Shared.SaveInfo;
import com.abdu.and_sep4.Shared.Terrarium;
import com.abdu.and_sep4.View.Main.MainActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CriticalValueWorker extends Worker {


    private static final String CHANNEL_ID = "channel-1";
    private MeasurementsRepository measurementsRepository;


    public CriticalValueWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        measurementsRepository = MeasurementsRepository.getInstance();
    }

    @NonNull
    @Override
    public Result doWork() {
        SharedPreferences sharedPreferencesTerrarium = getApplicationContext().getSharedPreferences("terrariumId", Context.MODE_PRIVATE);
        SharedPreferences sharedPreferencesCriticalValues = getApplicationContext().getSharedPreferences("CriticalValues", Context.MODE_PRIVATE);
        int id = sharedPreferencesTerrarium.getInt("id", 0);


        double tempMax = Double.parseDouble(sharedPreferencesCriticalValues.getString("tempMax", "0"));
        double tempMin = Double.parseDouble(sharedPreferencesCriticalValues.getString("tempMin", "0"));
        double humMax = Double.parseDouble(sharedPreferencesCriticalValues.getString("humMax", "0"));
        double humMin = Double.parseDouble(sharedPreferencesCriticalValues.getString("humMin", "0"));
        double co2Max = Double.parseDouble(sharedPreferencesCriticalValues.getString("co2Max", "0"));
        double co2Min = Double.parseDouble(sharedPreferencesCriticalValues.getString("co2Min", "0"));


        TerrariumApi terrariumApi = ServiceGenerator.getTerrariumApi();
        Call<List<Measurements>> call = terrariumApi.getMeasurementsByTerrariumId(id);

        call.enqueue(new Callback<List<Measurements>>() {
            @Override
            public void onResponse(Call<List<Measurements>> call, Response<List<Measurements>> response) {

                if (response.isSuccessful()) {
                    Log.e("worker", response.body().toString());
                    if (response.body() != null && !response.body().isEmpty()) {
                        if (response.body().get(response.body().size() - 1).getMeasurement_temp() >= tempMax) {
                            notification("Kritisk Temperatur tilstand", "Temperaturen har overstreget din grænse");
                        }

                        if (response.body().get( response.body().size()-1).getMeasurement_temp() <= tempMin){
                            notification("Kritisk Temperatur tilstand",  "Temperaturen har understeget din grænse");
                        }

                        if (response.body().get( response.body().size()-1).getMeasurement_air() >= humMax){
                            notification("Kritisk Luftfugtighed tilstand",  "Luftfugtigheden har overstreget din grænse");
                        }

                        if (response.body().get( response.body().size()-1).getMeasurement_air()  <= humMin){
                            notification("Kritisk Luftfugtighed tilstand",  "Luftfugtigheden har understeget din grænse");
                        }

                        if (response.body().get( response.body().size()-1).getMeasurement_co2() >= co2Max){
                            notification("Kritisk CO2 tilstand",  "CO2 har overstreget din grænse");
                        }

                        if (response.body().get( response.body().size()-1).getMeasurement_co2() <= co2Min){
                            notification("Kritisk CO2 tilstand",  "CO2 har understeget din grænse");
                        }

                    }


                }

            }

            @Override
            public void onFailure(Call<List<Measurements>> call, Throwable t) {
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
