package com.example.wu.com.example.department.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.wu.R;
import com.gyf.barlibrary.ImmersionBar;

/**
 *   fragmentActivity的第四页
 */

public class TabFourFragment extends Fragment implements AppBarLayout.OnOffsetChangedListener {
    protected View mView;

    private AppBarLayout abl_bar;
    private View v_title_big_mask;
    private View v_toolbar_small_mask;
    private View v_toolbar_search_mask;
    private View include_toolbar_search;
    private View include_toolbar_small;
    private int mMaskColor;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //设置沉浸式状态栏,可参考https://github.com/gyf-dev/ImmersionBar
        ImmersionBar.with(getActivity())
                .statusBarColor(R.color.white)     //状态栏颜色，不写默认透明色
                .init();
        mView = inflater.inflate(R.layout.fragment_tab_four, container, false);

        //AppBarLayout
        abl_bar = (AppBarLayout) mView.findViewById(R.id.abl_bar);
        abl_bar.addOnOffsetChangedListener(this);
        //顶部搜索布局
        include_toolbar_search = mView.findViewById(R.id.include_toolbar_search);
        //扫一扫 付一付 聊一聊 咻一咻 的小图标布局
        include_toolbar_small =mView. findViewById(R.id.include_toolbar_small);

        //顶部搜索布局遮罩()//不是很理解
        v_toolbar_search_mask = mView.findViewById(R.id.v_toolbar_search_mask);
        //扫一扫 付一付 聊一聊 咻一咻 的大图标布局遮罩
        v_title_big_mask =mView. findViewById(R.id.v_title_big_mask);
        //扫一扫 付一付 聊一聊 咻一咻 的小图标布局遮罩
        v_toolbar_small_mask =mView. findViewById(R.id.v_toolbar_small_mask);
        //背景颜色
        mMaskColor = getResources().getColor(R.color.white);
        return mView;
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        Log.d("aaa", "verticalOffset=" + verticalOffset);
        //720*1080手机 verticalOffset取值范围[0-200]px
        int absVerticalOffset = Math.abs(verticalOffset);//AppBarLayout竖直方向偏移距离px
        int totalScrollRange = appBarLayout.getTotalScrollRange();//AppBarLayout总的距离px
        //背景颜色转化成RGB的渐变色
        int argb = Color.argb(absVerticalOffset, Color.red(mMaskColor), Color.green(mMaskColor), Color.blue(mMaskColor));
        //appBarLayout上滑不到一半距离的颜色接近透明
        int argbDouble = Color.argb(absVerticalOffset * 2, Color.red(mMaskColor), Color.green(mMaskColor), Color.blue(mMaskColor));
        //appBarLayout上滑一半距离后小图标应该由渐变到全透明
        int title_small_offset = (200 - absVerticalOffset) < 0 ? 0 : 200 - absVerticalOffset;
        //全透明效果
        int title_small_argb = Color.argb(title_small_offset * 2, Color.red(mMaskColor),
                Color.green(mMaskColor), Color.blue(mMaskColor));
        //appBarLayout上滑不到一半距离
        if (absVerticalOffset <= totalScrollRange / 2) {
            include_toolbar_search.setVisibility(View.VISIBLE);
            include_toolbar_small.setVisibility(View.GONE);
            //为了和下面的大图标渐变区分,乘以2倍渐变
            v_toolbar_search_mask.setBackgroundColor(argbDouble);
        } else {
            include_toolbar_search.setVisibility(View.GONE);
            include_toolbar_small.setVisibility(View.VISIBLE);
            //appBarLayout上滑一半距离后小图标应该由渐变到全透明
            v_toolbar_small_mask.setBackgroundColor(title_small_argb);

        }
        //上滑时遮罩由全透明到半透明
        v_title_big_mask.setBackgroundColor(argb);
    }
}
