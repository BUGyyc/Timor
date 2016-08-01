package com.linkerlab.housemanager.activity;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.linkerlab.housemanager.R;
import com.linkerlab.housemanager.base.BaseActivity;
import com.linkerlab.housemanager.ui.FormFragment;
import com.linkerlab.housemanager.ui.MapFragment;
import com.linkerlab.housemanager.ui.PaperFragment;
import com.linkerlab.housemanager.ui.PersonCenterFragment;

import net.tsz.afinal.annotation.view.ViewInject;

/**
 * Created by x-man on 16/7/30.
 * 主框架逻辑
 *
 */
public class MainActivity extends BaseActivity implements View.OnClickListener{
    @ViewInject(id=R.id.bt_paper)
    LinearLayout btn_paper;
    @ViewInject(id=R.id.bt_form)
    LinearLayout btn_form;
    @ViewInject(id=R.id.bt_map)
    LinearLayout btn_map;
    @ViewInject(id=R.id.bt_person)
    LinearLayout btn_person;
    public static final int PAPER_TYPE = 1;//跳转排查日报界面
    public static final int FORM_TYPE = 2;//统计表单界面
    public static final int MAP_TYPE = 3;//排查地图
    public static final int PERSON_CENTER_TYPE = 4;//个人中心界面


    private FragmentManager mFragmentManager;
    private FragmentTransaction mFragmentTransaction;

    /*统计表单*/
    private FormFragment mFormFragment;
    /*排查地图界面*/
    private MapFragment mMapFragment;
    /*排查日报界面*/
    private PaperFragment mPaperFragment;
    /*个人中心界面*/
    private PersonCenterFragment mPersonCenterFragment;
    /*pager*/
    @ViewInject(id=R.id.iv_rb)
    ImageView mImage_Paper;
    @ViewInject(id=R.id.tv_rb)
    TextView mText_paper;
    /*form*/
    @ViewInject(id=R.id.iv_form)
    ImageView mImage_Form;
    @ViewInject(id=R.id.tv_form)
    TextView mText_Form;
    /*map*/
    @ViewInject(id=R.id.iv_map)
    ImageView mImage_Map;
    @ViewInject(id=R.id.tv_map)
    TextView mText_Map;
    /*person*/
    @ViewInject(id=R.id.iv_person)
    ImageView mImage_Person;
    @ViewInject(id=R.id.tv_person)
    TextView mText_Person;



    @Override
    protected void initVariables() {
        mFragmentManager = getSupportFragmentManager();

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        handlerPager(PAPER_TYPE);
        btn_form.setOnClickListener(this);
        btn_map.setOnClickListener(this);
        btn_paper.setOnClickListener(this);
        btn_person.setOnClickListener(this);

    }

    @Override
    protected void loadData() {

    }

