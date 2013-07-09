package com.example.view;

import com.example.simpletodo.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

public class ListItem extends RelativeLayout {
	
	public ListItem(Context context, AttributeSet attrs) {
	    super(context, attrs);
	    LayoutInflater.from(context).inflate(R.layout.list_item, this, true);
	}

}
