package com.example.wu.com.example.department.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.bumptech.glide.Glide;
import com.example.wu.R;
import com.example.wu.com.example.department.adapter.MyPagerAdapter;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 *   fragmentActivity的第二页
 */
public class TabSecondFragment extends Fragment {
    protected View mView;
    //翻页成员变量声明
    private ViewPager viewPager;
    private View viewpager1,viewpager2,viewpager3,viewpager4;
    private List<View> viewList;

    //按钮组的声明
    private RadioGroup radioGroup;

    //横幅条成员变量声明
    private Banner mbanner;
    private int[] imageResouce =new int[]{R.drawable.one, R.drawable.two,R.drawable.three,R.drawable.four};
    private List<Integer> imageList=new ArrayList<Integer>();
    private MyPagerAdapter myPagerAdapter;
    private RadioButton one,two,three,four,five,six,seven,eigth,nine;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_tab_second, container, false);
        //对控件的声明
        initView();
        //翻页视图的适配器方法
        viewPagerselect();
        //横幅条的运行方法
        init();
        //按钮组的点击事件
        radioGroup.setOnCheckedChangeListener(new MyOnClickListener());
        //翻页视图的事件监听
        viewPager.addOnPageChangeListener(new addOnPageChangeListener());

        return mView;
    }

    private void initView() {
        viewPager=(ViewPager)mView.findViewById(R.id.main_viewpager);
        radioGroup=(RadioGroup)mView.findViewById(R.id.radio);
        one=(RadioButton)mView.findViewById(R.id.sec_jingxuan);
        two=(RadioButton)mView.findViewById(R.id.sec_Vip);
        three=(RadioButton)mView.findViewById(R.id.sec_mianfei);
        four=(RadioButton)mView.findViewById(R.id.sec_man);
        five=(RadioButton)mView.findViewById(R.id.sec_women);
        six=(RadioButton)mView.findViewById(R.id.sec_newbook);
        seven=(RadioButton)mView.findViewById(R.id.sec_chuban);
        eigth=(RadioButton)mView.findViewById(R.id.sec_dongman);
    }

    private void viewPagerselect() {
        //书城页面翻页的添加
        LayoutInflater layoutInflater=getLayoutInflater();
        viewpager1=layoutInflater.inflate(R.layout.main_viewpager_first,null);
        viewpager2=layoutInflater.inflate(R.layout.main_viewpager_second,null);
        viewpager3=layoutInflater.inflate(R.layout.main_viewpager_third,null);
        viewpager4=layoutInflater.inflate(R.layout.main_viewpager_four,null);
        //将要分页显示的View装进数组中
        viewList=new ArrayList<View>();
        viewList.add(viewpager1);
        viewList.add(viewpager2);
        viewList.add(viewpager3);
        viewList.add(viewpager4);
        //翻页视图的适配器
        myPagerAdapter=new MyPagerAdapter((ArrayList<View>) viewList);
        viewPager.setAdapter(myPagerAdapter);
    }

    //横幅条的运行方法
    private void init() {
        imageList.clear();
        mbanner=mView.findViewById(R.id.sec_banner);
        for (int i=0;i<imageResouce.length;i++){
            imageList.add(imageResouce[i]);
            mbanner.setImageLoader(new ImageLoader() {
                @Override
                public void displayImage(Context context, Object path, ImageView imageView) {
                    Glide.with(getActivity()).load(path).into(imageView);
                }
            });
            mbanner.setDelayTime(3000);//时间
            mbanner.setImages(imageList);//添加的图片数组
            mbanner.start();
        }
    }
    //按钮组的点击事件
    class MyOnClickListener implements RadioGroup.OnCheckedChangeListener{
        //设置按钮组的字体大小
        private void CheckSelect(RadioButton radioButton){
            one.setTextSize(19);
            two.setTextSize(19);
            three.setTextSize(19);
            four.setTextSize(19);
            radioButton.setTextSize(25);
        }

        /*
        对点击单选按钮的监听
         */
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId){
                case R.id.sec_jingxuan:
                    viewPager.setCurrentItem(0);
                    CheckSelect(one);
                    break;
                case R.id.sec_Vip:
                    viewPager.setCurrentItem(1);
                    CheckSelect(two);
                    break;
                case R.id.sec_mianfei:
                    viewPager.setCurrentItem(2);
                    CheckSelect(three);
                    break;
                case R.id.sec_man:
                    viewPager.setCurrentItem(3);
                    CheckSelect(four);
                    break;
            }
        }
    }
    //翻页视图的事件监听
    class addOnPageChangeListener implements ViewPager.OnPageChangeListener{

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            switch (position){
                case 0:
                    one.setChecked(true);
                    break;
                case 1:
                    two.setChecked(true);
                    break;
                case 2:
                    three.setChecked(true);
                    break;
                case 3:
                    four.setChecked(true);
                    break;
            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }
}
