package com.nikdubrovin.list_of_projects_github;

import android.util.Log;
import org.json.JSONObject;
import java.net.URL;

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
    private int index;
    private final String TAG = ListJSON_To_ListString.class.getSimpleName();


    public ListJSON_To_ListString(JSONObject result) {
        this.result = result;
        try {
            name = result.getString("name");
            url = new URL(result.getString("url_repos"));
            desc = result.getString("description");
            lang = result.getString("language");
            fork = result.getString("fork");
        }catch (Exception e){e.printStackTrace();}
         catch (Throwable cause){cause.printStackTrace();}
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
}
