package com.whmnrc.cdy.ui.adapter;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.whmnrc.cdy.R;
import com.whmnrc.cdy.base.App;

import java.util.ArrayList;
import java.util.List;

import static android.provider.Telephony.Mms.Part.FILENAME;


/**
 * Created by yefeng on 6/2/15.
 * github:yefengfreedom
 */
public class SearchBleAdapter extends BaseAdapter {

    private List<BluetoothDevice> mDevices;
    private LayoutInflater mInflater;
    private String mConnectedDeviceAddress;
    private static final String DEFAULT_BLUETOOTH_DEVICE_ADDRESS = "default_bluetooth_device_address";//蓝牙设备地址

    public SearchBleAdapter(Context mContext, List<BluetoothDevice> mDevices) {
        this.mInflater = LayoutInflater.from(mContext);
        this.mDevices = null == mDevices ? new ArrayList<BluetoothDevice>() : mDevices;
        mConnectedDeviceAddress = getDefaultBluethoothDeviceAddress(mContext);
    }

    public static String getDefaultBluethoothDeviceAddress(Context mContext) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(FILENAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(DEFAULT_BLUETOOTH_DEVICE_ADDRESS, "");
    }

    public List<BluetoothDevice> getDevices() {
        return mDevices;
    }


    public void setDevices(List<BluetoothDevice> mDevices) {
        if (null == mDevices) {
            mDevices = new ArrayList<BluetoothDevice>();
        }
        this.mDevices = mDevices;
        this.notifyDataSetChanged();
    }

    @Override
    public void notifyDataSetChanged() {
        if (null != this.mDevices) {
            this.mDevices = sortByBond(this.mDevices);
        }
        super.notifyDataSetChanged();
    }

    private List<BluetoothDevice> sortByBond(List<BluetoothDevice> mDevices) {
        if (null == mDevices) {
            return null;
        }
        if (mDevices.size() < 2) {
            return mDevices;
        }
        List<BluetoothDevice> bondDevices = new ArrayList<BluetoothDevice>();
        List<BluetoothDevice> unBondDevices = new ArrayList<BluetoothDevice>();
        int size = mDevices.size();
        for (int i = 0; i < size; i++) {
            BluetoothDevice bluetoothDevice = mDevices.get(i);
            if (bluetoothDevice.getBondState() == BluetoothDevice.BOND_BONDED) {
                bondDevices.add(bluetoothDevice);
            } else {
                unBondDevices.add(bluetoothDevice);
            }
        }
        mDevices.clear();
        mDevices.addAll(bondDevices);
        mDevices.addAll(unBondDevices);
        bondDevices.clear();
        bondDevices = null;
        unBondDevices.clear();
        unBondDevices = null;
        return mDevices;
    }

    public void setConnectedDeviceAddress(String macAddress) {
        this.mConnectedDeviceAddress = macAddress;
    }

    public void addDevices(ArrayList<BluetoothDevice> mDevices) {
        if (null == mDevices) {
            return;
        }
        for (BluetoothDevice bluetoothDevice : mDevices) {
            addDevices(bluetoothDevice);
        }
    }

    public void addDevices(BluetoothDevice mDevice) {
        if (null == mDevice) {
            return;
        }
        if (!this.mDevices.contains(mDevice)) {
            this.mDevices.add(mDevice);
            this.notifyDataSetChanged();
        }
    }

    @Override
    public int getCount() {
        return mDevices.size();
    }

    @Override
    public BluetoothDevice getItem(int position) {
        return mDevices.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView != null) {
            holder = (ViewHolder) convertView.getTag();
        } else {
            convertView = mInflater.inflate(R.layout.adapter_bt_device, parent, false);
            holder = new ViewHolder();
            if (null != convertView) {
                convertView.setTag(holder);
            }
        }
        holder.name = (TextView) convertView.findViewById(R.id.txt_adapter_bt_name);
        holder.address = (TextView) convertView.findViewById(R.id.txt_adapter_bt_address);
        holder.bond = (TextView) convertView.findViewById(R.id.btn_adapter_bt_has_bond);

        BluetoothDevice bluetoothDevice = mDevices.get(position);
        String dName = bluetoothDevice.getName() == null ? "未知设备" : bluetoothDevice.getName();
        if (TextUtils.isEmpty(dName)) {
            dName = "未知设备";
        }
        holder.name.setText(dName);
        String dAddress = bluetoothDevice.getAddress() == null ? "未知地址" : bluetoothDevice.getAddress();
        if (TextUtils.isEmpty(dAddress)) {
            dAddress = "未知地址";
        }
        holder.address.setText(dAddress);
        int paddingVertical = 8;
        int paddingHorizontal = 16;
        if (App.density != 0) {
            paddingVertical = (int) (paddingVertical * App.density);
            paddingHorizontal = (int) (paddingHorizontal * App.density);
        }
        if (bluetoothDevice.getBondState() == BluetoothDevice.BOND_BONDED) {
            if (dAddress.equals(mConnectedDeviceAddress)) {
                holder.bond.setText("已连接");
                holder.bond.setPadding(paddingHorizontal, paddingVertical, paddingHorizontal, paddingVertical);
            } else {
                holder.bond.setText("已配对");
                holder.bond.setPadding(paddingHorizontal, paddingVertical, paddingHorizontal, paddingVertical);
            }
        } else {
            holder.bond.setText("未配对");
            holder.bond.setPadding(paddingHorizontal, paddingVertical, paddingHorizontal, paddingVertical);
        }
        return convertView;
    }

    static class ViewHolder {
        TextView name;
        TextView address;
        TextView bond;
    }
}
