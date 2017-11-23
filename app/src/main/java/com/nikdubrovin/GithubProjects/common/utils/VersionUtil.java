package com.nikdubrovin.GithubProjects.common.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.nikdubrovin.GithubProjects.common.config.GitHubConfig;

/**
 * Created by NikDubrovin on 16.09.2017.
 */

public class VersionUtil {

    public static String getVersionName(Context context) {
        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo packageInfo = pm.getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionName;
        }
        catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return GitHubConfig.VERSION_NAME;
    }
}
