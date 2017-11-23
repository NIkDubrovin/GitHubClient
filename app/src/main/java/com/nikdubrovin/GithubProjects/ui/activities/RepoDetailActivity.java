package com.nikdubrovin.GithubProjects.ui.activities;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.nikdubrovin.GithubProjects.R;
import com.nikdubrovin.GithubProjects.common.constant.IntentExtra;
import com.nikdubrovin.GithubProjects.ui.base.BaseActivity;
import com.nikdubrovin.GithubProjects.ui.fragments.LoginFragment;
import com.nikdubrovin.GithubProjects.ui.fragments.RepoDetailFragment;

import static com.nikdubrovin.GithubProjects.R.id.toolbar;

/**
 * Created by NikDubrovin on 01.10.2017.
 */

public class RepoDetailActivity extends BaseActivity {

    private Toolbar toolbar;

    @Override
    public Fragment getFragment() {
        return RepoDetailFragment.newInstance(getIntent().getExtras().getString(IntentExtra.EXTRA_REPOS_NAME));
    }

    @Override
    public int getIDResContainer() {
        return R.id.fragmentContainer;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activty_repo_detail);

        toolbar = (Toolbar) findViewById(R.id.toolbar_header);
        setSupportActionBar(toolbar);
        toolbar.setVisibility(View.GONE);
    }

    @Override
    public void onBackPressed() {
        finish();
    }

}
