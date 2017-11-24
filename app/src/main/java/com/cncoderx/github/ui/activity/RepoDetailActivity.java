package com.cncoderx.github.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.cncoderx.github.R;
import com.cncoderx.github.ui.fragment.RepoDetailCodeFragment;
import com.cncoderx.github.ui.fragment.RepoDetailHomeFragment;
import com.cncoderx.github.ui.fragment.RepoDetailIssuesFragment;
import com.cncoderx.github.ui.fragment.RepoDetailPullsFragment;
import com.cncoderx.github.utils.IntentExtra;

public class RepoDetailActivity extends TabPagerActivity {
    static final int TAB_COUNT = 4;
    static final int TAB_INDEX_HOME = 0;
    static final int TAB_INDEX_CODE = 1;
    static final int TAB_INDEX_ISSUES = 2;
    static final int TAB_INDEX_PULLS = 3;

    private String mOwner, mRepo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mOwner = getIntent().getStringExtra(IntentExtra.KEY_OWNER);
        mRepo = getIntent().getStringExtra(IntentExtra.KEY_REPO);
    }

    @Override
    public int getCount() {
        return TAB_COUNT;
    }

    @Override
    public CharSequence getTabItemText(int index) {
        switch (index) {
            case TAB_INDEX_HOME:
                return getString(R.string.home);
            case TAB_INDEX_CODE:
                return getString(R.string.code);
            case TAB_INDEX_ISSUES:
                return getString(R.string.issues);
            case TAB_INDEX_PULLS:
                return getString(R.string.pull_requests);
        }
        return "";
    }

    @Override
    public CharSequence getTabItemIconUnicode(int index) {
        switch (index) {
            case TAB_INDEX_HOME:
                return getString(R.string.ic_file);
            case TAB_INDEX_CODE:
                return getString(R.string.ic_code);
            case TAB_INDEX_ISSUES:
                return getString(R.string.ic_issue);
            case TAB_INDEX_PULLS:
                return getString(R.string.ic_pull);
        }
        return null;
    }

    @Override
    public Fragment getFragment(int index) {
        switch (index) {
            case TAB_INDEX_HOME:
                return RepoDetailHomeFragment.create(mOwner, mRepo);
            case TAB_INDEX_CODE:
                return RepoDetailCodeFragment.create(mOwner, mRepo);
            case TAB_INDEX_ISSUES:
                return RepoDetailIssuesFragment.create(mOwner, mRepo);
            case TAB_INDEX_PULLS:
                return RepoDetailPullsFragment.create(mOwner, mRepo);
        }
        return null;
    }
}
