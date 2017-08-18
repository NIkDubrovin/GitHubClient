package com.nikdubrovin.list_of_projects_github;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.widget.TextView;


import static com.nikdubrovin.list_of_projects_github.SelectTypeDataActivity.StorageClass.selfParseListStringArray;

/**
 * Created by NikDubrovin on 17.08.2017.
 */

public class InfoReposActivity extends Activity {

    private TextView textViewName;
    private TextView textViewDesc;
    private TextView textViewFork;
    private TextView textViewURL;
    private final String TAG = InfoReposActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_repos);

        textViewName =  findViewById(R.id.textViewName);
        textViewDesc =  findViewById(R.id.textViewDesc);
        textViewFork = findViewById(R.id.textViewFork);
        textViewURL =  findViewById(R.id.textViewURL);

        int index = getIntent().getExtras().getInt("index");
     //   Log.i(TAG,"Name: " + selfParseListStringArray.get(index).getName() + "\n"/* + editTextName.getText().toString() + "\n" */+ "Index: " + index + "\n" + "\n" + "\n");
        textViewName.setText("Name: " + selfParseListStringArray.get(index).getName());
        textViewDesc.setText("Description: " + selfParseListStringArray.get(index).getDesc());
        Log.i(TAG, "\n" + "Select lang: " + selfParseListStringArray.get(index).getLang() + "\n");
        textViewFork.setText("Language: " + selfParseListStringArray.get(index).getLang());
       // if( selfParseListStringArray.get(index).getFork().toString().replace("/forks","") == selfParseListStringArray.get(index).getUrl().toString())
     //   textViewFork.setText("Fork: " + selfParseListStringArray.get(index).getFork());
        textViewURL.setText(selfParseListStringArray.get(index).getUrl().toString());

        textViewURL.setMovementMethod(LinkMovementMethod.getInstance());
    }
}
