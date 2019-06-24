package com.whmnrc.cdy.ui;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.whmnrc.cdy.R;
import com.whmnrc.cdy.base.BaseFragment;
import com.whmnrc.cdy.base.adapter.BaseAdapter;
import com.whmnrc.cdy.bean.MenuBean;
import com.whmnrc.cdy.gpio.MeasureType;
import com.whmnrc.cdy.ui.adapter.MenuAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class HomeFragment extends BaseFragment {

    @BindView(R.id.rv_list1)
    RecyclerView rvList1;
    @BindView(R.id.rv_list2)
    RecyclerView rvList2;

    private MenuAdapter menuAdapter;
    private MenuAdapter menuAdapter1;


    public static HomeFragment newInstance() {
        Bundle args = new Bundle();
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int contentViewLayoutID() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initViewData() {
        rvList1.setLayoutManager(new GridLayoutManager(getContext(), 4));
        menuAdapter = new MenuAdapter(getContext());
        rvList1.setAdapter(menuAdapter);


        rvList2.setLayoutManager(new GridLayoutManager(getContext(), 4));
        menuAdapter1 = new MenuAdapter(getContext());
        rvList2.setAdapter(menuAdapter1);


        setData();
    }

    private void setData() {
        List<MenuBean> menuBeans = new ArrayList<>();
        menuBeans.add(new MenuBean("清洗", R.drawable.ic_menu1));
        menuBeans.add(new MenuBean("查询", R.drawable.ic_menu2));
        menuBeans.add(new MenuBean("输出", R.drawable.ic_menu3));
        menuBeans.add(new MenuBean("系统", R.drawable.ic_menu4));
        menuAdapter.addFirstDataSet(menuBeans);
        menuAdapter.setOnItemClickListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void onClick(View view, Object item, int position) {
                MenuBean menuBean = (MenuBean) item;
                switch (menuBean.getTitle()) {
                    case "清洗":
                        CleanActivity.start(getContext());
                        break;
                    case "查询":
                        OutputActivity.start(getContext());
                        break;
                    case "输出":
                        QueryActivity.start(getContext());
                        break;
                    case "系统":
                        SystemActivity.start(getContext());
                        break;
                }
            }
        });

        List<MenuBean> menuBeans1 = new ArrayList<>();
        menuBeans1.add(new MenuBean("空气氡", R.drawable.ic_menu5));
        menuBeans1.add(new MenuBean("土壤氡", R.drawable.ic_menu6));
        menuBeans1.add(new MenuBean("水中氡", R.drawable.ic_menu7));
        menuBeans1.add(new MenuBean("氡析出率", R.drawable.ic_menu8));
        menuBeans1.add(new MenuBean("连续测量", R.drawable.ic_menu9));
        menuBeans1.add(new MenuBean("本底测量", R.drawable.ic_menu10));
        menuAdapter1.addFirstDataSet(menuBeans1);
        menuAdapter1.setOnItemClickListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void onClick(View view, Object item, int position) {
                MenuBean menuBean = (MenuBean) item;
                MeasureType measureType;
                switch (menuBean.getTitle()) {
                    case "空气氡":
                        measureType = MeasureType.AIR;
                        break;
                    case "土壤氡":
                        measureType = MeasureType.SOIL;
                        break;
                    case "水中氡":
                        measureType = MeasureType.WATER;
                        break;
                    case "氡析出率":
                        measureType = MeasureType.RADON_EXHALATION_RATE;
                        break;
                    case "连续测量":
                        measureType = MeasureType.CONTINUOUS_MEASUREMENT;
                        break;
                    case "本底测量":
                        measureType = MeasureType.BACKGROUND;
                        break;
                    default:
                        measureType = MeasureType.AIR;
                        break;
                }
                MeasureActivity.start(getContext(), measureType);
            }
        });
    }

}
