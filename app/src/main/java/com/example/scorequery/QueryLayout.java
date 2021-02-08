package com.example.scorequery;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.List;

public class QueryLayout extends LinearLayout {

    public QueryLayout(Context context, AttributeSet attrs){
        super(context,attrs);
        LayoutInflater.from(context).inflate(R.layout.query, this);
        //Button titleBack=(Button)findViewById(R.id.button_query);
        //titleBack.setOnClickListener(new OnClickListener() {
        //    @Override
        //    public void onClick(View v) {
        //        ((Activity)getContext()).finish();
        //    }
        //});
    }
}
