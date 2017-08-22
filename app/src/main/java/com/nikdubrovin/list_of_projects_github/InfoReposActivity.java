package com.nikdubrovin.list_of_projects_github;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.widget.TextView;


import static com.nikdubrovin.list_of_projects_github.GetGitHubData.StorageClass.selfArrayList_ListJSON_To_ListStringArray;

/**
 * Created by NikDubrovin on 17.08.2017.
 */

public class InfoReposActivity extends Activity {

    private TextView textViewName;
    private TextView textViewDesc;
    private TextView textViewFork;
    private TextView textViewLang;
    private TextView textViewURL;
    private final String TAG = InfoReposActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_repos);

        textViewName =  findViewById(R.id.textViewName);
        textViewDesc =  findViewById(R.id.textViewDesc);
        textViewFork = findViewById(R.id.textViewFork);
        textViewLang = findViewById(R.id.textViewLang);
        textViewURL =  findViewById(R.id.textViewURL);

        int index = getIntent().getExtras().getInt("index");

        textViewName.setText("Name: " + selfArrayList_ListJSON_To_ListStringArray.get(index).getName());
        textViewDesc.setText("Description: " + selfArrayList_ListJSON_To_ListStringArray.get(index).getDesc());
        textViewFork.setText("Fork: " + selfArrayList_ListJSON_To_ListStringArray.get(index).getFork());
        textViewLang.setText("Language: " + selfArrayList_ListJSON_To_ListStringArray.get(index).getLang());
        textViewURL.setText(selfArrayList_ListJSON_To_ListStringArray.get(index).getUrl().toString());

        textViewURL.setMovementMethod(LinkMovementMethod.getInstance());
    }
}
