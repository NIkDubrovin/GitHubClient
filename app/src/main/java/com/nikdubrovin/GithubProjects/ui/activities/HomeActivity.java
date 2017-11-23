package com.nikdubrovin.GithubProjects.ui.activities;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.nikdubrovin.GithubProjects.R;

import com.nikdubrovin.GithubProjects.common.config.GitHubConfig;
import com.nikdubrovin.GithubProjects.common.constant.Constants;
import com.nikdubrovin.GithubProjects.common.utils.GLog;
import com.nikdubrovin.GithubProjects.common.utils.ImageLoader;
import com.nikdubrovin.GithubProjects.common.utils.NetworkUtil;
import com.nikdubrovin.GithubProjects.common.utils.OutUtils;
import com.nikdubrovin.GithubProjects.data.GetGitHubData;
import com.nikdubrovin.GithubProjects.data.StorageClass;
import com.nikdubrovin.GithubProjects.data.models.PostRateLimit;
import com.nikdubrovin.GithubProjects.data.models.PostRepos;
import com.nikdubrovin.GithubProjects.data.models.PostStarred;
import com.nikdubrovin.GithubProjects.data.models.PostUser;
import com.nikdubrovin.GithubProjects.data.network.RetrofitGithub;
import com.nikdubrovin.GithubProjects.ui.fragments.ListReposFragment;
import com.nikdubrovin.GithubProjects.ui.fragments.ListStarredFragment;
import com.orhanobut.logger.Logger;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.nikdubrovin.GithubProjects.common.constant.Constants.TOTAL_PAGE;

