package com.example.wu.com.example.department.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * 书城翻页视图的适配器
 */
public class MyPagerAdapter extends PagerAdapter {
    private ArrayList<View> viewLists;

    public MyPagerAdapter() {
    }

    public MyPagerAdapter(ArrayList<View> viewLists) {
        super();
        this.viewLists = viewLists;
    }
    //返回要滑动的View的个数
    @Override
    public int getCount() {
        return viewLists.size();
    }
    //不知道为啥
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
    //第一：将当前视图添加到container中，第二：返回当前View

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(viewLists.get(position));
        return viewLists.get(position);
    }
    //从当前container中删除指定位置的View
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(viewLists.get(position));
    }
}
