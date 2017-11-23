package com.nikdubrovin.GithubProjects.ui.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.nikdubrovin.GithubProjects.R;
import com.nikdubrovin.GithubProjects.common.constant.IntentExtra;
import com.nikdubrovin.GithubProjects.common.utils.NetworkUtil;
import com.nikdubrovin.GithubProjects.common.utils.OutUtils;
import com.nikdubrovin.GithubProjects.data.StorageClass;
import com.nikdubrovin.GithubProjects.ui.activities.HomeActivity;


/**
 * Created by NikDubrovin on 24.09.2017.
 */

public class LoginFragment extends Fragment{

    private ImageView mIcon;
    private EditText mUsername;
    private Button mLoginBtn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login,container,false);

        mUsername = view.findViewById(R.id.username);
        mLoginBtn = view.findViewById(R.id.login_button);

        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = mUsername.getText().toString();

                if (!TextUtils.isEmpty(username) && !username.isEmpty() && username != null && NetworkUtil.isNetworkAvailable(getActivity())) {
                    startActivity(OutUtils.newIntent(getActivity(), HomeActivity.class, IntentExtra.EXTRA_USERNAME, mUsername.getText().toString()));
                    StorageClass.get().setUserName(username);
                }
            }
        });

        return view;
    }

}
