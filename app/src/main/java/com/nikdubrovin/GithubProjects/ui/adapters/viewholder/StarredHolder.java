package com.nikdubrovin.GithubProjects.ui.adapters.viewholder;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.flyco.labelview.LabelView;
import com.mikepenz.octicons_typeface_library.Octicons;
import com.nikdubrovin.GithubProjects.R;
import com.nikdubrovin.GithubProjects.common.constant.Constants;
import com.nikdubrovin.GithubProjects.common.constant.IntentExtra;
import com.nikdubrovin.GithubProjects.common.utils.IconUtil;
import com.nikdubrovin.GithubProjects.common.utils.ImageLoader;
import com.nikdubrovin.GithubProjects.common.utils.OutUtils;
import com.nikdubrovin.GithubProjects.data.models.PostRepos;
import com.nikdubrovin.GithubProjects.data.models.PostStarred;
import com.nikdubrovin.GithubProjects.ui.activities.RepoDetailActivity;
import com.nikdubrovin.GithubProjects.ui.base.BaseViewHolder;

import java.util.List;

/**
 * Created by NikDubrovin on 04.11.2017.
 */

 public class StarredHolder extends BaseViewHolder implements View.OnClickListener {

    private PostRepos mPostStarred;

    private final TextView mNameTextView;
    private final TextView mDescTextView;
    private final TextView mLangTextView;
    private final ImageView mImageViewUser;
    private final ImageView mImageViewIconStar;
    private final ImageView mImageViewiconFork;
    private final TextView mTextViewCountForks;
    private final TextView mTextViewCountStars;

    private LabelView mLabelView;


    public StarredHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);

        mNameTextView = itemView.findViewById(R.id.name);
        mDescTextView = itemView.findViewById(R.id.desc);
        mImageViewUser = itemView.findViewById(R.id.icon_repos);
        mImageViewIconStar = itemView.findViewById(R.id.iconStar);
        mImageViewiconFork = itemView.findViewById(R.id.iconFork);
        mTextViewCountForks = itemView.findViewById(R.id.forkCount);
        mTextViewCountStars = itemView.findViewById(R.id.starCount);
        mLangTextView = itemView.findViewById(R.id.lang);

        mLabelView = itemView.findViewById(R.id.label_view);
    }

    @Override
    public <T> void bindRepos(T tList) {
        mPostStarred = (PostRepos) tList;

        try{
            mNameTextView.setText(mPostStarred.getName());
            mLangTextView.setText(OutUtils.isStrNull(mPostStarred.getLanguage(),"Lang of " + mPostStarred.getName()));
            mLangTextView.setVisibility(View.GONE);

            ImageLoader.loadWithCircle(mPostStarred.getOwner().getAvatarUrl(), mImageViewUser, Constants.IMAGESIZE[0],Constants.IMAGESIZE[1]);

            mImageViewIconStar.setImageDrawable(IconUtil.CreateIcon(mImageViewIconStar.getContext(), Octicons.Icon.oct_star, Color.rgb(255, 215, 0), Constants.ICONSIZE));
            mTextViewCountStars.setText(String.valueOf(mPostStarred.getStarCount()));
            mImageViewiconFork.setImageDrawable(IconUtil.CreateIcon(mImageViewiconFork.getContext(), Octicons.Icon.oct_gist_fork, Constants.DEFAULTCOLOR,Constants.ICONSIZE));
            mTextViewCountForks.setText(String.valueOf(mPostStarred.getForks()));

            mDescTextView.setText(OutUtils.isStrNull(mPostStarred.getDescription(),"Desc of " + mPostStarred.getName()));
            mDescTextView.setTextColor(Color.rgb(105,105,105));
           // mDescTextView.setTextColor(Color.BLACK);

            mLabelView.setText(OutUtils.isStrNull(mPostStarred.getLanguage(),"Lang of " + mPostStarred.getName()));

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *   implements View.OnClickListener
     */
    @Override
    public void onClick(View view) {
       // startActivity(OutUtils.newIntent(getActivity(), RepoDetailActivity.class, IntentExtra.EXTRA_REPOS_NAME, mPostStarred.getName()));
        // getFragmentManager().beginTransaction().add(R.id.main_layout,RepoDetailFragment()).commit();
        // Toast.makeText(getActivity(), String.valueOf(mPostStarred.getDescription()),Toast.LENGTH_SHORT).show();
    }

}
