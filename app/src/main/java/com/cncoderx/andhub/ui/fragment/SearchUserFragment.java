package com.cncoderx.andhub.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.cncoderx.andhub.BR;
import com.cncoderx.andhub.R;
import com.cncoderx.andhub.model.SearchResult;
import com.cncoderx.andhub.model.User;
import com.cncoderx.andhub.okhttp.ServiceGenerator;
import com.cncoderx.andhub.okhttp.service.ISearchService;
import com.cncoderx.andhub.ui.activity.ProfileActivity;
import com.cncoderx.andhub.ui.adapter.BindingAdapter;
import com.cncoderx.andhub.utils.IntentExtra;
import com.cncoderx.andhub.utils.Pagination;
import com.cncoderx.recyclerviewhelper.listener.OnItemClickListener;
import com.cncoderx.recyclerviewhelper.listener.OnLoadMoreListener;
import com.cncoderx.recyclerviewhelper.utils.ILoadingView;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.functions.Function;

/**
 * @author cncoderx
 */
public class SearchUserFragment extends RecyclerViewFragment
        implements OnItemClickListener, OnLoadMoreListener {
    private BindingAdapter<User> mAdapter = new BindingAdapter<>(R.layout.item_user_list, BR.user);
    private Pagination<User> mPagination = new Pagination<>(mAdapter, new Pagination.Factory<User>() {
        ISearchService mService = ServiceGenerator.create(ISearchService.class);

        @Override
        public Single<List<User>> create(int pageIndex, int pageSize) {
            return mService.searchUsers(mKey, mSort, mOrder, pageIndex, pageSize)
                    .map(new Function<SearchResult<User>, List<User>>() {
                        @Override
                        public List<User> apply(SearchResult<User> result) throws Exception {
                            return result.items;
                        }
                    });
        }
    });

    private String mKey, mSort, mOrder;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setAdapter(mAdapter);
        setOnItemClickListener(this);
        setOnLoadMoreListener(this);
        mPagination.setTransformer(this.<List<User>>bindToLifecycle());
    }

    public void search(String key, String sort, String order) {
        mKey = key; mSort = sort; mOrder = order;
        if (TextUtils.isEmpty(mKey)) {
            return;
        }
        setEmptyText(getString(R.string.search_no_result, key));
        mPagination.bind(getEmptyView());
        mPagination.bind(getRefreshLayout());
        mPagination.newPage();
        getRefreshLayout().setRefreshing(true);
    }

    @Override
    public void load(RecyclerView recyclerView, ILoadingView view) {
        mPagination.bind(view);
        mPagination.nextPage();
    }

    @Override
    public void onItemClick(RecyclerView recyclerView, View view, int position, long id) {
        User user = mAdapter.get(position);
        Intent intent = new Intent(getActivity(), ProfileActivity.class);
        intent.putExtra(IntentExtra.KEY_USER, user.login);
        startActivity(intent);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if (hidden) {
            if (mAdapter.size() > 0)
                mAdapter.clear();
        }
    }
}
