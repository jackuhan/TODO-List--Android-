package com.example.listener;

import java.util.List;

import com.example.bean.ListItem;
import com.example.db.STODOSQLiteOpenHelper;
import com.example.simpletodo.MainActivity;
import com.example.view.ListItemView;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.View.OnLongClickListener;

public class ListItemViewLongClickListener implements OnLongClickListener {
	
	private List<ListItem> listItems;
	private Context context;
	
	public ListItemViewLongClickListener(List<ListItem> listItems, Context context){
		this.listItems = listItems;
		this.context = context;
	}
	
	@Override
	public boolean onLongClick(View v) {
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
 
			// set title
			ListItemView listItemView = (ListItemView)v;
			final ListItem listItem = listItemView.getListItem();
			alertDialogBuilder.setTitle("Are you sure to delete "+ listItem.getItemName() +"?");
			final String listItemId  = listItem.getId();
 
			// set dialog message
			alertDialogBuilder
				.setMessage("Click yes to delete task")
				.setCancelable(false)
				.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,int id) {
						//delete LongClicked listItem in the container & database
						for(int i = 0; i<listItems.size(); i++){
							if(listItem.getId() == listItems.get(i).getId()){
								listItems.remove(listItems.get(i));
								STODOSQLiteOpenHelper sTODOSQLiteOpenHelper = new STODOSQLiteOpenHelper(context);
								sTODOSQLiteOpenHelper.deleteSpecificListItem(listItemId);
								MainActivity activity = (MainActivity)context;
								activity.setListItemViewContainer(); //refresh view
							}	
						}	
					}
				  })
				.setNegativeButton("No",new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,int id) {
						// if this button is clicked, just close
						// the dialog box and do nothing
						dialog.cancel();
					}
				});
 
				// create alert dialog
				AlertDialog alertDialog = alertDialogBuilder.create();
 
				// show it
				alertDialog.show();
			
		return true;
	}

}
