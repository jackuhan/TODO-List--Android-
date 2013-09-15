package com.simpletodo.service;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

import com.simpletodo.main.MainActivity;
import com.simpletodo.service.AccessSQLiteService.AccessSQLiteServiceBinder;

public class ASServiceConnection implements ServiceConnection {
	
	private AccessSQLiteService accessSQLiteService;
	
	@Override
    public void onServiceConnected(ComponentName className,
            IBinder service) {
		Log.d("TEST", "!!!!!!!!!!!!!!!!!!");
        // We've bound to LocalService, cast the IBinder and get AccessSQLiteService instance
    	AccessSQLiteServiceBinder binder = (AccessSQLiteServiceBinder) service;
    	accessSQLiteService = binder.getService();
		Log.d("TEST", binder.getService().toString());
    	
    	if(MainActivity.getAccessSQLiteService() == null){
    		MainActivity.setAccessSQLiteService(accessSQLiteService);
    	}
    	if(MainActivity.getSTODOSQLiteOpenHelper() != null){
    		MainActivity.setSTODOSQLiteOpenHelper(accessSQLiteService.getSTODOSQLiteOpenHelper());
    	}	
     }

     @Override
     public void onServiceDisconnected(ComponentName arg0) {
         //TODO
     	
     }
     
     public AccessSQLiteService getAccessSQLiteService(){
//    	 while(accessSQLiteService == null){
//    		// SLEEP 1 SECONDS HERE ...
//    		Handler handler = new Handler(); 
//    		handler.postDelayed(new Runnable() { 
//    		      public void run(){} }, 1000);
//    	 }
    	 
    	 return accessSQLiteService;
     }
}
