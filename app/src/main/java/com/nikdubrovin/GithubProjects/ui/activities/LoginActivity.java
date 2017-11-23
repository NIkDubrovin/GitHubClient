package com.nikdubrovin.GithubProjects.ui.activities;

import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import com.nikdubrovin.GithubProjects.R;
import com.nikdubrovin.GithubProjects.ui.base.BaseActivity;
import com.nikdubrovin.GithubProjects.ui.fragments.LoginFragment;
import com.nikdubrovin.GithubProjects.ui.fragments.RepoDetailFragment;

/**
 * Created by NikDubrovin on 24.09.2017.
 */

public class LoginActivity extends BaseActivity {

    @Override
    public Fragment getFragment() {
        return new LoginFragment();
    }

    @Override
    public int getIDResContainer() {
        return R.id.fragmentContainer;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onBackPressed() {
        ContinueOrExit();
    }

    public void ContinueOrExit() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.Theme_AppCompat_Light_Dialog_Alert);
        builder.setTitle(R.string.app_name);
        builder.setIcon(R.drawable.github_black_icon);
        builder.setMessage("Do you want exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();

    }
}
