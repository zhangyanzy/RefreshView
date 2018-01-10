package com.zhangyan.refreshview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView mRecycleView;
    private Button mAdd, mRemove;
    private MainRecycleViewAdapter mAdapter;
    private List<String> mDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecycleView = (RecyclerView) findViewById(R.id.main_recycle_view);
        mAdd = (Button) findViewById(R.id.add_recycle_view_item);
        mAdd.setOnClickListener(this);
        mRemove = (Button) findViewById(R.id.remove_recycle_view_item);
        mRemove.setOnClickListener(this);
        initDatas();
        initAdapter();
    }

    private void initDatas() {
        mDatas = new ArrayList<>();
        for (int i = 1; i <= 30; i++) {
            mDatas.add("Item" + i);
        }
    }

    private void initAdapter() {
        mAdapter = new MainRecycleViewAdapter(mDatas);
        mRecycleView.setLayoutManager(new LinearLayoutManager(this));
        mRecycleView.setAdapter(mAdapter);
        mRecycleView.setItemAnimator(new DefaultItemAnimator());
        mRecycleView.addItemDecoration(new DividerItemDecoration(this,LinearLayoutManager.VERTICAL));
        mAdapter.setOnItemClickListener(new MainRecycleViewAdapter.onItemClickListener() {
            @Override
            public void onClick(View v, int position) {
                Toast.makeText(getApplicationContext(), "item"+position, Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_recycle_view_item:
                mAdapter.addItem(0);
                break;
            case R.id.remove_recycle_view_item:
                mAdapter.removeItem(0);
            default:
                break;
        }

    }


}
