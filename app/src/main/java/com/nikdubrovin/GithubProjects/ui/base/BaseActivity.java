package com.nikdubrovin.GithubProjects.ui.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.nikdubrovin.GithubProjects.R;


/**
 * Created by NikDubrovin on 24.09.2017.
 */

/**
 * Base class for create or replace fragments.
 */
public abstract class BaseActivity extends AppCompatActivity {

    /**
     * @return Fragment to be created.
     */
    protected abstract Fragment getFragment();

    /**
     * @return R.id.layout fragment container
     */
    protected abstract int getIDResContainer();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_main);

        onCreateFragment();
    }

    protected void onCreateFragment() {
        if(getSupportFragmentManager().findFragmentById(getIDResContainer()) == null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(getIDResContainer(), getFragment())
                    .addToBackStack(null)
                    .commit();
        }
    }

    protected void onReplaceFragment(){
        if(getSupportFragmentManager().findFragmentById(getIDResContainer()) != null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(getIDResContainer(), getFragment())
                    .commit();
        }
    }
}


