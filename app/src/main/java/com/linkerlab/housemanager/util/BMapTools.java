package com.linkerlab.housemanager.util;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.PolygonOptions;
import com.baidu.mapapi.map.Polyline;
import com.baidu.mapapi.map.PolylineOptions;
import com.baidu.mapapi.map.Stroke;
import com.baidu.mapapi.model.LatLng;
import com.linkerlab.housemanager.R;
import com.linkerlab.housemanager.activity.MapDetialActivity;

public class BMapTools {
	private final int AREA_STROKE=5;
	private final int AREA_STROKE_COLOR=0xAA00FF00;
	private final int AREA_FILL_COLOR=0xAAFFFF00;
	
	private Context mContext;
	private BaiduMap mBaiduMap;
	public BMapTools(BaiduMap baiduMap,Context context){
		this.mBaiduMap=baiduMap;
		this.mContext=context;
	}
	
	/**
	 * 画区域
	 */
	public void drawArea(double x[],double y[]){
		LatLng pt =null;
		List<LatLng> pts = new ArrayList<LatLng>();  
		for(int i=0;i<x.length;i++){
			pt=new LatLng(x[i], y[i]); 
			pts.add(pt);
		}
		OverlayOptions polygonOption = new PolygonOptions()  
		    .points(pts)  
		    .stroke(new Stroke(AREA_STROKE,AREA_STROKE_COLOR))  
		    .fillColor(AREA_FILL_COLOR);  
		mBaiduMap.addOverlay(polygonOption);
	}
	
	/**
	 *画 原点
	 */
	public void drawCircle(double x,double y){
		LatLng point = new LatLng(x, y);  
		BitmapDescriptor bitmap = BitmapDescriptorFactory  
		    .fromResource(R.drawable.icon_dt_active);
		OverlayOptions option = new MarkerOptions()  
		    .position(point)  
		    .icon(bitmap);  
		mBaiduMap.addOverlay(option);
	}
	
	/**
	 * 画线
	 */
	public void drawLine(double x[],double y[]){
		List<LatLng> points = new ArrayList<LatLng>();
		for(int i=0;i<x.length;i++)
			points.add(new LatLng(x[i],y[i]));
		List<Integer> colors = new ArrayList<Integer>();
		colors.add(Integer.valueOf(Color.BLUE));
		colors.add(Integer.valueOf(Color.RED));
		colors.add(Integer.valueOf(Color.YELLOW));
		colors.add(Integer.valueOf(Color.GREEN));
		OverlayOptions ooPolyline = new PolylineOptions().width(10)
		        .colorsValues(colors).points(points);
		mBaiduMap.addOverlay(ooPolyline);
	}

	/**
	 * 绘制  悬浮窗口
	 * @param x
	 * @param y
	 * @param offSet 窗口的偏移量
	 */
	public void draw2Window(double x,double y,final int offSet,final PopupWindow pop){
		final Button button = new Button(mContext);  
		button.setBackgroundResource(R.drawable.icon_dt_active);
		LatLng pt = new LatLng(39.86923, 116.397428);
		InfoWindow mInfoWindow = new InfoWindow(button, pt, offSet);
		mBaiduMap.showInfoWindow(mInfoWindow);
		button.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
			if(pop==null) {
				return;
			}
				mContext.startActivity(new Intent(mContext, MapDetialActivity.class));
			//pop.showAsDropDown(v);
			}
		});
	}
	
	 
}
