package com.simpletodo.service;

import com.simpletodo.db.STODOSQLiteOpenHelper;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class AccessSQLiteService extends Service {
	
	private static STODOSQLiteOpenHelper sTODOSQLiteOpenHelper;
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
	  Log.d("TEST", "onStartCommand has been called");
	  return Service.START_NOT_STICKY;
	}

	@Override
	public IBinder onBind(Intent intent) {
		//return service instance through this Binder
		return new AccessSQLiteServiceBinder();
	}
	
	//This binder response for get AccessSQLiteServiceBinder
	public class AccessSQLiteServiceBinder extends Binder {
		public AccessSQLiteService getService() {
	        return  AccessSQLiteService.this;
	    }
	}
	
	//Service give the STODOSQLiteOpenHelper instance 
	public STODOSQLiteOpenHelper getSTODOSQLiteOpenHelper(){
		Log.d("TEST", "getSTODOSQLiteOpenHelper has been called");
		if(sTODOSQLiteOpenHelper == null){
			//create sTODOSQLiteOpenHelper and hold only one instance in this application
			sTODOSQLiteOpenHelper = new STODOSQLiteOpenHelper(this);
		}
		return sTODOSQLiteOpenHelper;
	}

}
