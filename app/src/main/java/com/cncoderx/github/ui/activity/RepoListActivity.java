package com.cncoderx.github.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.cncoderx.github.R;
import com.cncoderx.github.sdk.ServiceGenerator;
import com.cncoderx.github.sdk.model.Repository;
import com.cncoderx.github.sdk.service.IRepositoryService;
import com.cncoderx.github.ui.adapter.RepoListAdapter;
import com.cncoderx.github.utils.IntentExtra;
import com.cncoderx.github.utils.ListCallback;
import com.cncoderx.recyclerviewhelper.listener.OnItemClickListener;

import java.util.List;

import retrofit2.Call;

/**
 * @author cncoderx
 */
public class RepoListActivity extends SwipeRecyclerViewActivity implements OnItemClickListener {
    private RepoListAdapter mAdapter = new RepoListAdapter();
    private ListCallback<Repository> mCallback = new ListCallback<>(mAdapter, false);
    private String mUser;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUser = getIntent().getStringExtra(IntentExtra.KEY_USER);
        setAdapter(mAdapter);
        setOnItemClickListener(this);
        setEmptyText(getString(R.string.no_repos));
        mCallback.setEmptyView(getEmptyView());

        getSwipeRefreshLayout().setRefreshing(true);
        onRefresh();
    }

    @Override
    public void onItemClick(RecyclerView recyclerView, View view, int position, long id) {
        Repository repository = mAdapter.get(position);
        Intent intent = new Intent(this, RepositoryActivity.class);
        intent.putExtra(IntentExtra.KEY_OWNER, repository.owner.login);
        intent.putExtra(IntentExtra.KEY_REPO, repository.name);
        startActivity(intent);
    }

    @Override
    public void onRefresh() {
        IRepositoryService service = ServiceGenerator.create(IRepositoryService.class);
        Call<List<Repository>> call = service.getRepositories(mUser);
        mCallback.setRefreshView(getSwipeRefreshLayout()).queueTo(call, true);
    }
}
