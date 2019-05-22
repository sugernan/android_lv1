package com.example.wu.com.example.department.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wu.R;

/**
 *   fragmentActivity的第三页,和之前布局差不多就没写
 */
public class TabThirdFragment extends Fragment {
    protected View mView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_tab_third, container, false);
        return mView;
    }
}
