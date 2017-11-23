package com.nikdubrovin.GithubProjects.ui.adapters.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.flyco.labelview.LabelView;
import com.mikepenz.octicons_typeface_library.Octicons;
import com.nikdubrovin.GithubProjects.R;
import com.nikdubrovin.GithubProjects.common.constant.Constants;
import com.nikdubrovin.GithubProjects.common.utils.IconUtil;
import com.nikdubrovin.GithubProjects.common.utils.OutUtils;
import com.nikdubrovin.GithubProjects.data.models.PostRepos;
import com.nikdubrovin.GithubProjects.ui.base.BaseViewHolder;

import java.util.List;

import static com.nikdubrovin.GithubProjects.common.constant.Constants.LOADING;

/**
 * Created by NikDubrovin on 04.11.2017.
 */


 public class ReposHolder extends BaseViewHolder implements View.OnClickListener {

        private TextView mNameTextView;
        private TextView mDescTextView;
        private TextView mLangTextView;
        private PostRepos mPostRepos;
        private ImageView mImageViewIconRepos;
        private ImageView mImageViewIconStar;
        private ImageView mImageViewiconFork;
        private TextView mTextViewCountForks;
        private TextView mTextViewCountStars;

        private LabelView mLabelView;

        public ReposHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            mNameTextView = itemView.findViewById(R.id.name);
            mDescTextView = itemView.findViewById(R.id.desc);
            mImageViewIconRepos = itemView.findViewById(R.id.icon_repos);
            mImageViewIconStar = itemView.findViewById(R.id.iconStar);
            mImageViewiconFork = itemView.findViewById(R.id.iconFork);
            mImageViewiconFork = itemView.findViewById(R.id.iconFork);
            mImageViewiconFork = itemView.findViewById(R.id.iconFork);
            mTextViewCountForks = itemView.findViewById(R.id.forkCount);
            mTextViewCountStars = itemView.findViewById(R.id.starCount);
            mLangTextView = itemView.findViewById(R.id.lang);

            mLabelView = itemView.findViewById(R.id.label_view);
        }

    @Override
    public <T> void bindRepos(T tList) {
        mPostRepos = (PostRepos) tList;
            try {
                mNameTextView.setText(mPostRepos.getName());
                mLangTextView.setText(OutUtils.isStrNull(mPostRepos.getLanguage(), "Lang of " + mPostRepos.getName()));

                mLangTextView.setVisibility(View.GONE);

                mLabelView.setText(OutUtils.isStrNull(mPostRepos.getLanguage(), "Lang of " + mPostRepos.getName()));

                if (!mPostRepos.isFork())
                    mImageViewIconRepos.setImageDrawable(IconUtil.CreateIcon(mImageViewIconRepos.getContext(), Octicons.Icon.oct_repo, Constants.DEFAULTCOLOR, 40));
                else
                    mImageViewIconRepos.setImageDrawable(IconUtil.CreateIcon(mImageViewIconRepos.getContext(), Octicons.Icon.oct_repo_forked, Constants.DEFAULTCOLOR, Constants.ICONSIZE));

                mImageViewIconStar.setImageDrawable(IconUtil.CreateIcon(mImageViewIconStar.getContext(), Octicons.Icon.oct_star, Constants.DEFAULTCOLOR, Constants.ICONSIZE));
                mTextViewCountStars.setText(String.valueOf(mPostRepos.getStarCount()));
                mImageViewiconFork.setImageDrawable(IconUtil.CreateIcon(mImageViewiconFork.getContext(), Octicons.Icon.oct_gist_fork, Constants.DEFAULTCOLOR, Constants.ICONSIZE));
                mTextViewCountForks.setText(String.valueOf(mPostRepos.getForks()));

                mDescTextView.setText(OutUtils.isStrNull(mPostRepos.getDescription(), "Desc of " + mPostRepos.getName()));
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

        /**
         *   implements View.OnClickListener
         */
        @Override
        public void onClick(View view) {
            //  startActivity(OutUtils.newIntent(getActivity(), RepoDetailActivity.class, IntentExtra.EXTRA_REPOS_NAME, mPostRepos.getName()));
            // Toast.makeText(getActivity(), String.valueOf(mPostRepos.getDescription().length()),Toast.LENGTH_SHORT).show();
        }
}
