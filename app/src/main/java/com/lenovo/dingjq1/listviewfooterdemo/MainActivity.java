package com.lenovo.dingjq1.listviewfooterdemo;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.lenovo.dingjq1.listviewfooterdemo.adapter.MyAdapter;
import com.lenovo.dingjq1.listviewfooterdemo.entity.ApkEntity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LoadListView.ILoadListener{

    private static final String TAG = "dingjq";

    ArrayList<ApkEntity> apk_list = new ArrayList<ApkEntity>();
    MyAdapter adapter;
    LoadListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getData();
        showListView(apk_list);

    }

    private void showListView(ArrayList<ApkEntity> apk_list) {
        if (adapter == null) {
            listView = (LoadListView) findViewById(R.id.listView);
            listView.setInterface(this);
            adapter = new MyAdapter(this, apk_list);
            listView.setAdapter(adapter);
        } else {
            adapter.onDateChange(apk_list);
        }

    }

    private void getData() {
        for (int i = 0; i < 10; i++) {
            ApkEntity entity = new ApkEntity();
            entity.setName("测试程序"+i);
            entity.setInfo("50w用户");
            entity.setDes("这是一个神奇的应用！");
            apk_list.add(entity);
        }
    }




    @Override
    public void onLoad() {
        Handler mHandle = new Handler();
        mHandle.postDelayed(new Runnable() {
            @Override
            public void run() {
                getLoadData();
                showListView(apk_list);
                listView.loadComplete();
            }
        }, 2000);


    }

    private void getLoadData() {
        for (int i = 0; i < 2; i++) {
            ApkEntity entity = new ApkEntity();
            entity.setName("更多程序"+apk_list.size());
            entity.setInfo("50w用户");
            entity.setDes("这是一个神奇的应用！");
            apk_list.add(entity);
        }

    }
}
