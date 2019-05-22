package com.example.wu.com.example.department.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Toast的操作
 */
public class ToastUtil {
    public static Toast toast;
    public static void showMsg(Context context,String msg){
        if (toast==null){
            toast =Toast.makeText(context,msg,Toast.LENGTH_SHORT);
        }else {
            toast.setText(msg);
        }
        toast.show();
    }
}
