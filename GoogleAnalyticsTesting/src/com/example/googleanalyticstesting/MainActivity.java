package com.example.googleanalyticstesting;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import com.google.analytics.tracking.android.EasyTracker;
import com.google.analytics.tracking.android.GAServiceManager;
import com.google.analytics.tracking.android.GoogleAnalytics;
import com.google.analytics.tracking.android.Tracker;

public class MainActivity extends Activity {

	public GoogleAnalytics mainAnalytics;
	public Tracker mainTracker;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mainAnalytics = GoogleAnalytics.getInstance(this);
		mainTracker = mainAnalytics.getTracker("UA-42542775-1");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		EasyTracker.getInstance().activityStart(this);
		
		mainTracker.sendView("/MainActivity");
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		EasyTracker.getInstance().activityStop(this);
	}
	
	public View onClick(View view){
		
		mainTracker.sendEvent("ui_action", "button_press", "button", Long.valueOf(0));
		GAServiceManager.getInstance().dispatch();
		return null;
	}

}
