package com.whmnrc.cdy.ui;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.ActivityUtils;
import com.example.lpc.bluetoothsdk.BluetoothSdkManager;
import com.example.lpc.bluetoothsdk.constant.ConstantDefine;
import com.example.lpc.bluetoothsdk.listener.BluetoothConnectListener;
import com.example.lpc.bluetoothsdk.listener.BluetoothStateListener;
import com.example.lpc.bluetoothsdk.listener.DiscoveryDevicesListener;
import com.whmnrc.cdy.R;
import com.whmnrc.cdy.base.App;
import com.whmnrc.cdy.base.BaseActivity;
import com.whmnrc.cdy.bean.RadonBean;
import com.whmnrc.cdy.ui.adapter.RadonBeanAdapter;
import com.whmnrc.cdy.util.RadonPageUtils;
import com.whmnrc.cdy.util.ToastUtil;
import com.whmnrc.cdy.widget.AlertUtils;
import com.whmnrc.cdy.widget.BluetoothListDialog;
import java.util.List;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * 查询
 */
public class OutputActivity extends BaseActivity implements BluetoothStateListener , BluetoothConnectListener {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    private RadonBeanAdapter mRadonBeanAdapter;
    private int page = 0;


    private BluetoothSdkManager mBluetoothSdkManager;

    public static void start(Context context) {
        Intent starter = new Intent(context, OutputActivity.class);
        ActivityUtils.startActivity(starter,0,0);
    }


    @Override
    protected void onStart() {
        super.onStart();
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
        mBluetoothSdkManager = new BluetoothSdkManager(this);
        mBluetoothSdkManager.setBlueStateListener(this);
        mBluetoothSdkManager.setBluetoothConnectListener(this);
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
        if (!mBluetoothSdkManager.isBluetoothEnabled()){
            mBluetoothSdkManager.getBluetoothAdapter().enable();
        }

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
//                RadonBean radonBean = new RadonBean(38.6,"湖北武汉","我的测试",new Date(),"我的任务");
//                App.getInstance().getDaoSession().getRadonBeanDao().insert(radonBean);
                startPrinting();

                break;
        }
    }

    private BluetoothListDialog mBluetoothListDialog;

    private void startPrinting() {
        if (!mBluetoothSdkManager.isBluetoothSupported()){
            ToastUtil.showToast(this,"设备不支持蓝牙");
        }else {
            if (mBluetoothSdkManager.isDiscoverying()) {
                mBluetoothSdkManager.cancelDiscovery();
            } else {
                showProgress("搜索中..");
                mBluetoothSdkManager.setDiscoveryDeviceListener(new DiscoveryDevicesListener() {
                    @Override
                    public void startDiscovery() {

                    }

                    @Override
                    public void discoveryNew(BluetoothDevice bluetoothDevice) {
                        mBluetoothListDialog.addDevice(bluetoothDevice);
                    }

                    @Override
                    public void discoveryFinish(List<BluetoothDevice> list) {
                        hodeProgress();
                        mBluetoothListDialog = new BluetoothListDialog(OutputActivity.this, list);
                        mBluetoothListDialog.show();
                        mBluetoothListDialog.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                mBluetoothSdkManager.connect(mBluetoothListDialog.getDevices().get(position));
                            }
                        });

                    }
                });
            }
        }
    }

    @Override
    public void onConnectStateChanged(int state) {
        switch (state) {
            case ConstantDefine.CONNECT_STATE_NONE:
                Log.i("main", "  -----> none <----");
                break;
            case ConstantDefine.CONNECT_STATE_LISTENER:
                Log.i("main", "  -----> listener <----");
                break;
            case ConstantDefine.CONNECT_STATE_CONNECTING:
                Log.i("main", "  -----> connecting <----");
                break;
            case ConstantDefine.CONNECT_STATE_CONNECTED:
                Log.i("main", "  -----> connected <----");
                break;
        }
    }

    @Override
    public void onBTDeviceConnected(String address, String name) {
        Toast.makeText(OutputActivity.this, "已连接到名称为" + name + "的设备", Toast.LENGTH_SHORT).show();
        new AlertDialog.Builder(this)
                .setTitle("提示")
                .setMessage("已连接到设配，确定要打印吗？")
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mBluetoothSdkManager.printText("zhehe");
                    }
                })
                .create().show();
    }

    @Override
    public void onBTDeviceDisconnected() {
        Toast.makeText(OutputActivity.this, "连接已经断开，请重新尝试连接...", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBTDeviceConnectFailed() {
        Toast.makeText(OutputActivity.this, "连接失败，请重新连接...", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mBluetoothSdkManager != null) {
            mBluetoothSdkManager.stopService();
        }
    }
}
