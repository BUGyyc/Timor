package com.linkerlab.housemanager.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.linkerlab.housemanager.R;
import com.linkerlab.housemanager.base.BaseFragment;

import net.tsz.afinal.FinalActivity;
import net.tsz.afinal.annotation.view.ViewInject;

/**
 * Created by x-man on 16/7/30.
 */
public class FormFragment extends BaseFragment{
    @ViewInject(id=R.id.title_name)
    TextView mTitleName;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View ViewRoot = inflater.inflate(R.layout.fragment_form,container,false);
        FinalActivity.initInjectedView(this,ViewRoot);
        mTitleName.setText(R.string.title_form);
        return ViewRoot;
    }


}