    /**
     * 主界面控制逻辑
     * @param index
     */
    public void handlerPager(int index){
        mFragmentTransaction = mFragmentManager.beginTransaction();
        switch (index) {
            case PAPER_TYPE:
                if(mPaperFragment == null) {
                    mPaperFragment = new PaperFragment();
                }
                if(! mPaperFragment.isAdded()) {
                    if(mFormFragment != null) {
                        mFragmentTransaction.hide(mFormFragment);
                    }
                    if(mPersonCenterFragment != null) {
                        mFragmentTransaction.hide(mPersonCenterFragment);
                    }
                    if(mMapFragment != null) {
                        mFragmentTransaction.hide(mMapFragment);
                    }
                    mFragmentTransaction.add(R.id.content,mPaperFragment);
                }
                else{
                    //如果tab2不为空，把tab2隐藏就是、
                    if(mPaperFragment != null){
                        mFragmentTransaction.hide(mPaperFragment);
                    }
                    if(mFormFragment != null) {
                        mFragmentTransaction.hide(mFormFragment);
                    }
                    if(mPersonCenterFragment != null) {
                        mFragmentTransaction.hide(mPersonCenterFragment);
                    }
                    if(mMapFragment != null) {
                        mFragmentTransaction.hide(mMapFragment);
                    }
                    mFragmentTransaction.show(mPaperFragment);
                }

                break;
            case FORM_TYPE:
                if(mFormFragment == null) {
                    mFormFragment = new FormFragment();
                }
                if(! mFormFragment.isAdded()) {
                    if(mPersonCenterFragment != null) {
                        mFragmentTransaction.hide(mPersonCenterFragment);
                    }
                    if(mMapFragment != null) {
                        mFragmentTransaction.hide(mMapFragment);
                    }
                    if(mPaperFragment != null){
                        mFragmentTransaction.hide(mPaperFragment);
                    }
                    mFragmentTransaction.add(R.id.content,mFormFragment);
                }
                else{
                    //如果tab2不为空，把tab2隐藏就是、
                    if(mPaperFragment != null){
                        mFragmentTransaction.hide(mPaperFragment);
                    }
                    if(mFormFragment != null) {
                        mFragmentTransaction.hide(mFormFragment);
                    }
                    if(mPersonCenterFragment != null) {
                        mFragmentTransaction.hide(mPersonCenterFragment);
                    }
                    if(mMapFragment != null) {
                        mFragmentTransaction.hide(mMapFragment);
                    }
                    mFragmentTransaction.show(mFormFragment);
                }
                break;
            case MAP_TYPE:
                if(mMapFragment == null) {
                    mMapFragment = new MapFragment();
                }
                if(! mMapFragment.isAdded()) {
                    if(mPaperFragment != null){
                        mFragmentTransaction.hide(mPaperFragment);
                    }
                    if(mFormFragment != null) {
                        mFragmentTransaction.hide(mFormFragment);
                    }
                    if(mPersonCenterFragment != null) {
                        mFragmentTransaction.hide(mPersonCenterFragment);
                    }
                    mFragmentTransaction.add(R.id.content,mMapFragment);
                }
                else{
                    //如果tab2不为空，把tab2隐藏就是、
                    if(mPaperFragment != null){
                        mFragmentTransaction.hide(mPaperFragment);
                    }
                    if(mFormFragment != null) {
                        mFragmentTransaction.hide(mFormFragment);
                    }
                    if(mPersonCenterFragment != null) {
                        mFragmentTransaction.hide(mPersonCenterFragment);
                    }
                    if(mMapFragment != null) {
                        mFragmentTransaction.hide(mMapFragment);
                    }
                    mFragmentTransaction.show(mMapFragment);
                }
                break;
            case PERSON_CENTER_TYPE:
                if(mPersonCenterFragment == null) {
                    mPersonCenterFragment = new PersonCenterFragment();
                }
                if(! mPersonCenterFragment.isAdded()) {

                    if(mPaperFragment != null){
                        mFragmentTransaction.hide(mPaperFragment);
                    }
                    if(mFormFragment != null) {
                        mFragmentTransaction.hide(mFormFragment);
                    }
                    if(mMapFragment != null) {
                        mFragmentTransaction.hide(mMapFragment);
                    }
                    mFragmentTransaction.add(R.id.content,mPersonCenterFragment);
                }
                else{
                    if(mPaperFragment != null){
                        mFragmentTransaction.hide(mPaperFragment);
                    }
                    if(mFormFragment != null) {
                        mFragmentTransaction.hide(mFormFragment);
                    }
                    if(mPersonCenterFragment != null) {
                        mFragmentTransaction.hide(mPersonCenterFragment);
                    }
                    if(mMapFragment != null) {
                        mFragmentTransaction.hide(mMapFragment);
                    }
                    mFragmentTransaction.show(mPersonCenterFragment);
                }
                break;
            default:
                break;
        }
        mFragmentTransaction.commit();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bt_paper:
                /*btn_paper.setBackgroundResource(R.color.bt_green);
                btn_person.setBackgroundResource(R.color.black);
                btn_form.setBackgroundResource(R.color.black);
                btn_map.setBackgroundResource(R.color.black);*/
                mImage_Paper.setImageResource(R.drawable.icon_rb_active);
                mText_paper.setTextColor(getResources().getColor(R.color.font));
                mImage_Form.setImageResource(R.drawable.icon_bd);
                mText_Form.setTextColor(getResources().getColor(R.color.line));
                mImage_Map.setImageResource(R.drawable.icon_dt);
                mText_Map.setTextColor(getResources().getColor(R.color.line));
                mImage_Person.setImageResource(R.drawable.icon_zx);
                mText_Person.setTextColor(getResources().getColor(R.color.line));

                handlerPager(PAPER_TYPE);
                break;
            case R.id.bt_form:
                /*btn_person.setBackgroundResource(R.color.black);
                btn_form.setBackgroundResource(R.color.bt_green);
                btn_map.setBackgroundResource(R.color.black);
                btn_paper.setBackgroundResource(R.color.black);*/
                mImage_Paper.setImageResource(R.drawable.icon_rb);
                mText_paper.setTextColor(getResources().getColor(R.color.line));
                mImage_Form.setImageResource(R.drawable.icon_bd_active);
                mText_Form.setTextColor(getResources().getColor(R.color.font));
                mImage_Map.setImageResource(R.drawable.icon_dt);
                mText_Map.setTextColor(getResources().getColor(R.color.line));
                mImage_Person.setImageResource(R.drawable.icon_zx);
                mText_Person.setTextColor(getResources().getColor(R.color.line));
                handlerPager(FORM_TYPE);
                break;
            case R.id.bt_map:
                /*btn_person.setBackgroundResource(R.color.black);
                btn_form.setBackgroundResource(R.color.black);
                btn_map.setBackgroundResource(R.color.bt_green);
                btn_paper.setBackgroundResource(R.color.black);*/
                mImage_Paper.setImageResource(R.drawable.icon_rb);
                mText_paper.setTextColor(getResources().getColor(R.color.line));
                mImage_Form.setImageResource(R.drawable.icon_bd);
                mText_Form.setTextColor(getResources().getColor(R.color.line));
                mImage_Map.setImageResource(R.drawable.icon_dt_active);
                mText_Map.setTextColor(getResources().getColor(R.color.font));
                mImage_Person.setImageResource(R.drawable.icon_zx);
                mText_Person.setTextColor(getResources().getColor(R.color.line));
                handlerPager(MAP_TYPE);
                break;
            case R.id.bt_person:
                /*btn_person.setBackgroundResource(R.color.bt_green);
                btn_form.setBackgroundResource(R.color.black);
                btn_map.setBackgroundResource(R.color.black);
                btn_paper.setBackgroundResource(R.color.black);*/
                mImage_Paper.setImageResource(R.drawable.icon_rb);
                mText_paper.setTextColor(getResources().getColor(R.color.line));
                mImage_Form.setImageResource(R.drawable.icon_bd);
                mText_Form.setTextColor(getResources().getColor(R.color.line));
                mImage_Map.setImageResource(R.drawable.icon_dt);
                mText_Map.setTextColor(getResources().getColor(R.color.line));
                mImage_Person.setImageResource(R.drawable.icon_zx_active);
                mText_Person.setTextColor(getResources().getColor(R.color.font));
                handlerPager(PERSON_CENTER_TYPE);

                break;
            default:
                break;
        }
    }
}
