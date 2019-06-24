package com.whmnrc.cdy.ui;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.whmnrc.cdy.R;
import com.whmnrc.cdy.base.BaseActivity;
import com.whmnrc.cdy.util.CodeTimeUtils;
import com.whmnrc.cdy.util.ToastUtil;
import com.whmnrc.cdy.widget.AlertUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class CleanActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.et_text)
    EditText etText;

    private boolean isClean;

    public static void start(Context context) {
        Intent starter = new Intent(context, CleanActivity.class);
        ActivityUtils.startActivity(starter,0,0);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_clean;
    }

    @Override
    protected void initViewData() {

        tvTitle.setText("清洗");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @OnClick({R.id.iv_back, R.id.tv_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                if (isClean){
                    AlertUtils.showInterruptOperationDialog(this, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            interruptOperation();
                            CleanActivity.this.finish();
                        }
                    });
                }else {
                    this.finish();
                }
                break;
            case R.id.tv_confirm:
                startClean();
                break;
        }
    }

    private void startClean() {
        if (TextUtils.isEmpty(etText.getText().toString())){
            ToastUtil.showToast(this,"请输入清洗时间");
            return;
        }

        long time = Long.valueOf(etText.getText().toString()) * 60 * 1000;


        openClean();
        CodeTimeUtils.countDown(etText, time, new CodeTimeUtils.Listener() {
            @Override
            public void complete() {
                //关闭
                closeClean();
            }
        });


    }

    //关闭清洗设配
    private void closeClean() {
        isClean = false;
    }

    //打开清洗设配
    private void openClean(){
        isClean = true;
    }

    //中断操作
    private void interruptOperation() {
        CodeTimeUtils.cancelTimer();
        closeClean();
        isClean = false;
    }
}
