package com.nikdubrovin.GithubProjects.ui.base;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.nikdubrovin.GithubProjects.common.constant.Constants;

/**
 * Created by NikDubrovin on 04.11.2017.
 */

public abstract class BaseScrollListener extends RecyclerView.OnScrollListener {

    private LinearLayoutManager mLayoutManager;

    private int visibleThreshold = 5;
    private int currentPage = 0;
    private boolean isLoading = true;
    private int startingPageIndex = 0;
    private int previousTotalItemCount = 0;

    public BaseScrollListener(LinearLayoutManager layoutManager) {
         mLayoutManager = layoutManager;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

//        if (dy > 0) {
//            int visibleItemCount = mLayoutManager.getChildCount();
//            int totalItemCount = mLayoutManager.getItemCount();
//            int firstVisibleItemPosition = mLayoutManager.findLastVisibleItemPosition();
////            int totalItemCount = mLayoutManager.getItemCount();
////
////            lastVisibleItemPosition = ((LinearLayoutManager) mLayoutManager)
////                    .findLastVisibleItemPosition();
//
////
////            if ((lastVisibleItemPosition + Constants.TOTAL_PAGE) > totalItemCount) {
////                loadMoreItems();
////            }
//
//            if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount && firstVisibleItemPosition >= 0) {
//                loadMoreItems();
//            }
//        }

        if (dy > 0) {
            int lastVisibleItemPosition = 0;
            final int totalItemCount = mLayoutManager.getItemCount();

            lastVisibleItemPosition = ((LinearLayoutManager) mLayoutManager)
                    .findLastVisibleItemPosition();

            //reset the list.
            if (totalItemCount < previousTotalItemCount) {
                this.currentPage = this.startingPageIndex;
                this.previousTotalItemCount = totalItemCount;
                if (totalItemCount == 0) {
                    this.isLoading = true;
                }
            }

            if (isLoading && (totalItemCount > previousTotalItemCount)) {
                isLoading = false;
                previousTotalItemCount = totalItemCount;
            }

            if (!isLoading && (lastVisibleItemPosition + visibleThreshold) > totalItemCount) {
                currentPage++;
                loadMoreItems();
                isLoading = true;
            }
        }
    }

    protected abstract void loadMoreItems();
}
