package com.tutrieuchau.letgo.Service;

import android.content.Context;
import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by TuTrieuChau on 2017/06/26.
 */

public class Service extends AsyncTask<JSONObject,Void,JSONObject> {
    private Context context;
    ServiceCallback serviceCallback;
    public Service(Context context, ServiceCallback serviceCallback){
        this.context = context;
        this.serviceCallback = serviceCallback;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        //TODO: show Load process
    }

    @Override
    protected JSONObject doInBackground(JSONObject... params) {

        try {
                URL url = new URL("http://127.0.0.1/letgo/android/api");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setConnectTimeout(10000);
                connection.setReadTimeout(10000);
                connection.setRequestMethod("POST");
                connection.setDoInput(true);
                connection.setDoOutput(true);
                connection.setRequestProperty("Content-Type","application/json");

                OutputStream os = connection.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os,"utf-8"));
                writer.write(params[0].toString());
                writer.flush();
                writer.close();
                os.close();

                if(connection.getResponseCode() == HttpURLConnection.HTTP_OK){
                    BufferedReader in=new BufferedReader(
                            new InputStreamReader(
                                    connection.getInputStream()));
                    StringBuffer sb = new StringBuffer("");
                    String line="";

                    while((line = in.readLine()) != null) {

                        sb.append(line);
                        break;
                    }
                    in.close();
                    connection.disconnect();
                    return new JSONObject(sb.toString());
                }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(JSONObject result) {
        super.onPostExecute(result);
        serviceCallback.onProcessComplete(result);
        //TODO: Hide Load Process
    }
 private interface ServiceCallback{
     void onProcessComplete(JSONObject result);
 }

}

