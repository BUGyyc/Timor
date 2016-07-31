package com.linkerlab.housemanager.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.linkerlab.housemanager.R;
import com.linkerlab.housemanager.base.BaseFragment;

/**
 * Created by x-man on 16/7/30.
 */
public class PaperFragment extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View ViewRoot = inflater.inflate(R.layout.fragment_paper,container,false);
        return ViewRoot;
    }
}
