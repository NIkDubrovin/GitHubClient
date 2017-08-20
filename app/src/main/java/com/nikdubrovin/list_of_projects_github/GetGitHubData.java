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
import java.util.List;
import java.util.RandomAccess;

import static android.util.Log.i;

import static com.nikdubrovin.list_of_projects_github.GetGitHubData.StorageClass.selfArrayList_ListJSON_To_ListStringArray;
import static com.nikdubrovin.list_of_projects_github.GetGitHubData.StorageClass.CountRepos;



/**
 * Created by NikDubrovin on 16.08.2017.
 */

public class GetGitHubData extends AsyncTask<String,Void,Boolean> {

    private final String TAG = GetGitHubData.class.getSimpleName();
    private final String URL_API = "https://api.github.com/users/";
    private URL url;
    private String User;
    private String Data;
    private String selectLang;
    private boolean checkNull = false;
    private ArrayList<JSONObject> listJsonArray = new ArrayList<>();
    private static final int PERL_PAGE = 100; //Max = 100 item
    private int CountPage  = 1;
    private boolean ChangeReposUser;
    private boolean AddValueList = false;
    private boolean BanGitHub = false;

   // https://api.github.com/users/yatingupta10/repos - список репозиториев с данными

    @Override
     protected Boolean doInBackground(String... strings) {
        try {
            //region Получаем Json-строку(в виде String) от сервера при помощи Http запроса
            if(Data == null) Data = "";
            String url_str = URL_API + User + Data + "?per_page=" + Integer.toString(PERL_PAGE) + "&page=" + CountPage;
           // https://api.github.com/users/NIkDubrovin?per_page=100
          //  https://api.github.com/users/NIkDubrovin/repos?per_page=100

            if (validateUrl(url_str)) { // Проверка - является ли строка url-адресом
                url = new URL(url_str);
                i(TAG, url_str);
            } else
                i(TAG, "Failed URL!");

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            if (connection == null)
                i(TAG, "Failed to make connection!");
            else
                i(TAG, "Successfully to make connection!");

            connection.setRequestMethod("GET");

            StringBuilder response = new StringBuilder();
            try {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
             //   i(TAG, response.toString());

            in.close();
            }catch (IOException e){e.printStackTrace(); BanGitHub = true; }
            //endregion Получение строки закончили

            if(!AddValueList) {
                if (ChangeReposUser) {
                    JSONArray json = new JSONArray(response.toString()); // Превращаем String in JSONObject

                    for (int i = 0; i < json.length(); i++)
                        listJsonArray.add(json.getJSONObject(i));

                    selfArrayList_ListJSON_To_ListStringArray.clear();

                    for (int i = 0; i < listJsonArray.size(); i++) {
                        if (listJsonArray.get(i).getString("language").equals(selectLang) || selectLang.equals("All"))
                            selfArrayList_ListJSON_To_ListStringArray.add(
                                    new ListJSON_To_ListString(
                                            listJsonArray.get(i).
                                                    put("name", listJsonArray.get(i).getString("name")).
                                                    put("url_repos", listJsonArray.get(i).getString("html_url")).
                                                    put("description", CheckDesc(listJsonArray.get(i).getString("description"))).
                                                    put("language", listJsonArray.get(i).getString("language")).
                                                    put("fork", listJsonArray.get(i).getString("fork"))
                                    )
                            );
                        else
                            i(TAG, "language != selectLang " + listJsonArray.get(i).getString("name") + " / " + "false");
                    }

                    if (selfArrayList_ListJSON_To_ListStringArray.size() == 0) {
                        Log.i(TAG, "selfArrayList_ListJSON_To_ListStringArray = null");
                        checkNull = true;
                    } else Log.i(TAG, "selfArrayList_ListJSON_To_ListStringArray != null");

                    Log.i(TAG,"ChangeReposUser: " +  ChangeReposUser + "  / AddValueList: "  + AddValueList);
                } else {
                    JSONObject json_about = new JSONObject(response.toString()); // Превращаем String in JSONObject
                    CountRepos = 0;

                    CountRepos = json_about.getInt("public_repos");

                    Log.i(TAG,"ChangeReposUser: " +  ChangeReposUser + " / AddValueList: "  + AddValueList);
                    Log.i(TAG,"CountRepos : " + CountRepos + " = " + " In ChangeReposUser == false");
                }
            }else {
                JSONArray json = new JSONArray(response.toString()); // Превращаем String in JSONObject

                listJsonArray.clear();
                for (int i = 0; i < json.length(); i++)
                    listJsonArray.add(json.getJSONObject(i));

                Log.i(TAG, "CountRepos - selfArray.size() = " + (CountRepos - selfArrayList_ListJSON_To_ListStringArray.size()));
               final int temp_size = selfArrayList_ListJSON_To_ListStringArray.size();
                for (int i = 0; i < (CountRepos - temp_size); i++) {
                    if (listJsonArray.get(i).getString("language").equals(selectLang) || selectLang.equals("All"))
                        selfArrayList_ListJSON_To_ListStringArray.add(
                                new ListJSON_To_ListString(
                                        listJsonArray.get(i).
                                                put("name", listJsonArray.get(i).getString("name")).
                                                put("url_repos", listJsonArray.get(i).getString("html_url")).
                                                put("description", CheckDesc(listJsonArray.get(i).getString("description"))).
                                                put("language", listJsonArray.get(i).getString("language")).
                                                put("fork", listJsonArray.get(i).getString("fork"))
                                )
                        );
                    else
                        i(TAG, "language != selectLang " + listJsonArray.get(i).getString("name") + " / " + "false");
                }
                Log.i(TAG,"Count Repos: " +  CountRepos + " / selfArray.size(): "  + selfArrayList_ListJSON_To_ListStringArray.size() + " / temp_size : " + temp_size);
                Log.i(TAG,"ChangeReposUser: " +  ChangeReposUser + " / AddValueList: "  + AddValueList);
            }
            }catch(Exception e){
                e.printStackTrace();
            }
              catch(Throwable cause){
                cause.printStackTrace();
            }

            return checkNull;
        }


