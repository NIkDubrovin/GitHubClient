package com.nikdubrovin.GithubProjects.common.utils;

import android.content.Context;
import android.content.Intent;

import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.text.format.DateUtils;

import com.mikepenz.devicon_typeface_library.DevIcon;
import com.mikepenz.octicons_typeface_library.Octicons;
import com.nikdubrovin.GithubProjects.common.constant.Constants;
import com.nikdubrovin.GithubProjects.data.StorageClass;
import com.nikdubrovin.GithubProjects.data.models.PostRepos;
import com.nikdubrovin.GithubProjects.data.models.PostStarred;
import com.nikdubrovin.GithubProjects.data.models.PostUser;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by NikDubrovin on 23.09.2017.
 */

public class OutUtils {

    /**
     *  GB = 1073741824 bytes
     *
     *  Github API get kilobytes
     */
    private static final int GIGABYTE = 1_073_741_824 / 1024;

    /**
     * @param str String
     * @param mas_str  String mas[]
     * @return number str in mas[]
     */
    public static int findNumberString(String str,String[] mas_str) {
            for (int i = 0; i < mas_str.length; i++) {
                if (str.equals(mas_str[i])) {
                     return i;
                }
            }

        return -1;
    }

    public static String replaceAllBlank(String str) {
        if (TextUtils.isEmpty(str)) return "";

        Pattern p = Pattern.compile("\\s*|\t|\r|\n");
        Matcher m = p.matcher(str);
        String dest = m.replaceAll("");
        return dest;
    }

    public static String trimNewLine(String str) {
        if (TextUtils.isEmpty(str)) return "";

        str = str.trim();
        Pattern p = Pattern.compile("\t|\r|\n");
        Matcher m = p.matcher(str);
        String dest = m.replaceAll("");
        return dest;
    }

    /**
     * Search of postUser in List of PostUser
     * @param objects
     * @param str
     * @return the postUser
     */

    public static PostUser getPostUser(List<PostUser> objects, String str){
        for(int i = 0; i < objects.size();i++)
            if(objects.get(i).getLogin().equals(str))
                 return objects.get(i);
        return null;
    }

    /**
     *
     * @param postUser
     * @return ArrayList<PostUser> with one postUser
     */
    public static List<PostUser> getPostUsers(PostUser postUser){
        List<PostUser> postUsers = new ArrayList<>();
        postUsers.add(postUser);
        return postUsers;
    }

    /**
     * @param Class to
     * @param packageContext from
     * @param extra_str extra
     * @param put_str value
     * @return
     */
    public static Intent newIntent(Context packageContext,Class<?> Class, String extra_str, String put_str) {
        Intent intent = new Intent(packageContext,Class);
        intent.putExtra(extra_str, put_str);
        return intent;
    }

    /**
     * Return Drawable with a certain Icon.
     * Default - Octicons.Icon.oct_alert
     * @param number Number String[] - findNumberString()
     * @param packageContext
     * @return  IconUtil.CreateIcon()
     */
    public static Drawable getIconLanguge(Context packageContext,int number){
        switch (number) {
            case 0:
                return IconUtil.CreateIcon(packageContext, DevIcon.Icon.dev_html5_plain_wordmark, Constants.DEFAULTCOLOR,Constants.ICONSIZE);
            case 1:
                return IconUtil.CreateIcon(packageContext, DevIcon.Icon.dev_css3_plain_wordmark, Constants.DEFAULTCOLOR,Constants.ICONSIZE);
            case 2:
                return IconUtil.CreateIcon(packageContext, DevIcon.Icon.dev_javascript_plain, Constants.DEFAULTCOLOR,Constants.ICONSIZE);
            case 3:
                return IconUtil.CreateIcon(packageContext, DevIcon.Icon.dev_java_plain_wordmark, Constants.DEFAULTCOLOR,Constants.ICONSIZE);
            case 4:
                return IconUtil.CreateIcon(packageContext, DevIcon.Icon.dev_ruby_plain_wordmark, Constants.DEFAULTCOLOR,Constants.ICONSIZE);
            case 5:
                return IconUtil.CreateIcon(packageContext, DevIcon.Icon.dev_linux_plain, Constants.DEFAULTCOLOR,Constants.ICONSIZE);
            case 6:
                return IconUtil.CreateIcon(packageContext, DevIcon.Icon.dev_python_plain_wordmark, Constants.DEFAULTCOLOR,Constants.ICONSIZE);
            case 7:
                return IconUtil.CreateIcon(packageContext, DevIcon.Icon.dev_php_plain, Constants.DEFAULTCOLOR,Constants.ICONSIZE);
            case 8:
                return IconUtil.CreateIcon(packageContext, DevIcon.Icon.dev_c_line_wordmark, Constants.DEFAULTCOLOR,Constants.ICONSIZE);
            case 9:
                return IconUtil.CreateIcon(packageContext, DevIcon.Icon.dev_cplusplus_line_wordmark, Constants.DEFAULTCOLOR,Constants.ICONSIZE);
            case 10:
                return IconUtil.CreateIcon(packageContext, DevIcon.Icon.dev_csharp_line_wordmark, Constants.DEFAULTCOLOR,Constants.ICONSIZE);
            case 11:
                return IconUtil.CreateIcon(packageContext, DevIcon.Icon.dev_go_plain, Constants.DEFAULTCOLOR,Constants.ICONSIZE);
            case 12:
                return IconUtil.CreateIcon(packageContext, DevIcon.Icon.dev_coffeescript_plain_wordmark, Constants.DEFAULTCOLOR,Constants.ICONSIZE);
        }

        return IconUtil.CreateIcon(packageContext, Octicons.Icon.oct_alert, Constants.DEFAULTCOLOR,Constants.ICONSIZE);
    }

