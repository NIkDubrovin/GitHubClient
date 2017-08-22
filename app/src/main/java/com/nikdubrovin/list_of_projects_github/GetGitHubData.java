package com.nikdubrovin.list_of_projects_github;


import android.os.AsyncTask;
import android.support.v4.app.NotificationCompatSideChannelService;
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

import static android.util.Log.i;

import static com.nikdubrovin.list_of_projects_github.GetGitHubData.StorageClass.selfArrayList_ListJSON_To_ListStringArray;
import static com.nikdubrovin.list_of_projects_github.GetGitHubData.StorageClass.CountRepos;

/**
 * Created by NikDubrovin on 16.08.2017.
 */

public class GetGitHubData extends AsyncTask<String,Void,Boolean> {

    private final String TAG = GetGitHubData.class.getSimpleName();
    private final String URL_API = "https://api.github.com/users/";
    private String url_str;
    private URL url;
    private String User;
    private String Data;
    private String selectLang;
    private ArrayList<JSONObject> listJsonArray;
    JSONObject json_about;
    private static final int PERL_PAGE = 100; //Max = 100 item
    private int CountPage;
    private boolean ChangeReposUser;
    private boolean AddValueList = false;
    private boolean checkNull = false;
    private int FinishPut;
    private boolean IsRateLimit;
    private int CountRateLimit;

    @Override
     protected Boolean doInBackground(String... strings) {
        try {
            //region Получаем Json-строку(в виде String) от сервера при помощи Http запроса
            if(Data == null) Data = "";
            if(!IsRateLimit) {
                url_str = URL_API + User + Data + "?per_page=" + Integer.toString(PERL_PAGE) + "&page=" + CountPage;
            }
            else {
                url_str = "https://api.github.com/rate_limit";
            }
           // https://api.github.com/users/NIkDubrovin?per_page=100&page=1
          //  https://api.github.com/users/NIkDubrovin/repos?per_page=100&page=1

            if (validateUrl(url_str)) { // Проверка - является ли строка url-адресом
                url = new URL(url_str);
                i(TAG, url_str);
            } else
                i(TAG, "Failed URL!");

            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
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
          //  i(TAG,response.toString());
            in.close();
            }catch (IOException e){e.printStackTrace();checkNull = true;}
            //endregion Получение строки закончили

                if (ChangeReposUser && !checkNull && !IsRateLimit) {
                    JSONArray json = new JSONArray(response.toString()); // Превращаем String in JSONObject
                    listJsonArray = new ArrayList<>();

                    for (int i = 0; i < json.length(); i++)
                        listJsonArray.add(json.getJSONObject(i));

                    if(AddValueList)
                    {
                        Log.i(TAG, "CountRepos - selfArray.size() = " + (CountRepos - selfArrayList_ListJSON_To_ListStringArray.size()));
                        int temp_size = selfArrayList_ListJSON_To_ListStringArray.size();
                            FinishPut = (CountRepos - temp_size);
                    }
                    else {
                        selfArrayList_ListJSON_To_ListStringArray.clear();
                        FinishPut  = listJsonArray.size();
                    }

                    for (int i = 0; i < FinishPut; i++) {
                        if (listJsonArray.get(i).getString("language").equals(selectLang) || selectLang.equals("All") || selectLang.equals("Все"))
                            selfArrayList_ListJSON_To_ListStringArray.add(
                                    new ListJSON_To_ListString(
                                            listJsonArray.get(i).
                                                    put("name", listJsonArray.get(i).getString("name")).
                                                    put("description",CheckNullData(listJsonArray.get(i).getString("description"),"Description")).
                                                    put("language", CheckNullData(listJsonArray.get(i).getString("language"),"Language")).
                                                    put("fork", listJsonArray.get(i).getString("fork")).
                                                    put("url_repos", listJsonArray.get(i).getString("html_url"))
                                    )
                            );
                        else
                            i(TAG, "language != selectLang " + listJsonArray.get(i).getString("name") + " / " + "false");
                    }



                }
                else if (!ChangeReposUser && !checkNull && !IsRateLimit){
                    json_about = new JSONObject(response.toString()); // Превращаем String in JSONObject
                    CountRepos = 0;
                    CountRepos = json_about.getInt("public_repos");
                }
                else if(IsRateLimit && !ChangeReposUser && !checkNull) {
                    CountRateLimit = 0;
                    CountRateLimit = new JSONObject(new JSONObject(response.toString()).getString("rate")).getInt("remaining");
                    Log.i(TAG,"CountRateLimit: " +  CountRateLimit);
                }
            }catch(Exception e){
                e.printStackTrace();
            checkNull = true;
            }
              catch(Throwable cause){
                cause.printStackTrace();
                  checkNull = true;
            }

            return checkNull;
        }

    //region Set && Get
    public void setUser(String User) {this.User = User;}
    public void setData(String Data) {this.Data = "/" + Data;}
    public void setSelectLang(String selectLang) {this.selectLang = selectLang;}
    public void setChangeReposUser(boolean changeReposUser) {this.ChangeReposUser = changeReposUser;}
    public void setCountPage(int CountPage) {this.CountPage = CountPage;}
    public int getCountPage() {return CountPage;}
    public void setAddValueList(boolean AddValueList) {this.AddValueList = AddValueList;}
    public void setIsRateLimit (boolean IsRateLimit) {this.IsRateLimit = IsRateLimit;}
    public int getRateLimit () {return  CountRateLimit;}
    //endregion

    //region Methods
    public static boolean validateUrl(String adress ){
        return android.util.Patterns.WEB_URL.matcher(adress).matches();
    }
    public String CheckNullData(String str,String desc){
        if(str.equals("null"))
            str = desc + " not found!";
        return str;
    }
    //endregion

    static public class StorageClass{
        static ArrayList<ListJSON_To_ListString> selfArrayList_ListJSON_To_ListStringArray = new ArrayList<>();
        static int CountRepos;
        static String selfNameLogin = "";

        public StorageClass() {}
    }
}
