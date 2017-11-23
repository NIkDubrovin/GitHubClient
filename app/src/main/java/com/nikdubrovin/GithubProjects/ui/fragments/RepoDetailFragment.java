package com.nikdubrovin.GithubProjects.ui.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mikepenz.octicons_typeface_library.Octicons;
import com.nikdubrovin.GithubProjects.R;
import com.nikdubrovin.GithubProjects.common.constant.Constants;
import com.nikdubrovin.GithubProjects.common.utils.IconUtil;
import com.nikdubrovin.GithubProjects.common.utils.ImageLoader;
import com.nikdubrovin.GithubProjects.common.utils.OutUtils;
import com.nikdubrovin.GithubProjects.data.models.PostRepos;

import static com.nikdubrovin.GithubProjects.common.constant.IntentExtra.EXTRA_REPOS_NAME;

/**
 * Created by NikDubrovin on 25.09.2017.
 */

public class RepoDetailFragment extends Fragment {

    private ImageView mImageViewAvatar;
    private ImageView mImageViewStargazers;
    private ImageView mImageViewFork;
    private ImageView mImageViewWatcher;

    private ImageView mImageViewForkClick;
    private ImageView mImageViewStarredClick;
    private ImageView mImageViewInfo;

    private TextView mTextViewFullName;
    private TextView mTextViewDateCreated;
    private TextView mTextViewSizeRepos;
    private TextView mTextViewLang;
    private TextView mTextViewCountStarred;
    private TextView mTextViewCountFork;
    private TextView mTextWatcher;

    private PostRepos mPostRepos;

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_repos_detail,container,false);

        String nameRepos = (String) getArguments().getSerializable(EXTRA_REPOS_NAME);

        mImageViewAvatar = view.findViewById(R.id.imageAvatar);
        mImageViewWatcher = view.findViewById(R.id.IconWatcher);
        mImageViewStargazers = view.findViewById(R.id.IconStarred);
        mImageViewFork = view.findViewById(R.id.IconFork);

        mTextViewFullName = view.findViewById(R.id.headerTitleName);

        mTextViewDateCreated = view.findViewById(R.id.dateCreated);
        mTextViewSizeRepos = view.findViewById(R.id.sizeRepos);
        mTextViewLang = view.findViewById(R.id.language);

        mTextViewCountStarred = view.findViewById(R.id.stargazers_count);
        mTextViewCountFork = view.findViewById(R.id.forks_count);
        mTextWatcher = view.findViewById(R.id.watchers_count);

        mImageViewForkClick = view.findViewById(R.id.ForkClickIcon);
        mImageViewStarredClick = view.findViewById(R.id.StarredClickIcon);
        mImageViewInfo = view.findViewById(R.id.InfoClick);


        mPostRepos = (PostRepos) OutUtils.getItemRepos(nameRepos);


       // mImageViewWatcher.setImageDrawable(IconUtil.CreateIcon(getActivity(), Octicons.Icon.oct_eye_watch, Constants.DEFAULTCOLOR, 15));
        //mTextWatcher.setText(String.valueOf(mPostRepos.gets));

        mTextWatcher.setVisibility(View.GONE);
        mImageViewWatcher.setVisibility(View.GONE);

        mImageViewStargazers.setImageDrawable(IconUtil.CreateIcon(getActivity(), Octicons.Icon.oct_star, Constants.DEFAULTCOLOR,15));
        mTextViewCountStarred.setText(String.valueOf(mPostRepos.getStarCount()));

        mImageViewFork.setImageDrawable(IconUtil.CreateIcon(getActivity(), Octicons.Icon.oct_repo_forked, Constants.DEFAULTCOLOR,15));
        mTextViewCountFork.setText(String.valueOf(mPostRepos.getForks()));

        mTextViewDateCreated.setText(OutUtils.formatDate(mPostRepos.getCreatedAt()));
        mTextViewSizeRepos.setText(OutUtils.getSizeRepos(mPostRepos.getSize()));
        mTextViewLang.setText(mPostRepos.getLanguage());

        mImageViewForkClick.setImageDrawable(IconUtil.CreateIcon(getActivity(), Octicons.Icon.oct_repo_forked, Constants.DEFAULTCOLOR,20));
        mImageViewStarredClick.setImageDrawable(IconUtil.CreateIcon(getActivity(), Octicons.Icon.oct_star_add, Constants.DEFAULTCOLOR,20));
        mImageViewInfo.setImageDrawable(IconUtil.CreateIcon(getActivity(), Octicons.Icon.oct_info, Constants.DEFAULTCOLOR,20));

        ImageLoader.loadWithCircle(mPostRepos.getOwner().getAvatarUrl(),mImageViewAvatar,500,500);

        mTextViewFullName.setText(mPostRepos.getFullName());


        return view;
    }


    public static RepoDetailFragment newInstance(String nameRepos) {
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_REPOS_NAME, nameRepos);
        RepoDetailFragment fragment = new RepoDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

}
