package fr.nohas.projettp3;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class AsyncHTTP extends AsyncTask<String, Integer, String> {
    private Context activity;

    AsyncHTTP(Context c){
        this.activity=c;
    }


    @Override
    protected String doInBackground(String... strings) {
        StringBuilder response = new StringBuilder();

        //Prepare the URL and the connection
        URL u = null;
        try {
            u = new URL(strings[0]);
            System.out.println("Affichage pas de pb");
        } catch (MalformedURLException e) {
            System.out.println("Affichage: pb mal formed url");
            e.printStackTrace();
        }

        HttpURLConnection conn = null;
        try {
            conn = (HttpURLConnection) u.openConnection();
            System.out.println("Affichage: Ok");
        } catch (IOException e) {
            System.out.println("Affichage: PB io1");
            e.printStackTrace();
        }

        try {
            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK)
            {
                //Get the Stream reader ready
                BufferedReader input = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                //Loop through the return data and copy it over to the response object to be processed
                String line = null;

                while ((line = input.readLine()) != null)
                {
                    response.append(line);
                }

                input.close();
            }
            System.out.println("Affichage Biennnn");
        } catch (IOException e) {
            System.out.println("Affichage PB io2");
            e.printStackTrace();
        }
        System.out.println("Affichage response !!!!!\n"+response);
        try {
            JSONObject obj = new JSONObject(response.toString());
            System.out.println("Affichage response ? \n"+response);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return response.toString();
    }


    private String readStream(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader r = new BufferedReader(new InputStreamReader(is));
        for (String line = r.readLine(); line != null; line =r.readLine()){
            sb.append(line);
        }
        is.close();
        return sb.toString();
    }
}