    public static boolean validateUrl(String adress ){
        return android.util.Patterns.WEB_URL.matcher(adress).matches();
    }

    public void setUser(String User) {this.User = User;}
    public void setData(String Data) {this.Data = "/" + Data;}
    public void setSelectLang(String selectLang) {this.selectLang = selectLang;}
    public String CheckDesc(String str) {
            if (str == null)
              str = "Description not found";
        return str;
    }
    public void setChangeReposUser(boolean changeReposUser) {this.ChangeReposUser = changeReposUser;}
    public void setCountPage(int CountPage) {this.CountPage = CountPage;}
    public int getCountPage() {return CountPage;}
    public void setAddValueList(boolean AddValueList) {this.AddValueList = AddValueList;}
    public boolean getBunGithub(){return BanGitHub;}


    static public class StorageClass{
        static ArrayList<ListJSON_To_ListString> selfArrayList_ListJSON_To_ListStringArray = new ArrayList<>();
        static int CountRepos;
        public StorageClass() {}

    }

     public static ArrayList<ListJSON_To_ListString> AddArraList(ArrayList<ListJSON_To_ListString> a, ArrayList<ListJSON_To_ListString> b) {
        if ((a == null) || (a.isEmpty() && (b != null))) return b;
        if ((b == null) || b.isEmpty()) return a;
        int aSize = a.size();
        int bSize = b.size();
        // Закладываем размер достаточный для всех элементов
          ArrayList<ListJSON_To_ListString> result = new ArrayList(aSize + bSize);
        // Если списки обеспечивают быстрый доступ к своим элементам, например ArrayList
        if ((a instanceof RandomAccess) && (b instanceof RandomAccess)) {
            for (int i = 0; i < aSize; i++) result.add(a.get(i));
            for (int i = 0; i < bSize; i++) result.add(b.get(i));
        } else {
            // А если это какие-то крестьянские списки, то копируем по-крестьянски
            result.addAll(a);
            result.addAll(b);
        }
        return result;
    }
}
