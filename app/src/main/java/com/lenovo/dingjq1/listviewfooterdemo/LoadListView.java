package com.lenovo.dingjq1.listviewfooterdemo;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

/**
 * Created by dingjq on 2017/10/31.
 */

public class LoadListView extends ListView implements AbsListView.OnScrollListener {
    private View footer;
    private int lastVisibleItemCount;
    private int totalItemCount;
    private ILoadListener iloadListener;
    private boolean isLoading = false;

    public LoadListView(Context context) {
        super(context);
        initView(context);
    }


    public LoadListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public LoadListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public LoadListView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(context);
    }

    private void initView(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        footer = inflater.inflate(R.layout.footer_layout, null);
        footer.findViewById(R.id.load_layout).setVisibility(View.GONE);
        addFooterView(footer);
        this.setOnScrollListener(this);
      //  this.iloadListener = (ILoadListener)context;
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (lastVisibleItemCount == totalItemCount
                && scrollState == SCROLL_STATE_IDLE) {
            if (!isLoading) {
                isLoading = true;
                footer.findViewById(R.id.load_layout).setVisibility(View.VISIBLE);
                iloadListener.onLoad();
            }
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        this.lastVisibleItemCount = firstVisibleItem + visibleItemCount;
        this.totalItemCount = totalItemCount;

    }

    public void loadComplete() {
        isLoading = false;
        footer.findViewById(R.id.load_layout).setVisibility(View.GONE);
    }

    public void setInterface(ILoadListener iLoadListener) {
        this.iloadListener = iLoadListener;
    }

    interface ILoadListener{
        void onLoad();
    }
}
