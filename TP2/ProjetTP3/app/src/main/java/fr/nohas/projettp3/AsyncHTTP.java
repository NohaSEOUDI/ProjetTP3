package fr.nohas.projettp3;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

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
        URL url = null;
        HttpURLConnection urlConnection = null;
        String result = null;
        try {
            url = new URL(strings[0]);
            urlConnection = (HttpURLConnection) url.openConnection(); // Open ;;;??PBBBB
            InputStream in = new BufferedInputStream(urlConnection.getInputStream()); // Stream
            System.out.println("Affichage PB Bien");
            result = readStream(in); // Read stream
        }
        catch (MalformedURLException e) {
             System.out.println("Affichage 1er Catch");
             e.printStackTrace(); }
        catch (IOException e) {
            System.out.println("Affichage 2eme catch");
            e.printStackTrace();
        }
        finally {
             System.out.println("Affichage Finally Catch");
             if (urlConnection != null)
                 urlConnection.disconnect();
        }return result; // returns the result
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
