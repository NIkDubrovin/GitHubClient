package com.nikdubrovin.list_of_projects_github;

import android.content.Context;
import android.content.Intent;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

import java.net.URL;
import java.text.BreakIterator;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private final String TAG = "MainActivity";
    private EditText editText;
    private Button getDataButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getDataButton = (Button)findViewById(R.id.button_getData);
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
                        .setMessage("by @NIkDubrovin\n" +
                                "Rus\n" +
                                "По определенным критериям при помощи GitHub API v3, JSON и др. формируется и выводится список проектов + доп. информация.\n" +
                                "ENG\n" +
                                "By using GitHub API v3, JSON, etc. shows the list of projects + additional information.");
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
        editText = (EditText)findViewById(R.id.EditText_ReposName);

        Intent intent = new Intent(MainActivity.this, SelectDataActivity.class);

        intent.putExtra("username", editText.getText().toString());
       // intent.putExtra("username", "assusdan");
        startActivity(intent);
    }
}
