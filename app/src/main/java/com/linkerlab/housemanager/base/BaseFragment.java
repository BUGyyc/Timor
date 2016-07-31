package com.linkerlab.housemanager.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.linkerlab.housemanager.R;

import net.tsz.afinal.FinalActivity;

/**
 * Created by x-man on 16/7/30.
 * Fragment基类
 */
public  abstract class BaseFragment extends Fragment {
    public int layoutResID;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View viewRoot = inflater.inflate(layoutResID, container, false);
        FinalActivity.initInjectedView(this,viewRoot);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        FinalActivity.initInjectedView(this,view);
        super.onViewCreated(view, savedInstanceState);
    }
}
