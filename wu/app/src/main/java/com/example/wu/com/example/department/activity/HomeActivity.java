package com.example.wu.com.example.department.activity;


import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.LinearLayout;

import android.widget.TabHost;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import com.example.wu.MainActivity;
import com.example.wu.R;
import com.example.wu.com.example.department.fragment.TabFirstFragment;
import com.example.wu.com.example.department.fragment.TabFourFragment;
import com.example.wu.com.example.department.fragment.TabSecondFragment;
import com.example.wu.com.example.department.fragment.TabThirdFragment;

/***
 * 引导页之后的主页面
 */
public class HomeActivity extends AppCompatActivity {

    private TextView shujia,shucheng,luntan,wode;
    private static final String TAG = "可以传递参数";
    private FragmentTabHost mTabHost;
    private String[] strings={"书架","书城","论坛","个人"};
    //判断程序是否是第一次运行
    private Boolean first=false;
    public void sha(){
        SharedPreferences sharedPreferences =getSharedPreferences("share",MODE_PRIVATE);
        first=sharedPreferences.getBoolean("share",true);
        if (first){
            Intent intent=new Intent(HomeActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        }else {

        }
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putBoolean("share",false);
        editor.commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sha();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);

        Bundle bundle = new Bundle();
        bundle.putString("tag", TAG);

        //addTab(标题，跳转的Fragment，传递参数的Bundle)
        mTabHost.addTab(getTabView(0,R.drawable.tab_first_selector),
                TabFirstFragment.class,bundle);
        mTabHost.addTab(getTabView(1, R.drawable.tab_second_selector),
                TabSecondFragment.class, bundle);
        mTabHost.addTab(getTabView(2, R.drawable.tab_third_selector),
                TabThirdFragment.class, bundle);
        mTabHost.addTab(getTabView(3,R.drawable.tab_four_selector),
                TabFourFragment.class,bundle);
        //设置tabs之间的分隔线不显示
        mTabHost.getTabWidget().setShowDividers(LinearLayout.SHOW_DIVIDER_NONE);
    }


    //根据字符串和图标的资源编号，获取相应的标签规格
    private TabHost.TabSpec getTabView(int textId, int imgId) {
        String text = strings[textId];
        Drawable drawable = getResources().getDrawable(imgId);
        //必须设置图片大小，否则不显示
        drawable.setBounds(0, 0,120, 60);
        View item_tabbar = getLayoutInflater().inflate(R.layout.item_tabbar, null);
        TextView tv_item = (TextView) item_tabbar.findViewById(R.id.tv_item_tabbar);
        tv_item.setText(text);
        tv_item.setCompoundDrawables(null, drawable, null, null);
        TabHost.TabSpec spec = mTabHost.newTabSpec(text).setIndicator(item_tabbar);
        return spec;
    }
}
