package com.example.liaoli.refreshdemo;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;
import cn.bingoogolapple.refreshlayout.BGAStickinessRefreshViewHolder;

public class MainActivity extends AppCompatActivity implements BGARefreshLayout.BGARefreshLayoutDelegate {

    private BGARefreshLayout mRefreshLayout;
    private RecyclerView mDataRv;
    private NormalRecyclerViewAdapter mAdapter;
    List<RefreshModel> refreshModelList = new ArrayList<>();
    Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRefreshLayout = findViewById(R.id.rl_recyclerview_refresh);
        mDataRv = findViewById(R.id.rv_recyclerview_data);

        mRefreshLayout.setDelegate(this);

        mAdapter = new NormalRecyclerViewAdapter(mDataRv);

        BGAStickinessRefreshViewHolder stickinessRefreshViewHolder = new BGAStickinessRefreshViewHolder(getApplication(), true);
        stickinessRefreshViewHolder.setStickinessColor(R.color.colorPrimary);
        stickinessRefreshViewHolder.setRotateImage(R.mipmap.ic_launcher);
        mRefreshLayout.setRefreshViewHolder(stickinessRefreshViewHolder);

        for(int i = 0 ;  i < 40;i++){
            refreshModelList.add(new RefreshModel("i = " + i," hhhh"));
        }

        mAdapter.setData(refreshModelList);

        mDataRv.setAdapter(mAdapter);

        mDataRv.setLayoutManager(new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false));
//        mDataRv.setLayoutManager(new LinearLayoutManager(mApp, LinearLayoutManager.VERTICAL, false));

       // mDataRv.setAdapter(mAdapter.getHeaderAndFooterAdapter());

    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(final BGARefreshLayout refreshLayout) {

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                refreshLayout.endRefreshing();
            }
        },2000);
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(final BGARefreshLayout refreshLayout) {

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                for(int i = 0 ;  i < 40;i++){
                    refreshModelList.add(new RefreshModel("i = " + i," hhhh"));
                }
                mAdapter.notifyDataSetChanged();
                refreshLayout.endLoadingMore();
            }
        },2000);

        return true;
    }
}
