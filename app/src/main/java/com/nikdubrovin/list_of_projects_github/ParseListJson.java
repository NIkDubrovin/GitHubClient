package com.nikdubrovin.list_of_projects_github;

import android.util.Log;
import org.json.JSONObject;
import java.net.URL;

/**
 * Created by NikDubrovin on 17.08.2017.
 */

 public class ParseListJson {
    private JSONObject result;
    private String name;
    private URL url;
    private String desc;
    private String lang;
    private String fork;
    private int index;
    private final String TAG = "ParseListJson";


    public ParseListJson(JSONObject result) {
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

    public void setIndex(int index) { this.index = index;}
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
