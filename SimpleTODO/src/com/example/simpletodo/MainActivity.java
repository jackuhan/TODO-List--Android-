package com.example.simpletodo;

import java.util.ArrayList;
import java.util.List;

import com.example.bean.EventType;
import com.example.bean.ListItem;
import com.example.view.ListItemView;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.text.Editable;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends Activity {
	//data
	private List<ListItem> listItems;
	
	//View
	private Button addButton;
	private LinearLayout listItemViewContainer;
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		listItems = new ArrayList<ListItem>();
		linkView();
		setListItemViewContainer();
		
		TextView testText = new TextView(this);
		testText.setText("111111111111");
		listItemViewContainer.addView(testText);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	private void linkView(){
		addButton = (Button)findViewById(R.id.addbutton);
		setButtonListener();	
		listItemViewContainer = (LinearLayout)findViewById(R.id.linearlayout_in_scrollview);
	}
	
	private void setButtonListener(){
		addButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				addAEvent();
			}
			
		});
	}
	
	private void addAEvent(){
		AlertDialog.Builder alert = new AlertDialog.Builder(this);

		alert.setTitle("Add A Event");
		alert.setMessage("Type your Event Here");
		
		LinearLayout linerInDialogue = new LinearLayout(this);
		linerInDialogue.setOrientation(LinearLayout.VERTICAL);
		// Set an EditText view to get user input 
		final EditText input = new EditText(this);
		input.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
		linerInDialogue.addView(input);
		// Set a Spinner to  choose a type
		final Spinner typeMenu = new Spinner(this);
		setupSpinner(typeMenu);
		linerInDialogue.addView(typeMenu);
		
		alert.setView(linerInDialogue);

		alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
		public void onClick(DialogInterface dialog, int whichButton) {
		  // Create a ListItem and store it
		  Editable value = input.getText();
		  ListItem listItem = new ListItem();
		  listItem.setItemName(value.toString());

		  String eventType = (String) typeMenu.getSelectedItem();
		  listItem.setItemType(eventType);
		  
		  listItems.add(listItem);
		  
		  setListItemViewContainer(); //refresh ListItemViewContainer
		  }
		});

		alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
		  public void onClick(DialogInterface dialog, int whichButton) {
		    // Canceled.
		  }
		});

		alert.show();
		// see http://androidsnippets.com/prompt-user-input-with-an-alertdialog
	}
	
	// Set the EventType enum values to Spinner menu
	private void setupSpinner(Spinner spinner){
		ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, android.R.id.text1);
		for(EventType type : EventType.values()){
			spinnerAdapter.add(type.toString());
		}
		spinner.setAdapter(spinnerAdapter);
	}
	
	//Add the ListItemView into main screen
	private void setListItemViewContainer(){
		listItemViewContainer.removeAllViews();
		for(ListItem listItem : listItems){
			ListItemView listItemView = new ListItemView(this, null, listItem);
			listItemViewContainer.addView(listItemView);
		}
	}


}
