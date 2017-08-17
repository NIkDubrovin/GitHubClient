package com.nikdubrovin.list_of_projects_github;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.nikdubrovin.list_of_projects_github.SelectDataActivity.StorageClass.selfparseListJsonArray;



/**
 * Created by NikDubrovin on 17.08.2017.
 */

public class ListOfRepositories extends Activity {

    private List<String> list_reposes = new ArrayList();
    private ArrayAdapter<String> adapter;
    private ListView listView;
    private final String TAG = "ListOfRepositories";
    private ArrayList<ParseListJson> parseListJsonArray;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_repos);

        listView = (ListView) findViewById(R.id.repos_list);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,list_reposes);
        listView.setAdapter(adapter);

        //setListAdapter(adapter);

        for (int i =0;i<selfparseListJsonArray.size();i++)
        adapter.add(Integer.toString(i) + ". " + selfparseListJsonArray.get(i).getName());

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id)
            {
                // по позиции получаем выбранный элемент
                String selectedRepos = adapter.getItem(position);
                int index = adapter.getPosition(selectedRepos);
                Toast.makeText(getApplicationContext(),
                    "Репозиторий : " + selectedRepos + "\n" +
                            "URL: " + selfparseListJsonArray.get(index).getUrl().toString() + "\n" +
                            "Описание: " + selfparseListJsonArray.get(index).getDesc() + "\n" +
                            "Язык: " + selfparseListJsonArray.get(index).getLang() + "\n" +
                            "Fork :" + selfparseListJsonArray.get(index).getFork(), Toast.LENGTH_SHORT).show();
            }
        });

        adapter.notifyDataSetChanged();

    }
}
