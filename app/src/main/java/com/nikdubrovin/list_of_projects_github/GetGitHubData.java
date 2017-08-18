package com.nikdubrovin.list_of_projects_github;


import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import static android.R.attr.name;
import static android.R.id.list;
import static android.util.Log.i;

import static com.nikdubrovin.list_of_projects_github.GetGitHubData.StorageClass.selfArrayList_ListJSON_To_ListStringArray;

/**
 * Created by NikDubrovin on 16.08.2017.
 */

public class GetGitHubData extends AsyncTask<String,Void,Boolean> {

    private final String TAG = GetGitHubData.class.getSimpleName();
    private final String URL_API = "https://api.github.com/users/";
    private URL url;
    private URL url_about;
    private String User;
    private String Data;
    private String selectLang;
    private boolean checkNull = false;
    private ArrayList<JSONObject> listJsonArray = new ArrayList<>();
    private int per_page = 1000; // Yet

   // https://api.github.com/users/yatingupta10/repos - список репозиториев с данными

    @Override
     protected Boolean doInBackground(String... strings) {
        try{

            //region Получаем Json-строку(в виде String) от сервера при помощи Http запроса
            String url_about_user =  URL_API + User;
            String url_str = URL_API + User + "/" + Data + "?per_page=" + Integer.toString(per_page);

            if(validateUrl(url_str) && validateUrl(url_about_user)) { // Проверка - является ли строка url-адресом
                url = new URL(url_str);
                url_about = new URL(url_about_user);
                i(TAG,url_str + " / " + url_about_user);
            }
            else
                i(TAG,"Failed URL!");

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                if (connection == null/* && connection_about == null*/)
                    i(TAG, "Failed to make connection!");
                else
                    i(TAG, "Successfully to make connection!");

             connection.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

               while ((inputLine = in.readLine()) != null ) {
                   response.append(inputLine);
               }

            in.close();

//                StringBuilder response_about = new StringBuilder();
//
//                HttpURLConnection connection_about = (HttpURLConnection) url_about.openConnection();
//                if (connection_about == null)
//                    i(TAG, "Failed to make connection!");
//                else
//                    i(TAG, "Successfully to make connection!");
//
//                connection_about.setRequestMethod("GET");
//
//                     BufferedReader in_about = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//                    String inputLine_about;
//
//                    while ((inputLine_about = in_about.readLine()) != null) {
//                        response_about.append(inputLine_about);
//                    }
//            System.out.println(TAG + " / " + response_about);
//                  in_about.close();

            //endregion Получение строки закончили

            JSONArray json = new JSONArray(response.toString()); // Превращаем String in JSONObject
       //     JSONObject json_about = new JSONObject(response_about.toString()); // Превращаем String in JSONObject

            for(int i= 0;i < json.length();i++)
                listJsonArray.add(json.getJSONObject(i));


            selfArrayList_ListJSON_To_ListStringArray.clear();


            for (int i = 0;i < listJsonArray.size();i++) {
                if(listJsonArray.get(i).getString("language").equals(selectLang) || selectLang.equals("All"))
                    selfArrayList_ListJSON_To_ListStringArray.add(
                            new ListJSON_To_ListString(
                                    listJsonArray.get(i).
                                            put("name",listJsonArray.get(i).getString("name")).
                                            put("url_repos", listJsonArray.get(i).getString("html_url")).
                                            put("description", CheckDesc(listJsonArray.get(i).getString("description"))).
                                            put("language", listJsonArray.get(i).getString("language")).
                                            put("fork", listJsonArray.get(i).getString("fork"))
                            )
                    );
                 else i(TAG,"language != selectLang " + listJsonArray.get(i).getString("name") + " / " + "false" );
            }

            if (selfArrayList_ListJSON_To_ListStringArray.size() == 0) {
                Log.i(TAG, "selfArrayList_ListJSON_To_ListStringArray = null");
                checkNull = true;
            }
            else  Log.i(TAG,"selfArrayList_ListJSON_To_ListStringArray != null");

        }catch (Exception e){
            e.printStackTrace();
        }
        catch (Throwable cause){
            cause.printStackTrace();
        }

        return checkNull;
    }

    public static boolean validateUrl(String adress ){
        return android.util.Patterns.WEB_URL.matcher(adress).matches();
    }

    public void setUser(String User) {this.User = User;}
    public void setData(String Data) {this.Data = Data;}
    public void setSelectLang(String selectLang) {this.selectLang = selectLang;}
    public String CheckDesc(String str) {
            if (str == null)
              str = "Description not found";
        return str;
    }

    static public class StorageClass{
        static ArrayList<ListJSON_To_ListString> selfArrayList_ListJSON_To_ListStringArray = new ArrayList<>();
        public StorageClass() {}

    }
}
