package com.levylin.detailscrollview;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.levylin.detailscrollview.adapter.LoadMoreAdapter;
import com.levylin.detailscrollview.adapter.RecyclerViewAdapter;
import com.levylin.detailscrollview.views.DetailScrollView;
import com.levylin.detailscrollview.views.DetailWebView;

import java.util.ArrayList;
import java.util.List;

public class WebViewRecyclerViewActivity extends AppCompatActivity implements View.OnClickListener {

    private DetailScrollView mScrollView;
    int page = 1;
    BaseQuickAdapter mQuickAdapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_webview_recyclerview);
        mScrollView = (DetailScrollView) findViewById(R.id.test_sv);
        findViewById(R.id.move_to_list).setOnClickListener(this);
        recyclerView = (RecyclerView) findViewById(R.id.test_lv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mQuickAdapter = new LoadMoreAdapter(initDatas(page));
        recyclerView.setAdapter(mQuickAdapter);

        mQuickAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                recyclerView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        page ++;
                        mQuickAdapter.addData(initDatas(page));
                        mQuickAdapter.loadMoreComplete();
                    }
                },2000);
            }
        }, recyclerView);

        String url = "http://www.jianshu.com/p/f36efc27e945";
        DetailWebView webView = (DetailWebView) findViewById(R.id.test_webview);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) { //  重写此方法表明点击网页里面的链接还是在当前的webview里跳转，不跳到浏览器那边
                view.loadUrl(url);
                return true;
            }
        });
        webView.loadUrl(url);
    }

    private List<String> initDatas(int page) {
        List<String> list = new ArrayList<>();
        for (int i = page * 10 - 10; i < page * 10; i++) {
            list.add("测试:" + i);
        }
        return list;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.move_to_list:
                mScrollView.toggleScrollToListView();
                break;
        }
    }
}