    /**
     * Check for null String
     * @param str
     * @return
     */
    public static String isStrNull(String str, String log){
        if(str == null || str.equals("null") || str.equals("Null") || str.equals("NULL") || str.equals("") || str.equals(" ") || TextUtils.isEmpty(str) || str.isEmpty()) {
            //GLog.i(log + " = null");
            return " ";
        }
        return str;
    }

    /**
     * Parsing http header - Link || Parsing count repos
     * <p>
     * For example, :
     *  <pre><string>
     *  https://api.github.com/user/3139143/repos?per_page=10&page=2>; rel="next", <https://api.github.com/user/3139143/repos?per_page=10&page=11>; rel="last"
     *  https://api.github.com/user/3139143/starred?per_page=10&page=2>; rel="next", <https://api.github.com/user/3139143/starred?per_page=10&page=11>; rel="last"
     * </https></pre>
     *
     * @param linkHeader Header Link
     * @return Count Requests
     */

    public static int getCountRequests(String linkHeader){
        if(!TextUtils.isEmpty(linkHeader)) {
            int begin = linkHeader.lastIndexOf("page") + 5;
            int end = linkHeader.lastIndexOf(">");
            return Integer.parseInt(linkHeader.substring(begin, end));
        }
        return 0;
    }

    public static int getCountRequests(PostUser postUser){
        int count = postUser.getPublicRepos() / 100;
        if(count != 0)
            return count + 1;
        else
            return 1;
    }

    /**
     *  Parsing created_at date <p>
     * "2017-09-26T13:49:08Z"
     *
     * @param dateStr
     * @return String Date
     */
    public static String formatDate(String dateStr) {
        dateStr = dateStr.substring(0,10);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-DD");
        SimpleDateFormat simpleDateFormatOut = new SimpleDateFormat("EEE, d MMM yyyy");
        Date date = null;
        try {
            date = simpleDateFormat.parse(dateStr);
            String formatDate = simpleDateFormatOut.format(date);
            return formatDate;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Parse size Repos
     * @param size
     * @return
     */

    public static String getSizeRepos(Integer size){

      double size_t = size.doubleValue();

      if(size > 1024)
         return String.format("%.2f MB",size_t /= 1024);
      else if(size > GIGABYTE)
         return String.format("%.2f GB",size_t /= (1024*1024));
      else
         return String.format("%.2f KB",size_t);
    }

    public static Object getItemRepos(String nameRepos) {
        for(PostRepos postRepos : StorageClass.get().getPostReposes()){
            for(PostRepos postStarred : StorageClass.get().getPostStarreds())
            if(postRepos.getName().equals(nameRepos))
                return postRepos;
            else if(postStarred.getName().equals(nameRepos))
                return postStarred;
        }
      //  GLog.i("Repos of " + nameRepos + " not found");
        return(null);
    }
}
