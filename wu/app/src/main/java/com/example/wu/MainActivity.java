package com.example.wu;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.wu.com.example.department.activity.HomeActivity;
import com.example.wu.com.example.department.adapter.ViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 引导页的操作
 */
public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    private ViewPager vp;
    private ViewPagerAdapter viewPagerAdapter;
    private List<View> views;

    //导航点
    private ImageView[] dots;
    private int[] ids={R.id.iv1,R.id.iv2,R.id.iv3};

    //进入按钮
    private Button bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.initView();
        this.initDots();
    }

    //初始化viewpager
    private void initView() {
        LayoutInflater inflater=LayoutInflater.from(MainActivity.this);
        views=new ArrayList<View>();
        views.add(inflater.inflate(R.layout.viewpagerone,null));
        views.add(inflater.inflate(R.layout.viewpagertwo,null));
        views.add(inflater.inflate(R.layout.viewpagerthree,null));
        viewPagerAdapter=new ViewPagerAdapter(views,MainActivity.this);
        vp=(ViewPager)findViewById(R.id.viewpager);
        vp.setAdapter(viewPagerAdapter);

        vp.setOnPageChangeListener(this);
        //按钮事件的点击事件
        bt=(Button)views.get(2).findViewById(R.id.btn_start);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    //初始化导航点
    private void initDots() {
        this.dots = new ImageView[this.views.size()];
        for (int i=0; i<this.views.size(); i++) {
            dots[i] = (ImageView) this.findViewById(this.ids[i]);
        }
    }

    @Override
    public void onPageScrolled(int position, float v, int i1) {
        for (int i=0; i<this.ids.length; i++) {
            if (position == i) {
                this.dots[i].setImageResource(R.drawable.login_selectd);  // 变成选中的
            } else {
                this.dots[i].setImageResource(R.drawable.login_point);    //变成不选中的
            }
        }
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
