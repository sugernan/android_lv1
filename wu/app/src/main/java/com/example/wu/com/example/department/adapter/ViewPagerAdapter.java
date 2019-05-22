package com.example.wu.com.example.department.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.wu.R;

import java.util.List;

/**
 * 引导页的适配器
 */
public class ViewPagerAdapter extends PagerAdapter {

    private List<View> views;
    private Context context;



    public   ViewPagerAdapter(List<View> views, Context context){
        this.views=views;
        this.context=context;
    }
    //移除一个view
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
//        super.destroyItem(container, position, object);
        container.removeView(views.get(position));
    }
    //加载一个view
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        container.addView(views.get(position));
        return views.get(position);
    }
    //返回当前的View的个数
    @Override
    public int getCount() {
        return this.views.size();
    }
    //判断当前的view是否是我们需要的对象
    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return (view==o);
    }
}
