package com.meizhu.a3dgame.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.meizhu.a3dgame.News;
import com.meizhu.a3dgame.R;
import com.meizhu.a3dgame.adapter.MainArticlePicFramentViewPagerAdapter;
import com.meizhu.a3dgame.adapter.MyAdapter;
import com.meizhu.a3dgame.asynctask.DownDataAscytast;
import com.meizhu.a3dgame.customview.MainArticleFragmentViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kun Yu on 2016/7/8.
 */
public class ArticleCenterFragment extends Fragment {
    String urlStr = "http://www.3dmgame.com/sitemap/api.php?row=20&typeid=1&paging=1&page=";
    int pagerIndex = 1;

    //定义文章类型
    private int typeid;
    ListView articleListView;
    List<News> news;
    MyAdapter myAdapter;

    MainArticleFragmentViewPager mainArticleFragmentViewPager;
    MainArticlePicFramentViewPagerAdapter mainArticlePicFramentViewPagerAdapter;

    public ArticleCenterFragment() {
    }

    //Annotation 注解
    @SuppressLint("ValidFragment")
    public ArticleCenterFragment(int typeid) {
        this.typeid = typeid;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //获得Fragment中整体布局
        View view = inflater.inflate(R.layout.activity_center_fragment, null);
        //获取Fragment中的ViewPager
        MainArticleFragmentViewPager mainArticleFragmentViewPager = (MainArticleFragmentViewPager)
                view.findViewById(R.id.main_articlefragment_pic_viewpager);
        articleListView = (ListView) view.findViewById(R.id.main_articlefragment_context_listview);

        int imageRsId[] = {R.drawable.default1, R.drawable.default2, R.drawable.default3};
        //初始化viewpager的数据
        List<ImageView> imageViews = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            ImageView imageView = new ImageView(getContext());
            //设置图片的缩放类型 铺满全屏
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setImageResource(imageRsId[i]);
            imageViews.add(imageView);
        }

        mainArticlePicFramentViewPagerAdapter = new MainArticlePicFramentViewPagerAdapter(imageViews);
        mainArticleFragmentViewPager.setAdapter(mainArticlePicFramentViewPagerAdapter);


        //填充listView数据
        news = new ArrayList<>();
        myAdapter = new MyAdapter(getContext(), news);
        articleListView.setAdapter(myAdapter);

        String urlPath = urlStr + pagerIndex;
        new DownDataAscytast(articleListView, myAdapter, news).execute(urlPath);
        return  view;
    }
}
