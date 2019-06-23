package com.whmnrc.cdy.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by lizhe on 2019/5/30.
 */
public abstract class BaseFragment extends Fragment {

    // 标识fragment视图已经初始化完毕
    private boolean isViewPrepared;
    //标识已经触发过懒加载数据
    private boolean hasFetchData;

    protected View mRootView;

    private Unbinder mUnbinder;

    @LayoutRes
    protected abstract int contentViewLayoutID();


    protected abstract void initViewData();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(contentViewLayoutID(),container,false);
        mUnbinder = ButterKnife.bind(this,mRootView);
        contentViewLayoutID();
        return mRootView;

    }

    private void lazyFetchDataIfPrepared() {
        if (getUserVisibleHint() && !hasFetchData && isViewPrepared) {
            hasFetchData = true;
            initViewData();
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        isViewPrepared = true;
        lazyFetchDataIfPrepared();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser){
            lazyFetchDataIfPrepared();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        hasFetchData = false;
        isViewPrepared = false;
        mUnbinder.unbind();
    }

}
