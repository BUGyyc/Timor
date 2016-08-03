package com.linkerlab.housemanager.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.linkerlab.housemanager.R;
import com.linkerlab.housemanager.base.BaseActivity;

import net.tsz.afinal.annotation.view.ViewInject;

/**
 * Created by x-man on 16/8/3.
 */

public class AddHouseActivty extends BaseActivity{
    @ViewInject(id=R.id.title_name)
    TextView mTitleName;
    @ViewInject(id=R.id.delete)
    ImageView mDelete;
    @ViewInject(id =R.id.back)
    ImageView mBack;
    @Override
    protected void initVariables() {
        setContentView(R.layout.activity_addhouse);
        mTitleName.setText("步骤1: 填写楼宇基本信息");
        mBack.setVisibility(View.VISIBLE);
        mDelete.setVisibility(View.VISIBLE);
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {

    }

    @Override
    protected void loadData() {

    }
}
