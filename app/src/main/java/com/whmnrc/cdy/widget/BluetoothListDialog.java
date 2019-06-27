package com.whmnrc.cdy.widget;

import android.app.Dialog;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.whmnrc.cdy.R;
import com.whmnrc.cdy.ui.adapter.SearchBleAdapter;
import java.util.List;

public class BluetoothListDialog extends Dialog {

    private SearchBleAdapter mSearchBleAdapter;
    private List<BluetoothDevice> devices;

    private TextView tv_title;
    private TextView tv_summary;
    private ListView lv_searchblt;
    private AdapterView.OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(AdapterView.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public BluetoothListDialog(@NonNull Context context, List<BluetoothDevice> devices) {
        super(context);
        this.devices = devices;
    }

    public BluetoothListDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_bluetooth_list);
        mSearchBleAdapter = new SearchBleAdapter(getContext(),devices);
        lv_searchblt = (ListView) findViewById(R.id.lv_searchblt);
        lv_searchblt.setAdapter(mSearchBleAdapter);
        if (onItemClickListener != null) {
            lv_searchblt.setOnItemClickListener(onItemClickListener);
        }

    }

    public void addDevice(BluetoothDevice bluetoothDevice){
        mSearchBleAdapter.addDevices(bluetoothDevice);

    }

    public List<BluetoothDevice> getDevices() {
        return mSearchBleAdapter.getDevices();
    }
}
