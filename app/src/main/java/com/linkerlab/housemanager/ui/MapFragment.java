package com.linkerlab.housemanager.ui;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapView;
import com.linkerlab.housemanager.R;
import com.linkerlab.housemanager.base.BaseFragment;
import com.linkerlab.housemanager.util.BMapTools;

import net.tsz.afinal.FinalActivity;
import net.tsz.afinal.annotation.view.ViewInject;

/**
 * Created by x-man on 16/7/30.
 * 地图界面
 */
public class MapFragment extends BaseFragment{
    @ViewInject(id=R.id.title_name)
    TextView mTitleName;
    @ViewInject(id=R.id.bmapView)
    MapView bMapView;
    private BaiduMap baiduMap;
    private PopupWindow mPopupWindow;
    private View popupView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View ViewRoot = inflater.inflate(R.layout.fragment_map,container,false);
        FinalActivity.initInjectedView(this,ViewRoot);
        mTitleName.setText(R.string.titie_map);
        popupView = this.getActivity().getLayoutInflater()
                .inflate(R.layout.layout_popupwindow, null);
        baiduMap=bMapView.getMap();
        return ViewRoot;
    }

    @Override
    public void onResume() {
        super.onResume();
        /**
         * 测试效果
         */
        setData2DrawMap();
    }

    /**
     * 在地图上绘制
     */
    private void setData2DrawMap(){
        BMapTools bMapTools=new BMapTools(baiduMap,this.getActivity());
        bMapTools.drawCircle(39.963175, 116.400244);
        bMapTools.draw2Window(39.963115, 116.400244, -20,setPop2Map());
    }

    /**
     * 创建悬浮窗
     */
    private PopupWindow setPop2Map(){
        mPopupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        mPopupWindow.setTouchable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable(getResources(), (Bitmap) null));
        return mPopupWindow;
    }
}
