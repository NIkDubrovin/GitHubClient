package com.nikdubrovin.list_of_projects_github;


import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import static android.util.Log.i;

/**
 * Created by NikDubrovin on 16.08.2017.
 */

public class GetGitHubData extends AsyncTask<String,Void,ArrayList<JSONObject>> {

    private final String TAG = GetGitHubData.class.getSimpleName();
    private final String URL_API = "https://api.github.com/users/";
    private URL url;
    private String User;
    private String Data;
    private String selectLang;
    private ArrayList<JSONObject> listJsonArray = new ArrayList<>();
   // https://api.github.com/users/yatingupta10/repos - список репозиториев с данными

    @Override
     protected ArrayList<JSONObject> doInBackground(String... strings) {

        try{

            //region Получаем  Json-строку( виде String) от сервера при помощи Http запроса

            String url_str = URL_API + User + "/" + Data;

            if(validateUrl(url_str)) { // Проверка - является ли строка url-адресом
                url = new URL(url_str);
                i(TAG,url_str);
            }
            else
                i(TAG,"Failed URL!");

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            if (connection == null)
                i(TAG,"Failed to make connection!");
            else
                i(TAG,"Successfully to make connection!");
            connection.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            //endregion Получение строки закончили
            JSONArray json = new JSONArray(response.toString()); // Превращаем String in JSONObject

            for(int i= 0;i < json.length();i++)
                listJsonArray.add(json.getJSONObject(i));

            for (int i = 0;i < listJsonArray.size();i++) {
                if(listJsonArray.get(i).getString("language").toString() == selectLang){
                   /* listJsonArray.get(i).
                            put("name",listJsonArray.get(i).getString("name")).
                            put("url_repos", listJsonArray.get(i).getString("html_url")).
                            put("description", CheckDesc(listJsonArray.get(i).getString("description"))).
                            put("language", listJsonArray.get(i).getString("language")).
                            put("fork", listJsonArray.get(i).getString("fork"));*/
                } else  i(TAG,"InFor: " + listJsonArray.get(i).getString("name") + " / " + "false" );
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        catch (Throwable cause){
            cause.printStackTrace();
        }

        return listJsonArray;
    }

    public static boolean validateUrl(String adress){
        return android.util.Patterns.WEB_URL.matcher(adress).matches();
    }

    public void setUser(String User) {this.User = User;}
    public void setData(String Data) {this.Data = Data;}
    public void setLang(String selectLang) {this.selectLang = selectLang;}
    public String CheckDesc(String str) {
            if (str.equals(""))
              str = "Description not found";
        return str;
    }

}
