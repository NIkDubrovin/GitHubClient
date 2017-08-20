package com.nikdubrovin.list_of_projects_github;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import static com.nikdubrovin.list_of_projects_github.GetGitHubData.StorageClass.CountRepos;
import static com.nikdubrovin.list_of_projects_github.GetGitHubData.StorageClass.selfArrayList_ListJSON_To_ListStringArray;

/**
 * Created by NikDubrovin on 17.08.2017.
 */

public class ListOfRepositories extends Activity  {

    private ArrayList<String> list_reposes = new ArrayList();
    private ArrayAdapter<String> adapter;
    private ListView listView;
    private final String TAG = ListOfRepositories.class.getSimpleName();
    private boolean Event = false;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_repos);

        listView = findViewById(R.id.repos_list);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,list_reposes);
        listView.setAdapter(adapter);

        if(selfArrayList_ListJSON_To_ListStringArray.size() != 0 && !Event) {
            for (int i = 0; i < selfArrayList_ListJSON_To_ListStringArray.size(); i++)
                adapter.add(Integer.toString(i) + ". " + selfArrayList_ListJSON_To_ListStringArray.get(i).getName());
              Log.i(TAG,"selfArrayList_ListJSON_To_ListStringArray != null");
        }else Log.i(TAG,"selfArrayList_ListJSON_To_ListStringArray = null");

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id)
            {
                // по позиции получаем выбранный элемент
                String selectedRepos = adapter.getItem(position);
                int index = adapter.getPosition(selectedRepos);

                Intent intent = new Intent(ListOfRepositories.this, InfoReposActivity.class);
                intent.putExtra("index", index);
                if(selectedRepos.isEmpty())  Toast.makeText(getApplicationContext(),"Ничего не выбрано", Toast.LENGTH_SHORT).show();
                else startActivity(intent);
//                Toast.makeText(getApplicationContext(),
//                    "Репозиторий : " + selectedRepos + "\n" +
//                            "URL: " + selfParseListStringArray.get(index).getUrl().toString() + "\n" +
//                            "Описание: " + selfParseListStringArray.get(index).getDesc() + "\n" +
//                            "Язык: " + selfParseListStringArray.get(index).getLang() + "\n" +
//                            "Fork :" + selfParseListStringArray.get(index).getFork(), Toast.LENGTH_SHORT).show()
            }
        });
        adapter.notifyDataSetChanged();
    }


}
