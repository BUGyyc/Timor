package com.linkerlab.housemanager.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.linkerlab.housemanager.R;
import com.linkerlab.housemanager.base.BaseFragment;

import net.tsz.afinal.annotation.view.ViewInject;

/**
 * Created by x-man on 16/7/30.
 */
public class FirstFragment extends BaseFragment {
//    @ViewInject(id = R.id.tv,
//                click = "myclick")
//    TextView tv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View ViewRoot = inflater.inflate(R.layout.activity_main,container,false);
        return ViewRoot;
    }



    public void myclick(View view){
        Toast.makeText(getActivity(),"俞侠",Toast.LENGTH_LONG).show();
    }
}
