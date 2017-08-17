package com.nikdubrovin.list_of_projects_github;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;


/**
 * Created by NikDubrovin on 17.08.2017.
 */

public class ListOfRepositories extends Activity {

    private ArrayList<String> reposes = new ArrayList();
    private ArrayAdapter<String> adapter;
    private ListView reposList;
    private final String TAG = "ListOfRepositories";

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_list_of_repos);

        reposList = (ListView) findViewById(R.id.repos_list);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, reposes);
        reposList.setAdapter(adapter);

        final String name = getIntent().getStringExtra("name");
        final URL url_repos = (URL) getIntent().getExtras().get("url_repos");
        final String desc = getIntent().getStringExtra("desc");
        final String lang = getIntent().getStringExtra("lang");
        final String fork = getIntent().getStringExtra("fork");
        adapter.add(name);
        Log.i(TAG,"URI_REPOS: " + url_repos);

        reposList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id)
            {
                // по позиции получаем выбранный элемент
                String selectedRepos = adapter.getItem(position);
                Toast.makeText(getApplicationContext(),
                    "Репозиторий : " + selectedRepos + "\n" +
                            "URL: " + url_repos + "\n" +
                            "Описание: " + desc + "\n" +
                            "Язык: " + lang + "\n" +
                            "Fork :" + fork, Toast.LENGTH_LONG).show();
            }
        });

        adapter.notifyDataSetChanged();

    }
}
