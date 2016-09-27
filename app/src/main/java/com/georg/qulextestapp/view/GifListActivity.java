package com.georg.qulextestapp.view;

import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.georg.qulextestapp.R;
import com.georg.qulextestapp.presentor.GifListPresenter;
import com.georg.qulextestapp.view.adapter.GifAdapter;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;

public class GifListActivity extends BaseActivity implements GifListView, SearchView.OnQueryTextListener, SwipeRefreshLayout.OnRefreshListener {

    @Inject
    GifListPresenter presenter;

    @BindView(R.id.swiperefresh)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.rv_gifs)
    RecyclerView recyclerView;
    @BindView(R.id.tv_empty_list)
    TextView emptyView;

    private GifAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getAppComponent().inject(this);
        setContentView(R.layout.activity_gif_list);
        presenter.attachView(this);

        initList();
        presenter.showTrendGifList();
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    @Override
    public void startUpdate() {
        swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void stopUpdate() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showMessage(String title, String message) {
        Toast.makeText(this,title + " " + message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setGifItemList(ArrayList<String> gifItemList) {
        if (gifItemList != null && gifItemList.size()>0) {
            swipeRefreshLayout.setVisibility(View.VISIBLE);
            emptyView.setVisibility(View.GONE);
            mAdapter.setGifItems(gifItemList);
        }
        else {
            swipeRefreshLayout.setVisibility(View.GONE);
            emptyView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        final MenuItem item = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setQueryHint(getString(R.string.search_hint));
        searchView.setOnQueryTextListener(this);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_refresh){
            onRefresh();
        }

        return super.onOptionsItemSelected(item);
    }

    private void initList(){
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        mAdapter = new GifAdapter(this);
        mAdapter.setHasStableIds(true);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        presenter.showRequestGifList(query);
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    @Override
    public void onRefresh() {
        presenter.refresh();
    }
}
