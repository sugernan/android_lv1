package com.example.wu.com.example.department.fragment;


import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.example.wu.R;
import com.example.wu.com.example.department.Interface.RecyclerExtras;
import com.example.wu.com.example.department.adapter.LinearDynamicAdapter;
import com.example.wu.com.example.department.bean.GoodsInfo;
import com.example.wu.com.example.department.util.ToastUtil;

import java.util.ArrayList;

/**
 *   fragmentActivity的第一页
 */
public class TabFirstFragment extends Fragment implements  RecyclerExtras.OnItemClickListener,
        RecyclerExtras.OnItemLongClickListener, RecyclerExtras.OnItemDeleteClickListener , SwipeRefreshLayout.OnRefreshListener ,
        View.OnClickListener {
    protected View mView;
    private RecyclerView rv_dynamic;

    private LinearDynamicAdapter mAdapter;
    private ArrayList<GoodsInfo> mPublicArray;
    private ArrayList<GoodsInfo> mAllArray;
    private Button btn_recycler_add;
    private SwipeRefreshLayout srl_simple;

    //顶部文本框声明
    private TextView tv_pay,tv_search,tv_history,tv_overflow;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_tab_first, container, false);
        setHasOptionsMenu(true);
        //循环视图的操作方法
        initRcy();
        return mView;
    }

    private void initRcy() {
        //文本框的声明
        tv_pay=(TextView)mView.findViewById(R.id.tv_pay);
        tv_search=(TextView)mView.findViewById(R.id.tv_search);
        tv_history=(TextView)mView.findViewById(R.id.tv_history);
        tv_overflow=(TextView)mView.findViewById(R.id.tv_overflow);
        tv_pay.setOnClickListener(this);
        tv_search.setOnClickListener(this);
        tv_history.setOnClickListener(this);
        tv_overflow.setOnClickListener(this);

        //循环视图的操作
        rv_dynamic=(RecyclerView) mView.findViewById(R.id.rv_dynamic);
        srl_simple=(SwipeRefreshLayout)mView.findViewById(R.id.srl_simple);
        srl_simple.setOnRefreshListener(this);
        srl_simple.setColorSchemeResources(R.color.white);
//        btn_recycler_add=(Button)mView.findViewById(R.id.btn_recycler_add);
//        btn_recycler_add.setOnClickListener(this);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayout.VERTICAL);
        rv_dynamic.setLayoutManager(manager);
        //两个集合的声明
        mAllArray = GoodsInfo.getDefaultList();
        mPublicArray = GoodsInfo.getDefaultList();
        //下滑线
        mAdapter = new LinearDynamicAdapter(getActivity(), mPublicArray);
        //事件监听
        mAdapter.setOnItemClickListener(this);
        mAdapter.setOnItemLongClickListener(this);
        mAdapter.setOnItemDeleteClickListener(this);
        //设置循环线性适配器
        rv_dynamic.setAdapter(mAdapter);
        //动画效果和列表项的空白装饰
        rv_dynamic.setItemAnimator(new DefaultItemAnimator());
        rv_dynamic.addItemDecoration(new MyDecoration());
    }

    //列表项的点击事件监听
    @Override
    public void onItemClick(View view, int position) {
    ToastUtil.showMsg(getActivity(),"您点击了"+position+"项");
    }
    //列表项的长按监听事件
    @Override
    public void onItemLongClick(View view, int position) {
        GoodsInfo item = mPublicArray.get(position);
        item.bPressed = !item.bPressed;
        mPublicArray.set(position, item);
        mAdapter.notifyItemChanged(position);
    }
    //列表项的删除事件
    @Override
    public void onItemDeleteClick(View view, int position) {
        mPublicArray.remove(position);
        mAdapter.notifyItemRemoved(position);
    }
    //下拉刷新监听器
    @Override
    public void onRefresh() {
        mHandler.postDelayed(mRunnable,2000);

    }
    //声明一个处理器对象
    private Handler mHandler=new Handler();
    private Runnable mRunnable=new Runnable() {
        @Override
        public void run() {
            int position = (int) (Math.random()*100%mAllArray.size());
            GoodsInfo old_item = mAllArray.get(position);
            GoodsInfo new_item = new GoodsInfo(old_item.pic_id, old_item.title, old_item.desc);
            mPublicArray.add(0, new_item);
            Log.d("btn_onclick","运行");
            //通知适配器在第一项插入数据
            mAdapter.notifyItemInserted(0);
            //让循环视图滚动到第一项所在的位置
            rv_dynamic.scrollToPosition(0);
            srl_simple.setRefreshing(false);
        }
    };

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.tv_pay:
                ToastUtil.showMsg(getActivity(),"您点击了打钱按钮");
                break;
            case R.id.tv_search:
                ToastUtil.showMsg(getActivity(),"您点击了搜索按钮");
                break;
            case R.id.tv_history:
                ToastUtil.showMsg(getActivity(),"您点击了历史记录按钮");
                break;
            case R.id.tv_overflow:
                PopupMenu popupMenu=new PopupMenu(getActivity(),v);
                popupMenu.getMenuInflater().inflate(R.menu.menu_message,popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.mu_tongbu:
                                ToastUtil.showMsg(getActivity(),"您点击了打钱按钮");
                                return true;
                            case R.id.mu_saomiao:
                                ToastUtil.showMsg(getActivity(),"您点击了搜索按钮");
                                return true;
                            case R.id.mu_WiFi:
                                ToastUtil.showMsg(getActivity(),"您点击了历史记录按钮");
                                return true;
                            case R.id.yijian:
                                ToastUtil.showMsg(getActivity(),"您点击了历史记录按钮");
                                return true;
                            case R.id.neight:
                                ToastUtil.showMsg(getActivity(),"您点击了历史记录按钮");
                                return true;
                            default:
                                return false;
                        }
                    }
                });
                //弹出菜单
                popupMenu.show();
                break;
        }
    }

    //列表下划线
    class MyDecoration extends RecyclerView.ItemDecoration{

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            int decoration=getResources().getDimensionPixelOffset(R.dimen.dividerHeigth);
            outRect.set(decoration,decoration,decoration,decoration);
        }
    }
}
