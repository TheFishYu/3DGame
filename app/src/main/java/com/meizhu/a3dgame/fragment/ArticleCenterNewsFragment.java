package com.meizhu.a3dgame.fragment;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.meizhu.a3dgame.ArticleDailsActivity;
import com.meizhu.a3dgame.News;
import com.meizhu.a3dgame.R;
import com.meizhu.a3dgame.adapter.MyAdapter;
import com.meizhu.a3dgame.asynctask.DownDataAscytast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kun Yu on 2016/7/10.
 */
public class ArticleCenterNewsFragment extends Fragment {
    //定义文章类型
    private int typeid;
    int pagerIndex = 1;




    View footView;
    ListView articleListView;
    List<News> news;
    MyAdapter myAdapter;

    public ArticleCenterNewsFragment() {
    }

    //Annotation 注解
    @SuppressLint("ValidFragment")
    public ArticleCenterNewsFragment(int typeid) {
        this.typeid = typeid;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //获得Fragment中整体布局
        View view = inflater.inflate(R.layout.fragment_listview, null);

        //获取Fragment中的ListView
        articleListView = (ListView) view.findViewById(R.id.fragment_lv);

        //填充listView数据
        news = new ArrayList<>();
        myAdapter = new MyAdapter(getContext(), news);
        footView = View.inflate(getContext(),R.layout.loading,null);
        articleListView.addFooterView(footView);
        articleListView.setAdapter(myAdapter);
        articleListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(), ArticleDailsActivity.class);
                intent.putExtra("articleId", news.get(position).getId());
                intent.putExtra("articleTypeId", news.get(position).getTypeid());
                Toast.makeText(getActivity(), "dizhi:"+
                        news.get(position).getId(), Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });

        //开启异步任务
        String urlStr = "http://www.3dmgame.com/sitemap/api.php?" +
                "row=20&paging=1&typeid="+typeid+"&page=";
        final String urlPath = urlStr + pagerIndex;
        new DownDataAscytast(articleListView, myAdapter, news).execute(urlPath);
        articleListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            boolean isBottom;

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (scrollState == 0 && isBottom ) {

                    pagerIndex++;
                    DownDataAscytast downDataAscytast =
                            new DownDataAscytast(articleListView,myAdapter,news);
                    downDataAscytast.execute(urlPath);
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (firstVisibleItem + visibleItemCount == totalItemCount) {
                    isBottom = true;
                }else{
                    isBottom = false;
                }
            }
        });
        return  view;
    }
}
