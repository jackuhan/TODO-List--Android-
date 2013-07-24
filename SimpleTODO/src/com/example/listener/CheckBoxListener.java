package com.example.listener;

import com.example.bean.ListItem;

import android.util.Log;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class CheckBoxListener implements OnCheckedChangeListener {
	
	private ListItem listItem;
	
	public CheckBoxListener(ListItem listItem){
		this.listItem = listItem;
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		
		if (isChecked){
			listItem.setComplete(false);
			Log.d("DEBUG", "set to false");
		}
		
		else {
			listItem.setComplete(true);
			Log.d("DEBUG", "set to true");
		}
		
	}

}
