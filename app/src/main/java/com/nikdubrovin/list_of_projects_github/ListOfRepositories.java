package com.nikdubrovin.list_of_projects_github;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


import static com.nikdubrovin.list_of_projects_github.GetGitHubData.StorageClass.CheckFollowers;
import static com.nikdubrovin.list_of_projects_github.GetGitHubData.StorageClass.CheckFollowing;
import static com.nikdubrovin.list_of_projects_github.GetGitHubData.StorageClass.selfArrayList_ListJSON_To_ListStringArray;

/**
 * Created by NikDubrovin on 17.08.2017.
 */

public class ListOfRepositories extends Activity  {

    private ArrayList<String> list_reposes = new ArrayList();
    private ArrayAdapter<String> adapter;
    private ListView listView;
    private final String TAG = ListOfRepositories.class.getSimpleName();

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_repos);

        listView = findViewById(R.id.repos_list);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,list_reposes);
        listView.setAdapter(adapter);

            if(!CheckFollowers && !CheckFollowing) {
                for (int i = 0; i < selfArrayList_ListJSON_To_ListStringArray.size(); i++)
                    adapter.add(Integer.toString(i + 1) + ". " + selfArrayList_ListJSON_To_ListStringArray.get(i).getName());
            }
            else if(CheckFollowing){
                for (int i = 0; i < selfArrayList_ListJSON_To_ListStringArray.size(); i++)
                    adapter.add(Integer.toString(i + 1) + ". " + selfArrayList_ListJSON_To_ListStringArray.get(i).getLogin());
            }


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id)
            {
                // По позиции получаем выбранный элемент
                String selectedRepos = adapter.getItem(position);
                int index = adapter.getPosition(selectedRepos);

                Intent intent = new Intent(ListOfRepositories.this, InfoReposActivity.class);
                intent.putExtra("index", index);
                if(selectedRepos.isEmpty())  Toast.makeText(getApplicationContext(),getResources().getString(R.string.null_select), Toast.LENGTH_SHORT).show();
                else startActivity(intent);
            }
        });
        adapter.notifyDataSetChanged();
    }
}
