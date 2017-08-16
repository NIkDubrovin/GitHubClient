package com.nikdubrovin.list_of_projects_github;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * Created by NikDubrovin on 16.08.2017.
 */

public class GetGithubData extends AsyncTask<String,Void,JSONObject> {

    private final String TAG = "GitHubAPI";
    private final String URL_API = "https://api.github.com/users/";
    private URL url;
    private String User;
    private String Data;
   // https://api.github.com/users/yatingupta10/repos - список репозиториев с данными


    @Override
    protected JSONObject doInBackground(String... strings) {
        JSONObject jsonReturnFile = new JSONObject();

        try{

            //region Получаем  Json-строку( виде String) от сервера при помощи Http запроса

            String url_str = URL_API +  User + "/" + Data;

            if(validateUrl(url_str)) { // Проверка - является ли строка url-адресом
                url = new URL(url_str);
                Log.i(TAG,url_str);
            }
            else
                Log.i(TAG,"Failed URL!");

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            if (connection == null)
                Log.i(TAG,"Failed to make connection!");
            else
                Log.i(TAG,"Successfully to make connection!");
            connection.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
          //  Log.i(TAG, "Json File: " + response.toString()); -  Skipped 105 frames!  The application may be doing too much work on its main thread.

            //endregion Получение строки закончили

            JSONArray json = new JSONArray(response.toString()); // Превращаем String in JSONObject
                String name = json.getJSONObject(0).getString("name");
                Log.i(TAG, "NAME: " + name);
                String lang = json.getJSONObject(0).getString("language");
                Log.i(TAG, "Language: " + lang);
                String url_repos = json.getJSONObject(0).getString("html_url");
                Log.i(TAG, "URL_REPOS: " + url_repos);
                String description = json.getJSONObject(0).getString("description");
                Log.i(TAG, "Description: " + description);

                jsonReturnFile.put("name", name).put("url_repos", url_repos).put("description", description).put("language", lang);

        }catch (Exception e){
            e.printStackTrace();
        }
        catch (Throwable cause){
            cause.printStackTrace();
        }
        Log.i(TAG, "jsonReturn: " + jsonReturnFile);
        return jsonReturnFile;
    }

    public static boolean validateUrl(String adress){
        return android.util.Patterns.WEB_URL.matcher(adress).matches();
    }


    public void setUser(String User) {this.User = User;}
    public void setData(String Data) {this.Data = Data;}
}
