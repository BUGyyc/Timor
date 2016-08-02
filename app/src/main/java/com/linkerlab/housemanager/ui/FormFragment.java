package com.linkerlab.housemanager.ui;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.linkerlab.housemanager.R;
import com.linkerlab.housemanager.base.BaseFragment;
import com.linkerlab.housemanager.util.DensityUtil;

import net.tsz.afinal.FinalActivity;
import net.tsz.afinal.annotation.view.ViewInject;

/**
 * Created by x-man on 16/7/30.
 */
public class FormFragment extends BaseFragment{
    @ViewInject(id=R.id.title_name)
    TextView mTitleName;
    @ViewInject(id=R.id.title_right)
    TextView mTitleRight;
    @ViewInject(id=R.id.ed_search)
    EditText mEditTextSearch;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View ViewRoot = inflater.inflate(R.layout.fragment_form,container,false);
        FinalActivity.initInjectedView(this,ViewRoot);
        mTitleName.setText(R.string.title_form);
        mTitleRight.setText("新增楼宇");
        Drawable drawable = getResources().getDrawable(R.drawable.icon_search);
        drawable.setBounds(10, 10, 40, 40);
        mEditTextSearch.setCompoundDrawables(drawable,null,null,null);
        return ViewRoot;
    }


}
