package com.linkerlab.housemanager.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.linkerlab.housemanager.R;
import com.linkerlab.housemanager.base.BaseActivity;
import com.linkerlab.housemanager.ui.FirstFragment;

import net.tsz.afinal.FinalDb;

/**
 * Created by x-man on 16/7/30.
 * 引导界面
 */
public class SplashActivity extends BaseActivity {
    RelativeLayout mRelativeLayout;
    private FragmentManager mFragmentManager;
    @Override
    protected void initVariables() {
        FinalDb db = FinalDb.create(this);

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_splash);
        mFragmentManager = getSupportFragmentManager();
        FirstFragment mFirstFragment = new FirstFragment();
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.content,mFirstFragment);
        fragmentTransaction.commit();
        FirstFragment firstFragment = new FirstFragment();


//        if(Utils.ping()){
//            //Toast.makeText(this,"Ok",Toast.LENGTH_LONG).show();
//        }
    }


    @Override
    protected void loadData() {

    }
    //Test
   /* public void myclick(View v){
        Toast.makeText(this,"rao",Toast.LENGTH_LONG).show();
    }*/
}
