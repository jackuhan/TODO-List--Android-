package com.simpletodo.main;

import com.example.simpletodo.R;
import com.simpletodo.bean.ListItem;
import com.simpletodo.bean.ListItemData;
import com.simpletodo.listener.AddButtonListener;
import com.simpletodo.listener.ListItemViewLongClickListener;
import com.simpletodo.main.ListItemView;
import com.simpletodo.main.MainActivity;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class MainView extends RelativeLayout {
	
	private Button addButton;
	private LinearLayout listItemViewContainer;
	private MainActivity mainActivity;

	public MainView(Context context, AttributeSet attrs) {
		super(context, attrs);
		LayoutInflater.from(context).inflate(R.layout.activity_main, this, true);
		mainActivity = (MainActivity)context;
		linkView();
	}
	
	private void linkView() {
		addButton = (Button) findViewById(R.id.addbutton);
		addButton.setOnClickListener(new AddButtonListener(mainActivity, ListItemData.getListItems()));
		listItemViewContainer = (LinearLayout) findViewById(R.id.linearlayout_in_scrollview);
		refreshItemViews();
	}

	public void refreshItemViews(){
		listItemViewContainer.removeAllViews();
		for(ListItem listItem : ListItemData.getListItems()){
			ListItemView listItemView = new ListItemView(mainActivity, null, listItem);
			listItemView.setOnLongClickListener(
					new ListItemViewLongClickListener
					(ListItemData.getListItems(), mainActivity));
			listItemViewContainer.addView(listItemView);
		}
	}

}