/**
 * Created by NikDubrovin on 24.09.2017.
 */

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private ImageView mImageViewAvatar;
    private TextView mTextViewUserName;
    private View headerView;
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private CollapsingToolbarLayout collapsingToolbar;
    private ImageView backDrop;
    private com.nikdubrovin.GithubProjects.ui.adapters.FragmentPagerAdapter mFragmentPagerAdapter;

    private ProgressBar progressBar;
    private LinearLayout errorLayout;
    private Button btnRetry;
    private TextView txtError;

    private List<PostRepos> mPostReposes;
    private List<PostStarred> mPostStarreds;

    private int currentPage = 1;
    private int countRequests = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        if(NetworkUtil.isNetworkAvailable(getApplicationContext()))
            getRequest();
        else
            Toast.makeText(getApplicationContext(), Constants.WARING_ERROR, Toast.LENGTH_SHORT).show();

        // ToolBar
        toolbar = (Toolbar) findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);
        toolbar.setVisibility(View.VISIBLE);

        // CollapsingToolbarLayout
        collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);

        backDrop = (ImageView) findViewById(R.id.backdrop);


        // ActionBar
        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // ViewPager
        // TabLayout
        mViewPager = (ViewPager) findViewById(R.id.viewpager_main);
        mTabLayout = (TabLayout) findViewById(R.id.tabs);

        mFragmentPagerAdapter = new com.nikdubrovin.GithubProjects.ui.adapters.FragmentPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mFragmentPagerAdapter);

        mTabLayout.setupWithViewPager(mViewPager);

        // DrawerLayout
        // ActionBarDrawerToggle
        mDrawerLayout = (DrawerLayout) findViewById(R.id.main_drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.setDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(true);
        toggle.syncState();

        // NavigationView
        navigationView = (NavigationView) findViewById(R.id.main_nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        headerView = navigationView.inflateHeaderView(R.layout.nav_header_main);
        mImageViewAvatar = (ImageView) headerView.findViewById(R.id.image_header);
        mTextViewUserName = (TextView) headerView.findViewById(R.id.headerText);

        // Error Layout
//        errorLayout = (LinearLayout) findViewById(R.id.error_layout);
//        btnRetry = (Button) errorLayout.findViewById(R.id.error_btn_retry);
//        txtError = (TextView) errorLayout.findViewById(R.id.error_txt_cause);
//
//        errorLayout.setVisibility(View.GONE);

        setTransulcentStatusBar(this,toolbar);

    }

    public void getRequest(){
//        GetGitHubData getGitHubData = new GetGitHubData();
//        getGitHubData.setNameScopes(GitHubConfig.SCOPES[6]);
//        getGitHubData.execute();
//        try {
//            getGitHubData.get();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        StorageClass storageClass = StorageClass.get();
//        GetGitHubData getGitHubData = new GetGitHubData();
//        getGitHubData.setNameLogin(storageClass.getUserName());
//
        progressBar = (ProgressBar) findViewById(R.id.main_progress);
        progressBar.setVisibility(View.VISIBLE);
//
////        getGitHubData.setSCOPES(GitHubConfig.SCOPES.USER);
////
////        getGitHubData.loadDataEnqueue();
////
////        getGitHubData.setSCOPES(GitHubConfig.SCOPES.RATE);
////        getGitHubData.loadDataEnqueue();

        RetrofitGithub.getApi().getUser(StorageClass.get().getUserName()).enqueue(new Callback<PostUser>() {
            @Override
            public void onResponse(Call<PostUser> call, Response<PostUser> response) {
                StorageClass.get().getPostUsers().add(response.body());

                try {
                    ImageLoader.load(OutUtils.getPostUser(StorageClass.get().getPostUsers(), StorageClass.get().getUserName()).getAvatarUrl(), backDrop);
                    mTextViewUserName.setText(OutUtils.getPostUser(StorageClass.get().getPostUsers(), StorageClass.get().getUserName()).getName());
                    collapsingToolbar.setTitle(OutUtils.isStrNull(OutUtils.getPostUser(StorageClass.get().getPostUsers(), StorageClass.get().getUserName()).getName() + "\t\t\t\t\t", " Collapsing text = "));
                    ImageLoader.loadWithCircle(OutUtils.getPostUser(StorageClass.get().getPostUsers(), StorageClass.get().getUserName()).getAvatarUrl(), mImageViewAvatar, 500, 500);
                }catch(Exception e){e.printStackTrace();}
                progressBar.setVisibility(View.GONE);
                setUpTab(R.id.nav_home);
            }

            @Override
            public void onFailure(Call<PostUser> call, Throwable t) {
                t.printStackTrace();
            }
        });

        RetrofitGithub.getApi().getRateLimit().enqueue(new Callback<PostRateLimit>() {
            @Override
            public void onResponse(Call<PostRateLimit> call, Response<PostRateLimit> response) {
                StorageClass.get().setPostRateLimit(response.body());
            }

            @Override
            public void onFailure(Call<PostRateLimit> call, Throwable t) {
                t.printStackTrace();
            }
        });
        hideProgressBar();
    }



    public void ContinueOrExit() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.Theme_AppCompat_Light_Dialog_Alert);
        builder.setTitle(R.string.app_name);
        builder.setIcon(R.drawable.github_black_icon);
        builder.setMessage("Do you want return to login?")
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

    public void onCreateFragment(int ResContainer,Fragment fragment) {
        if(getSupportFragmentManager().findFragmentById(ResContainer) == null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(ResContainer,fragment)
                    .commit();
        }
    }

    public void onReplaceFragment(int ResContainer,Fragment fragment) {
        if(getSupportFragmentManager().findFragmentById(ResContainer) == null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(ResContainer,fragment)
                    .addToBackStack(null)
                    .commit();
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.main_drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }

        /*
         * onBackPressed() in Fragments
         */
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        } else {
            ContinueOrExit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        //noinspection SimplifiableIfStatement
        switch (item.getItemId()) {
            case R.id.rate_limit:
                Toast.makeText(getApplicationContext(), String.valueOf(StorageClass.get().getPostRateLimit().getRateLimit()),Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_settings:
                return true;
            case R.id.about_app:
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.main_drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
       // drawer.closeDrawers();

        setUpTab(item.getItemId());

        if (item.getItemId() == R.id.nav_home || item.getItemId() == R.id.nav_friends)
            item.setChecked(true);
        return true;
    }

    public void setUpTab(int id) {
        switch (id) {
            case R.id.nav_home:
                mFragmentPagerAdapter.clear();
                mViewPager.setOffscreenPageLimit(2);
                mFragmentPagerAdapter.addFragment(new ListStarredFragment(),"Starred");
                mFragmentPagerAdapter.addFragment(new ListReposFragment(), "Repository");
                mFragmentPagerAdapter.notifyDataSetChanged();
                mTabLayout.setupWithViewPager(mViewPager);
                break;

            case R.id.nav_friends:
                mFragmentPagerAdapter.clear();
                mViewPager.setOffscreenPageLimit(2);
                mFragmentPagerAdapter.addFragment(new ListReposFragment(),"Followers");
                mFragmentPagerAdapter.addFragment(new ListReposFragment(), "Following");
                mFragmentPagerAdapter.notifyDataSetChanged();
                mTabLayout.setupWithViewPager(mViewPager);
                break;

            case R.id.nav_setting:
               // onReplaceFragment(R.id.main_layout,new RepoDetailFragment());
                return;

            case R.id.nav_about:
                break;
        }
    }

    private void hideErrorView() {
        if (errorLayout.getVisibility() == View.VISIBLE) {
            errorLayout.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
        }
    }

    private void hideProgressBar() {
        if (progressBar.getVisibility() == View.VISIBLE) {
            progressBar.setVisibility(View.GONE);
        }
        else
            progressBar.setVisibility(View.VISIBLE);
    }

    public void setTransulcentStatusBar(@NonNull Activity activity, Toolbar toolbar) {

            toolbar.setPadding(0, getStatusBarHeight(activity) >> 1, 0, 0);

            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            activity.getWindow().setStatusBarColor(Color.TRANSPARENT);
    }

    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }
}
