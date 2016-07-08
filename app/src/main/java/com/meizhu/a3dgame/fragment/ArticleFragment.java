package com.meizhu.a3dgame.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.meizhu.a3dgame.R;
import com.meizhu.a3dgame.adapter.MainArticleCenterFragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kun Yu on 2016/7/6.
 */
public class ArticleFragment extends Fragment implements RadioGroup.OnCheckedChangeListener, ViewPager.OnPageChangeListener {
    HorizontalScrollView horizontalScrollView_top;
    RadioGroup radioGroup_top;
    RadioButton rb01_top, rb02_top, rb03_top, rb04_top, rb05_top,
            rb06_top, rb07_top, rb08_top, rb09_top, rb10_top;

    ViewPager viewPager;
    List<Fragment> fragments;
    MainArticleCenterFragmentPagerAdapter mainArticleCenterFragmentPagerAdapter;

    ListView artivleListView;

    FragmentManager fragmentManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.article_fragment, null);

        initView(view);
        initFragment();
        initListener();

        return view;
    }

    private void initListener() {
        radioGroup_top.setOnCheckedChangeListener(this);
        viewPager.addOnPageChangeListener(this);
    }

    private void initView(View view) {
        horizontalScrollView_top = (HorizontalScrollView) view.findViewById(R.id.main_top_hsv);
        radioGroup_top = (RadioGroup) view.findViewById(R.id.main_top_rg);
        rb01_top = (RadioButton) view.findViewById(R.id.main_top_rb1);
        rb02_top = (RadioButton) view.findViewById(R.id.main_top_rb2);
        rb03_top = (RadioButton) view.findViewById(R.id.main_top_rb3);
        rb04_top = (RadioButton) view.findViewById(R.id.main_top_rb4);
        rb05_top = (RadioButton) view.findViewById(R.id.main_top_rb5);
        rb06_top = (RadioButton) view.findViewById(R.id.main_top_rb6);
        rb07_top = (RadioButton) view.findViewById(R.id.main_top_rb7);
        rb08_top = (RadioButton) view.findViewById(R.id.main_top_rb8);
        rb09_top = (RadioButton) view.findViewById(R.id.main_top_rb9);
        rb10_top = (RadioButton) view.findViewById(R.id.main_top_rb10);
        rb01_top.setChecked(true);
        viewPager = (ViewPager) view.findViewById(R.id.main_center_vp);
    }


    private void initFragment() {
        fragments = new ArrayList<>();
        //添加Fragment
        ArticleCenterFragment f1 = new ArticleCenterFragment(1);
        ArticleCenterFragment f2 = new ArticleCenterFragment(2);
        ArticleCenterFragment f3 = new ArticleCenterFragment(1);
        ArticleCenterFragment f4 = new ArticleCenterFragment(2);
        ArticleCenterFragment f5 = new ArticleCenterFragment(1);
        ArticleCenterFragment f6 = new ArticleCenterFragment(1);
        ArticleCenterFragment f7 = new ArticleCenterFragment(1);
        ArticleCenterFragment f8 = new ArticleCenterFragment(1);
        fragments.add(f1);
        fragments.add(f2);
        fragments.add(f3);
        fragments.add(f4);
        fragments.add(f5);
        fragments.add(f6);
        fragments.add(f7);
        fragments.add(f8);
        mainArticleCenterFragmentPagerAdapter = new MainArticleCenterFragmentPagerAdapter(getFragmentManager(), fragments);
        viewPager.setAdapter(mainArticleCenterFragmentPagerAdapter);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.main_top_rb1:
                viewPager.setCurrentItem(0);
                Toast.makeText(getContext(), "top rb01", Toast.LENGTH_SHORT).show();
                break;
            case R.id.main_top_rb2:
                viewPager.setCurrentItem(1);
                Toast.makeText(getContext(), "top rb02", Toast.LENGTH_SHORT).show();
                break;
            case R.id.main_top_rb3:
                viewPager.setCurrentItem(2);
                Toast.makeText(getContext(), "top rb03", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        //顶部的滚动条出现移动效果
        horizontalScrollView_top.setVisibility(View.VISIBLE);
        radioGroup_top.setVisibility(View.VISIBLE);

        //获得当前ViewPager对应的RadioButton
        RadioButton radioButton = (RadioButton) radioGroup_top.getChildAt(position);
        radioButton.setChecked(true);
        //让顶部的RadioButton随着ViewPager一起滚动
        int left = radioButton.getLeft();
        horizontalScrollView_top.smoothScrollTo(left, 0);

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
