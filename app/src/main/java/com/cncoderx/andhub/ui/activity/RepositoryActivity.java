package com.cncoderx.andhub.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.cncoderx.andhub.R;
import com.cncoderx.andhub.ui.fragment.RepoCodeFragment;
import com.cncoderx.andhub.ui.fragment.RepoHomeFragment;
import com.cncoderx.andhub.ui.fragment.RepoIssuesFragment;
import com.cncoderx.andhub.ui.fragment.RepoPullsFragment;
import com.cncoderx.andhub.ui.view.AppBar;
import com.cncoderx.andhub.utils.IntentExtra;

public class RepositoryActivity extends TabPagerActivity {
    static final int TAB_COUNT = 4;
    static final int TAB_INDEX_HOME = 0;
    static final int TAB_INDEX_CODE = 1;
    static final int TAB_INDEX_ISSUES = 2;
    static final int TAB_INDEX_PULLS = 3;

    private String mOwner, mRepo;

    AppBar mAppBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAppBar = (AppBar) findViewById(R.id.app_bar);
        mOwner = getIntent().getStringExtra(IntentExtra.KEY_OWNER);
        mRepo = getIntent().getStringExtra(IntentExtra.KEY_REPO);
        mAppBar.setTitle(mOwner + "/" + mRepo);
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
                return RepoHomeFragment.create(mOwner, mRepo);
            case TAB_INDEX_CODE:
                return RepoCodeFragment.create(mOwner, mRepo);
            case TAB_INDEX_ISSUES:
                return RepoIssuesFragment.create(mOwner, mRepo);
            case TAB_INDEX_PULLS:
                return RepoPullsFragment.create(mOwner, mRepo);
        }
        return null;
    }
}
