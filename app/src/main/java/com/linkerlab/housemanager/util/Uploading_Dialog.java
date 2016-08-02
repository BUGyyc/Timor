package com.linkerlab.housemanager.util;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.linkerlab.housemanager.R;

/**
 * Created by Administrator on 2016/8/1.
 */
public class Uploading_Dialog extends Dialog {

    public interface  OnMyDialogLister{
        public void onClick();
    }
    private Context context;
    private int layout_id;  //传入的xml文件id
    private TextView ok;
    private OnMyDialogLister onMyDialogLister;



    public Uploading_Dialog(Context context, int layout_id,OnMyDialogLister dialogLister) {
        super(context);
        this.context = context;
        this.layout_id = layout_id;
        this.onMyDialogLister = dialogLister;
        requestWindowFeature(Window.FEATURE_NO_TITLE);//去除标题栏
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout_id);
        ok = (TextView) findViewById(R.id.ok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onMyDialogLister.onClick();
                Uploading_Dialog.this.dismiss();
            }
        });
    }
}
