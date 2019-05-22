package com.example.wu.com.example.department.Interface;

import android.view.View;

public interface RecyclerExtras {

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public interface OnItemLongClickListener {
        void onItemLongClick(View view, int position);
    }

    public interface OnItemDeleteClickListener {
        void onItemDeleteClick(View view, int position);
    }
}
