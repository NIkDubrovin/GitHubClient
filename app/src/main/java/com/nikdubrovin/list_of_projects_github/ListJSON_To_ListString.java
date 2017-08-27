package com.nikdubrovin.list_of_projects_github;

import android.util.Log;
import org.json.JSONObject;
import java.net.URL;

import static com.nikdubrovin.list_of_projects_github.GetGitHubData.StorageClass.CheckFollowers;

/**
 * Created by NikDubrovin on 17.08.2017.
 */

 public class ListJSON_To_ListString {

    private JSONObject result;
    private String name;
    private URL url;
    private String desc;
    private String lang;
    private String fork;
    private String login;
    private final String TAG = ListJSON_To_ListString.class.getSimpleName();


    public ListJSON_To_ListString(JSONObject result) {
        this.result = result;
        try {
            if(!CheckFollowers) {
                this.name = result.getString("name");
                this.desc = result.getString("description");
                this.lang = result.getString("language");
                this.fork = result.getString("fork");
                this.url = new URL(result.getString("url_repos"));
            }else
            this.login = result.getString("login");
        }catch (Exception e){e.printStackTrace();}
         catch (Throwable cause){cause.printStackTrace();}
    }

    public ListJSON_To_ListString(ListJSON_To_ListString l) {
       l.lang = "C#";
    }



    public String getName() {
     //   Log.i(TAG,"getName: " + name);
        return name;
    }
    public URL getUrl() {
       // Log.i(TAG,"getUrl: " + url);
        return url;
    }
    public String getDesc() {return desc;}
    public String getLang() {return lang;}
    public String getFork() {return fork;}
    public String getLogin() {return login;}

}
