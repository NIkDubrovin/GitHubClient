package com.nikdubrovin.list_of_projects_github;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private GetGithubData getGithubData;
    private final String TAG = "MainActivity";
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }


    //region Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        //noinspection SimplifiableIfStatement
        switch (item.getItemId()){
            case R.id.action_settings :
                return true;
            case R.id.about_app :
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("About")
                        .setMessage("by @NIkDubrovin");
                AlertDialog alert = builder.create();
                alert.show();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
    //endregion

    public  boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting())
        {
            return true;
        }
        return false;
    }

    public void onClickGetData(View view) {
        if(!isOnline())  Toast.makeText(getApplicationContext(),"Проверьте подключение к интернету", Toast.LENGTH_SHORT).show();
        editText = (EditText)findViewById(R.id.EditText_CityName);
        String name_login = editText.getText().toString();

        String[] str = name_login.split(" ");
        Log.i(TAG,  str[0] + str[1]);

        getGithubData = new GetGithubData();
       // getGithubData.setUser(name_login);
        getGithubData.setUser(str[0]);
        getGithubData.setData("repos");
        getGithubData.execute();
        try {
            ArrayList<JSONObject> result =  getGithubData.get();
            int count = Integer.parseInt(str[1]);
            Log.i(TAG, "result: " + result);
            String name = result.get(count).getString("name");
            URL url = new URL(result.get(count).getString("url_repos"));
            String desc = result.get(count).getString("description");
            String lang = result.get(count).getString("language");

            Toast.makeText(getApplicationContext(),
                    "Репозиторий : " + name + "\n" +"URL: " + url + "\n" +
                            "Описание: " + desc + "\n" +
                            "Язык: " + lang, Toast.LENGTH_LONG).show();
        }catch (Throwable cause){
            cause.printStackTrace();
            Toast.makeText(getApplicationContext(),"Что-то пошло не так..", Toast.LENGTH_SHORT).show();
        }
    }

}
