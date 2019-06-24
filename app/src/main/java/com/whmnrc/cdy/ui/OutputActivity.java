package com.whmnrc.cdy.ui;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.whmnrc.cdy.R;
import com.whmnrc.cdy.base.App;
import com.whmnrc.cdy.base.BaseActivity;
import com.whmnrc.cdy.bean.RadonBean;
import com.whmnrc.cdy.ui.adapter.RadonBeanAdapter;
import com.whmnrc.cdy.util.RadonPageUtils;
import com.whmnrc.cdy.widget.AlertUtils;

import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 查询
 */
public class OutputActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    private RadonBeanAdapter mRadonBeanAdapter;
    private int page = 0;


    public static void start(Context context) {
        Intent starter = new Intent(context, OutputActivity.class);
        ActivityUtils.startActivity(starter);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_output;
    }

    @Override
    protected void initViewData() {
        tvTitle.setText("查询");
        mRadonBeanAdapter = new RadonBeanAdapter(this);
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

    @OnClick({R.id.iv_back, R.id.tv_clean, R.id.tv_printing})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                this.finish();
                break;
            case R.id.tv_clean:
                //清除
                AlertUtils.showCleanAllDialog(this, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        App.getInstance().getDaoSession().getRadonBeanDao().deleteAll();
                        page = 0;
                        loadData();
                    }
                });
                break;
            case R.id.tv_printing:
                //打印
                RadonBean radonBean = new RadonBean(38.6,"湖北武汉","我的测试",new Date(),"我的任务");
                App.getInstance().getDaoSession().getRadonBeanDao().insert(radonBean);
                break;
        }
    }
}
