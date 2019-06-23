package com.whmnrc.cdy.ui;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import com.whmnrc.cdy.R;
import com.whmnrc.cdy.base.BaseActivity;
import com.whmnrc.cdy.bean.RadonBean;
import com.whmnrc.cdy.ui.adapter.RadonBeanAdapter;
import com.whmnrc.cdy.util.ExcelUtil;
import com.whmnrc.cdy.util.RadonPageUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class QueryActivity extends BaseActivity {


    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    private RadonBeanAdapter mRadonBeanAdapter;
    private int page = 1;
    private AlertDialog alertDialog;


    String[] title = {"序号", "任务名称", "氡测量值","采气时间","地址","备注"};
    String sheetName = "氡气测量数据";

    String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
    private int REQUEST_PERMISSION_CODE = 1000;

    private String filePath = Environment.getExternalStorageDirectory() + "/Record";

    private void requestPermission() {
        if (Build.VERSION.SDK_INT > 23) {
            if (ContextCompat.checkSelfPermission(QueryActivity.this,
                    permissions[0])
                    == PackageManager.PERMISSION_GRANTED) {
                //授予权限
                Log.i("requestPermission:", "用户之前已经授予了权限！");
            } else {
                //未获得权限
                Log.i("requestPermission:", "未获得权限，现在申请！");
                requestPermissions(permissions
                        , REQUEST_PERMISSION_CODE);
            }
        }

    }

    public static void start(Context context) {
        Intent starter = new Intent(context, QueryActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_query;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Override
    protected void initViewData() {
        tvTitle.setText("输出");
        requestPermission();
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        builder.detectFileUriExposure();
        mRadonBeanAdapter = new RadonBeanAdapter(this);
        mRadonBeanAdapter.setSelect(true);
        rvList.setLayoutManager(new LinearLayoutManager(this));
        rvList.setAdapter(mRadonBeanAdapter);
        rvList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                LinearLayoutManager lm = (LinearLayoutManager) recyclerView.getLayoutManager();
                int totalItemCount = recyclerView.getAdapter().getItemCount();
                int lastVisibleItemPosition = lm.findLastVisibleItemPosition();
                int visibleItemCount = recyclerView.getChildCount();

                if (newState == RecyclerView.SCROLL_STATE_IDLE
                        && lastVisibleItemPosition == totalItemCount - 1
                        && visibleItemCount > 0) {
                    //加载更多
                    loadData();
                }
            }
        });

        loadData();


    }

    private void loadData() {
        List<RadonBean> list = RadonPageUtils.getTwentyRec(page);
        if (list.size() > 0) {
            mRadonBeanAdapter.addFirstDataSet(RadonPageUtils.getTwentyRec(page));
            page++;
        }
    }


    @OnClick({R.id.iv_back, R.id.tv_export, R.id.tv_all_export})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                this.finish();
                break;
            case R.id.tv_export:
                //导出
                exportExcel1();

                break;
            case R.id.tv_all_export:
                //全部导出
                exportExcel();
                break;
        }
    }

    private void exportExcel() {
        File file = new File(filePath);
        if (!file.exists()) {
            file.mkdirs();
        }
        String excelFileName = "/radon.xls";
        filePath = filePath + excelFileName;
        ExcelUtil.initExcel(filePath, sheetName, title);
        ExcelUtil.writeObjListToExcel(mRadonBeanAdapter.getDataSource(), filePath, this);
        notifySystemToScan(filePath);
    }

    private void exportExcel1() {
        File file = new File(filePath);
        if (!file.exists()) {
            file.mkdirs();
        }
        String excelFileName = "/radon.xls";
        filePath = filePath + excelFileName;
        ExcelUtil.initExcel(filePath, sheetName, title);
        List<RadonBean> list = new ArrayList<>();
        for (RadonBean radonBean : mRadonBeanAdapter.getDataSource()) {
            if (radonBean.isSelect()){
                list.add(radonBean);
            }
        }

        ExcelUtil.writeObjListToExcel(list, filePath, this);
        notifySystemToScan(filePath);
    }

    public void notifySystemToScan(String filePath) {
        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File file = new File(filePath);

        Uri uri = Uri.fromFile(file);
        intent.setData(uri);
        sendBroadcast(intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_PERMISSION_CODE) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.i("onPermissionsResult:", "权限" + permissions[0] + "申请成功");
                // permission was granted, yay! Do the
                // contacts-related task you need to do.

            } else {
                Log.i("onPermissionsResult:", "用户拒绝了权限申请");
                AlertDialog.Builder builder = new AlertDialog.Builder(QueryActivity.this);
                builder.setTitle("permission")
                        .setMessage("点击允许才可以使用我们的app哦")
                        .setPositiveButton("去允许", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                if (alertDialog != null && alertDialog.isShowing()) {
                                    alertDialog.dismiss();
                                }
                                ActivityCompat.requestPermissions(QueryActivity.this,
                                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                            }
                        });
                alertDialog = builder.create();
                alertDialog.setCanceledOnTouchOutside(false);
                alertDialog.show();
                // permission denied, boo! Disable the
                // functionality that depends on this permission.
            }
        }
    }
}
