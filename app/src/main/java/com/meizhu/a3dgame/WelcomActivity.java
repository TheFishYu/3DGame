package com.meizhu.a3dgame;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Toast;

import com.meizhu.a3dgame.services.DownloadService;
import com.meizhu.a3dgame.utils.NetUtils;

import pl.droidsonroids.gif.GifImageView;

public class WelcomActivity extends AppCompatActivity {

    private GifImageView gifImageView;
    private Animation animation;
    private boolean netopen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcom);
        gifImageView = (GifImageView) findViewById(R.id.gifimageview_welcom);
        //添加个动画
        animation = new AlphaAnimation(0,1.0f);
        animation.setDuration(2000);
        gifImageView.startAnimation(animation);
        //给动画添加个监听
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                //判断网络
                netopen = NetUtils.netConnect(WelcomActivity.this);
                if (netopen) {
                    //开始Service，下载数据
                    Intent downloadServiceIntent = new Intent(WelcomActivity.this, DownloadService.class);
                    startService(downloadServiceIntent);
                }
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (!netopen) {
                    Toast.makeText(WelcomActivity.this, "网络连接失败", Toast.LENGTH_SHORT).show();
                }
                //判断是否第一次登陆,选择性进行跳转
                isFristLogin();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }
    //判断是否第一次登陆
    private  void isFristLogin(){
        //创建sharedPreferences对象
        SharedPreferences sharedPreferences = getSharedPreferences("isFistLogin", Context.MODE_PRIVATE);
        //获得sharedPreferences对象中的isLogin属性
        boolean isLogin = sharedPreferences.getBoolean("isLogin",false);

        //如果是第一次登陆，就跳转到引导界面，否则的话，跳转到主界面
        if(!isLogin){
            Intent guideIntent = new Intent(WelcomActivity.this,GuideActivity.class);
            startActivity(guideIntent);
            finish();
        }else{
            Intent mainIntent = new Intent(WelcomActivity.this,MainActivity.class);
            startActivity(mainIntent);
            finish();
        }
    }
}
