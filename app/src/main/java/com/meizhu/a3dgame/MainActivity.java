package com.meizhu.a3dgame;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.meizhu.a3dgame.fragment.ArticleFragment;
import com.meizhu.a3dgame.fragment.ForumFragment;
import com.meizhu.a3dgame.fragment.GameFragment;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    RadioGroup radioGroup_bottom;
    RadioButton rb01_bottom, rb02_bottom, rb03_bottom;
    FrameLayout frameLayout;

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    ForumFragment forumFragment;
    ArticleFragment articleFragment;
    WebView webView;

    GameFragment gameFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initListener();

        articlePlay();
//        gamePlay();
    }

    private void initView() {

        frameLayout = (FrameLayout) findViewById(R.id.main_framelayout);
        radioGroup_bottom = (RadioGroup) findViewById(R.id.main_bottom_rg);
        rb01_bottom = (RadioButton) findViewById(R.id.main_bottom_rb01);
        rb02_bottom = (RadioButton) findViewById(R.id.main_bottom_rb02);
        rb03_bottom = (RadioButton) findViewById(R.id.main_bottom_rb03);

        //初始化碎片管理
        fragmentManager = getSupportFragmentManager();

    }

    private void initListener() {

        radioGroup_bottom.setOnCheckedChangeListener(this);
    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.main_bottom_rb01:
                articlePlay();
                break;
            case R.id.main_bottom_rb02:
                forumPlay();
                break;
            case R.id.main_bottom_rb03:
                gamePlay();
                break;
        }
    }

    private void gamePlay() {
        fragmentTransaction = fragmentManager.beginTransaction();
        gameFragment = new GameFragment();
        fragmentTransaction.replace(R.id.main_framelayout,gameFragment);
        fragmentTransaction.commit();
    }

    private void forumPlay() {
        fragmentTransaction = fragmentManager.beginTransaction();
        forumFragment = new ForumFragment();
        fragmentTransaction.replace(R.id.main_framelayout, forumFragment);
        fragmentTransaction.commit();
    }

    private void articlePlay() {
        fragmentTransaction = fragmentManager.beginTransaction();
        articleFragment = new ArticleFragment();
        fragmentTransaction.replace(R.id.main_framelayout, articleFragment);
        fragmentTransaction.commit();
    }


}
