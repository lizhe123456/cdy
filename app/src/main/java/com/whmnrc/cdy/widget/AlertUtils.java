package com.whmnrc.cdy.widget;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

public class AlertUtils {

    public static void showInterruptOperationDialog(Context context, DialogInterface.OnClickListener onClickListener){
        new AlertDialog.Builder(context)
                .setTitle("提示")
                .setMessage("此时退出将中断当前操作，确定要退出吗？")
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("确认",onClickListener)
                .create().show();
    }

    public static void showCleanDialog(Context context, DialogInterface.OnClickListener onClickListener){
        new AlertDialog.Builder(context)
                .setTitle("提示")
                .setMessage("确定要清除此条数据吗")
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("确认",onClickListener)
                .create().show();
    }

    public static void showCleanAllDialog(Context context, DialogInterface.OnClickListener onClickListener){
        new AlertDialog.Builder(context)
                .setTitle("提示")
                .setMessage("确定要清除所有数据吗？")
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("确认",onClickListener)
                .create().show();
    }

}
