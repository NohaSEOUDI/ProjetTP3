package fr.nohas.projettp3;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;

import androidx.annotation.Nullable;

import org.json.JSONException;
import org.json.JSONObject;

//ou appeller cette classe ??
public class MyIntentService extends IntentService {
    public String FILE_Name="example.json";
    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            try {
                JSONObject obj = new JSONObject(FILE_Name);
                String name=obj.getString("Nom");
                System.out.println(name);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}