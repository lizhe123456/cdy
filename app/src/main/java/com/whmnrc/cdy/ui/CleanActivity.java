package com.whmnrc.cdy.ui;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.whmnrc.cdy.R;
import com.whmnrc.cdy.base.BaseActivity;
import butterknife.BindView;
import butterknife.OnClick;

public class CleanActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.et_text)
    EditText etText;

    public static void start(Context context) {
        Intent starter = new Intent(context, CleanActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_clean;
    }

    @Override
    protected void initViewData() {

        tvTitle.setText("清洗");


    }


    @OnClick({R.id.iv_back, R.id.tv_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                this.finish();
                break;
            case R.id.tv_confirm:

                break;
        }
    }
}
