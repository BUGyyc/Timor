package com.linkerlab.housemanager.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.linkerlab.housemanager.R;
import com.linkerlab.housemanager.base.BaseActivity;

import net.tsz.afinal.annotation.view.ViewInject;

/**
 * Created by Administrator on 2016/7/30.
 * wzc
 */
public class Login_Activity extends BaseActivity implements View.OnClickListener{
    @ViewInject(id = R.id.job_login)Button mbt_job;


    @Override
    protected void initVariables() {


    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_login);
        mbt_job.setOnClickListener(this);
    }

    @Override
    protected void loadData() {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);

    }

    @Override
    public void onClick(View view) {

    }
}
