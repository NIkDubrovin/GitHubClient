package com.nikdubrovin.GithubProjects.common.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;

import retrofit2.Response;

/**
 * Created by NikDubrovin on 16.09.2017.
 */

public class NetworkUtil {

    private NetworkUtil() {}

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        //return networkInfo != null && networkInfo.isAvailable();
        return networkInfo != null && networkInfo.isConnectedOrConnecting();
    }

    public static boolean validateUrl(String adress ){
        return android.util.Patterns.WEB_URL.matcher(adress).matches();
    }

    public static boolean isSuccessfulRequest(Response response){
        return response != null && response.isSuccessful();
    }
}
