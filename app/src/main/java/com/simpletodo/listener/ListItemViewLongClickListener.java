package com.simpletodo.listener;

import java.util.List;

import com.simpletodo.bean.ListItem;
import com.simpletodo.db.MySQLiteOpenHelper;
import com.simpletodo.main.MainActivity;
import com.simpletodo.main.ListItemView;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.View.OnLongClickListener;

public class ListItemViewLongClickListener implements OnLongClickListener {

    private List<ListItem> listItems;
    private Context context;

    public ListItemViewLongClickListener(List<ListItem> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @Override
    public boolean onLongClick(View v) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

        // set title
        ListItemView listItemView = (ListItemView) v;
        final ListItem listItem = listItemView.getListItem();
        alertDialogBuilder.setTitle("Are you sure to delete " + listItem.itemName + "?");
        final String listItemId = listItem.id;

        // set dialog message
        alertDialogBuilder
                .setMessage("Click yes to delete task")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //delete LongClicked listItem in the container & database
                        for (int i = 0; i < listItems.size(); i++) {
                            if (listItem.id == listItems.get(i).id) {
                                listItems.remove(listItems.get(i));
                                MySQLiteOpenHelper sTODOSQLiteOpenHelper = MainActivity.getSTODOSQLiteOpenHelper();
                                sTODOSQLiteOpenHelper.deleteSpecificListItem(listItemId);
                                MainActivity activity = (MainActivity) context;
                                activity.getMainView().refreshItemViews(); //refresh view
                            }
                        }
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

        return true;
    }

}
