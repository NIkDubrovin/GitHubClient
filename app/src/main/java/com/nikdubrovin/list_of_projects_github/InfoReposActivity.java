package com.nikdubrovin.list_of_projects_github;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Bundle;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.URLSpan;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
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

        textViewName.setText("Name: " + selfParseListStringArray.get(index).getName().toString());
        textViewDesc.setText("Description: " + selfParseListStringArray.get(index).getDesc().toString());
        textViewFork.setText("Fork: " + selfParseListStringArray.get(index).getFork().toString());
        textViewURL.setText(selfParseListStringArray.get(index).getUrl().toString());

        textViewURL.setMovementMethod(LinkMovementMethod.getInstance());
    }
}
